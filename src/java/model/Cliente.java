package model;

import java.sql.SQLException;
import java.util.List;
import persistence.ClienteDAO;
import java.util.Observer;
import java.util.Observable;
 import util.Email;


public class Cliente implements Observer {

    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private String dataNasc;
    private String email;
    private String telefone;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private Observable pedido;
  
    
    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public Cliente setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Cliente setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getRg() {
        return rg;
    }

    public Cliente setRg(String rg) {
        this.rg = rg;
        return this;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public Cliente setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Cliente setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Cliente setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente setNumero(int numero) {
        this.numero = numero;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Cliente setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Cliente setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Cliente setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public Cliente setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Cliente setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public Observable getPedido() {
        return pedido;
    }

    public Cliente setPedido(Observable pedido) {
        this.pedido = pedido;
        return this;
    }

    public Cliente(Observable pedido) {
        this.pedido = pedido;
        pedido.addObserver(this);
    }

    

    public static List<Cliente> obterClientes() throws ClassNotFoundException, SQLException {
        
        return ClienteDAO.getInstance().obterClientes();
    }

    public static Cliente obterCliente(int id) throws ClassNotFoundException, SQLException {
        return ClienteDAO.getInstance().obterCliente(id);
    }
    
    public void gravar() throws SQLException, ClassNotFoundException {
        ClienteDAO.getInstance().gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        ClienteDAO.getInstance().alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        ClienteDAO.getInstance().excluir(this);
    }
    
    public void update(Observable pedidoObservado, Object arg1) {
        if (pedidoObservado instanceof Pedido) {
            Pedido pedido = (Pedido)pedidoObservado;
            Email.enviarEmail(
                   "Estado alterado de seu pedido!", 
                   "Olá " + pedido.getCliente().getNome() + "! Seu pedido " + pedido.getNumero() + " mudou para o estado " + pedido.getNomeEstado() + ". Agradecemos sua preferência!", 
                   pedido.getCliente().getEmail());
            System.out.println("Olá " + pedido.getCliente().getNome() + "! Seu pedido " + pedido.getNumero() + " mudou para o estado " + pedido.getNomeEstado() + ". Agradecemos sua preferência!");
        }
    }
}
