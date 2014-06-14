package com.novation.eligibility.service.party.impl;

import java.util.Collection;
import java.util.HashSet;

import javax.inject.Inject;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novation.eligibility.domain.model.Individual;
import com.novation.eligibility.domain.model.Organization;
import com.novation.eligibility.domain.model.Party;
import com.novation.eligibility.domain.repo.IndividualRepository;
import com.novation.eligibility.domain.repo.OrganizationRepository;
import com.novation.eligibility.service.security.AppUser;
import com.novation.eligibility.support.AbstractLoggable;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl extends AbstractLoggable implements UserDetailsService {

	@Inject
	protected IndividualRepository indRepo;

	@Inject
	protected OrganizationRepository orgRepo;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			debug("finding user by username {}", username);

			Individual individual = indRepo.findByUsername(username);
			Organization org = orgRepo.findByUsername(username);

			if (individual != null) {
				debug("individual {} found", username);
				return grantRoles(individual);
			} else if (org != null) {
				debug("organization {} found", username);
				return grantRoles(individual);				
			}

			warn("user {} not found; throwing UsernameNotFoundException",
					username);

			throw new UsernameNotFoundException("ExpressesUser named "
					+ username + " not found");
		} catch (Exception e) {

			error("caught exception type {}, message {} while trying to get username {}; wrapping in UsernameNotFoundException and rethrowing",
					e.getClass().getName(), e.getMessage(), username);

			throw new UsernameNotFoundException("ExpressesUser named "
					+ username + "not found");
		}
	}
	
	private User grantRoles(Party user) {
		Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		debug("added ROLE_USER to user {}", user.getUsername());

		User springUser = new AppUser(
				user.getId(), user.getUsername(),
				user.getPasswordHash(), user.getSalt(), authorities, user.getPrimaryEmail());

		debug("created and returning {} of type {}", springUser,
				springUser.getClass().getName());

		return springUser;

	}
}
