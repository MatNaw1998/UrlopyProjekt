package edu.ib.JSP20201JDBC;

import java.util.List;

public class DBUtilUser extends DBUtil {

    private String URL;
    private String name;
    private String password;

    public DBUtilUser(String URL) {
        this.URL = URL;
    }

    @Override
    List<Urlopy> getUrlopy() throws Exception {
        return null;
    }
}
