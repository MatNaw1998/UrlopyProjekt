package edu.ib.JSP20201JDBC.model;

public class DaneLogowania {
    private String id_uzytkownika, login, haslo;

    public DaneLogowania(String id_uzytkownika, String login, String haslo) {
        this.id_uzytkownika = id_uzytkownika;
        this.login = login;
        this.haslo = haslo;
    }

    public String getId_uzytkownika() {
        return id_uzytkownika;
    }

    public void setId_uzytkownika(String id_uzytkownika) {
        this.id_uzytkownika = id_uzytkownika;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

}
