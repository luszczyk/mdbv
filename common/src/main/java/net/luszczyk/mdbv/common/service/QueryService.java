package net.luszczyk.mdbv.common.service;

import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Result;

import java.util.List;

public interface QueryService {
	
	public Result runQuery(String query);
    public byte[] getContentByte(Domain domain, Integer size);
    public List<String> getAllDbs();
    public List<String> getAllSchemas();
    public List<String> getAllTablesForSchema(String schema);

}
