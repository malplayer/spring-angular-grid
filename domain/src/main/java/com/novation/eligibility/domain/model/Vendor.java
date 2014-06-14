package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Vendor extends BaseEntity {

	protected String name;
	
	@NotNull
	@Column(nullable = false)
	protected String vendorId;
	
	@OneToOne(optional = false)
	protected Organization organization;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "selectedDistributor")
	protected List<ContractExecution> contractExecutions = new ArrayList<ContractExecution>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "VENDOR_CONTRACTS",
		joinColumns = @JoinColumn(name = "vendor_id"),
		inverseJoinColumns = @JoinColumn(name = "contract_id"))
	protected List<Contract> possibleDistributionContracts = new ArrayList<Contract>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
	protected List<Contract> contracts = new ArrayList<Contract>();
	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<ContractExecution> getContractExecutions() {
		return contractExecutions;
	}

	public void setContractExecutions(List<ContractExecution> contractExecutions) {
		this.contractExecutions = contractExecutions;
	}
	
	public void addContractExecution(ContractExecution contractExecution) {
		this.contractExecutions.add(contractExecution);
	}

	public List<Contract> getPossibleDistributionContracts() {
		return possibleDistributionContracts;
	}

	public void setPossibleDistributionContracts(
			List<Contract> possibleDistributionContracts) {
		this.possibleDistributionContracts = possibleDistributionContracts;
	}
	
	public void addPossibleDistributionContract(Contract contract) {
		this.possibleDistributionContracts.add(contract);
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	public void addContract(Contract contract) {
		this.contracts.add(contract);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
