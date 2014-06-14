package com.novation.eligibility.support.security.hash;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Small helper class to assist in consistent MD5 hashing & salting. Salt
 * generation uses {@link SecureRandom} with algorithm "SHA1PRNG".
 */
public class Hasher {

	/**
	 * Convenient class to return a password hash along with a newly created
	 * salt.
	 */
	public static class Tuple {

		public Tuple() {
		}

		public Tuple(String passwordHash, String salt) {
			this.passwordHash = passwordHash;
			this.salt = salt;
		}

		public String passwordHash;
		public String salt;

		public String getPasswordHash() {
			return passwordHash;
		}

		public void setPasswordHash(String passwordHash) {
			this.passwordHash = passwordHash;
		}

		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}
	}

	/**
	 * The default size of salt in bytes.
	 */
	public static final int DEFAULT_SALT_BYTE_SIZE = 32;

	protected static SecureRandom RNG = null;
	protected static final Md5PasswordEncoder MD5 = new Md5PasswordEncoder();

	static {
		try {
			RNG = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * Returns a new salt value as a string with the default salt byte size.
	 */
	public static String createSalt() {
		return createSalt(DEFAULT_SALT_BYTE_SIZE);
	}

	/**
	 * Returns a new salt value as a string from the given salt byte size.
	 */
	public static String createSalt(int byteSize) {
		byte[] bytes = new byte[byteSize * 2];
		RNG.nextBytes(bytes);
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * Hash the given password with the given salt.
	 * 
	 * @param cleartextPassword
	 *            The password in clear text.
	 * @param salt
	 *            The salt, which may be null.
	 */
	public static String hash(String cleartextPassword, String salt) {
		return MD5.encodePassword(cleartextPassword, salt);
	}

	/**
	 * Creates a new salt, hashes the given cleartext password with it, then
	 * returns a tuple containing the hashed password and the newly created
	 * salt.
	 * 
	 * @param cleartextPassword
	 *            The password in clear text.
	 */
	public static Tuple hash(String cleartextPassword) {
		String salt = createSalt();
		return new Tuple(hash(cleartextPassword, salt), salt);
	}
}
