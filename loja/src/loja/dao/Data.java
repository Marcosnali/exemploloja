/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.dao;

import java.sql.*;
import java.util.List;

/**
 *
 * @author dirceu
 */
public class Data {

    public static Connection openConnection() throws Exception {
        return openConnectionPostgre("localhost", "exemplo", "exemplo", "exemplo");
    }

    public static Connection openConnectionPostgre(String server, String database, String user, String password) throws Exception {
        Connection conn = null;
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://" + server
                + ":5432/" + database, user, password);
        return conn;
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection : " + e.getMessage());
            }
        }
    }

    public static ResultSet executeQuery(Connection conn, String query) throws SQLException {
        Statement sta = conn.createStatement();
        ResultSet rs = null;
        try {
            rs = sta.executeQuery(query);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return rs;
    }

    public static int executeUpdate(Connection conn, String query, Object[] parametros) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        for (int i = 1; i <= parametros.length; i++) {
            pstmt.setObject(i, retiraInject(parametros[i - 1]));
        }

        return pstmt.executeUpdate();
    }

    public static int executeUpdate(Connection conn, String query, Object p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setObject(1, retiraInject(p));

        return pstmt.executeUpdate();
    }

    public static int executeUpdate(Connection conn, String query, List<Object> p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        int i = 1;
        for (Object o : p) {
            pstmt.setObject(i++, retiraInject(o));
        }

        return pstmt.executeUpdate();
    }

    public static int executeUpdate(Connection conn, String query) throws SQLException {
        Statement stm = conn.createStatement();
        return stm.executeUpdate(query);
    }

    public static ResultSet executeQuery(Connection conn, String query, Object[] parametros) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        for (int i = 1; i <= parametros.length; i++) {
            pstmt.setObject(i, retiraInject(parametros[i - 1]));
        }

        return pstmt.executeQuery();
    }

    public static ResultSet executeQuery(Connection conn, String query, Object p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setObject(1, retiraInject(p));

        return pstmt.executeQuery();
    }

    public static ResultSet executeQuery(Connection conn, String query, List<Object> p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        int i = 1;
        for (Object o : p) {
            pstmt.setObject(i++, retiraInject(o));
        }

        return pstmt.executeQuery();
    }

    public static Object retiraInject(Object o) {
        if (o != null && o.getClass().getCanonicalName().contains("String")) {
            String s = (String) o;
            o = s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        }
        return o;
    }

    public static void closeResultSet(ResultSet rs) throws Exception {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new Exception("Error closing ResultSet : " + e.getMessage());
            }
        }
    }
}
