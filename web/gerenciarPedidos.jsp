<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>iFood</title>

        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script>
            $(document).ready(function () {

                alterarEstado = function (item, estado) {
                    var tr = $(item).closest('tr');
                    var numero = tr.find('td[data-numero]').data('numero');
                    $.ajax({
                        url: 'GerenciarPedidosController?acao=mudarEstado',
                        method: 'POST',
                        data: {numero: numero,
                            estado: estado},
                        success: function (resposta) {
                            alert(resposta);
                            location.reload();
                        }
                    });
                };
            });
        </script>

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
            <div class="row">
                <div class="col-sm-12 page-header">
                    <h1 class="header">Gerenciar Pedidos</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="row">
                        <table class="table table-striped table-bordered table-hover table-condensed" id="tabela" name="tabela">
                            <tr>
                                <td>Pedido</td>
                                <td>Cliente</td>
                                <td>Estado</td>
                                <td colspan="5">Ações</td>
                            </tr>

                            <c:forEach items="${pedidos}" var="umPedido">
                                <tr>
                                    <td data-numero="${umPedido.numero}">${umPedido.numero}</td>
                                    <td>${umPedido.cliente.nome}</td>
                                    <td>${umPedido.nomeEstado}</td>
                                    <td><button type="button" class="btn btn-info btn-xs" onclick="alterarEstado(this, 'receber')">Recebido <span class="glyphicon glyphicon-thumbs-up"></span></button></td>
                                    <td><button type="button" class="btn btn-primary btn-xs" onclick="alterarEstado(this, 'preparar')">Preparo <span class="glyphicon glyphicon-refresh"></span></button></td>
                                    <td><button type="button" class="btn btn-warning btn-xs" onclick="alterarEstado(this, 'enviar')">Enviado <span class="glyphicon glyphicon-send"></span></button></td>
                                    <td><button type="button" class="btn btn-success btn-xs" onclick="alterarEstado(this, 'entregar')">Entregue <span class="glyphicon glyphicon-ok-circle"></span></button></td>
                                    <td><button type="button" class="btn btn-danger btn-xs" onclick="alterarEstado(this, 'cancelar')">Cancelado <span class="glyphicon glyphicon-remove-circle"></span></button></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>