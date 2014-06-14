package com.novation.eligibility.service.vendor.impl;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novation.eligibility.domain.model.Vendor;
import com.novation.eligibility.domain.repo.VendorRepository;
import com.novation.eligibility.dto.dtos.VendorDto;
import com.novation.eligibility.service.AbstractService;
import com.novation.eligibility.service.ServiceExecution;
import com.novation.eligibility.service.vendor.VendorCreationResult;
import com.novation.eligibility.service.vendor.VendorCreationResult.Cause;
import com.novation.eligibility.service.vendor.VendorService;
import com.novation.eligibility.service.vendor.converter.VendorToVendorDtoConverter;
import com.novation.eligibility.service.response.Response;

@Service
@Transactional(readOnly = true)
public class VendorServiceImpl extends AbstractService implements VendorService {

	@Inject
	protected VendorRepository repo;
	
	@Inject
	protected Converter<VendorDto, Vendor> vendorWriteConverter;	
	protected Converter<Vendor, VendorDto> vendorReadConverter = new VendorToVendorDtoConverter();
	
	protected VendorDto doFindVendorByName(String name) {
		Vendor v = repo.findByName(name == null ? "" : name.trim());
		
		if (v == null) {
			return null; // no vendor found
		}
		return vendorReadConverter.convert(v);	
	}

	@Override
	public Response<VendorDto> findVendorByName(final String name) {
		return executeSafely(new ServiceExecution<VendorDto>() {
			
			public VendorDto execute() {
				return doFindVendorByName(name);
			}
		});
	}

	@Transactional(readOnly = false)
	@Override
	public Response<VendorCreationResult> createVendor(VendorDto dto) {
		
		Response<VendorCreationResult> response = new Response<VendorCreationResult>();
		VendorCreationResult result = new VendorCreationResult();
		response.setPayload(result);
		
		try {
			Vendor v = null;
			
			try {
				v = repo.findByName(dto.getName());
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}
			if (v != null) {
				result.cause = Cause.NAME_EXISTS;
				return response.failure();
			}
						
			try {
				v = vendorWriteConverter.convert(dto);
			} catch (Exception e) {
				result.cause = Cause.MARSHALLING_EXCEPTION;
				return response.failure(e);
			}
			
			try {
				v = repo.save(v);
				flush();
			} catch (Exception e) {
				result.cause = Cause.REPO_FAILURE;
				return response.failure(e);
			}

			try {
				VendorDto returnDto = vendorReadConverter.convert(v);
				result.vendor = returnDto;
			} catch (Exception e) {
				result.cause = Cause.MARSHALLING_EXCEPTION;
				return response.failure(e);
			}

			return response.success();
			
		} catch (Exception e) {			
			result.cause = Cause.UNHANDLED_EXCEPTION;
			return response.failure(e);
		}
	}

}
