package com.novation.eligibility.ui.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.novation.eligibility.ui.web.model.Facility;

/**
 * Created with IntelliJ IDEA. User: ajmal
 */
@Service("facilityService")
public class FacilityServiceImpl {
	private static List<Facility> facilityList = new ArrayList<Facility>();
	private static Long id = 0L;

	public List<Facility> getAllFacilitys() {
		return facilityList;
	}

	public Facility getFacilityById(Long id) {
		return findFacilityById(id);
	}

	public void addFacility(Facility facility) {
		facility.setId(++id);
		facilityList.add(facility);
	}

	public void deleteFacilityById(Long id) {
		Facility foundFacility = findFacilityById(id);
		if (foundFacility != null) {
			facilityList.remove(foundFacility);
			id--;
		}
	}

	public void deleteAll() {
		facilityList.clear();
		id = 0L;
	}

	public void updateFacility(Facility facility) {
		Facility foundFacility = findFacilityById(facility.getId());
		if (foundFacility != null) {
			facilityList.remove(foundFacility);
			facilityList.add(facility);
		}
	}

	private Facility findFacilityById(Long id) {
		for (Facility facility : facilityList) {
			if (facility.getId() == id) {
				return facility;
			}
		}

		return null;
	}
}
