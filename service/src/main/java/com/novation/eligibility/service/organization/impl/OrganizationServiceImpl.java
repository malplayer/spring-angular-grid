package com.novation.eligibility.service.organization.impl;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novation.eligibility.domain.model.Organization;
import com.novation.eligibility.domain.repo.OrganizationRepository;
import com.novation.eligibility.dto.dtos.OrganizationDto;
import com.novation.eligibility.service.ServiceExecution;
import com.novation.eligibility.service.organization.OrganizationCreationResult;
import com.novation.eligibility.service.organization.OrganizationService;
import com.novation.eligibility.service.organization.OrganizationCreationResult.Cause;
import com.novation.eligibility.service.organization.converter.OrganizationToOrganizationDtoConverter;
import com.novation.eligibility.service.party.ChangePasswordResult;
import com.novation.eligibility.service.party.impl.PartyServiceImpl;
import com.novation.eligibility.service.response.Response;

@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl extends PartyServiceImpl implements OrganizationService {

	@Inject
	protected OrganizationRepository repo;
	
	@Inject
	protected Converter<OrganizationDto, Organization> organizationWriteConverter;
	protected Converter<Organization, OrganizationDto> organizationReadConverter = new OrganizationToOrganizationDtoConverter();	

	protected OrganizationDto doFindOrganizationByName(String name) {
		Organization o = repo.findByName(name == null ? "" : name.trim());
		
		if (o == null) {
			log.debug("no organization found with name: " + name);
			return null;
		}

		return organizationReadConverter.convert(o);		
	}
	
	@Override
	public Response<OrganizationDto> findOrganizationByName(final String name) {
		return executeSafely(new ServiceExecution<OrganizationDto>() {
			
			public OrganizationDto execute() {
				return doFindOrganizationByName(name);
			}
		});
	}

	protected OrganizationDto doFindOrganizationByDuns(String duns) {
		Organization o = repo.findByDuns(duns == null ? "" : duns.trim());
		
		if (o == null) {
			log.debug("no organization found with duns: " + duns);
			return null;
		}
		return organizationReadConverter.convert(o);		
	}
	
	@Override
	public Response<OrganizationDto> findOrganizationByDuns(final String duns) {
		return executeSafely(new ServiceExecution<OrganizationDto>() {
			
			public OrganizationDto execute() {
				return doFindOrganizationByDuns(duns);
			}
		});
	}

	@Override
	public Response<Boolean> organizationExistsWithName(String name) {
		Response<Boolean> response = new Response<Boolean>();
		
		Response<OrganizationDto> findResponse = findOrganizationByName(name);
		
		return response.status(findResponse.getStatus()).payload(findResponse.getPayload() != null);
	}

	@Transactional(readOnly = false)
	@Override
	public Response<OrganizationCreationResult> createOrganization(OrganizationDto dto) {
		
		Response<OrganizationCreationResult> response = new Response<OrganizationCreationResult>();
		OrganizationCreationResult result = new OrganizationCreationResult();
		response.setPayload(result);
		
		try {
			Organization o = null;
			
			try {
				o = repo.findByName(dto.getName());
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			if (o != null) {
				result.cause = Cause.NAME_EXISTS;
				return response.failure();
			}
			
			try {
				o = organizationWriteConverter.convert(dto);
				o = repo.save(o);
				flush();
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			
			try {
				OrganizationDto returnDto = organizationReadConverter.convert(o);
				result.organization = returnDto;
			} catch (Exception e) {
				result.cause = Cause.MARSHALLING_EXCEPTION;
				return response.failure();
			}
			
			return response.success();
			
		} catch (Exception e) {
			result.cause = Cause.UNHANDLED_EXCEPTION;
			return response.failure(e);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Response<ChangePasswordResult> changePasswordByUsername(String username, String currentHash, String newHash) {
		return changePasswordByUsername(username, currentHash, newHash, null);
	}

	@Override
	@Transactional(readOnly = false)
	public Response<ChangePasswordResult> changePasswordByUsername(String username, String currentHash, String newHash, String salt) {
		return changePassword(repo.findByUsername(username), currentHash, newHash, salt);
	}

	@Override
	@Transactional(readOnly = false)
	public Response<ChangePasswordResult> changePasswordById(String partyId, String currentHash, String newHash) {
		return changePasswordById(partyId, currentHash, newHash, null);
	}

	@Override
	@Transactional(readOnly = false)
	public Response<ChangePasswordResult> changePasswordById(String partyId, String currentHash, String newHash, String salt) {
		return changePassword(repo.findOne(partyId), currentHash, newHash, salt);
	}

}
