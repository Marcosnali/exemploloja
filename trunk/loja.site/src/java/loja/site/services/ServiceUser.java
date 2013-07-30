/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.site.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import loja.bo.BOFactory;
import loja.dao.DAOUser;
import loja.fw.Criptografia;
import loja.fw.Guid;
import loja.to.TOUser;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author dirceubelem
 */
@Path("user")
public class ServiceUser {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceUser
     */
    public ServiceUser() {
    }

    @GET
    @Path("list")
    public String list() throws Exception {

        JSONObject j = new JSONObject();

        try {

            JSONArray ja = BOFactory.list(new DAOUser());

            j.put("data", ja);
            j.put("success", true);

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();

    }

    @POST
    @Path("insert")
    public String insert(
            @FormParam("username") String userName,
            @FormParam("password") String password,
            @FormParam("email") String email) throws Exception {

        JSONObject j = new JSONObject();

        try {

            TOUser t = new TOUser();
            t.setId(Guid.getString());
            t.setUserName(userName);
            t.setPassword(Criptografia.md5(password));
            t.setEmail(email);

            BOFactory.insert(new DAOUser(), t);

            j.put("id", t.getId());
            j.put("success", true);
        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();

    }

    @POST
    @Path("update")
    public String update(
            @FormParam("id") String id,
            @FormParam("username") String userName,
            @FormParam("password") String password,
            @FormParam("email") String email) throws Exception {

        JSONObject j = new JSONObject();

        try {

            TOUser t = new TOUser();
            t.setId(id);

            t = (TOUser) BOFactory.get(new DAOUser(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Usuário não encontrado");
            } else {
                t.setEmail(email);
                t.setUserName(userName);
                t.setPassword(Criptografia.md5(password));

                BOFactory.update(new DAOUser(), t);
            }

            j.put("success", true);

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();

    }

    @POST
    @Path("delete")
    public String delete(@FormParam("id") String id) throws Exception {
        JSONObject j = new JSONObject();

        try {

            TOUser t = new TOUser();
            t.setId(id);

            t = (TOUser) BOFactory.get(new DAOUser(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Usuário não encontrado");
            } else {
                BOFactory.delete(new DAOUser(), t);
            }

            j.put("success", true);

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }
}
