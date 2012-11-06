package net.luszczyk.mdbv.common.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewerService {

    @Autowired
    private RegisterService registerService;
	
	protected final List<String> typeList = new ArrayList<String>();

	public List<String> getTypeList() {
		return typeList;
	}
	
	public abstract String getLink(final String domainId);
    public abstract String getResourceView(final String domainId);

    @PostConstruct
    public void registerNewType() {

        for (String t : typeList) {
            registerService.addTypeAndViewer(t, this);
        }
    }

}
