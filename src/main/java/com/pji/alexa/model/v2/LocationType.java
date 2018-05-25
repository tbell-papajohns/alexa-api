package com.pji.alexa.model.v2;

/**

PapaJohns
- locationDescription: optional
- locationTypeEnum: optional, Allowable Values: ["HOME", "BUSINESS", "UNIVERSITY", "MILITARY"]
- locationTypeId: optional
*/

import java.io.Serializable;

public class LocationType implements Serializable {

    public String locationDescription;

    public String locationTypeEnum;

    public Byte locationTypeId;

}
