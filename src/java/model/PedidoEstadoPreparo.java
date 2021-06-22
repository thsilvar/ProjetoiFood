/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PedidoEstado;
import model.Pedido;

/**
 *
 * @author Dell
 */
public class PedidoEstadoPreparo implements PedidoEstado {
    public boolean receber(Pedido pedido) {
        return false;
    }

    public boolean preparar(Pedido pedido) {
        return false;
    }

    public boolean enviar(Pedido pedido) {
        try {
            pedido.setPedidoEstado(new PedidoEstadoEnviado());
            pedido.alterarEstado();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean entregar(Pedido pedido) {
        return false;
    }

    public boolean cancelar(Pedido pedido) {
        try {
            pedido.setPedidoEstado(new PedidoEstadoCancelado());
            pedido.alterarEstado();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public String getNome() {
        return "Preparo";
    }

    
   }
 

