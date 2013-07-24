/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import loja.to.TOBase;
import loja.to.TOUser;
import org.json.JSONArray;

/**
 *
 * @author dirceubelem
 */
public class DAOUser extends DAOBase {

    @Override
    public void insert(Connection c, TOBase t) throws Exception {

        String sql = " insert into sysuser (id, username, email, password) values (?, ?, ?, ?) ";

        TOUser to = (TOUser) t;

        List<Object> p = new ArrayList<Object>();

        p.add(to.getId());
        p.add(to.getUserName());
        p.add(to.getEmail());
        p.add(to.getPassword());

        Data.executeUpdate(c, sql, p);

    }

    @Override
    public void update(Connection c, TOBase t) throws Exception {
        String sql = " update sysuser set username = ?, email = ?, password = ? where id = ? ";

        TOUser to = (TOUser) t;

        List<Object> p = new ArrayList<Object>();

        p.add(to.getUserName());
        p.add(to.getEmail());
        p.add(to.getPassword());
        p.add(to.getId());

        Data.executeUpdate(c, sql, p);
    }

    @Override
    public void delete(Connection c, TOBase t) throws Exception {
        String sql = " delete from sysuser where id = ? ";

        TOUser to = (TOUser) t;

        List<Object> p = new ArrayList<Object>();
        p.add(to.getId());

        Data.executeUpdate(c, sql, p);
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = " select id, username, email, password from sysuser where id = ? ";

        ResultSet rs = null;

        try {
            TOUser to = (TOUser) t;

            rs = Data.executeQuery(c, sql, to.getId());

            if (rs.next()) {
                return new TOUser(rs);
            } else {
                return null;
            }

        } finally {
            rs.close();
        }
    }

    @Override
    public JSONArray list(Connection c) throws Exception{
        JSONArray ja = new JSONArray();
        
        String sql = " select id, username, email, password from sysuser order by username ";

        ResultSet rs = null;

        try {
            rs = Data.executeQuery(c, sql);

            while (rs.next()) {
                
                TOUser t = new TOUser(rs);
                ja.put(t.getJson());
                
            }
        } finally {
            rs.close();
        }
        
        return ja;
    }
}
