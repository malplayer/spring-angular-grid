package com.novation.eligibility.dto.dtos;

public class OrganizationDto extends PartyDto {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String duns;
	private String webSite;
	private String gpoMemberId;
	private String vendorId;
	
	public String getDuns() {
		return duns;
	}
	public void setDuns(String duns) {
		this.duns = duns;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGpoMemberId() {
		return gpoMemberId;
	}
	public void setGpoMemberId(String gpoMemberId) {
		this.gpoMemberId = gpoMemberId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
}
