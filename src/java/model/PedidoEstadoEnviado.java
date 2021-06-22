
package model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PedidoEstado;
import model.Pedido;

public class PedidoEstadoEnviado implements PedidoEstado {
  
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
        try {
            pedido.setPedidoEstado(new PedidoEstadoEntregue());
            pedido.alterarEstado();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
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
        return "Enviado";
    }

   
    
}

 
