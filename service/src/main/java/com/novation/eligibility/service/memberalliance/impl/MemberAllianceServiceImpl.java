package com.novation.eligibility.service.memberalliance.impl;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novation.eligibility.domain.model.MemberAlliance;
import com.novation.eligibility.domain.repo.MemberAllianceRepository;
import com.novation.eligibility.dto.dtos.MemberAllianceDto;
import com.novation.eligibility.service.AbstractService;
import com.novation.eligibility.service.ServiceExecution;
import com.novation.eligibility.service.memberalliance.MemberAllianceCreationResult;
import com.novation.eligibility.service.memberalliance.MemberAllianceCreationResult.Cause;
import com.novation.eligibility.service.memberalliance.MemberAllianceService;
import com.novation.eligibility.service.memberalliance.converter.MemberAllianceToMemberAllianceDtoConverter;
import com.novation.eligibility.service.response.Response;

@Service
@Transactional(readOnly = true)
public class MemberAllianceServiceImpl extends AbstractService implements MemberAllianceService {

	@Inject
	protected MemberAllianceRepository repo;

	@Inject
	protected Converter<MemberAllianceDto, MemberAlliance> memberAllianceWriteConverter;
	protected Converter<MemberAlliance, MemberAllianceDto> memberAllianceReadConverter = new MemberAllianceToMemberAllianceDtoConverter();

	protected MemberAllianceDto doFindMemberAllianceByName(String name) {
		MemberAlliance m = repo.findByName(name == null ? "" : name.trim());
		
		if (m == null) {
			return null; // no member alliance found
		}
		return memberAllianceReadConverter.convert(m);		
	}
	
	@Override
	public Response<MemberAllianceDto> findMemberAllianceByName(final String name) {
		return executeSafely(new ServiceExecution<MemberAllianceDto>() {
			
			public MemberAllianceDto execute() {
				return doFindMemberAllianceByName(name);
			}
		});
	}

	@Override
	@Transactional(readOnly = false)
	public Response<MemberAllianceCreationResult> createMemberAlliance(MemberAllianceDto dto) {
		
		Response<MemberAllianceCreationResult> response = new Response<MemberAllianceCreationResult>();
		MemberAllianceCreationResult result = new MemberAllianceCreationResult();
		response.setPayload(result);
		
		try {
			MemberAlliance m = null;
			
			try {
				m = repo.findByName(dto.getName());
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			if (m != null) {
				result.cause = Cause.NAME_EXISTS;
				return response.failure();
			}
			
			try {
				m = memberAllianceWriteConverter.convert(dto);
				m = repo.save(m);
				flush();
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			
			try {
				MemberAllianceDto returnDto = memberAllianceReadConverter.convert(m);
				result.memberAlliance = returnDto;
			} catch (Exception e) {
				result.cause = Cause.MARSHALLING_EXCEPTION;
				return response.failure(e);
			}
			
			return response.success();
			
		} catch (Exception e) {
			result.cause = Cause.UNHANDLED_EXCEPTION;
			return response.failure(e);
		}
		
	}

}
