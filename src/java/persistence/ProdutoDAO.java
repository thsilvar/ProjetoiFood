
package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Produto;


public class ProdutoDAO {
    
    private static ProdutoDAO instance = new ProdutoDAO();

    private ProdutoDAO() {
    }

    public static ProdutoDAO getInstance() {
        return instance;
    }

    public void gravar(Produto produto) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("insert into Produto (id, nome, preco)"
                    + "values ('" + produto.getId() + "', '" + produto.getNome() + "', '" + produto.getPreco() + "')");
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public void alterar(Produto produto) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("update produto set nome = '" + produto.getNome() + "', preco = '" + produto.getPreco() + "' where id = " + produto.getId());
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public void excluir(Produto produto) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        String stringSQL;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            stringSQL = "delete from produto where id = "
                     + produto.getId();
            st.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public List<Produto> obterProdutos()
            throws ClassNotFoundException, SQLException {

        Connection conexao = null;
        Statement comando = null;
        List<Produto> produtos = new ArrayList<Produto>();
        Produto produto = null;
        try {
            conexao = DatabaseLocator.getInstance().getConnection();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from produto");
            
            //for (Iterator i = produto.iterator();i.hasNext();){
        
            while (rs.next()) {
                produto = instanciarProduto(rs);
                produtos.add(produto);
            }
        } finally {
            closeResources(conexao, comando);
        }
        return produtos;
    }

    public Produto obterProduto(int id)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Produto produto = null;
        try {
            conexao = DatabaseLocator.getInstance().getConnection();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from produto where id = " + id);
            rs.first();
            produto = instanciarProduto(rs);
        } finally {
            closeResources(conexao, comando);
        }
        return produto;
    }
    
    public Produto instanciarProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getInt("id"))
                .setNome(rs.getString("nome"))
                .setPreco(rs.getString("preco"));
        return produto;
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
