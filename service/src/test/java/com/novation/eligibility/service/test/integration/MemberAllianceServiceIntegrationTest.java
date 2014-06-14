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

import com.novation.eligibility.domain.model.MemberAlliance;
import com.novation.eligibility.domain.repo.MemberAllianceRepository;
import com.novation.eligibility.dto.dtos.MemberAllianceDto;
import com.novation.eligibility.service.memberalliance.MemberAllianceCreationResult;
import com.novation.eligibility.service.memberalliance.MemberAllianceService;
import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.service.response.Status;

public class MemberAllianceServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Inject
	MemberAllianceRepository repo;
	
	@Inject
	protected MemberAllianceService svc;
	
	@Test
	public void testCreateMemberAlliance() {
		MemberAllianceDto m = new MemberAllianceDto();
		m.setName(uuid());
		m.setDescription(uuid());
		
		Response<MemberAllianceCreationResult> response = svc.createMemberAlliance(m);
		assertThat(response.getStatus(), equalTo(Status.SUCCESS));
		
		MemberAllianceDto dto = response.getPayload().getMemberAlliance();
		assertThat(dto, is(not(nullValue())));
		assertThat(dto.getName(), equalTo(m.getName()));
		assertThat(dto.getDescription(), equalTo(m.getDescription()));
	}
	
	@Test
	public void testFindByName() {
		String name = uuid();
		MemberAlliance m = new MemberAlliance();
		m.setName(name);
		repo.save(m);
		
		em.flush();
		
		Response<MemberAllianceDto> r = svc.findMemberAllianceByName(name);
		
		assertNotNull(r);
		
		MemberAllianceDto dto = null;
		assertNotNull(dto = r.getPayload());
		
		assertEquals(m.getName(), dto.getName());		
	}
}
