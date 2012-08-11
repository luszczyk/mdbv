package net.luszczyk.mdbv.common.table;

import net.luszczyk.mdbv.common.service.ViewerService;

public class DomainDetails {

	private String contentPath;
	private String mediaPath;
	private ViewerService viewerService;

	public DomainDetails(final String contentPath, final String mediaPath,
			final ViewerService viewerService) {
		this.contentPath = contentPath;
		this.mediaPath = mediaPath;
		this.viewerService = viewerService;
	}
	
	public DomainDetails(final String contentPath, final String mediaPath) {
		this(contentPath, mediaPath, null);
	}

	public String getContentPath() {
		return contentPath;
	}

	public String getMediaType() {
		return mediaPath;
	}
	
	public ViewerService getViewerService() {
		return viewerService;
	}

}
