package com.novation.eligibility.service.organization;

import com.novation.eligibility.dto.dtos.OrganizationDto;
import com.novation.eligibility.service.party.ChangePasswordResult;
import com.novation.eligibility.service.response.Response;

public interface OrganizationService {

	Response<OrganizationDto> findOrganizationByName(String name);
	
	Response<OrganizationDto> findOrganizationByDuns(String duns);
	
	Response<Boolean> organizationExistsWithName(String name);
	
	Response<OrganizationCreationResult> createOrganization(OrganizationDto dto);
	
	Response<ChangePasswordResult> changePasswordByUsername(String username, String currentHash, String newHash);

	Response<ChangePasswordResult> changePasswordByUsername(String username, String currentHash, String newHash, String salt);

	Response<ChangePasswordResult> changePasswordById(String individualId, String currentHash, String newHash);

	Response<ChangePasswordResult> changePasswordById(String individualId, String currentHash, String newHash, String salt);

}
