package com.novation.eligibility.service.party.impl;

import static com.novation.eligibility.service.response.Response.failWithPayload;
import static com.novation.eligibility.service.response.Response.succeed;

import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import com.novation.eligibility.domain.model.Party;
import com.novation.eligibility.service.AbstractService;
import com.novation.eligibility.service.party.ChangePasswordResult;
import com.novation.eligibility.service.response.Response;

@Transactional(readOnly = true)
public class PartyServiceImpl extends AbstractService {

	protected Response<ChangePasswordResult> changePassword(@NotNull Party party, String currentHash, String newHash, String salt) {

		try {
			String partyHash = party.getPasswordHash();
			if (partyHash != null && partyHash.length() > 0) {
				if (!partyHash.equals(currentHash)) {
					warn("attempt to change password failed due to invalid currentHash");
					return failWithPayload(ChangePasswordResult.FAILURE);
				}
			}

			party.setPasswordHash(newHash);
			party.setSalt(salt);

			flush();

			info("successfully changed password of party id {}", party.getIdString());

			return succeed(ChangePasswordResult.SUCCESS);
		} catch (Exception x) {
			return failWithPayload(ChangePasswordResult.FAILURE).message(x);
		}
	}

}
