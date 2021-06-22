package model;

import java.sql.SQLException;
import java.util.List;
import persistence.ProdutoDAO;



public class Produto {

    private int id;
    protected String nome;
    private String preco;
    
    
    

    public Produto() {
    }

    public int getId() {
        return id;
    }

    public Produto setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getPreco() {
        return preco;
    }

    public Produto setPreco(String preco) {
        this.preco = preco;
        return this;
    }
    
    public static List<Produto> obterProdutos() throws ClassNotFoundException, SQLException {
        return ProdutoDAO.getInstance().obterProdutos();
    }
    
     public static Produto obterProduto(int id) throws ClassNotFoundException, SQLException {
        return ProdutoDAO.getInstance().obterProduto(id);
    }
     
     public void gravar() throws SQLException, ClassNotFoundException {
        ProdutoDAO.getInstance().gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        ProdutoDAO.getInstance().alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        ProdutoDAO.getInstance().excluir(this);
    }

}
