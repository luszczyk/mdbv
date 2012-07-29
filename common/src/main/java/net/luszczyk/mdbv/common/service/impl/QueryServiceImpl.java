package net.luszczyk.mdbv.common.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.luszczyk.mdbv.common.Column;
import net.luszczyk.mdbv.common.Domain;
import net.luszczyk.mdbv.common.Entity;
import net.luszczyk.mdbv.common.Result;
import net.luszczyk.mdbv.common.Table;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;
import net.luszczyk.mdbv.common.service.FileService;
import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.service.RegisterService;

import org.apache.log4j.Logger;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService {

	private static final Logger logger = Logger
			.getLogger(QueryServiceImpl.class);

	@Autowired
	private FileService fileService;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private DatabaseConnectionService databaseConnectionService;

	public Result runQuery(String query) {

		Result result = null;
		try {
			Connection conn = databaseConnectionService.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			List<Column> columns = new ArrayList<Column>();
			List<Entity> entities = new ArrayList<Entity>();

			for (int i = 1; i < numberOfColumns; ++i) {
				columns.add(new Column(i, rsmd.getColumnName(i), rsmd
						.getColumnTypeName(i)));
			}

			while (rs.next()) {

				List<Domain> objects = new ArrayList<Domain>();
				for (Column c : columns) {
					Domain d = null;
					if ("oid".equals(c.getType())) {
						d = saveFileInTemp(rs, conn, c.getId());
					} else {
						d = new Domain(rs.getObject(c.getId()), "");
					}

					if (d != null) {
						objects.add(d);
					} else {
						logger.warn("Try to save null as domain class for column: "
								+ c.getName());
					}
				}
				entities.add(new Entity(objects));
			}

			Table table = new Table(columns, entities);
			result = new Result(table);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Domain saveFileInTemp(ResultSet rs, Connection conn, int columnId)
			throws SQLException {

		LargeObjectManager lobj = null;
		Domain result = null;
		lobj = ((org.postgresql.PGConnection) conn).getLargeObjectAPI();
		long oid = rs.getInt(columnId);
		LargeObject obj = lobj.open(oid, LargeObjectManager.READ);

		byte buf[] = new byte[obj.size()];
		obj.read(buf, 0, obj.size());
		obj.close();

		String filePath = fileService.saveFile(buf);

		if (filePath != null) {

			String type = fileService.getFileType(filePath);

			if (type != null) {

				if (registerService.getTypeList().contains(type)) {

					result = new Domain(filePath, type,
							registerService.getViewerService(type));
				} else {

					result = new Domain(filePath, type);
				}
			}
		}
		return result;
	}
}
