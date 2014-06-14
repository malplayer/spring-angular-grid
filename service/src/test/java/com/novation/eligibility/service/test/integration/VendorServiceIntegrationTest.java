package com.novation.eligibility.service.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.inject.Inject;

import org.testng.annotations.Test;

import com.novation.eligibility.domain.model.Vendor;
import com.novation.eligibility.domain.repo.OrganizationRepository;
import com.novation.eligibility.domain.repo.VendorRepository;
import com.novation.eligibility.dto.dtos.OrganizationDto;
import com.novation.eligibility.dto.dtos.VendorDto;
import com.novation.eligibility.service.organization.OrganizationCreationResult;
import com.novation.eligibility.service.organization.OrganizationService;
import com.novation.eligibility.service.vendor.VendorCreationResult;
import com.novation.eligibility.service.vendor.VendorService;
import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.service.response.Status;

public class VendorServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Inject
	protected OrganizationService orgSvc;
	
	@Inject
	protected VendorService vendorSvc;
	
	@Inject
	protected OrganizationRepository orgRepo;
	
	@Inject
	protected VendorRepository vendorRepo;
	
	private Response<OrganizationCreationResult> createOrganization() {
		OrganizationDto o = new OrganizationDto();
		o.setName(uuid());
		Response<OrganizationCreationResult> orgResp = orgSvc.createOrganization(o);
		assertThat(orgResp.getStatus(), equalTo(Status.SUCCESS));
		return orgResp;
	}
	
	@Test
	public void testCreateVendor() {
		
		VendorDto v = new VendorDto();
		v.setName(uuid());
		v.setVendorId(uuid());
		v.setOrganizationId(createOrganization().getPayload().getOrganization().getId());
		
		Response<VendorCreationResult> response = vendorSvc.createVendor(v);
		assertThat(response.getStatus(), equalTo(Status.SUCCESS));
		VendorDto payload = response.getPayload().getVendor();
		assertThat(payload, is(not(nullValue())));
		assertThat(payload.getName(), equalTo(v.getName()));
		assertThat(payload.getVendorId(), equalTo(v.getVendorId()));
		assertThat(payload.getOrganizationId(), equalTo(v.getOrganizationId()));
	}
	
	@Test
	public void testFindByName() {
		String name = uuid();
		
		Vendor v = new Vendor();
		v.setName(name);
		v.setVendorId(uuid());
		v.setOrganization(orgRepo.findOne(createOrganization().getPayload().getOrganization().getId()));
		vendorRepo.save(v);
		
		em.flush();
		
		Response<VendorDto> r = vendorSvc.findVendorByName(name);
		assertNotNull(r);
		
		VendorDto dto = null;
		assertNotNull(dto = r.getPayload());
		
		assertEquals(v.getName(), dto.getName());
	}
}
