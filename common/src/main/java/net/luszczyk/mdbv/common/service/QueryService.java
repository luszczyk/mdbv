package net.luszczyk.mdbv.common.service;

import net.luszczyk.mdbv.common.exception.GettingLargeObjectException;
import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.DomainDetails;
import net.luszczyk.mdbv.common.table.Result;

public interface QueryService {
	
	public Result runQuery(String query);
	public DomainDetails getDomainDetails(Domain domain) throws GettingLargeObjectException;

}
