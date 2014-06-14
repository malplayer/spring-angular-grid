package com.novation.eligibility.domain.repo;

import com.novation.eligibility.domain.model.Vendor;


public interface VendorRepository extends CustomRepository<Vendor, String> {
	
	Vendor findByName(String name);
}
