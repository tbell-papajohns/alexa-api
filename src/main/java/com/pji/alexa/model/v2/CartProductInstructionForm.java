package com.pji.alexa.model.v2;

/**

PapaJohns
- detailId: optional
- groupId: optional
*/

import java.io.Serializable;

public class CartProductInstructionForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long detailId;

    private Long groupId;

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
    
}
