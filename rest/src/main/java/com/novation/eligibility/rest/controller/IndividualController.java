package com.novation.eligibility.rest.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.dto.dtos.ChangePasswordRequestDto;
import com.novation.eligibility.dto.dtos.IndividualDto;
import com.novation.eligibility.service.individual.IndividualService;
import com.novation.eligibility.service.party.ChangePasswordResult;

@Controller
@RequestMapping("/individuals")
public class IndividualController {

	@Inject
	IndividualService individualSvc;

	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	public @ResponseBody
	Response<IndividualDto> findUser(@PathVariable String username) {
		return individualSvc.findIndividualByUsername(username);
	}

	@RequestMapping(value = "/username/{username}/password", method = RequestMethod.POST)
	public @ResponseBody
	Response<ChangePasswordResult> changePassword(
			@PathVariable String username,
			@RequestBody ChangePasswordRequestDto request) {
		return individualSvc.changePasswordByUsername(username, request.currentHash,
				request.newHash, request.salt);
	}
}
