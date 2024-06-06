/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.programacaodesktop.pdvdesktop.DAO;

import br.unipar.programacaodesktop.pdvdesktop.Coneccao.ConexaoBd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author felip
 */
public abstract class GenericDao<Objeto> {
    public Connection conn = null;
    
    public GenericDao(){
        conn = ConexaoBd.getConnection();
    }
    protected abstract Objeto ConstruirObjeto(ResultSet rs);
    
    public abstract boolean Salvar(Objeto obj);
    
    public abstract boolean Atualizar(Objeto obj);
    
    public ArrayList<Objeto> retornaLista(String sql){
        ArrayList<Objeto> lista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
            try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                lista.add(ConstruirObjeto(rs));
            }
             ps.close();
            } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        return lista;
    }
    
    public Objeto retornaPeloId(int id, String tabela, String chavePrimaria){
        PreparedStatement ps;
        ResultSet rs;
        Objeto obj = null;
        try{
            ps = conn.prepareStatement("SELECT * FROM public.\""+tabela+"\" where \""+chavePrimaria+ "\" = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                obj = ConstruirObjeto(rs);
            }
            ps.close();
        }catch(SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public void Delete(int id, String tabela, String chavePrimaria){
        PreparedStatement ps;
         try{
            ps = conn.prepareStatement("DELETE FROM public.\""+tabela+"\" WHERE \""+chavePrimaria+ "\" = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
