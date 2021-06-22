/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import model.PedidoEstado;
import model.Pedido;
/**
 *
 * @author thiagosilva
 */
public class PedidoEstadoCancelado implements PedidoEstado {

    public boolean receber(Pedido pedido) {
        return false;
    }

    public boolean preparar(Pedido pedido) {
        return false;
    }

    public boolean enviar(Pedido pedido) {
        return false;
    }

    public boolean entregar(Pedido pedido) {
        return false;
    }

    public boolean cancelar(Pedido pedido) {
        return false;
    }
    
    public String getNome() {
        return "Cancelado";
    }

  

   
}