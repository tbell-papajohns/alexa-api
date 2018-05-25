package com.pji.alexa.model.v2;

/**

PapaJohns
- allowEmailFlag: required
- allowSmsFlag: required
- cellPhone: optional
- rewardsFlag: required
*/

import java.io.Serializable;

public class CustomerCommunicationPreferencesForm implements Serializable {

    public Boolean allowEmailFlag;

    public Boolean allowSmsFlag;

    public String cellPhone;

    public Boolean rewardsFlag;

}
