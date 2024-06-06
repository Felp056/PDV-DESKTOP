package br.unipar.programacaodesktop.pdvdesktop.DAO;

import br.unipar.programacaodesktop.pdvdesktop.Model.Venda;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author adrian
 */

public class VendaDao extends GenericDao<Venda> {

    @Override
    protected Venda ConstruirObjeto(ResultSet rs) {
        Venda venda = new Venda();
        try {
            venda.setId(rs.getInt("id"));
            venda.setObservacoes(rs.getString("observacoes"));
            venda.setData(rs.getDate("data"));
            venda.setTotal(rs.getDouble("total"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return venda;
    }

    @Override
    public boolean Salvar(Venda venda) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO venda (observacoes, data, total) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, venda.getObservacoes());
            ps.setDate(2, new java.sql.Date(venda.getData().getTime()));
            ps.setDouble(3, venda.getTotal());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    venda.setId(generatedKeys.getInt(1));
                }
            }
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
    public boolean Atualizar(Venda venda) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE venda SET observacoes = ?, data = ?, total = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, venda.getObservacoes());
            ps.setDate(2, new java.sql.Date(venda.getData().getTime()));
            ps.setDouble(3, venda.getTotal());
            ps.setInt(4, venda.getId());
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

    public boolean Delete(Venda venda) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM venda WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, venda.getId());
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
