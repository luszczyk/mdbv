package net.luszczyk.mdbv.common.table;

import net.luszczyk.mdbv.common.exception.GettingLargeObjectException;
import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.service.ViewerService;

import org.apache.log4j.Logger;

public abstract class Domain {

	private static final Logger LOGGER = Logger.getLogger(Domain.class);
	private DomainDetails domainDetails;

	protected QueryService queryService;
	protected Table table;
	protected Column column;
	protected String preView;
	protected Long oid;

	public abstract String getContentPath();

	public abstract ViewerService getViewerService();

	public abstract String getMediaType();

	public DomainDetails getDomainDetails() {

		if (domainDetails == null) {
			try {
				domainDetails = queryService.getDomainDetails(this);
				LOGGER.debug("Found domainDetails for oid: " + oid);
			} catch (GettingLargeObjectException e) {
				LOGGER.warn("No content path for domain" + this, e);
			}
		}
		return domainDetails;
	}

	public boolean isViewable() {
		if (oid == null || getDomainDetails() == null
				|| getDomainDetails().getViewerService() == null) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	public String getLinkToView() {
		if (getDomainDetails() != null) {
			return getDomainDetails().getViewerService().getLink(
					getContentPath());
		} else {
			return null;
		}

	}

	public String getPreView() {
		return preView;
	}

	public Table getTable() {
		return table;
	}

	public Column getColumn() {
		return column;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(final Long oid) {
		this.oid = oid;
	}

	public String getType() {
		return column.getType();
	}

}
