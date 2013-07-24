/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author dirceubelem
 */
public class TOUser extends TOBase {

    private String id;
    private String userName;
    private String password;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TOUser() {
    }

    public TOUser(ResultSet rs) throws Exception {
        this.id = rs.getString("id");
        this.userName = rs.getString("username");
        this.email = rs.getString("email");
        this.password = rs.getString("password");
    }

    @Override
    public JSONObject getJson() throws Exception {
        JSONObject j = new JSONObject();

        j.put("id", id);
        j.put("username", userName);
        j.put("email", email);

        return j;
    }
}
