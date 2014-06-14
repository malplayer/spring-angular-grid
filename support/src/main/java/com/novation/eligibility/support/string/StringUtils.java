package com.novation.eligibility.support.string;

public class StringUtils {

	public static String bracket(Object o) {
		return bracket(o == null ? "null" : o.toString());
	}

	public static String bracket(String s) {
		return new StringBuilder().append('[').append(s).append(']').toString();
	}

	public static StringBuilder bracket(StringBuilder s) {
		return new StringBuilder().append('[').append(s).append(']');
	}
}
