package edu.ib.JSP20201JDBC;

public class Login {

    private String login;
    private String haslo;
    private String imieNazwisko;

    public Login( String login, String haslo, String imieNazwisko) {

        this.login = login;
        this.haslo = haslo;
        this.imieNazwisko = imieNazwisko;
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

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }
}
