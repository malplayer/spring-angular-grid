package com.novation.eligibility.ui.web.model;

import java.util.ArrayList;
import java.util.List;

public class FacilitySelectionList {
	private List<FacilitySelection> facilitySelections = new ArrayList<FacilitySelection>();

	public FacilitySelectionList() {

	}

	public List<FacilitySelection> getFacilitySelections() {
		return facilitySelections;
	}

	public void setFacilitySelections(List<FacilitySelection> facilitySelections) {
		this.facilitySelections = facilitySelections;
	}

	public void add(FacilitySelection facilitySelection) {
		facilitySelections.add(facilitySelection);
	}


	public int size() {
		return facilitySelections.size();
	}

	public void addAll(List<FacilitySelection> argFacilitySelectionsList) {
		facilitySelections.addAll(argFacilitySelectionsList);
	}

	public FacilitySelectionList filter(String id, String name,
			String alliance, String existingContract, String existingTier, String dea, String license) {

		FacilitySelectionList result = new FacilitySelectionList();
		for (FacilitySelection facilitySelection : facilitySelections) {
			if (facilitySelection.filter(id, name, alliance, existingContract, existingTier, dea, license)) {
				result.add(facilitySelection);
			}
		}
		return result;
	}


	@Override
	public String toString() {
		return "FacilitySelectionList [facilitySelections="
				+ facilitySelections + "]";
	}

	public List<FacilitySelection> getFacilitySelectionAtPageSizeAndPageNumber(int recordsPerPage,
			int pageNumber) {

		int endIndex = facilitySelections.size();

		if(recordsPerPage * pageNumber < facilitySelections.size()) {
			endIndex = recordsPerPage * pageNumber;
		}
		
		return facilitySelections.subList((pageNumber - 1) * recordsPerPage, endIndex);
	}
}
