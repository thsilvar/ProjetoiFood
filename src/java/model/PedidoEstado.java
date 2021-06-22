
package model;

import model.Pedido;

public interface PedidoEstado {
    
     public boolean receber(Pedido pedido);
    public boolean preparar(Pedido pedido);
    public boolean enviar(Pedido pedido);
    public boolean entregar(Pedido pedido);
    public boolean cancelar(Pedido pedido);
    public String getNome();

    
    
}

