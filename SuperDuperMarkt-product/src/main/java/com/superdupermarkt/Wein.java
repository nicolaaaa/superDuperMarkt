package com.superdupermarkt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Wein extends Product {

    public Wein(String Bezeichnung, int Qualität, LocalDate Verfallsdatum, double Preis) {
        super(Bezeichnung, Qualität, Verfallsdatum, Preis);
    }

    // compute quality based on days / not higher than 50
    public int getTagesQualität(LocalDate Abfragetag) {
        int Qualität = super.getQualität();
        LocalDate Stichtag = LocalDate.now();
        long timeDifference = ChronoUnit.DAYS.between(Stichtag, Abfragetag);
        Qualität = Qualität + ((int) timeDifference / 10);
        if (Qualität > 50)
            Qualität = 50;
        return Qualität;
    }

    // Update the 'raus' property -> never
    public boolean isProductRaus(LocalDate Abfragetag) {
        return false;
    }

    // Preis not changing from Tagespreis (Stichtag)
    public double calculateTagesPreis(LocalDate Abfragetag) {
        LocalDate Stichtag = LocalDate.now();
        return getPreis() * 0.1 * getTagesQualität(Stichtag);
    }

}