package com.pji.alexa.model.v2;

/**

PapaJohns
- id: optional
- name: optional
*/

import java.io.Serializable;

public class CampusForm implements Serializable {

    public Long id;

    public String name;

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
