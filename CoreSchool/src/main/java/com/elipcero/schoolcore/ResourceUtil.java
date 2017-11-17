package com.elipcero.schoolcore;

import java.util.Arrays;

import org.springframework.hateoas.Resource;

public class ResourceUtil {

	public static Integer GetIntegerId(Resource<?> resource) {
		return Integer.parseInt(GetStringId(resource));
	}
	
	public static String GetStringId(Resource<?> resource) {
		String self = resource.getLink("self").getHref();
		return Arrays.stream(self.split("/")).reduce((first, second) -> second).get();
	}
}
