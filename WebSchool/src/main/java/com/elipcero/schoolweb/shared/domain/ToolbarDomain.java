package com.elipcero.schoolweb.shared.domain;

import lombok.Getter;

import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class ToolbarDomain {
	
	public enum EnumMenuOption {
		Home,
		Customer,
		Invoice
	}

	private EnumMenuOption menuOptionActive;
	
	public Boolean isHome() {
		return this.menuOptionActive == EnumMenuOption.Home;
	}
	
	public Boolean isCustomer() {
		return this.menuOptionActive == EnumMenuOption.Customer;
	}
	
	public Boolean isInvoice() {
		return this.menuOptionActive == EnumMenuOption.Invoice;
	}
}