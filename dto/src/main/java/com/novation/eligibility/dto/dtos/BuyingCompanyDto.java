package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.novation.eligibility.dto.general.DataTransferObject;

public class BuyingCompanyDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	private Date whenEffective;
	private Date whenExpires;
	private String memberAllianceId;
	private String novationMemberId;
	private Collection<String> classOfTradeIds = new ArrayList<String>();
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getMemberAllianceId() {
		return memberAllianceId;
	}
	public void setMemberAllianceId(String memberAllianceId) {
		this.memberAllianceId = memberAllianceId;
	}
	public String getNovationMemberId() {
		return novationMemberId;
	}
	public void setNovationMemberId(String novationMemberId) {
		this.novationMemberId = novationMemberId;
	}
	public Collection<String> getClassOfTradeIds() {
		return classOfTradeIds;
	}
	public void setClassOfTradeIds(Collection<String> classOfTradeIds) {
		this.classOfTradeIds = classOfTradeIds;
	}
	public void addClassOfTradeId(String id) {
		classOfTradeIds.add(id);
	}
	
}
