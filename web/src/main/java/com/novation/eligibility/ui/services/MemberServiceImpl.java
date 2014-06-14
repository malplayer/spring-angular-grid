package com.novation.eligibility.ui.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.novation.eligibility.ui.web.model.Member;


@Service("MemberService")
public class MemberServiceImpl  {
    
    private static List<Member> rsList = new ArrayList<Member>();
    private static Long id = 0L;

    
    public Member getMemberById(Long id) {
    	
    	return findMemberById(id);
    }

    private Member findMemberById(Long id) {
        for (Member rs : rsList) {
            if (rs.getId() == id) {
                return rs;
            }
        }

        return null;
    }

    
	public List<Member> getAllMembers() {		
		return rsList;
	}


	
	public void addMember(Member member) {
		member.setId(++ id);
		rsList.add(member);		
		
	}

	
	public void deleteMemberById(Long id) {
        Member found = findMemberById(id);
        if (found != null) {
            rsList.remove(found);
            id--;
        }
		
	}

	
	public void updateMember(Member member) {
        Member found = findMemberById(member.getId());
        if (found != null) {
            rsList.remove(found);
            rsList.add(member);
        }
	}
	
    
    public void deleteAll() {
        rsList.clear();
        id = 0L;
    }
}
