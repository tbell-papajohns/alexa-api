package com.pji.alexa.model.v2;

/**

PapaJohns
- dayOfWeek: optional, Allowable Values: ["SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"]
- holiday: optional
- holidayClosedFlag: optional
- holidayMessage: optional
- pickupCloseTime: optional
- storeCloseTime: optional
- storeOpenTime: optional
*/

import java.io.Serializable;

public class StoreHourForm implements Serializable {

    public String dayOfWeek;

    public Boolean holiday;

    public Boolean holidayClosedFlag;

    public String holidayMessage;

    public String pickupCloseTime;

    public String storeCloseTime;

    public String storeOpenTime;

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Boolean getHoliday() {
		return holiday;
	}

	public void setHoliday(Boolean holiday) {
		this.holiday = holiday;
	}

	public Boolean getHolidayClosedFlag() {
		return holidayClosedFlag;
	}

	public void setHolidayClosedFlag(Boolean holidayClosedFlag) {
		this.holidayClosedFlag = holidayClosedFlag;
	}

	public String getHolidayMessage() {
		return holidayMessage;
	}

	public void setHolidayMessage(String holidayMessage) {
		this.holidayMessage = holidayMessage;
	}

	public String getPickupCloseTime() {
		return pickupCloseTime;
	}

	public void setPickupCloseTime(String pickupCloseTime) {
		this.pickupCloseTime = pickupCloseTime;
	}

	public String getStoreCloseTime() {
		return storeCloseTime;
	}

	public void setStoreCloseTime(String storeCloseTime) {
		this.storeCloseTime = storeCloseTime;
	}

	public String getStoreOpenTime() {
		return storeOpenTime;
	}

	public void setStoreOpenTime(String storeOpenTime) {
		this.storeOpenTime = storeOpenTime;
	}

}
