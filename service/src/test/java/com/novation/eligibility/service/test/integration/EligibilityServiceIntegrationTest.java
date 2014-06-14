package com.novation.eligibility.service.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import javax.inject.Inject;

import org.testng.annotations.Test;

import com.novation.eligibility.dto.dtos.ContractDto;
import com.novation.eligibility.dto.dtos.ContractExecutionDto;
import com.novation.eligibility.dto.dtos.EligibilityRequestDto;
import com.novation.eligibility.dto.dtos.GPOMemberDto;
import com.novation.eligibility.dto.dtos.OrganizationDto;
import com.novation.eligibility.dto.dtos.VendorDto;
import com.novation.eligibility.service.contract.ContractCreationResult;
import com.novation.eligibility.service.contract.ContractService;
import com.novation.eligibility.service.eligibility.EligibilityService;
import com.novation.eligibility.service.gpomember.GPOMemberCreationResult;
import com.novation.eligibility.service.gpomember.GPOMemberService;
import com.novation.eligibility.service.memberalliance.MemberAllianceService;
import com.novation.eligibility.service.organization.OrganizationCreationResult;
import com.novation.eligibility.service.organization.OrganizationService;
import com.novation.eligibility.service.vendor.VendorCreationResult;
import com.novation.eligibility.service.vendor.VendorService;
import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.service.response.Status;

public class EligibilityServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Inject
	protected EligibilityService eligibilitySvc;
	
	@Inject
	protected OrganizationService orgSvc;
	
	@Inject
	protected MemberAllianceService memberAllianceSvc;
	
	@Inject
	protected ContractService contractSvc;
	
	@Inject
	protected VendorService vendorSvc;
	
	@Inject
	protected GPOMemberService gpoMemberSvc;
	
	/****************************************************/
	// Helpers
	/****************************************************/

	private Response<OrganizationCreationResult> createOrganization() {
		OrganizationDto o = new OrganizationDto();
		o.setName(uuid());
		Response<OrganizationCreationResult> orgResp = orgSvc.createOrganization(o);
		assertThat(orgResp.getStatus(), equalTo(Status.SUCCESS));
		return orgResp;
	}

	private Response<VendorCreationResult> createVendor() {
		Response<OrganizationCreationResult> orgResp = createOrganization();
		assertThat(orgResp.getStatus(), equalTo(Status.SUCCESS));

		// create Vendor
		VendorDto v = new VendorDto();
		v.setName(uuid());
		v.setVendorId(uuid());
		v.setOrganizationId(orgResp.getPayload().getOrganization().getId());
		Response<VendorCreationResult> vendResp = vendorSvc.createVendor(v);
		assertThat(vendResp.getStatus(), equalTo(Status.SUCCESS));
		return vendResp;
	}
	
	private Response<ContractCreationResult> createContract() {
		ContractDto c = new ContractDto();
		c.setName(uuid());
		c.setDescription(uuid());
		c.setEffectiveDate(new Date());
		c.setSupplierVendorId(createVendor().getPayload().getVendor().getId());
		Response<ContractCreationResult> response = contractSvc.createContract(c);
		assertThat(response.getStatus(), equalTo(Status.SUCCESS));
		return response;		
	}

	@Test
	public void testRequestEligibility() {

		////////////////
		// SETUP
		GPOMemberDto g = new GPOMemberDto();
		g.setMemberNumber(uuid());
		g.setOrganizationId(createOrganization().getPayload().getOrganization().getId());		
		Response<GPOMemberCreationResult> memberResponse = gpoMemberSvc.createGPOMember(g);
		
		Response<VendorCreationResult> vendorResponse = createVendor();
		
		Response<ContractCreationResult> contractResponse = createContract();		
		////////////////////////////////
				
		EligibilityRequestDto dto = new EligibilityRequestDto();
		//dto.setContractId(contractResponse.getPayload().getContract().getId());
		dto.setGpoMemberId(memberResponse.getPayload().getGPOMember().getId());
		dto.setVendorId(vendorResponse.getPayload().getVendor().getId());
		
		Response<ContractExecutionDto> response = eligibilitySvc.requestEligibility(dto);
		assertThat(response.getStatus(), equalTo(Status.SUCCESS));
	}
}
