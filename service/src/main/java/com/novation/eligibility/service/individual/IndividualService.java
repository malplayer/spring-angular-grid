package com.novation.eligibility.service.individual;

import com.novation.eligibility.dto.dtos.IndividualDto;
import com.novation.eligibility.service.party.ChangePasswordResult;
import com.novation.eligibility.service.response.Response;

public interface IndividualService {
	
	Response<Boolean> individualExistsWithUsername(String username);
	
	Response<IndividualDto> findIndividualByUsername(String username);
	
    Response<MinimalIndividualCreationResult> createIndividualMinimally(String username, String passwordHash, String email);

    Response<MinimalIndividualCreationResult> createIndividualMinimally(String username, String passwordHash, String email, String salt);
    
	Response<ChangePasswordResult> changePasswordByUsername(String username, String currentHash, String newHash);

	Response<ChangePasswordResult> changePasswordByUsername(String username, String currentHash, String newHash, String salt);

	Response<ChangePasswordResult> changePasswordById(String individualId, String currentHash, String newHash);

	Response<ChangePasswordResult> changePasswordById(String individualId, String currentHash, String newHash, String salt);

}
