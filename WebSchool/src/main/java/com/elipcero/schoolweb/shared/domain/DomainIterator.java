package com.elipcero.schoolweb.shared.domain;

import java.util.ArrayList;
import java.util.List;

public class DomainIterator {
	
	public static <T> List<List<T>> Create(List<T> domains, Integer columns) {
		
		List<List<T>> rowsColsDomains = new ArrayList<List<T>>();
		List<T> domainsCols = new ArrayList<T>(); 
		
		for (int i = 0; i < domains.size(); i++) {
			
			if (i != 0 && i % columns == 0) {
				rowsColsDomains.add(domainsCols);
				domainsCols = new ArrayList<T>();
			}
			
			domainsCols.add(domains.get(i));
		}
		
		if (domainsCols.size() > 0) 
			rowsColsDomains.add(domainsCols);
		
		return rowsColsDomains;
	}
}
