/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author thiagosilva
 */
public class ManterClienteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);
            }
        }
    }
    
    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            if (!operacao.equals("Incluir")) {
                int codCliente = Integer.parseInt(request.getParameter("codCliente"));
                Cliente cliente = Cliente.obterCliente(codCliente);
                request.setAttribute("cliente", cliente);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterCliente.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String operacao = request.getParameter("operacao");
        int id = Integer.parseInt(request.getParameter("txtCodCliente"));
        String nome = request.getParameter("txtNomeCliente");
        String cpf = request.getParameter("txtCpfCliente");
        String rg = request.getParameter("txtRgCliente");
        String dataNasc = request.getParameter("txtDataNascCliente");
        String email = request.getParameter("txtEmailCliente");
        String telefone = request.getParameter("txtTelefoneCliente");
        String logradouro = request.getParameter("txtLogradouroCliente");
        int numero = Integer.parseInt(request.getParameter("txtNumeroCliente"));
        String complemento = request.getParameter("txtComplementoCliente");
        String bairro = request.getParameter("txtBairroCliente");
        String cidade = request.getParameter("txtCidadeCliente");
        String uf = request.getParameter("txtUfCliente");
        String cep = request.getParameter("txtCepCliente");

        try {
            Cliente cliente = new Cliente();
         cliente.setId(id)
                        .setNome(nome)
                        .setCpf(cpf)
                        .setRg(rg)
                        .setDataNasc(dataNasc)
                        .setEmail(email)
                        .setTelefone(telefone)
                        .setLogradouro(logradouro)
                        .setNumero(numero)
                        .setComplemento(complemento)
                        .setBairro(bairro)
                        .setCidade(cidade)
                        .setUf(uf)
                        .setCep(cep);

            if (operacao.equals("Incluir")) {
                cliente.gravar();
            } else {
                if (operacao.equals("Editar")) {
                    cliente.alterar();
                } else {
                    if (operacao.equals("Excluir")) {
                        cliente.excluir();
                    }
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaClienteController");
            view.forward(request, response);
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        } catch (ServletException e) {
            throw e;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
