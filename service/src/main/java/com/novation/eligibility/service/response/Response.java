package com.novation.eligibility.service.response;
//instance method noun - static method verb
import static com.novation.eligibility.service.response.Status.FAILURE;
import static com.novation.eligibility.service.response.Status.PARTIAL;
import static com.novation.eligibility.service.response.Status.SUCCESS;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
* The purpose of this class is to allow the service layer to return responses
* instead of throwing exceptions to clients (REST or otherwise). Each service
* method should return a Response<T> with an appropriate data transfer object
* as necessary along with a status, and any error information in the case of a
* failure.
* <p/>
* For convenience, you can statically import the static <code>fail(..)</code>
* &amp; <code>succeed(..)</code> methods and/or use the builder methods.
*/
public class Response<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	// convenient factory methods (need class argument for generics)

	public static Response<? extends Serializable> fail() {
		return fail(Serializable.class);
	}

	public static <P extends Serializable> Response<P> fail(Class<P> payloadType) {
		return fail((String) null, payloadType);
	}

	public static Response<? extends Serializable> failWithMessage(Enum<?> e) {
		return failWithMessage(e, Serializable.class);
	}

	public static <P extends Serializable> Response<P> failWithMessage(
			Enum<?> e, Class<P> payloadType) {
		return new Response<P>().failure().message(e == null ? "" : e.name());
	}

	@SuppressWarnings("unchecked")
	public static <P extends Enum<P>> Response<P> failWithPayload(Enum<P> e) {
		return new Response<P>().failure().payload((P) e);
	}

	public static Response<? extends Serializable> fail(Throwable cause) {
		return fail(cause, Serializable.class);
	}

	public static <P extends Serializable> Response<P> fail(Throwable cause,
			Class<P> payloadType) {
		return fail(toMessage(cause), payloadType);
	}

	public static Response<? extends Serializable> fail(String message) {
		return fail(message, Serializable.class);
	}

	public static <P extends Serializable> Response<P> fail(String message,
			Class<P> payloadType) {
		return new Response<P>().failure(message);
	}

	public static <P extends Serializable> Response<P> succeed(P payload) {
		return new Response<P>().success(payload);
	}

	/**
	 * Converts a Throwable to a String
	 */
	protected static String toMessage(Throwable t) {
		return t == null ? "" : String.format("%s: %s", t.getClass().getName(),
				t.getMessage());
	}

	/**
	 * Each response must have a status enum indicating success, failure, or
	 * partial success (in the event of aggregate service operations).
	 */
	public Status status = FAILURE;

	/**
	 * Any error message reported by the service
	 */
	public String message;

	/**
	 * The data object being returned
	 */
	public T payload;

	public Response() {
	}

	public Response(T payload) {
		payload(payload);
	}

	// builder methods
	public Response<T> payload(T payload) {
		setPayload(payload);
		return this;
	}

	public Response<T> message(Throwable cause) {
		return message(toMessage(cause));
	}

	public Response<T> message(Enum<?> e) {
		return message(e == null ? "" : e.name());
	}

	public Response<T> message(String message) {
		setMessage(message);
		return this;
	}

	public Response<T> status(Status status) {
		setStatus(status);
		return this;
	}

	// setters/getters
	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public T getPayload() {
		return payload;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(Throwable t) {
		setMessage(toMessage(t));
	}

	// convenient failure methods
	public Response<T> failure() {
		return failure((String) null);
	}

	public Response<T> failure(Enum<?> e) {
		return failure(e == null ? "" : e.name());
	}

	public Response<T> failure(Throwable cause) {
		return failure(toMessage(cause));
	}

	public Response<T> failure(String message) {
		return status(FAILURE).message(message);
	}

	// convenient partial success methods
	public Response<T> partial() {
		return partial((String) null);
	}

	public Response<T> partial(Enum<?> e) {
		return partial(e == null ? "" : e.name());
	}

	public Response<T> partial(Throwable cause) {
		return partial(toMessage(cause));
	}

	public Response<T> partial(String message) {
		return status(PARTIAL).message(message);
	}

	// convenient success methods
	public Response<T> success() {
		return status(SUCCESS);
	}

	public Response<T> success(T payload) {
		return status(SUCCESS).payload(payload);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
