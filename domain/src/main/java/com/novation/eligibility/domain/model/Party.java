package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;

import com.novation.eligibility.support.string.StringUtils;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING, length = 255)
@Entity
public abstract class Party extends BaseEntity {

	public final static String ADMIN_USERNAME = "admin";
	
	protected String username;
	
	protected String passwordHash;
	
	protected String salt;
	
	protected String description;
	
	@OneToMany(mappedBy = "initiator", cascade = CascadeType.ALL)
	protected List<ContractTierExecutionEvent> events = new ArrayList<ContractTierExecutionEvent>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PARTY_ADDRESSES", inverseJoinColumns = @JoinColumn(name = "address_id"))
	@MapKeyColumn(name = "address_name")
	protected Map<String, Address> addresses = new HashMap<String, Address>();

	protected String primaryAddressName;
	
	@ElementCollection
	@CollectionTable(name = "PARTY_EMAILS")
	@Column(name = "email")
	@OrderColumn(name = "i")
	public List<String> emails = new ArrayList<String>();
	
	@ElementCollection
	@CollectionTable(name = "PARTY_PHONES")
	@Column(name = "phone")
	@OrderColumn(name = "i")
	public List<String> phones = new ArrayList<String>();

	public Address getAddress(String name) {
		return addresses.get(name);
	}

	public void putAddress(@NotNull String name, @NotNull Address address) {
		testPutAddress(name, address);
		doPutAddress(name, address);
	}

	protected void testPutAddress(String name, Address address) {
		if (addresses.containsKey(name)) {
			throw new IllegalArgumentException("addresses already contains key");
		}
	}

	protected void doPutAddress(String name, Address address) {
		addresses.put(name, address);

		if (addresses.size() == 1) {
			doSetPrimaryAddressName(name);
		}
	}

	public Address getPrimaryAddress() {
		return primaryAddressName == null ? null : addresses
				.get(primaryAddressName);
	}

	public void setPrimaryAddressName(@NotNull String name) {
		testSetPrimaryAddressName(name);
		doSetPrimaryAddressName(name);
	}

	protected void testSetPrimaryAddressName(String name) {
		if (!addresses.containsKey(name)) {
			throw new IllegalArgumentException(
					"addresses does not contain given key");
		}
	}

	protected void doSetPrimaryAddressName(String name) {
		primaryAddressName = name;
	}

	public void setPrimaryAddress(@NotNull String name, @NotNull Address address,
			boolean leniently) {

	}

	public List<ContractTierExecutionEvent> getEvents() {
		return events;
	}

	public void setEvents(List<ContractTierExecutionEvent> events) {
		this.events = events;
	}

	public void addEvent(ContractTierExecutionEvent event) {
		events.add(event);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getPrimaryEmail() {
		return getEmailAt(0);
	}

	public boolean containsEmail(String email) {
		if (email == null) {
			return false;
		}
		return emails.contains(email.toLowerCase());
	}

	public void setPrimaryEmail(String email) {
		if (containsEmail(email)) {
			removeEmail(email);
		}
		addEmail(email, 0);
	}

	public List<String> getEmails() {
		return Collections.unmodifiableList(emails);
	}

	public void addEmail(@NotNull String email) {
		addEmail(email, emails.size());
	}

	public void addEmail(@NotNull String email, int index) {
		testAddEmail(email = email.toLowerCase(), index);
		doAddEmail(email, index);
	}

	public void testAddEmail(String email, int index) {
		defaultTestAddEmail(email, index);
	}

	public void defaultTestAddEmail(String email, int index) {
		if (emails.contains(email)) {
			throw new IllegalArgumentException(StringUtils.bracket(email)
					+ " already contained");
		}
		if (index == 0 && emails.size() == 0) {
			return;
		}
		if (index == emails.size()) {
			return;
		}
		if (index < 0 || index >= emails.size()) {
			throw new IllegalArgumentException(StringUtils.bracket(index)
					+ " is out of bounds");
		}
	}

	public void doAddEmail(String email, int index) {
		if ((index == 0 && emails.size() == 0) || index == emails.size()) {
			emails.add(email);
		} else {
			emails.add(index, email);
		}
	}

	public void removeAllEmails() {
		testRemoveAllEmails();
		doRemoveAllEmails();
	}

	public void testRemoveAllEmails() {
	}

	public void doRemoveAllEmails() {
		emails.clear();
	}

	public void removeEmail(@NotNull String email) {
		testRemoveEmail(email);
		doRemoveEmail(email);
	}

	public void testRemoveEmail(String email) {
		defaultTestRemoveEmail(email);
	}

	public void defaultTestRemoveEmail(String email) {
		if (!emails.contains(email.toLowerCase())) {
			throw new IllegalArgumentException("user doesn't have given email"
					+ StringUtils.bracket(email));
		}
	}

	public void doRemoveEmail(String email) {
		emails.remove(email = email.toLowerCase());
	}

	public int getEmailCount() {
		return emails.size();
	}

	public boolean primaryEmailIs(@NotNull String email) {
		return emails.size() == 0 ? false : emails.get(0).equals(
				email.toLowerCase());
	}

	public String getEmailAt(int index) {
		if (index < 0 || index >= emails.size()) {
			return null;
		}
		return emails.get(index);
	}
	
	public String getPrimaryPhone() {
		return getPhoneAt(0);
	}

	public boolean containsPhone(String phone) {
		if (phone == null) {
			return false;
		}
		return phones.contains(phone.toLowerCase());
	}

	public void setPrimaryPhone(String phone) {
		if (containsPhone(phone)) {
			removePhone(phone);
		}
		addPhone(phone, 0);
	}

	public List<String> getPhones() {
		return Collections.unmodifiableList(phones);
	}

	public void addPhone(@NotNull String phone) {
		addPhone(phone, phones.size());
	}

	public void addPhone(@NotNull String phone, int index) {
		testAddPhone(phone = phone.toLowerCase(), index);
		doAddPhone(phone, index);
	}

	public void testAddPhone(String phone, int index) {
		defaultTestAddPhone(phone, index);
	}

	public void defaultTestAddPhone(String phone, int index) {
		if (phones.contains(phone)) {
			throw new IllegalArgumentException(StringUtils.bracket(phone)
					+ " already contained");
		}
		if (index == 0 && phones.size() == 0) {
			return;
		}
		if (index == phones.size()) {
			return;
		}
		if (index < 0 || index >= phones.size()) {
			throw new IllegalArgumentException(StringUtils.bracket(index)
					+ " is out of bounds");
		}
	}

	public void doAddPhone(String phone, int index) {
		if ((index == 0 && phones.size() == 0) || index == phones.size()) {
			phones.add(phone);
		} else {
			phones.add(index, phone);
		}
	}

	public void removeAllPhones() {
		testRemoveAllPhones();
		doRemoveAllPhones();
	}

	public void testRemoveAllPhones() {
	}

	public void doRemoveAllPhones() {
		phones.clear();
	}

	public void removePhone(@NotNull String phone) {
		testRemovePhone(phone);
		doRemovePhone(phone);
	}

	public void testRemovePhone(String phone) {
		defaultTestRemovePhone(phone);
	}

	public void defaultTestRemovePhone(String phone) {
		if (!phones.contains(phone.toLowerCase())) {
			throw new IllegalArgumentException("user doesn't have given phone"
					+ StringUtils.bracket(phone));
		}
	}

	public void doRemovePhone(String phone) {
		phones.remove(phone = phone.toLowerCase());
	}

	public int getPhoneCount() {
		return phones.size();
	}

	public boolean primaryPhoneIs(@NotNull String phone) {
		return phones.size() == 0 ? false : phones.get(0).equals(
				phone.toLowerCase());
	}

	public String getPhoneAt(int index) {
		if (index < 0 || index >= phones.size()) {
			return null;
		}
		return phones.get(index);
	}

}
