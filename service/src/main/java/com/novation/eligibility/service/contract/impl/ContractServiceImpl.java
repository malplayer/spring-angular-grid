package com.novation.eligibility.service.contract.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.novation.eligibility.domain.model.Contract;
import com.novation.eligibility.domain.repo.ContractRepository;
import com.novation.eligibility.dto.dtos.ContractDto;
import com.novation.eligibility.service.AbstractService;
import com.novation.eligibility.service.ServiceExecution;
import com.novation.eligibility.service.contract.ContractCreationResult;
import com.novation.eligibility.service.contract.ContractService;
import com.novation.eligibility.service.contract.ContractCreationResult.Cause;
import com.novation.eligibility.service.contract.converter.ContractToContractDtoConverter;
import com.novation.eligibility.service.response.Response;

@Service
@Transactional(readOnly = true)
public class ContractServiceImpl extends AbstractService implements ContractService {

	@Inject
	protected ContractRepository repo;
	
	@Inject
	protected Converter<ContractDto, Contract> contractWriteConverter;
	protected Converter<Contract, ContractDto> contractReadConverter = new ContractToContractDtoConverter();	 
	
	protected ContractDto doFindContractByName(String name) {
		Contract c = repo.findByName(name == null ? "" : name.trim());
		
		if (c == null) {
			return null; // no contract found
		}
		return contractReadConverter.convert(c);		
	}
	
	@Override
	public Response<ContractDto> findContractByName(final String name) {
		return executeSafely(new ServiceExecution<ContractDto>() {
			
			public ContractDto execute() {
				return doFindContractByName(name);
			}
		});
	}

	protected ContractDto doFindContractByEffectiveDate(Date effectiveDate) {
		Contract c = repo.findByEffectiveDate(effectiveDate);
		
		if (c == null) {
			return null; // no contract found
		}
		return contractReadConverter.convert(c);

	}
	@Override
	public Response<ContractDto> findContractByEffectiveDate(final Date effectiveDate) {
		return executeSafely(new ServiceExecution<ContractDto>() {
			
			public ContractDto execute() {
				return doFindContractByEffectiveDate(effectiveDate);
			}
		});		
	}

	@Transactional(readOnly = false)
	@Override
	public Response<ContractCreationResult> createContract(ContractDto dto) {
		
		Response<ContractCreationResult> response = new Response<ContractCreationResult>();
		ContractCreationResult result = new ContractCreationResult();
		response.setPayload(result);
		
		try {
			Contract c = null;
			
			try {
				c = repo.findByName(dto.getName());
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			if (c != null) {
				result.cause = Cause.NAME_EXISTS;
				return response.failure();
			}
			
			try {
				c = contractWriteConverter.convert(dto);
			} catch (Exception e) {
				result.cause = Cause.MARSHALLING_EXCEPTION;
				return response.failure(e);
			}
			
			try {
				c = repo.save(c);
				flush();
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			
			try {
				ContractDto returnDto = contractReadConverter.convert(c);
				result.contract = returnDto;
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
