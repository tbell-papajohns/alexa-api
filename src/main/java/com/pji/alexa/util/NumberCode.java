package com.pji.alexa.util;

import java.util.HashMap;
import java.util.Map;

public enum NumberCode {

    ZERO(0, "ZERO"),
    ONE(1, "ONE"),
	TWO(2, "TWO"),
	THREE(3, "THREE"),
	FOUR(4, "FOUR"),
	FIVE(5, "FIVE"),
	SIX(6, "SIX"),
	SEVEN(7, "SEVEN"),
	EIGHT(8, "EIGHT"),
	NINE(9, "NINE");

    private final Integer value;
    private final String text;

    /**
     * A mapping between the integer code and its corresponding text to facilitate lookup by code.
     */
    private static Map<Integer, NumberCode> valueToTextMapping;

    private NumberCode(Integer value, String text){
        this.value = value;
        this.text = text;
    }

    public static NumberCode getText(Integer i){
        if(valueToTextMapping == null){
            initMapping();
        }
        return valueToTextMapping.get(i);
    }

    private static void initMapping(){
        valueToTextMapping = new HashMap<>();
        for(NumberCode s : values()){
            valueToTextMapping.put(s.value, s);
        }
    }

    public Integer getValue(){
        return value;
    }

    public String getText(){
        return text;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("NumberCode");
        sb.append("{value=").append(value);
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
