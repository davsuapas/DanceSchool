package com.elipcero.schoolweb.customer.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.elipcero.schoolcore.CollectionUtil;
import com.elipcero.schoolweb.customer.domain.CustomerDescriptor;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class CustomerService {

	private @NonNull final CustomerResource customerResource;
	
	public List<CustomerDescriptor> find(String secondName, String thirdName) {
		
		if (StringUtils.isEmpty(thirdName)) {
			return CollectionUtil.ConvertToList(this.customerResource.findBySecondName(secondName).getContent());
		}
		else {
			return CollectionUtil.ConvertToList(this.customerResource.findBySecondAndThirdName(secondName, thirdName).getContent());
		}
	}

	public void delete(Integer id) {
		this.customerResource.delete(id);
	}
}
