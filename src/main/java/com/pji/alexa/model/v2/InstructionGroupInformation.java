package com.pji.alexa.model.v2;

/**

PapaJohns
- defaultInstructionId: optional
- description: optional
- id: optional
- instructions: optional
- name: optional
*/

import java.io.Serializable;
import java.util.List;

public class InstructionGroupInformation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long defaultInstructionId;

    private String description;

    private Long id;

    private List<InstructionInformation> instructions;

    private String name;

	public Long getDefaultInstructionId() {
		return defaultInstructionId;
	}

	public void setDefaultInstructionId(Long defaultInstructionId) {
		this.defaultInstructionId = defaultInstructionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<InstructionInformation> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<InstructionInformation> instructions) {
		this.instructions = instructions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
