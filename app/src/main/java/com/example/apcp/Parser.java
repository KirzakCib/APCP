package com.example.apcp;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import org.simpleframework.xml.Root;

@Root(name="ValCurs", strict = false)
public class Parser {

    @Attribute(name = "Date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ElementList(name="Valute", inline=true)
    private List<ValuteCBR> valuteCBRList;


    public List<ValuteCBR> getValuteCBRList() {
        return valuteCBRList;
    }

    public void setValuteCBRList(List<ValuteCBR> valuteCBRList) {
        this.valuteCBRList = valuteCBRList;
    }
}