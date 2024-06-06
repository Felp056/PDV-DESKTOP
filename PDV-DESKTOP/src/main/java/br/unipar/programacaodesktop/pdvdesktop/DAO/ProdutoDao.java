package br.unipar.programacaodesktop.pdvdesktop.DAO;

import br.unipar.programacaodesktop.pdvdesktop.Model.Produto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ProdutoDao extends GenericDao<Produto> {

    @Override
    protected Produto ConstruirObjeto(ResultSet rs) {
        Produto produto = new Produto();
        try {
            produto.setId(rs.getInt("id"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setValor(rs.getDouble("valor"));
            produto.setCategoria(rs.getString("categoria"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produto;
    }

    @Override
    public boolean Salvar(Produto produto) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO produto (descricao, valor, categoria) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getValor());
            ps.setString(3, produto.getCategoria());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean Atualizar(Produto produto) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE produto SET descricao = ?, valor = ?, categoria = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getValor());
            ps.setString(3, produto.getCategoria());
            ps.setInt(4, produto.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public boolean Delete(Produto produto) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM produto WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, produto.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
