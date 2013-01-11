package net.luszczyk.mdbv.common.model.impl;

import net.luszczyk.mdbv.common.dto.DataBaseDTO;
import net.luszczyk.mdbv.common.model.AbstractDataBase;
import net.luszczyk.mdbv.common.model.DataBase;

public class DataBasePostgres extends AbstractDataBase implements DataBase {

	public DataBasePostgres(DataBaseDTO dataBaseDTO) {
		super(dataBaseDTO);
        this.dataBaseQueryExecutor = new DataBasePostgresQueryExecutor();
	}

	@Override
	public String getDriverName() {
		return "postgresql";
	}

	@Override
	public String getDriverPackage() {
		return "org.postgresql.Driver";
	}
}
