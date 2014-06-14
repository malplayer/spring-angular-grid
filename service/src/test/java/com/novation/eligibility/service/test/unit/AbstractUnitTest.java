package com.novation.eligibility.service.test.unit;

import java.util.UUID;

public abstract class AbstractUnitTest {
	
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
}
