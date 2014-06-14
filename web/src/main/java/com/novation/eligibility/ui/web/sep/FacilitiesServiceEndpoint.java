package com.novation.eligibility.ui.web.sep;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novation.eligibility.ui.services.DummyFacilitySelectionRepository;
import com.novation.eligibility.ui.web.model.FacilitySelection;


@Controller
@RequestMapping("/facilities")
public class FacilitiesServiceEndpoint {

	private static final Logger log = LoggerFactory.getLogger(FacilitiesServiceEndpoint.class);
	
	@Autowired
	private DummyFacilitySelectionRepository  facilitySelectionRepository;

	/**
	 * Returns the number of facilities for the search criteria.
	 */
    @RequestMapping(value = "getNumberOfFacilities")
    public @ResponseBody int getNumberOfFacilities(@RequestParam("id") String id,@RequestParam("name") String name) {
    	
    	log.info("Reached getNumberOfFacilities id: " + id + " name: " + name);
    	
    	facilitySelectionRepository.filter( id,  name, null, null, null, null,  null);
    	return facilitySelectionRepository.numberOfRecords();

    }
	
    /**
     * Returns a list of facilities (number of records per page is defined by the recordsPerPage) and page number is defined by pageNumber. 
     * Returns after applying the filter.
     * @param recordsPerPage
     * @param pageNumber
     */
    @RequestMapping(value = "getFacilities")
    public @ResponseBody List<FacilitySelection> getFacilitiesList(@RequestParam("pageSize") int recordsPerPage,@RequestParam("currentPage") int pageNumber,
    		@RequestParam("id") String id,@RequestParam("name") String name) {
    	
    	log.info("Reached getFacilitiesList pageSize: " + recordsPerPage + " pageNumber: " + pageNumber + " id: " + id + " name: " + name);
    	
    	// Assumes filtering is already applied before asking for each page 
    	// by calling <code>findNumberOfFacilities</code>, but, once database 
    	// is integrated this filtering will be called always.
    	// facilitySelectionRepository.filter( id,  name, null, null, null, null,  null);
    	
    	return facilitySelectionRepository.getFilteredFacilitySelectionAtRecordsPerPageAndPageNumber(recordsPerPage,pageNumber);

    }
	  
}
