package com.novation.eligibility.domain.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Organization extends Party {

	protected String name;
	
	protected String duns;
	
	protected String webSite;
	
	public Organization() {}
	
	public Organization(@NotNull String username) {
		setUsername(username);
	}
	
	@OneToOne(optional = true, mappedBy = "organization")
	protected GPOMember memberRole;
	
	@OneToOne(optional = true, mappedBy = "organization")
	protected Vendor vendor;
	
	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getDuns() {
		return duns;
	}

	public void setDuns(String duns) {
		this.duns = duns;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public GPOMember getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(GPOMember memberRole) {
		this.memberRole = memberRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
