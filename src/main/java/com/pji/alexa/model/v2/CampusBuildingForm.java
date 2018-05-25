package com.pji.alexa.model.v2;

/**

PapaJohns
Store ID to fetch
- buildingNumberRequired: optional
- id: optional
- name: optional
*/

import java.io.Serializable;

public class CampusBuildingForm implements Serializable {

    public Boolean buildingNumberRequired;

    public Long id;

    public String name;

	public Boolean getBuildingNumberRequired() {
		return buildingNumberRequired;
	}

	public void setBuildingNumberRequired(Boolean buildingNumberRequired) {
		this.buildingNumberRequired = buildingNumberRequired;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
