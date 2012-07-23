package net.luszczyk.mdbv.modules.png.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.luszczyk.mdbv.common.service.ViewerService;

@Service
public class ViewerPngService implements ViewerService {
	
	
	private static final List<String> typeList = new ArrayList<String>();
	
	{
		typeList.add("image/jpeg");
	}

	@Override
	public List<String> getTypeList() {
		return typeList;
	}

}
