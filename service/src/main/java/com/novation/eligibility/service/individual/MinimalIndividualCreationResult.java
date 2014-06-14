package com.novation.eligibility.service.individual;

import com.novation.eligibility.dto.dtos.IndividualDto;
import com.novation.eligibility.dto.general.DataTransferObject;

@SuppressWarnings("serial")
public class MinimalIndividualCreationResult extends DataTransferObject {

        public enum Cause {
        	BAD_USERNAME, USERNAME_EXISTS, EMAIL_EXISTS, REPO_FAILURE, UNHANDLED_EXCEPTION, DOMAIN_EXCEPTION, MARSHALLING_EXCEPTION
        }

        public Cause cause;
        public IndividualDto individual;

        public Cause getCause() {
                return cause;
        }

        public MinimalIndividualCreationResult setCause(Cause reason) {
                this.cause = reason;
                return this;
        }

        public IndividualDto getIndividual() {
                return individual;
        }

        public MinimalIndividualCreationResult setIndividual(IndividualDto user) {
                this.individual = user;
                return this;
        }
}