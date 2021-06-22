
package model;

import model.Produto;

public class ItemPedido {
    private Produto produto;
    private Integer quantidade;
    private Float precoUnitario;

    public ItemPedido(Produto produto, Integer quantidade, Float precoUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    
    
    public Produto getProduto() {
        return produto;
    }

    public ItemPedido setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public ItemPedido setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public Float getPrecoUnitario() {
        return precoUnitario;
    }

    public ItemPedido setPrecoUnitario(Float precoUnitario) {
        this.precoUnitario = precoUnitario;
        return this;
    }
    
}
