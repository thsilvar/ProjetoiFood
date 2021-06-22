
package model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PedidoEstado;
import model.Pedido;

public class PedidoEstadoRecebido implements PedidoEstado {

    public boolean receber(Pedido pedido) {
        return false;
    }

    public boolean preparar(Pedido pedido) {
        try {
            pedido.setPedidoEstado(new PedidoEstadoPreparo());
            pedido.alterarEstado();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean enviar(Pedido pedido) {
        return false;
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
        return "Recebido";
    }

    

}
