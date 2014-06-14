package com.novation.eligibility.service.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Date;

import javax.inject.Inject;

import org.testng.annotations.Test;

import com.novation.eligibility.domain.model.Contract;
import com.novation.eligibility.domain.repo.ContractRepository;
import com.novation.eligibility.domain.repo.VendorRepository;
import com.novation.eligibility.dto.dtos.ContractDto;
import com.novation.eligibility.dto.dtos.OrganizationDto;
import com.novation.eligibility.dto.dtos.VendorDto;
import com.novation.eligibility.service.contract.ContractCreationResult;
import com.novation.eligibility.service.contract.ContractService;
import com.novation.eligibility.service.organization.OrganizationCreationResult;
import com.novation.eligibility.service.organization.OrganizationService;
import com.novation.eligibility.service.vendor.VendorCreationResult;
import com.novation.eligibility.service.vendor.VendorService;
import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.service.response.Status;

public class ContractServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Inject
	ContractRepository contractRepo;
	
	@Inject
	VendorRepository vendorRepo;
	
	@Inject
	protected ContractService contractSvc;
	
	@Inject
	protected VendorService vendorSvc;
	
	@Inject
	protected OrganizationService orgSvc;

	private Response<VendorCreationResult> createVendor() {
		// create Organization
		OrganizationDto o = new OrganizationDto();
		o.setName(uuid());
		Response<OrganizationCreationResult> orgResp = orgSvc.createOrganization(o);
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
	
	@Test
	public void testCreateContract() {
		
		// create Contract
		ContractDto c = new ContractDto();
		c.setName(uuid());
		c.setDescription(uuid());
		c.setEffectiveDate(new Date());
		c.setSupplierVendorId(createVendor().getPayload().getVendor().getId());		
		Response<ContractCreationResult> response = contractSvc.createContract(c);
		assertThat(response.getStatus(), equalTo(Status.SUCCESS));
		
		ContractDto dto = response.getPayload().getContract();
		assertThat(dto, is(not(nullValue())));
		assertThat(dto.getName(), equalTo(c.getName()));
		assertThat(dto.getDescription(), equalTo(c.getDescription()));
		assertThat(dto.getEffectiveDate(), equalTo(c.getEffectiveDate()));
		assertThat(dto.getSupplierVendorId(), equalTo(c.getSupplierVendorId()));
	}
	
	@Test
	public void testFindByName() {
		Response<VendorCreationResult> vendResponse = createVendor();
		
		String name = uuid();
		Contract c = new Contract();
		c.setName(name);
		c.setEffectiveDate(new Date());
		c.setSupplier(vendorRepo.findOne(vendResponse.getPayload().getVendor().getId()));
		contractRepo.save(c);

		em.flush();
		
		Response<ContractDto> r = contractSvc.findContractByName(name);
		assertNotNull(r);
		
		ContractDto dto = null;
		assertNotNull(dto = r.getPayload());
		
		assertEquals(c.getName(), dto.getName());
	}
}
