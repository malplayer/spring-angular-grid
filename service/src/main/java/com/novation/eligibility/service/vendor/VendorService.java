package com.novation.eligibility.service.vendor;

import com.novation.eligibility.dto.dtos.VendorDto;
import com.novation.eligibility.service.response.Response;

public interface VendorService {

	Response<VendorDto> findVendorByName(String name);
	
	Response<VendorCreationResult> createVendor(VendorDto dto);
}
