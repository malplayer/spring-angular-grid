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

import com.novation.eligibility.domain.model.GPOMember;
import com.novation.eligibility.domain.repo.GPOMemberRepository;
import com.novation.eligibility.domain.repo.OrganizationRepository;
import com.novation.eligibility.dto.dtos.GPOMemberDto;
import com.novation.eligibility.dto.dtos.OrganizationDto;
import com.novation.eligibility.service.gpomember.GPOMemberCreationResult;
import com.novation.eligibility.service.gpomember.GPOMemberService;
import com.novation.eligibility.service.organization.OrganizationCreationResult;
import com.novation.eligibility.service.organization.OrganizationService;
import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.service.response.Status;

public class GPOMemberServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Inject
	protected GPOMemberRepository gpoMemberRepo;
	
	@Inject
	protected GPOMemberService gpoMemberSvc;
	
	@Inject
	protected OrganizationService orgSvc;
	
	@Inject
	protected OrganizationRepository orgRepo;
	
	private Response<OrganizationCreationResult> createOrganization() {
		OrganizationDto o = new OrganizationDto();
		o.setName(uuid());
		Response<OrganizationCreationResult> orgResp = orgSvc.createOrganization(o);
		assertThat(orgResp.getStatus(), equalTo(Status.SUCCESS));
		return orgResp;
	}

	@Test
	public void testCreateGPOMember() {
		GPOMemberDto g = new GPOMemberDto();
		g.setMemberNumber(uuid());
		g.setOrganizationId(createOrganization().getPayload().getOrganization().getId());
		
		Response<GPOMemberCreationResult> response = gpoMemberSvc.createGPOMember(g);
		assertThat(response.getStatus(), equalTo(Status.SUCCESS));
		GPOMemberDto payload = response.getPayload().getGPOMember();
		assertThat(payload, is(not(nullValue())));
		assertThat(payload.getMemberNumber(), equalTo(g.getMemberNumber()));
		assertThat(payload.getOrganizationId(), equalTo(g.getOrganizationId()));
	}
	
	@Test
	public void testFindByMemberNumber() {
		String memberNumber = uuid();
		
		GPOMember g = new GPOMember();
		g.setMemberNumber(memberNumber);
		g.setOrganization(orgRepo.findOne(createOrganization().getPayload().getOrganization().getId()));
		gpoMemberRepo.save(g);
		
		em.flush();
		
		Response<GPOMemberDto> r = gpoMemberSvc.findGPOMemberByMemberNumber(memberNumber);
		assertNotNull(r);
		
		GPOMemberDto dto = null;
		assertNotNull(dto = r.getPayload());
		
		assertEquals(g.getMemberNumber(), dto.getMemberNumber());		
	}
}
