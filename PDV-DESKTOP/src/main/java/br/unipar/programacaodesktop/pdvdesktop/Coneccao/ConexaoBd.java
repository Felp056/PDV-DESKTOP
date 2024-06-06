/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.programacaodesktop.pdvdesktop.Coneccao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author felip
 */
public class ConexaoBd {
     private static final String Driver = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/TrabalhoDesktop";
    private static final String user = "postgres";
    private static final String PassWord = "#Deadp00l";
    
      public static Connection getConnection(){
        try {
            Class.forName(Driver);
            return DriverManager.getConnection(URL, user, PassWord);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoBd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void closeTransaction(Connection conn, PreparedStatement state){
        if(state != null){
            try {
                state.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoBd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        closeConnection(conn);
    }
}
