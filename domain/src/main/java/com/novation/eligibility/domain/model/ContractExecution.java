package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class ContractExecution extends BaseEntity {

	@NotNull
	@Column(nullable = false)
	protected Date whenStarted;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	protected List<ContractTierExecutionEvent> events = new ArrayList<ContractTierExecutionEvent>();
	
	@ManyToOne(optional = false)
	protected GPOMember requestingMember;
	
	@ManyToOne(optional = true)
	protected Vendor selectedDistributor;
	
	@OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
	protected List<ContractTierSelection> selections = new ArrayList<ContractTierSelection>();
	
	@ManyToMany
	@JoinTable(name = "EXECUTION_MEMBERS",
		joinColumns = @JoinColumn(name = "contractExecution_id"),
		inverseJoinColumns = @JoinColumn(name = "gpoMember_id"))
	protected List<GPOMember> gpoMembers = new ArrayList<GPOMember>();
	
	public Date getWhenStarted() {
		return whenStarted;
	}

	public void setWhenStarted(Date whenStarted) {
		this.whenStarted = whenStarted;
	}

	public List<ContractTierExecutionEvent> getEvents() {
		return events;
	}

	public void setEvents(List<ContractTierExecutionEvent> events) {
		this.events = events;
	}

	public GPOMember getRequestingMember() {
		return requestingMember;
	}

	public void setRequestingMember(GPOMember requestingMember) {
		this.requestingMember = requestingMember;
	}

	public Vendor getSelectedDistributor() {
		return selectedDistributor;
	}

	public void setSelectedDistributor(Vendor selectedDistributor) {
		this.selectedDistributor = selectedDistributor;
	}

	public List<ContractTierSelection> getSelections() {
		return selections;
	}

	public void setSelections(List<ContractTierSelection> selections) {
		this.selections = selections;
	}
	public List<GPOMember> getGpoMembers() {
		return gpoMembers;
	}

	public void setGpoMembers(List<GPOMember> gpoMembers) {
		this.gpoMembers = gpoMembers;
	}

	public void addGpoMember(GPOMember gpoMember) {
		this.gpoMembers.add(gpoMember);
	}
	
}
