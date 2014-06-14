package com.novation.eligibility.dto.general;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class DataTransferObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, true);		
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, true);		
	}
}
