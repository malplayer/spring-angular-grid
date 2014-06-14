package com.novation.eligibility.test.unit;

import java.util.UUID;

public abstract class AbstractUnitTest {

	static public String uuid() {
		return UUID.randomUUID().toString();
	}

	public interface Callback {

		void onEntitiesCreated(Object... entities);

		void onEntitiesChanged(Object... entities);

		void onUnitTestDone();
	}

	public static final Callback NO_OP = new Callback() {
		@Override
		public void onEntitiesCreated(Object... entities) {
		}

		@Override
		public void onEntitiesChanged(Object... entities) {
		}

		@Override
		public void onUnitTestDone() {
		}
	};

	public Callback callback = NO_OP;

	public AbstractUnitTest() {
	}

	public AbstractUnitTest(Callback callback) {
		this.callback = callback;
	}
}
