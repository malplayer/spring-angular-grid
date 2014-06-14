package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class BuyingCompany extends BaseEntity {
	
	protected String name;
	
	protected String description;
	
	@NotNull
	@Column( nullable = false )
	protected Date whenEffective;
	
	protected Date whenExpires;
	
	@ManyToOne(optional = false)
	protected MemberAlliance memberAlliance;
	
	@ManyToOne(optional = false)
	protected GPOMember gpoMember;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "alliances")
	protected List<ClassOfTrade> classOfTrades = new ArrayList<ClassOfTrade>();
	
	// didn't include the following as I understand the System can be obtained through
	// another system
	//
	// association SystemOfMembers {
	//   start MemberAllianceMembership parent?
	//   end MemberAllianceMembership children*
    // }
	
	public Date getWhenEffective() {
		return whenEffective;
	}

	public void setWhenEffective(Date whenEffective) {
		this.whenEffective = whenEffective;
	}

	public Date getWhenExpires() {
		return whenExpires;
	}

	public void setWhenExpires(Date whenExpires) {
		this.whenExpires = whenExpires;
	}

	public MemberAlliance getMemberAlliance() {
		return memberAlliance;
	}

	public void setMemberAlliance(MemberAlliance memberAlliance) {
		this.memberAlliance = memberAlliance;
	}

	public GPOMember getGpoMember() {
		return gpoMember;
	}

	public void setGpoMember(GPOMember novMember) {
		this.gpoMember = novMember;
	}

	public List<ClassOfTrade> getClassOfTrades() {
		return classOfTrades;
	}

	public void setClassOfTrades(List<ClassOfTrade> classOfTrades) {
		this.classOfTrades = classOfTrades;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
