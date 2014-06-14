package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class PartyDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private String description;
	private Collection<String> contractTierExecutionEventIds = new ArrayList<String>(); // events
	private String username;
	private String passwordHash;
	private String salt;
	private String primaryEmail;
	// TODO address, phone
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public Collection<String> getContractTierExecutionEventIds() {
		return contractTierExecutionEventIds;
	}
	public void setContractTierExecutionEventIds(
			Collection<String> contractTierExecutionEventIds) {
		this.contractTierExecutionEventIds = contractTierExecutionEventIds;
	}
	public void addContractTierExecutionEventId(String id) {
		contractTierExecutionEventIds.add(id);
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
