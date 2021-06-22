package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;


public class ClienteDAO {

    private static ClienteDAO instance = new ClienteDAO();

    private ClienteDAO() {
    }

    public static ClienteDAO getInstance() {
        return instance;
    }

    public void gravar(Cliente cliente) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("insert into cliente (id, nome, cpf, rg, dataNasc, email, telefone, logradouro, numero, complemento, bairro, cidade, uf, cep)"
                    + "values ('" + cliente.getId() + "', '" + cliente.getNome() + "', '" + cliente.getCpf() + "', '" + cliente.getRg() + "', '" + cliente.getDataNasc() + "', '" + cliente.getEmail() + "', '" + cliente.getTelefone() + "', '" + cliente.getLogradouro() + "', '" + cliente.getNumero() + "', '" + cliente.getComplemento() + "', '" + cliente.getBairro() + "', '" + cliente.getCidade() + "','" + cliente.getUf() + "', '" + cliente.getCep() + "')");
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public void alterar(Cliente cliente) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("update cliente set nome = '" + cliente.getNome() + "', cpf = '" + cliente.getCpf() + "', rg = '" + cliente.getRg() + "', dataNasc = '" + cliente.getDataNasc() + "', email = '" + cliente.getEmail() + "', telefone = '" + cliente.getTelefone() + "', logradouro = '" + cliente.getLogradouro() + "', numero = '" + cliente.getNumero() + "', complemento = '" + cliente.getComplemento() + "', bairro = '" + cliente.getBairro() + "', cidade = '" + cliente.getCidade() + "', uf = '" + cliente.getUf() + "', cep = '" + cliente.getCep() + "' where id = " + cliente.getId());
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public void excluir(Cliente cliente) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        String stringSQL;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            stringSQL = "delete from cliente where id = "
                    + cliente.getId();
            st.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public List<Cliente> obterClientes()
            throws ClassNotFoundException, SQLException {

        Connection conexao = null;
        Statement comando = null;
        List<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = null;
        try {
            conexao = DatabaseLocator.getInstance().getConnection();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from cliente");
            
            
            while (rs.next()) {
                cliente = instanciarCliente(rs);
                clientes.add(cliente);
            }
        } finally {
            closeResources(conexao, comando);
        }
        return clientes;
    }

    public Cliente obterCliente(int id)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Cliente cliente = null;
        try {
            conexao = DatabaseLocator.getInstance().getConnection();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from cliente where id = " + id);
            rs.first();
            cliente = instanciarCliente(rs);
        } finally {
            closeResources(conexao, comando);
        }
        return cliente;
    }

    public Cliente instanciarCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"))
                .setNome(rs.getString("nome"))
                .setCpf(rs.getString("cpf"))
                .setRg(rs.getString("rg"))
                .setDataNasc(rs.getString("dataNasc"))
                .setEmail(rs.getString("email"))
                .setTelefone(rs.getString("telefone"))
                .setLogradouro(rs.getString("logradouro"))
                .setNumero(rs.getInt("numero"))
                .setComplemento(rs.getString("complemento"))
                .setBairro(rs.getString("bairro"))
                .setCidade(rs.getString("cidade"))
                .setUf(rs.getString("uf"))
                .setCep(rs.getString("cep"));
        return cliente;
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
