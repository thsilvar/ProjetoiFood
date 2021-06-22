<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <title>iFood</title>
    </head>
    <body>
<nav class="navbar navbar-default">
            <div class="container">

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" 
                            data-toggle="collapse" data-target="#barra-navegacao">
                        <span class="sr-only">Alternar Menu</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#" class="navbar-brand">Pedidos</a>
                </div>

                <div class="collapse navbar-collapse" id="barra-navegacao">

                    <ul class="nav navbar-nav navbar-right">
                        <li> <a href="#">Home</a> </li>

                        <li class="dropdown"> 
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Cadastros <span class="caret"></span>
                            </a> 
                            <ul class="dropdown-menu">
                                <li ><a href="PesquisaClienteController">Cliente</a></li>
                                <li > <a href="PesquisaProdutoController">Produto</a></li>
                            </ul>
                        </li>

                        <li class="dropdown"> 
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Processos <span class="caret"></span>
                            </a> 
                            <ul class="dropdown-menu">
                                <<li> <a href="EfetuarPedidoController?acao=preparar">Efetuar Pedido</a> </li>
                                <li> <a href="GerenciarPedidosController?acao=listar">Gerenciar Pedidos</a> </li>
                            </ul>

                        </li>


                    </ul>

                </div>

            </div>
        </nav>
        <div class="container">

            <div class="page-header">
                <h1>Manter Cliente - ${operacao}</h1>
            </div>

            <div class="row">
                <div class="col-sm-8">

                    <form action="ManterClienteController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterCliente">

                        <div class="form-group">
                            <label for="txtCodCliente">Código</label>
                            <input type="number" class="form-control" id="txtCodCliente" name="txtCodCliente" value="${cliente.id}" <c:if test="${operacao != 'Incluir'}">readonly</c:if> required>
                        </div>

                        <div class="form-group">
                            <label for="txtNomeCliente">Nome</label>
                            <input type="text" class="form-control" id="txtNomeCliente" name="txtNomeCliente" value="${cliente.nome}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>

                        <div class="form-group">
                            <label for="txtCpfCliente">CPF</label>
                            <input type="text" class="form-control" id="txtCpfCliente" name="txtCpfCliente" value="${cliente.cpf}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>
                        <div class="form-group">
                            <label for="txtRgCliente">RG</label>
                            <input type="text" class="form-control" id="txtRgCliente" name="txtRgCliente" value="${cliente.rg}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required>
                        </div>

                        <div class="form-group">
                            <label for="txtDataNascCliente">Data de Nascimento</label>
                            <input type="text" class="form-control" id="txtDataNascCliente" name="txtDataNascCliente" value="${cliente.dataNasc}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>

                        <div class="form-group">
                            <label for="txtEmailCliente">Email</label>
                            <input type="email" class="form-control" id="txtEmailCliente" name="txtEmailCliente" value="${cliente.email}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>
                        <div class="form-group">
                            <label for="txtTelefoneCliente">Telefone</label>
                            <input type="text" class="form-control" id="txtTelefoneCliente" name="txtTelefoneCliente" value="${cliente.telefone}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required>
                        </div>

                        <div class="form-group">
                            <label for="txtLogradouroCliente">Logradouro</label>
                            <input type="text" class="form-control" id="txtLogradouroCliente" name="txtLogradouroCliente" value="${cliente.logradouro}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>

                        <div class="form-group">
                            <label for="txtNumeroCliente">Número</label>
                            <input type="number" class="form-control" id="txtNumeroCliente" name="txtNumeroCliente" value="${cliente.numero}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>
                         <div class="form-group">
                            <label for="txtComplementoCliente">Complemento</label>
                            <input type="text" class="form-control" id="txtComplementoCliente" name="txtComplementoCliente" value="${cliente.complemento}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required>
                        </div>

                        <div class="form-group">
                            <label for="txtBairroCliente">Bairro</label>
                            <input type="text" class="form-control" id="txtBairroCliente" name="txtBairroCliente" value="${cliente.bairro}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>

                        <div class="form-group">
                            <label for="txtCidadeCliente">Cidade</label>
                            <input type="text" class="form-control" id="txtCidadeCliente" name="txtCidadeCliente" value="${cliente.cidade}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>
                        <div class="form-group">
                            <label for="txtUfCliente">UF</label>
                            <input type="text" class="form-control" id="txtUfCliente" name="txtUfCliente" value="${cliente.uf}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>

                        <div class="form-group">
                            <label for="txtCepCliente">CEP</label>
                            <input type="text" class="form-control" id="txtCepCliente" name="txtCepCliente" value="${cliente.cep}" <c:if test="${operacao == 'Excluir'}">readonly</c:if> required> 
                        </div>
                        <button type="submit" class="btn btn-success">Confirmar</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
