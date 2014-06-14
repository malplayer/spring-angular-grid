package com.novation.eligibility.ui.web.model;

public class FacilitySelection {

	private String id;
	private String name;
	private String alliance;
	private boolean existingContract;
	private boolean existingTier;
	private String dea;
	private String license;

	public FacilitySelection() {

	}

	public FacilitySelection(String id, String name, String alliance,
			boolean existingContract, boolean existingTier, String dea,
			String license) {
		super();
		this.id = id;
		this.name = name;
		this.alliance = alliance;
		this.existingContract = existingContract;
		this.existingTier = existingTier;
		this.dea = dea;
		this.license = license;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlliance() {
		return alliance;
	}

	public void setAlliance(String alliance) {
		this.alliance = alliance;
	}

	public boolean isExistingContract() {
		return existingContract;
	}

	public void setExistingContract(boolean existingContract) {
		this.existingContract = existingContract;
	}

	public boolean isExistingTier() {
		return existingTier;
	}

	public void setExistingTier(boolean existingTier) {
		this.existingTier = existingTier;
	}

	public String getDea() {
		return dea;
	}

	public void setDea(String dea) {
		this.dea = dea;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public boolean filter(String id, String name, String alliance,
			String existingContract, String existingTier, String dea,
			String license) {

		return ((isEmpty(id) || match(this.id, id))
				&& (isEmpty(name) || match(this.name, name))
				&& (isEmpty(alliance) || match(this.alliance, alliance))
				&& this.existingContract == Boolean
						.valueOf(trim(existingContract))
				&& this.existingTier == Boolean.valueOf(trim(existingTier))
				&& (isEmpty(alliance) || match(this.dea, alliance))
				&& (isEmpty(license) || match(this.license, license))
				);
	}

	private static boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}

	private static String trim(String value) {
		return value == null ? "" : value.trim();
	}

	private static boolean match(String value1, String value2) {
		return trim(value1).toLowerCase().contains(trim(value2).toLowerCase());
	}


	@Override
	public String toString() {
		return "FacilitySelection [id=" + id + ", name=" + name + ", alliance="
				+ alliance + ", existingContract=" + existingContract
				+ ", existingTier=" + existingTier + ", dea=" + dea
				+ ", license=" + license + "]";
	}

}
