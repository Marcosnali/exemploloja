/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.bo;

import java.sql.Connection;
import loja.dao.DAOBase;
import loja.dao.Data;
import loja.to.TOBase;
import org.json.JSONArray;

/**
 *
 * @author dirceubelem
 */
public class BOFactory {

    public static void insert(DAOBase d, TOBase t) throws Exception {
        Connection c = null;
        try {
            c = Data.openConnection();

            d.insert(c, t);

        } finally {
            c.close();
        }
    }

    public static void update(DAOBase d, TOBase t) throws Exception {
        Connection c = null;
        try {
            c = Data.openConnection();

            d.update(c, t);

        } finally {
            c.close();
        }
    }

    public static void delete(DAOBase d, TOBase t) throws Exception {
        Connection c = null;
        try {
            c = Data.openConnection();

            d.delete(c, t);

        } finally {
            c.close();
        }
    }

    public static TOBase get(DAOBase d, TOBase t) throws Exception {
        Connection c = null;
        try {
            c = Data.openConnection();

            return d.get(c, t);

        } finally {
            c.close();
        }
    }

    public static JSONArray list(DAOBase d) throws Exception {
        Connection c = null;
        try {
            c = Data.openConnection();

            return d.list(c);

        } finally {
            c.close();
        }
    }
}
