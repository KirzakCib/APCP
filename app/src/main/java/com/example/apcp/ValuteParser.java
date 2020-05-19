package com.example.apcp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="ValCurs", strict = false)
public class ValuteParser {

    @ElementList(name="Record", inline=true)
    private List<ValPars> valutePars;


    public List<ValPars> getValutePars() {
        return valutePars;
    }

    public void setValutePars(List<ValPars> valutePars) {
        this.valutePars = valutePars;
    }
}