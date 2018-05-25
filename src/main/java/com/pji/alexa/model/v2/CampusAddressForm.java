package com.pji.alexa.model.v2;

/**

PapaJohns
- buildingNumber: optional
- campus: optional
- campusBuilding: optional
- roomNumber: optional
*/

import java.io.Serializable;

public class CampusAddressForm implements Serializable {

    public String buildingNumber;

    public CampusForm campus;

    public CampusBuildingForm campusBuilding;

    public String roomNumber;

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public CampusForm getCampus() {
		return campus;
	}

	public void setCampus(CampusForm campus) {
		this.campus = campus;
	}

	public CampusBuildingForm getCampusBuilding() {
		return campusBuilding;
	}

	public void setCampusBuilding(CampusBuildingForm campusBuilding) {
		this.campusBuilding = campusBuilding;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
