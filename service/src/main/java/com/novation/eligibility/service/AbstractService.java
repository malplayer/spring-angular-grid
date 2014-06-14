package com.novation.eligibility.service;

import static com.novation.eligibility.service.response.Response.succeed;
import static com.novation.eligibility.service.response.Response.fail;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.novation.eligibility.support.AbstractLoggable;
import com.novation.eligibility.service.response.Response;

@Component
public abstract class AbstractService extends AbstractLoggable {

	@PersistenceContext
	protected transient EntityManager em;
	
	@SuppressWarnings("unchecked")
	public <T extends Serializable> Response<T> executeSafely(ServiceExecution<T> callback) {
		T t;
		try {
			t = callback.execute();
			return succeed(t);
		} catch (Exception e) {
			rollback();
			return (Response<T>) fail(e);
		}
	}
	
	protected void flush() {
		em.flush();
	}
	
	protected void rollback() {
		setRollbackOnly();
	}
	
	protected void setRollbackOnly() {
		TransactionStatus status = null;
		String msg = null;
		
		try {
			status = TransactionAspectSupport.currentTransactionStatus();
			if (status == null) {
				msg = "current TransactionStatus is null; can't roll back";
			}
		} catch (NoTransactionException x) {
			msg = x.getClass().getName() + ": " + x.getMessage();
			log.error(msg);
			throw x;
		}

		if (status == null) {
			log.warn(msg);
			return;
		}

		if (status.isRollbackOnly()) {
			msg = "current TransactionStatus is already rolling back; ignoring rollback request";
			log.warn(msg);
			return;
		}

		if (status.isCompleted()) {
			msg = "current TransactionStatus is already completed; can't roll back!";
			log.warn(msg);
			return;
		}

		status.setRollbackOnly();

		msg = "successfully set TransactionStatus to rollback only";
		log.info(msg);

	}
}
