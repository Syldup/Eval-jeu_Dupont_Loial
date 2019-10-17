package model;

import bo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBean {

    public static User getUser(ResultSet rs) {
        int id;
        try { id = rs.getInt("idUser"); }
        catch (SQLException e) { id = 0; }

        String username;
        try { username = rs.getString("username"); }
        catch (SQLException e) { username = ""; }

        int idbestpartie;
        try { idbestpartie = rs.getInt("idbestpartie"); }
        catch (SQLException e) { idbestpartie = 0; }
        return new User(id, username, "");
    }
}
