package com.novation.eligibility.service.gpomember.impl;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novation.eligibility.domain.model.GPOMember;
import com.novation.eligibility.domain.repo.GPOMemberRepository;
import com.novation.eligibility.dto.dtos.GPOMemberDto;
import com.novation.eligibility.service.AbstractService;
import com.novation.eligibility.service.ServiceExecution;
import com.novation.eligibility.service.gpomember.GPOMemberCreationResult;
import com.novation.eligibility.service.gpomember.GPOMemberService;
import com.novation.eligibility.service.gpomember.GPOMemberCreationResult.Cause;
import com.novation.eligibility.service.gpomember.converter.GPOMemberToGPOMemberDtoConverter;
import com.novation.eligibility.service.response.Response;

@Service
@Transactional(readOnly = true)
public class GPOMemberServiceImpl extends AbstractService implements GPOMemberService {
	
	@Inject
	protected GPOMemberRepository repo;
	
	@Inject
	protected Converter<GPOMemberDto, GPOMember> gpoMemberWriteConverter;
	protected Converter<GPOMember, GPOMemberDto> gpoMemberReadConverter = new GPOMemberToGPOMemberDtoConverter();

	protected GPOMemberDto doFindGPOMemberByMemberNumber(String memberNumber) {
		GPOMember m = repo.findByMemberNumber(memberNumber == null ? "" : memberNumber.trim());
		
		if (m == null) {
			return null; // gpo member not found
		}
		return gpoMemberReadConverter.convert(m);		
	}
	
	@Override
	public Response<GPOMemberDto> findGPOMemberByMemberNumber(final String memberNumber) {
		return executeSafely(new ServiceExecution<GPOMemberDto>() {
			
			public GPOMemberDto execute() {
				return doFindGPOMemberByMemberNumber(memberNumber);
			}
		});
	}

	@Transactional(readOnly = false)
	@Override
	public Response<GPOMemberCreationResult> createGPOMember(GPOMemberDto dto) {

		Response<GPOMemberCreationResult> response = new Response<GPOMemberCreationResult>();
		GPOMemberCreationResult result = new GPOMemberCreationResult();
		response.setPayload(result);
		
		try {
			GPOMember o = null;
			
			try {
				o = repo.findByMemberNumber(dto.getMemberNumber());
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			if (o != null) {
				result.cause = Cause.MEMBERNUMBER_EXISTS;
				return response.failure();
			}
			
			try {
				o = gpoMemberWriteConverter.convert(dto);
				o = repo.save(o);
				flush();
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			
			try {
				GPOMemberDto returnDto = gpoMemberReadConverter.convert(o);
				result.gpoMember = returnDto;
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

}
