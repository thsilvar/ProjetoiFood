/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import persistence.ClienteDAO;
import persistence.PedidoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PedidoEstado;
import java.util.Iterator;

public class Pedido extends Observable {

    private Integer numero;
    private Cliente cliente;
    private Date dataPedido;
    private Float valorTotal;
    private ArrayList<ItemPedido> itensPedido;
    private PedidoEstado pedidoEstado;
    private Cupom promocao;

    private Integer codCliente = 0;

    public Pedido() {
        this.itensPedido = new ArrayList<ItemPedido>();
        this.pedidoEstado = new PedidoEstadoRecebido();
    }

    public int desconto() {
        return promocao.obterDesconto();
    }

    public String getPromocao() {
        return promocao.obterCupom();
    }

    public void adicionarCliente(Cliente cliente) {
        this.addObserver(cliente);
    }

    public Integer getNumero() {
        return numero;
    }

    public Pedido setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public Cliente getCliente() {
        if ((this.codCliente != 0) && (this.cliente == null)) {
            try {
                this.cliente = ClienteDAO.getInstance().obterCliente(this.codCliente);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.cliente;
    }

    public Pedido setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public Pedido setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
        return this;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public Pedido setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public Pedido setItensPedido(ArrayList<ItemPedido> itensVenda) {
        this.itensPedido = itensPedido;
        return this;
    }

    public void adicionarItem(ItemPedido item) {
        this.itensPedido.add(item);
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public Pedido setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
        return this;
    }

    public PedidoEstado getPedidoEstado() {
        return pedidoEstado;
    }

    public Pedido setPedidoEstado(PedidoEstado pedidoEstado) {
        this.pedidoEstado = pedidoEstado;
        return this;
    }

    public String getNomeEstado() {
        return this.pedidoEstado.getNome();
    }

    public static Pedido obterPedido(int numero)
            throws ClassNotFoundException, SQLException {
        return PedidoDAO.getInstance().obterPedido(numero);
    }

    public static List<Pedido> obterPedidos()
            throws ClassNotFoundException, SQLException {
        return PedidoDAO.getInstance().obterPedidos();
    }

    public boolean receber() {
        return this.pedidoEstado.receber(this);
    }

    public boolean preparar() {
        return this.pedidoEstado.preparar(this);
    }

    public boolean enviar() {
        return this.pedidoEstado.enviar(this);
    }

    public boolean entregar() {
        return this.pedidoEstado.entregar(this);
    }

    public boolean cancelar() {
        return this.pedidoEstado.cancelar(this);
    }

    public int gravar() throws ClassNotFoundException, SQLException {
        return PedidoDAO.getInstance().gravar(this);
    }

    public void alterarEstado() throws SQLException, ClassNotFoundException {
        PedidoDAO.getInstance().alterarEstado(this);
        setChanged();
        notifyObservers();
    }

}
