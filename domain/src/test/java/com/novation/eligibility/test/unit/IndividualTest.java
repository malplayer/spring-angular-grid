package com.novation.eligibility.test.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.Test;

import com.novation.eligibility.domain.model.Individual;

public class IndividualTest extends AbstractUnitTest {

	public static Individual create() {
		Individual i = new Individual();
		i.setFirstName(uuid());
		i.setLastName(uuid());
		i.setMiddleName(uuid());
		i.setDescription(uuid());
		i.setUsername(uuid());
		i.setPasswordHash(uuid());
		i.setSalt(uuid());

		assertThat(i.getFirstName(), notNullValue());
		assertThat(i.getLastName(), notNullValue());
		assertThat(i.getMiddleName(), notNullValue());
		assertThat(i.getDescription(), notNullValue());
		assertThat(i.getUsername(), notNullValue());
		assertThat(i.getPasswordHash(), notNullValue());
		assertThat(i.getSalt(), notNullValue());

		return i;
	}

	public IndividualTest() {
	}

	public IndividualTest(Callback callback) {
		this.callback = callback;
	}

	@Test
	public void testCreateIndividual() {
		Individual i = create();
		callback.onEntitiesCreated(i);
	}

	@Test
	public void testIndividualNames() {
		Individual i = new Individual();
		callback.onEntitiesCreated(i);

		assertThat(i.getFirstName(), nullValue());
		assertThat(i.getLastName(), nullValue());
		assertThat(i.getMiddleName(), nullValue());

		String g = uuid();

		i.setFirstName(g);
		callback.onEntitiesChanged(i);

		assertThat(g, equalTo(i.getFirstName()));
		assertThat(i.getLastName(), nullValue());
		assertThat(i.getMiddleName(), nullValue());

		String s = uuid();

		i.setLastName(s);
		callback.onEntitiesChanged(i);

		assertThat(g, equalTo(i.getFirstName()));
		assertThat(s, equalTo(i.getLastName()));
		assertThat(i.getMiddleName(), nullValue());

		String m = uuid();
		i.setMiddleName(m);
		callback.onEntitiesChanged(i);

		assertThat(g, equalTo(i.getFirstName()));
		assertThat(s, equalTo(i.getLastName()));
		assertThat(m, equalTo(i.getMiddleName()));

	}
	
	@Test
	public void testIndividualEmails() {

		Individual i = create();

		callback.onEntitiesCreated(i);

		String e = uuid() + "@" + uuid() + ".com";

		i.addEmail(e);

		callback.onEntitiesChanged(i);

		assertThat(i.getEmails().size(), equalTo(1));
		assertThat(i.getEmails(), contains(e));
		assertThat(i.getPrimaryEmail(), equalTo(e));

		String e2 = uuid() + "@" + uuid() + ".com";

		i.setPrimaryEmail(e2);

		callback.onEntitiesChanged(i);

		assertThat(i.getEmails().size(), equalTo(2));
		assertThat(i.getEmails(), containsInAnyOrder(e, e2));
		assertThat(i.getPrimaryEmail(), equalTo(e2));

		String e3 = uuid() + "@" + uuid() + ".com";

		i.setPrimaryEmail(e3);

		callback.onEntitiesChanged(i);

		assertThat(i.getEmails().size(), equalTo(3));
		assertThat(i.getEmails(), containsInAnyOrder(e, e2, e3));
		assertThat(i.getPrimaryEmail(), equalTo(e3));

		i.removeEmail(e3);

		callback.onEntitiesChanged(i);

		assertThat(i.getEmails().size(), equalTo(2));
		assertThat(i.getEmails(), containsInAnyOrder(e, e2));
		assertThat(i.getPrimaryEmail(), equalTo(e2));

		callback.onEntitiesChanged(i);

		assertThat(i.getEmails().size(), equalTo(2));
		assertThat(i.getEmails(), containsInAnyOrder(e, e2));
		assertThat(i.getPrimaryEmail(), equalTo(e2));
	}

	@Test
	public void testIndividualPhones() {
		
		Individual i = create();

		callback.onEntitiesCreated(i);

		String p = uuid();

		i.addPhone(p);

		callback.onEntitiesChanged(i);

		assertThat(i.getPhones().size(), equalTo(1));
		assertThat(i.getPhones(), contains(p));
		assertThat(i.getPrimaryPhone(), equalTo(p));

		String p2 = uuid();

		i.setPrimaryPhone(p2);

		callback.onEntitiesChanged(i);

		assertThat(i.getPhones().size(), equalTo(2));
		assertThat(i.getPhones(), containsInAnyOrder(p, p2));
		assertThat(i.getPrimaryPhone(), equalTo(p2));

		String p3 = uuid();

		i.setPrimaryPhone(p3);

		callback.onEntitiesChanged(i);

		assertThat(i.getPhones().size(), equalTo(3));
		assertThat(i.getPhones(), containsInAnyOrder(p, p2, p3));
		assertThat(i.getPrimaryPhone(), equalTo(p3));

		i.removePhone(p3);

		callback.onEntitiesChanged(i);

		assertThat(i.getPhones().size(), equalTo(2));
		assertThat(i.getPhones(), containsInAnyOrder(p, p2));
		assertThat(i.getPrimaryPhone(), equalTo(p2));

		callback.onEntitiesChanged(i);

		assertThat(i.getPhones().size(), equalTo(2));
		assertThat(i.getPhones(), containsInAnyOrder(p, p2));
		assertThat(i.getPrimaryPhone(), equalTo(p2));
	}

}
