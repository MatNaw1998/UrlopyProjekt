package edu.ib.JSP20201JDBC;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Urlopy {

    private int id;
    private String imieNazwisko ;
    private String od;
    private String doU;
    private Long  iloscDni ;
    private String statusU;


    public Urlopy(int id, String imieNazwisko, String od, String doU, Long iloscDni, String statusU) {
        this.id = id;
        this.imieNazwisko = imieNazwisko;
        this.od = od;
        this.doU = doU;
        this.iloscDni = iloscDni;
        this.statusU = statusU;
    }

    public Urlopy(String imieNazwisko, String od, String doU, Long iloscDni, String statusU) {
        this.imieNazwisko = imieNazwisko;
        this.od = od;
        this.doU = doU;
        this.iloscDni = iloscDni;
        this.statusU = statusU;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public String getDoU() {
        return doU;
    }

    public void setDoU(String doU) {
        this.doU = doU;
    }

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }


    public Long getIloscDni() {
        return iloscDni;
    }

    public void setIloscDni(Long iloscDni) {
        this.iloscDni = iloscDni;
    }

    public String getStatusU() {
        return statusU;
    }

    public void setStatusU(String statusU) {
        this.statusU = statusU;
    }

    public long iloscDniOblicz(LocalDate od, LocalDate doU){
        return DAYS.between(od,doU);
    }

}
