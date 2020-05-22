package com.example.apcp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Record", strict=false)
public class ValPars {

        @Attribute(name="Date")
        private String date;

        @Element(name = "Value")
        private String value;

        @Element(name = "Nominal")
        private String nominal;

        public String getNominal(){return nominal;}

        public void setNominal(String nominal) {
            this.nominal = nominal;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

}
