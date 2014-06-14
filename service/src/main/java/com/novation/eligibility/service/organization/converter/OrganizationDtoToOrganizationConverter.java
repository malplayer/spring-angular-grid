package com.novation.eligibility.service.organization.converter;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.novation.eligibility.domain.model.Organization;
import com.novation.eligibility.domain.repo.GPOMemberRepository;
import com.novation.eligibility.domain.repo.VendorRepository;
import com.novation.eligibility.dto.dtos.OrganizationDto;

@Component
public class OrganizationDtoToOrganizationConverter implements Converter<OrganizationDto, Organization> {

	@Inject
	GPOMemberRepository novMemberRepo;
	
	@Inject
	VendorRepository vendorRepo;
	
	@Override
	public Organization convert(OrganizationDto s) {
		return convert(s, new Organization());
	}
	
	public Organization convert(OrganizationDto s, Organization t) {
		if (t == null) {
			t = new Organization();
		}
		
		t.setName(s.getName());
		if (s.getDescription() != null) t.setDescription(s.getDescription());
		if (s.getDuns() != null) t.setDuns(s.getDuns());
		if (s.getPrimaryEmail() != null) t.setPrimaryEmail(s.getPrimaryEmail());
		if (s.getUsername() != null) t.setUsername(s.getUsername());
		if (s.getPasswordHash() != null) t.setPasswordHash(s.getPasswordHash());
		if (s.getSalt() != null) t.setSalt(s.getSalt());
		if (s.getWebSite() != null) t.setWebSite(s.getWebSite());
		
		if (s.getGpoMemberId() != null) t.setMemberRole(novMemberRepo.findOne(s.getGpoMemberId()));
		if (s.getVendorId() != null) t.setVendor(vendorRepo.findOne(s.getVendorId()));
		// TODO address and other fields from party
		
		return t;
	}

}
