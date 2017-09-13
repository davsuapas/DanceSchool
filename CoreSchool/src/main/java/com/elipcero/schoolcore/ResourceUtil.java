package com.elipcero.schoolcore;

import java.util.Arrays;

import org.springframework.hateoas.Resource;

public class ResourceUtil {
	
	public static Integer GetId(Resource<?> resource) {
		String self = resource.getLink("self").getHref();
		return Integer.parseInt(Arrays.stream(self.split("/")).reduce((first, second) -> second).get());
	}

}
