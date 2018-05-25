package com.pji.alexa.model.v2;

/**

PapaJohns
- geoLocation: optional
- name: optional
- phone: optional
- specialInstructions: optional
*/

import java.io.Serializable;

public class OrderCustomerInformationForm implements Serializable {

    public GeoLocationForm geoLocation;

    public String name;

    public String phone;

    public String specialInstructions;

}
