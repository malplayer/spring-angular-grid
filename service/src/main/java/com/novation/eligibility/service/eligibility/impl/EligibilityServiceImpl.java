package com.novation.eligibility.service.eligibility.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.novation.eligibility.domain.model.ContractExecution;
import com.novation.eligibility.domain.repo.ContractExecutionRepository;
import com.novation.eligibility.domain.repo.ContractTierSelectionRepository;
import com.novation.eligibility.domain.repo.GPOMemberRepository;
import com.novation.eligibility.domain.repo.VendorRepository;
import com.novation.eligibility.dto.dtos.ContractExecutionDto;
import com.novation.eligibility.dto.dtos.EligibilityRequestDto;
import com.novation.eligibility.service.AbstractService;
import com.novation.eligibility.service.ServiceExecution;
import com.novation.eligibility.service.eligibility.EligibilityService;
import com.novation.eligibility.service.response.Response;

@Service
public class EligibilityServiceImpl extends AbstractService implements EligibilityService {

	@Inject
	protected VendorRepository vendorRepo;
	
	@Inject
	protected ContractTierSelectionRepository ctsRepo;
	
	@Inject
	protected GPOMemberRepository gpoMemberRepo;
	
	@Inject
	protected ContractExecutionRepository contractExecutionRepo;

	protected ContractExecutionDto doRequestEligibility(EligibilityRequestDto request) {
		ContractExecution ce = new ContractExecution();
		ce.setWhenStarted(new Date());
		ce.setRequestingMember(gpoMemberRepo.findOne(request.getGpoMemberId()));
		ce.setSelectedDistributor(vendorRepo.findOne(request.getVendorId()));
		
		return null; // TODO		
	}
	
	@Override
	public Response<ContractExecutionDto> requestEligibility(final EligibilityRequestDto request) {
		return executeSafely(new ServiceExecution<ContractExecutionDto>() {
			
			public ContractExecutionDto execute() {
				return doRequestEligibility(request);
			}
		});
	}

	@Override
	public Response<ContractExecutionDto> queryStatus(
			String contractExecutionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
