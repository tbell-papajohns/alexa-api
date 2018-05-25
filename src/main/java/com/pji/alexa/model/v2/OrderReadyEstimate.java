package com.pji.alexa.model.v2;

/**

PapaJohns
- estimatedOrderReadyMinutesHigh: optional
- estimatedOrderReadyMinutesLow: optional
- estimatedOrderReadyTimeHigh: optional
- estimatedOrderReadyTimeLow: optional
*/

import java.io.Serializable;

public class OrderReadyEstimate implements Serializable {

    public Double estimatedOrderReadyMinutesHigh;

    public Double estimatedOrderReadyMinutesLow;

    public String estimatedOrderReadyTimeHigh;

    public String estimatedOrderReadyTimeLow;

}
