package edu.ib.JSP20201JDBC;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class Urlopy {


    private String imieNazwisko ;
    private LocalDate od;
    private LocalDate doU;
    private Long   iloscDni ;
    private String  status;


    public Urlopy(String imieNazwisko, LocalDate od, LocalDate doU, Long iloscDni, String status) {
        this.imieNazwisko = imieNazwisko;
        this.od = od;
        this.doU = doU;
        this.iloscDni = iloscDni;
        this.status = status;
    }

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public LocalDate getOd() {
        return od;
    }

    public void setOd(LocalDate od) {
        this.od = od;
    }

    public LocalDate getDoU() {
        return doU;
    }

    public void setDoU(LocalDate doU) {
        this.doU = doU;
    }

    public Long getIloscDni() {
        return iloscDni;
    }

    public void setIloscDni(Long iloscDni) {
        this.iloscDni = iloscDni;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long iloscDniOblicz(LocalDate od, LocalDate doU){
        return DAYS.between(od,doU);
    }

}
