/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.PedidoEstado;
import model.PedidoEstadoRecebido;

/**
 *
 * @author Marco /**
 *
 * @author Marco
 *
 *
 */
public class PedidoDAO {

    private static PedidoDAO instance = new PedidoDAO();

    public static PedidoDAO getInstance() {
        return instance;
    }

    public List<Pedido> obterPedidos() throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido pedido = null;
        try {
            conexao = DatabaseLocator.getInstance().getConnection();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from pedido");
            while (rs.next()) {
                pedido = instanciarPedido(rs);
                pedidos.add(pedido);
            }
        } finally {
            closeResources(conexao, comando);
        }
        return pedidos;
    }

    public Pedido obterPedido(int numero) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Pedido pedido = null;
        try {
            conexao = DatabaseLocator.getInstance().getConnection();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from pedido where numero = " + numero);
            rs.first();
            pedido = instanciarPedido(rs);
        } finally {
            closeResources(conexao, comando);
        }
        return pedido;
    }

    public Pedido instanciarPedido(ResultSet rs) throws SQLException {
        Class classe = null;
        Object objeto = null;
        Pedido pedido = null;
        try {
            classe = Class.forName("model.PedidoEstado" + rs.getString("status"));
            objeto = classe.newInstance();
           Cliente cliente = Cliente.obterCliente(rs.getInt("cod_cliente"));
            
            pedido = new Pedido();
            pedido.setNumero(rs.getInt("numero"))
                    .setCodCliente(rs.getInt("cod_cliente"))
                    .setDataPedido(rs.getDate("data_venda"))
                    .setValorTotal(rs.getFloat("valor_total"))
                    .setPedidoEstado((PedidoEstado) objeto);
                    pedido.adicionarCliente(cliente);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        }
        return pedido;
    }

    public int gravar(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        int id = 0;
        try {
            conexao = DatabaseLocator.getInstance().getConnection();
            conexao.setAutoCommit(false);
            comando = conexao.prepareStatement(
                    "insert into pedido (cod_cliente, data_venda, valor_total, status) values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            comando.setInt(1, pedido.getCliente().getId());
            comando.setDate(2, new java.sql.Date(pedido.getDataPedido().getTime()));
            comando.setFloat(3, pedido.getValorTotal());
            comando.setString(4, "Recebido");
            comando.executeUpdate();

            ResultSet rs = comando.getGeneratedKeys();
            id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            int num_item = 0;
            for (ItemPedido item : pedido.getItensPedido()) {
                comando = conexao.prepareStatement(
                        "insert into item_pedido (num_pedido, num_item, cod_produto, quantidade, valor_unitario) values (?,?,?,?,?)");
                num_item = num_item + 1;
                comando.setInt(1, id);
                comando.setInt(2, num_item);
                comando.setInt(3, item.getProduto().getId());
                comando.setFloat(4, item.getQuantidade());
                comando.setFloat(5, item.getPrecoUnitario());
                comando.executeUpdate();
            }
            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
        } finally {
            closeResources(conexao, comando);
        }
        return id;
    }

    public void alterarEstado(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;

        try {
            conexao = DatabaseLocator.getInstance().getConnection();
            comando = conexao.createStatement();
            stringSQL = "update pedido set "
                    + "status = '" + pedido.getPedidoEstado().getNome() + "' "
                    + "where numero = " + pedido.getNumero();
            comando.execute(stringSQL);
        } finally {
            closeResources(conexao, comando);
        }
    }

    public void closeResources(Connection conn, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {

        }
    }
}
