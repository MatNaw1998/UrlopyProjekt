package edu.ib.JSP20201JDBC;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class UrlopyDoZatwierdzenia {

    private int id_pracownika;
    private String poczatek, koniec;

    public UrlopyDoZatwierdzenia() {}

    public UrlopyDoZatwierdzenia(int id_pracownika, String poczatek, String koniec) {
        this.id_pracownika = id_pracownika;
        this.poczatek = poczatek;
        this.koniec = koniec;
    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getPoczatek() {
        return poczatek;
    }

    public void setPoczatek(String poczatek) {
        this.poczatek = poczatek;
    }

    public String getKoniec() {
        return koniec;
    }

    public void setKoniec(String koniec) {
        this.koniec = koniec;
    }
}
