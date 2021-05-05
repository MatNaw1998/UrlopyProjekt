package edu.ib.JSP20201JDBC;


/**
 * @author Gabriela Wrona i Mateusz Nawrocki
 */

public class PracownikInfo {
    private int id;
    private String email;
    private String latapracy;
    private String mcepracy;
    private String dateZ;
    private String wyksztalcenie;
    private int iloscDni;

    /**
     *Konstruktor z danymi o pracowniku
     */
    public PracownikInfo(int id, String email, String latapracy, String mcepracy, String dateZ, String wyksztalcenie, int iloscDni) {
        this.id = id;
        this.email = email;
        this.latapracy = latapracy;
        this.mcepracy = mcepracy;
        this.dateZ = dateZ;
        this.wyksztalcenie = wyksztalcenie;
        this.iloscDni = iloscDni;
    }
    /**
     *Konstruktor z danymi o pracowniku, bez id
     */
    public PracownikInfo(String email, String latapracy, String mcepracy, String dateZ, String wyksztalcenie, int iloscDni) {
        this.email = email;
        this.latapracy = latapracy;
        this.mcepracy = mcepracy;
        this.dateZ = dateZ;
        this.wyksztalcenie = wyksztalcenie;
        this.iloscDni = iloscDni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatapracy() {
        return latapracy;
    }

    public void setLatapracy(String latapracy) {
        this.latapracy = latapracy;
    }

    public String getMcepracy() {
        return mcepracy;
    }

    public void setMcepracy(String mcepracy) {
        this.mcepracy = mcepracy;
    }

    public String getDateZ() {
        return dateZ;
    }

    public void setDateZ(String dateZ) {
        this.dateZ = dateZ;
    }

    public String getWyksztalcenie() {
        return wyksztalcenie;
    }

    public void setWyksztalcenie(String wyksztalcenie) {
        this.wyksztalcenie = wyksztalcenie;
    }

    public int getIloscDni() {
        return iloscDni;
    }

    public void setIloscDni(int iloscDni) {
        this.iloscDni = iloscDni;
    }
}
