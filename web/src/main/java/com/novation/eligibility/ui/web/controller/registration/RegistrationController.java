package com.novation.eligibility.ui.web.controller.registration;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.novation.eligibility.ui.services.SpringSecurityService;
import com.novation.eligibility.ui.web.ViewDecoratingSpringValidatingController;
import com.novation.eligibility.support.security.hash.Hasher;
import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.service.response.Status;
import com.novation.eligibility.service.individual.IndividualService;
import com.novation.eligibility.service.individual.MinimalIndividualCreationResult;

@Controller
@RequestMapping("/register")
public class RegistrationController extends
		ViewDecoratingSpringValidatingController {

	public static final String COMMAND_NAME = "registration";

	protected static final String FORM_VIEW_NAME = "/registration-form";

	@Inject
	IndividualService individualService;

	@Inject
	SpringSecurityService securityService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String prepareForm(
			@ModelAttribute(COMMAND_NAME) Registration reg,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "primaryEmail", required = false) String primaryEmail) {

		reg.username = username;
		reg.primaryEmail = primaryEmail;

		return decorateViewName(FORM_VIEW_NAME);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(COMMAND_NAME) Registration reg,
			HttpServletRequest request, BindingResult errs) {

		validate(reg, errs);
		reg.validateGlobally(errs);

		if (errs.hasErrors()) {
			return decorateViewName(FORM_VIEW_NAME);
		}

		request.getSession(true); // ensure session exists

		// IMPORTANT NOTE! This registration currently creates an Individual but the domain
		// model allows for an Organization with the same fields (username, email, password, etc).
		// This can be tackled later with a real UI implementation instead of this example
		String salt = Hasher.createSalt();
		String passwordHash = Hasher.hash(reg.password, salt);
		Response<MinimalIndividualCreationResult> response = individualService
				.createIndividualMinimally(reg.username, passwordHash,
						reg.primaryEmail, salt);

		if (response.getStatus() == Status.FAILURE) {
			error("failed to create user [{}] with primaryEmail [{}]: {}",
					reg.username, reg.primaryEmail, response.message);
			errs.reject("createUserMinimally.failure", response.message);
			return decorateViewName(FORM_VIEW_NAME);
		}

		if (!securityService.tryLogUserIn(reg.username, reg.password, request)) {
			warn("created user [{}] with primaryEmail [{}], but couldn't log in",
					reg.username, reg.primaryEmail);
			return redirect("/login-form?registered");
		}

		info("created user [{}] with primaryEmail [{}]", reg.username,
				reg.primaryEmail);
		return redirect("/private/registered");
	}
}
