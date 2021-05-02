package edu.ib.JSP20201JDBC.db;


/*tutaj mamy haselka do bazy uzytkownikow dnaych */
public class Passwords {
    private final String EMPLOYEEPASS = "root";
    private final String ADMINPASS = "root";
    private final String EMPLOYEELOGIN = "root";
    private final String ADMINLOGIN = "root";

    public String getEMPLOYEEPASS() {
        return EMPLOYEEPASS;
    }

    public String getADMINPASS() {
        return ADMINPASS;
    }

    public String getEMPLOYEELOGIN() {
        return EMPLOYEELOGIN;
    }

    public String getADMINLOGIN() {
        return ADMINLOGIN;
    }
}
