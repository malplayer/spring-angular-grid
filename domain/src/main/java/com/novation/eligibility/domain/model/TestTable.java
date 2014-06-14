package com.novation.eligibility.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "XXX_PETER_TEST")
public class TestTable {

	@Id
	@Column(name = "Peter_Column_1")
	private int id = 100;
	


	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TestTable [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestTable other = (TestTable) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
