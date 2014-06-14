package com.novation.eligibility.service.individual.impl;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novation.eligibility.domain.model.Individual;
import com.novation.eligibility.domain.repo.IndividualRepository;
import com.novation.eligibility.dto.dtos.IndividualDto;
import com.novation.eligibility.service.ServiceExecution;
import com.novation.eligibility.service.individual.IndividualService;
import com.novation.eligibility.service.individual.MinimalIndividualCreationResult;
import com.novation.eligibility.service.individual.MinimalIndividualCreationResult.Cause;
import com.novation.eligibility.service.individual.converter.IndividualToIndividualDtoConverter;
import com.novation.eligibility.service.party.ChangePasswordResult;
import com.novation.eligibility.service.party.impl.PartyServiceImpl;
import com.novation.eligibility.service.response.Response;

@Service
@Transactional(readOnly = true)
public class IndividualServiceImpl extends PartyServiceImpl implements IndividualService {

	@Inject
	protected IndividualRepository repo;
	
	@Inject
	protected Converter<IndividualDto, Individual> individualWriteConverter;
	protected Converter<Individual, IndividualDto> individualReadConverter = new IndividualToIndividualDtoConverter();
	
	protected Boolean doIndividualExistsWithUsername(String username) {
		Response<IndividualDto> findResponse = findIndividualByUsername(username);

		return findResponse.getPayload() != null;
	}
	
	@Override
	public Response<Boolean> individualExistsWithUsername(final String username) {
		return executeSafely(new ServiceExecution<Boolean>() {
			
			public Boolean execute() {
				return doIndividualExistsWithUsername(username);
			}
		});
	}

	protected IndividualDto doFindIndividualByUsername(String username) {
		Individual u = repo.findByUsername(username == null ? "" : username.trim());

		if (u == null) {
			return null; // no user found
		}
		return individualReadConverter.convert(u);
	}
	
	@Override
	public Response<IndividualDto> findIndividualByUsername(final String username) {
		return executeSafely(new ServiceExecution<IndividualDto>() {
			
			public IndividualDto execute() {
				return doFindIndividualByUsername(username);
			}
		});
	}

	@Override
	@Transactional(readOnly = false)
	public Response<ChangePasswordResult> changePasswordByUsername(String username, String currentHash, String newHash) {
		return changePasswordByUsername(username, currentHash, newHash, null);
	}

	@Override
	@Transactional(readOnly = false)
	public Response<ChangePasswordResult> changePasswordByUsername(String username, String currentHash, String newHash, String salt) {
		return changePassword(repo.findByUsername(username), currentHash, newHash, salt);
	}

	@Override
	@Transactional(readOnly = false)
	public Response<ChangePasswordResult> changePasswordById(String individualId, String currentHash, String newHash) {
		return changePasswordById(individualId, currentHash, newHash, null);
	}

	@Override
	@Transactional(readOnly = false)
	public Response<ChangePasswordResult> changePasswordById(String individualId, String currentHash, String newHash, String salt) {
		return changePassword(repo.findOne(individualId), currentHash, newHash, salt);
	}

	@Override
	@Transactional(readOnly = false)
	public Response<MinimalIndividualCreationResult> createIndividualMinimally(String username, String passwordHash, String email) {
		return createIndividualMinimally(username, passwordHash, email, null);
	}

	@Override
	@Transactional(readOnly = false)
	public Response<MinimalIndividualCreationResult> createIndividualMinimally(String username, String passwordHash, String email, String salt) {
		
		Response<MinimalIndividualCreationResult> response = new Response<MinimalIndividualCreationResult>();
		MinimalIndividualCreationResult result = new MinimalIndividualCreationResult();
		response.setPayload(result);

		try {
			username = username == null ? "" : username.trim().toLowerCase();
			if (username.length() == 0) {
				result.cause = Cause.BAD_USERNAME;
				return response.failure();
			}

			Individual u = null;
			try {
				u = repo.findByUsername(username);
			} catch (Exception x) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(x);
			}
			if (u != null) { // TODO: 'count' query instead
				result.cause = Cause.USERNAME_EXISTS;
				return response.failure();
			}

			email = email == null ? "" : email.trim().toLowerCase();
			// TODO: find by email

			try {
				u = new Individual(username);
				u.setPasswordHash(passwordHash);
				u.setSalt(salt);
				u.setPrimaryEmail(email);
			} catch (Exception e) {
				result.cause = Cause.DOMAIN_EXCEPTION;
				return response.failure(e);
			}

			try {
				u = repo.save(u);
				flush();
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}

			try {
				IndividualDto dto = individualReadConverter.convert(u);
				result.individual = dto;
			} catch (Exception e) {
				result.cause = Cause.MARSHALLING_EXCEPTION;
				return response.failure(e);
			}

			return response.success();

		} catch (Exception x) {
			result.cause = Cause.UNHANDLED_EXCEPTION;
			return response.failure(x);
		}
	}

}
