package com.novation.eligibility.ui.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.novation.eligibility.ui.web.model.FacilitySelection;
import com.novation.eligibility.ui.web.model.FacilitySelectionList;

@Component
public class DummyFacilitySelectionRepository {

	private List<FacilitySelection> randomFacilitySelectionsList = new ArrayList<FacilitySelection>();
	private FacilitySelectionList facilitySelectionListCache = new FacilitySelectionList();
	private FacilitySelectionList facilitySelectionListFilteredCache;

	private DummyFacilitySelectionRepository() {
		initialize();
		generatefacilitySelectionListCache();
	}

	private void initialize() {
		randomFacilitySelectionsList.add(new FacilitySelection("97213",
				"ABC Pediatrics, PLLC", "PRQV", true, false, "BG1634567",
				"BAZK"));
		randomFacilitySelectionsList.add(new FacilitySelection("5134",
				"Acute Kindey Dialysis", "VHA", true, true, "BC12163", "BBPX"));
		randomFacilitySelectionsList
				.add(new FacilitySelection("7893434", "Adames Medicine inc.",
						"PROV", false, false, "PG672", "BCIWW"));
		randomFacilitySelectionsList
				.add(new FacilitySelection("81452", "Adult O/P Services",
						"CHA", false, true, "RT4567899", "CLSCV"));
		randomFacilitySelectionsList.add(new FacilitySelection("62639",
				"Arthtiis & Joint replacement Clinic P.C.", "VHA", true, false,
				"FW45829898", "BFYU"));
		randomFacilitySelectionsList.add(new FacilitySelection("9876422",
				"Associated Surgons of Washington", "PROV", false, false,
				"R%673848", "DGTL"));
		randomFacilitySelectionsList.add(new FacilitySelection("99152",
				"Beach Cities", "VHA", true, true, "BM5100643", "DCFRG"));
		randomFacilitySelectionsList.add(new FacilitySelection("81703",
				"Bethney Rehab", "CHA", true, false, "ER45", "QSCV"));
	}

	public void generatefacilitySelectionListCache() {

		Random random = new Random();
		random = new Random(System.currentTimeMillis());
		int numberOfRecords = random.nextInt(3000);

		for (int i = -2; i < numberOfRecords; i++) {
			facilitySelectionListCache.add(randomFacilitySelectionsList.get(random.nextInt(7)));
		}

		// Just add all the 8 records to make sure we have at least one instance of each from the 8 records.
		facilitySelectionListCache.addAll(randomFacilitySelectionsList);

	}
	
	public void filter(String id, String name,
			String alliance, String existingContract, String existingTier,String dea, String license) {
		
		// filter based on filter fields.
		facilitySelectionListFilteredCache = facilitySelectionListCache.filter(id, name, alliance,existingContract, existingTier, dea, license);
	}	

	public int numberOfRecords() {
		return facilitySelectionListFilteredCache.size();
	}

	
	public List<FacilitySelection> getAllFilteredFacilitySelection() {
		return facilitySelectionListFilteredCache.getFacilitySelections();
	}

	public List<FacilitySelection> getFilteredFacilitySelectionAtRecordsPerPageAndPageNumber(int recordsPerPage, int pageNumber) {
		return facilitySelectionListFilteredCache.getFacilitySelectionAtPageSizeAndPageNumber(recordsPerPage, pageNumber);
	}
	
	
}
