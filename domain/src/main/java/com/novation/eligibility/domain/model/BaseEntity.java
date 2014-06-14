package com.novation.eligibility.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.novation.eligibility.support.AbstractLoggable;

@MappedSuperclass
public abstract class BaseEntity extends AbstractLoggable {

	@Id
	@Column(name = "id")
	private String id = uuid();
	
	@Version
	@Column(name = "version")
	private long version;

	public Long getVersion() {
		return version;
	}	
	
	public String getId() {
		return id;
	}

	public String getIdString() {
		return getId() == null ? "" : getId().toString();
	}

	public String toString() {
		return buildString(this, StringifiableStyle.DEFAULT);
	}

	public int hashCode() {
		return identityHashCode();
	}
	
	public boolean identifies(Object that) {
		if (that == null) {
			return false;
		}
		if (this == that) {
			return true;
		}
		if (!(that instanceof BaseEntity)) {
			return false;
		}
		BaseEntity other = (BaseEntity) that;

		return this.getId().equals(other.getId());
	}

	private int identityHashCode() {
		return getId() == null ? 0 : getId().hashCode();
	}
	
	private String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	private String buildString(Object thiz, StringifiableStyle style) {

		ToStringStyle toStringStyle = ToStringStyle.DEFAULT_STYLE;

		switch (style) {
		case DEFAULT:
			toStringStyle = ToStringStyle.DEFAULT_STYLE;
			break;
		case SHORT:
			toStringStyle = ToStringStyle.SHORT_PREFIX_STYLE;
			break;
		case NO_FIELD_NAMES:
			toStringStyle = ToStringStyle.NO_FIELD_NAMES_STYLE;
			break;
		case MULTILINE:
			toStringStyle = ToStringStyle.MULTI_LINE_STYLE;
			break;
		}

		return ReflectionToStringBuilder.toString(thiz, toStringStyle);
	}

	private enum StringifiableStyle {
		DEFAULT, SHORT, MULTILINE, NO_FIELD_NAMES;
	}
	
}
