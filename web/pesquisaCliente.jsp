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
                                <li> <a href="EfetuarPedidoController?acao=preparar">Efetuar Pedido</a> </li>
                                <li> <a href="GerenciarPedidosController?acao=listar">Gerenciar Pedidos</a> </li>
                            </ul>

                        </li>


                    </ul>

                </div>

            </div>
        </nav>
        <div class="container">

            <div class="page-header">
                <h1>Pesquisa de Clientes</h1>
            </div>

            <table class="table table-striped table-bordered table-hover table-condensed">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Data Nascimento</th>
                        <th>Email</th>
                        <th>Telefone</th>
                        
                        <th colspan=2>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${clientes}" var="cliente">
                        <tr>
                            <td><c:out value="${cliente.id}" /></td>
                            <td><c:out value="${cliente.nome}" /></td>
                            <td><c:out value="${cliente.dataNasc}" /></td>
                            <td><c:out value="${cliente.email}" /></td> 
                            <td><c:out value="${cliente.telefone}" /></td>
                           
                            <td><a class="btn btn-primary btn-sm" href=
                                   "ManterClienteController?acao=prepararOperacao&operacao=Editar&codCliente=<c:out value="${cliente.id}"/>">Editar</a></td>
                            <td><a class="btn btn-danger btn-sm" href=
                                   "ManterClienteController?acao=prepararOperacao&operacao=Excluir&codCliente=<c:out value="${cliente.id}"/>">Excluir</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="ManterClienteController?acao=prepararOperacao&operacao=Incluir" 
                  method="post">
                <input class="btn btn-success" type="submit" name="btnIncluir" value="Incluir">
            </form>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
