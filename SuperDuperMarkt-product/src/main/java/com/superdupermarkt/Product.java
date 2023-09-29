package com.superdupermarkt;

import java.time.LocalDate;

public class Product {
    private String Bezeichnung;
    private int Qualität;
    private LocalDate Verfallsdatum;
    private double Preis;

    public Product(String Bezeichnung, int Qualität, LocalDate Verfallsdatum, double Preis) {
        this.Bezeichnung = Bezeichnung;
        this.Qualität = Qualität;
        this.Verfallsdatum = Verfallsdatum;
        this.Preis = Preis;
    }

    public String getBezeichnung() {
        return Bezeichnung;
    }

    public LocalDate getVerfallsdatum() {
        return Verfallsdatum;
    }

    public double getPreis() {
        return Preis;
    }

    public int getQualität() {
        return Qualität;
    }

    public int getTagesQualität(LocalDate Abfragetag) {
        return Qualität;
    }

    public double calculateTagesPreis(LocalDate Abfragetag) {
        return Preis + 0.1 * getTagesQualität(Abfragetag);
    }

    // Determine if the product is "raus" based on date
    public boolean isProductRaus(LocalDate Abfragetag) {
        return Verfallsdatum.equals(Abfragetag) || Abfragetag.isAfter(Verfallsdatum);
    }

    public String getOriginalData() {
        return Bezeichnung + "      --- Preis: Euro " + Preis + "       --- Verfallsdatum: "
                + Verfallsdatum
                + "     --- Qualität: " + Qualität;
    }

    public String getDataForDate(LocalDate Abfragetag) {
        if (!isProductRaus(Abfragetag)) {
            return "(( OK )) ---" + Bezeichnung + "       --- Tagespreis: "
                    + String.format("%.2f", calculateTagesPreis(Abfragetag)) + "       --- Qualität: "
                    + getTagesQualität(Abfragetag);
        } else {
            return "(( RAUS )) ---" + Bezeichnung + "       --- Tagespreis: "
                    + String.format("%.2f", calculateTagesPreis(Abfragetag)) + "       --- Qualität: "
                    + getTagesQualität(Abfragetag);
        }
    }

}