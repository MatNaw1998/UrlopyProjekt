package edu.ib.JSP20201JDBC;

public class DaneLogowania {
    private String id_uzytkownika, email, haslo;

    public DaneLogowania(String email, String haslo) {
        this.email = email;
        this.haslo = haslo;
    }

    public String getId_uzytkownika() {
        return id_uzytkownika;
    }

    public void setId_uzytkownika(String id_uzytkownika) {
        this.id_uzytkownika = id_uzytkownika;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public DaneLogowania(String id_uzytkownika, String email, String haslo) {
        this.id_uzytkownika = id_uzytkownika;
        this.email = email;
        this.haslo = haslo;
    }
}
