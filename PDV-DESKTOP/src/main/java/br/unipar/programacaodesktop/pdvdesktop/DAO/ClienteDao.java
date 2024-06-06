package br.unipar.programacaodesktop.pdvdesktop.DAO;

import br.unipar.programacaodesktop.pdvdesktop.Model.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author adrian
 */

public class ClienteDao extends GenericDao<Cliente> {

    @Override
    protected Cliente ConstruirObjeto(ResultSet rs) {
        Cliente cliente = new Cliente();
        try {
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }

    @Override
    public boolean Salvar(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO cliente (nome, telefone, email) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEmail());
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
    public boolean Atualizar(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE cliente SET nome = ?, telefone = ?, email = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEmail());
            ps.setInt(4, cliente.getId());
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

    public boolean Delete(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
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


