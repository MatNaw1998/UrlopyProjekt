package edu.ib.JSP20201JDBC;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


//TODO
// dodanie requesta o urlop zmniejsza pule urlopu
//dodanie requesta o usuniecie nie zwieksza puli urlopu, dopoki admin nie zatwierdzi usuniecia
// nie mozna zmodyfikowac zawierdzonego urlopu
// for fun ogranac jak wyeksportowac urlopy na csv


public class Urlopy {

    private int id;
    private String imieNazwisko ;
    private String od;
    private String doU;
    private int  iloscDni ;
    private String statusU;


    public Urlopy(int id, String imieNazwisko, String od, String doU, int iloscDni, String statusU) {
        this.id = id;
        this.imieNazwisko = imieNazwisko;
        this.od = od;
        this.doU = doU;
        this.iloscDni = iloscDni;
        this.statusU = statusU;
    }

    public Urlopy(String imieNazwisko, String od, String doU, int iloscDni, String statusU) {
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


    public int getIloscDni() {
        return iloscDni;
    }

    public void setIloscDni(int iloscDni) {
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
