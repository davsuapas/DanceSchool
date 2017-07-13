package com.elipcero.schoolcore;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtil {
	
	public static <T> List<T> ConvertToList(Collection<T> collection) {
		return collection.stream().collect(Collectors.toList());
	}

}
