package net.luszczyk.mdbv.common.service;

import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Result;

public interface QueryService {
	
	public Result runQuery(String query);
    public byte[] getContentByte(Domain domain, Integer size);

}
