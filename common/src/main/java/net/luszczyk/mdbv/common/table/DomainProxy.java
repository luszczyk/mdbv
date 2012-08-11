package net.luszczyk.mdbv.common.table;

import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.service.ViewerService;

public class DomainProxy extends Domain {

	public DomainProxy(final QueryService queryService, final Table table, final Column column,
			final String preView) {
		this(queryService, table,column,preView, null);
	}
	
	public DomainProxy(final QueryService queryService, final Table table, final Column column,
			final String preView, final Long oid) {
		this.queryService = queryService;
		this.table = table;
		this.column = column;
		this.preView = preView;
		this.oid = oid;
	}

	@Override
	public String getContentPath() {

		String result = null;

		if (getDomainDetails() != null) {
			result = getDomainDetails().getContentPath();
		}
		return result;
	}

	@Override
	public String getMediaType() {

		String result = null;

		if (getDomainDetails() != null) {
			result = getDomainDetails().getMediaType();
		}
		return result;
	}

	@Override
	public ViewerService getViewerService() {
		
		ViewerService result = null;
		if (getDomainDetails() != null) {
			result = getDomainDetails().getViewerService();
		}
		return result;
	}

}
