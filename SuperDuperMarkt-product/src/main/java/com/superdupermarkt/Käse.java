package com.superdupermarkt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Käse extends Product {

    public Käse(String Bezeichnung, int Qualität, LocalDate Verfallsdatum, double Preis) {
        super(Bezeichnung, Qualität, Verfallsdatum, Preis);
    }

    // compute quality based on days
    public int getTagesQualität(LocalDate Abfragetag) {
        LocalDate Stichtag = LocalDate.now();
        long timeDifference = ChronoUnit.DAYS.between(Stichtag, Abfragetag);
        return super.getQualität() - (int) timeDifference;
    }

    // Update the 'raus' property based on quality and date
    public boolean isProductRaus(LocalDate Abfragetag) {
        boolean qualityCondition = getTagesQualität(Abfragetag) < 30;
        return (super.isProductRaus(Abfragetag) || qualityCondition);
    }
}
