package br.unipar.programacaodesktop.pdvdesktop.DAO;

import br.unipar.programacaodesktop.pdvdesktop.Model.Item_Venda;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ItemVendaDao extends GenericDao<Item_Venda> {

    @Override
    protected Item_Venda ConstruirObjeto(ResultSet rs) {
        Item_Venda itemVenda = new Item_Venda();
        try {
            itemVenda.setId(rs.getInt("id"));
            itemVenda.setQuantidade(rs.getInt("quantidade"));
            itemVenda.setValor_unitario(rs.getDouble("valor_unitario"));
            itemVenda.setValor_total(rs.getDouble("valor_total"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return itemVenda;
    }

    @Override
    public boolean Salvar(Item_Venda itemVenda) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO item_venda (quantidade, valor_unitario, valor_total) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, itemVenda.getQuantidade());
            ps.setDouble(2, itemVenda.getValor_unitario());
            ps.setDouble(3, itemVenda.getValor_total());
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
    public boolean Atualizar(Item_Venda itemVenda) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE item_venda SET quantidade = ?, valor_unitario = ?, valor_total = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, itemVenda.getQuantidade());
            ps.setDouble(2, itemVenda.getValor_unitario());
            ps.setDouble(3, itemVenda.getValor_total());
            ps.setInt(4, itemVenda.getId());
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

    public boolean Delete(Item_Venda itemVenda) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM item_venda WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, itemVenda.getId());
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

