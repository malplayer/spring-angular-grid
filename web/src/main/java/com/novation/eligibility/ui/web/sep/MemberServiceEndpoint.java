package com.novation.eligibility.ui.web.sep;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novation.eligibility.domain.dao.SampleDao;
import com.novation.eligibility.domain.repo.TestRepository;
import com.novation.eligibility.ui.services.MemberServiceImpl;
import com.novation.eligibility.ui.web.model.Member;

@Controller
@RequestMapping("/members")
public class MemberServiceEndpoint {

	// To test spring jdbc template, this is PoC to make sure jdbc template can work, ideally web layer should not 
	// directly talk to jdbc layer, should go through service layer
	@Autowired
	private SampleDao sampleDao;

	// To test hibernate, this is PoC to make sure hibernate DAO can work, ideally web layer should not 
	// directly talk to DAOs, should go through service layer
	@Autowired
	protected TestRepository testRepository;
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceEndpoint.class);
	
    @Autowired
    private MemberServiceImpl membersService;

    @RequestMapping("memberlist.json")
    public @ResponseBody List<Member> getMemberList() {

    	log.info("Count is: " + sampleDao.findCount());
    	log.info("hibernate class  is: " + testRepository.findById(2));
    	
    	log.info("Return member list.");    	
        return membersService.getAllMembers();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody void addMember(@RequestBody Member member) {
        membersService.addMember(member);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody void updateMember(@RequestBody Member member) {
        membersService.updateMember(member);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void removeMember(@PathVariable("id") Long id) {
        membersService.deleteMemberById(id);
    }

    @RequestMapping(value = "/removeAll", method = RequestMethod.DELETE)
    public @ResponseBody void removeAllMembers() {
        membersService.deleteAll();
    }

}
