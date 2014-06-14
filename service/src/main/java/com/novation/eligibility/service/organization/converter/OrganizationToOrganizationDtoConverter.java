package com.novation.eligibility.service.organization.converter;

import org.springframework.core.convert.converter.Converter;

import com.novation.eligibility.domain.model.ContractTierExecutionEvent;
import com.novation.eligibility.domain.model.Organization;
import com.novation.eligibility.dto.dtos.OrganizationDto;

public class OrganizationToOrganizationDtoConverter implements Converter<Organization, OrganizationDto> {

	@Override
	public OrganizationDto convert(Organization s) {
		return convert(s, new OrganizationDto());
	}
	
	public OrganizationDto convert(Organization s, OrganizationDto t) {
		if (t == null) {
			t = new OrganizationDto();
		}
		
		t.setId(s.getId());
		t.setName(s.getName());
		if (s.getDescription() != null) t.setDescription(s.getDescription());
		if (s.getDuns() != null) t.setDuns(s.getDuns());
		if (s.getPrimaryEmail() != null) t.setPrimaryEmail(s.getPrimaryEmail());
		if (s.getUsername() != null) t.setUsername(s.getUsername());
		if (s.getWebSite() != null) t.setWebSite(s.getWebSite());
		if (s.getVendor() != null) t.setVendorId(s.getVendor().getId());
		if (s.getMemberRole() != null) t.setGpoMemberId(s.getMemberRole().getId());
		if (s.getPasswordHash() != null) t.setPasswordHash(s.getPasswordHash());
		if (s.getSalt() != null) t.setSalt(s.getSalt());
		
		for (ContractTierExecutionEvent e : s.getEvents()) {
			t.addContractTierExecutionEventId(e.getId());
		}

		// TODO address?
		
		return t;
	}

}
