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

                adicionar = function () {

                    if ($("#quantidade").val() != 0) {
                        var valorTotal = parseFloat($("#valorTotal").text());
                        var linha = $("<tr>");
                        var colunas = "";
                        var opcao = $("#produto").find("option:selected");
                        valorTotal = valorTotal + (opcao.data('preco') * $("#quantidade").val());
                        colunas += '<td style="visibility:hidden;" data-cod-produto=' + $("#produto").val() + '>' + $("#produto").val() + '</td>';
                        colunas += '<td>' + opcao.data('nome') + '</td>';
                        colunas += '<td data-quantidade=' + $("#quantidade").val() + '>' + $("#quantidade").val() + '</td>';
                        colunas += '<td data-preco=' + opcao.data('preco') + '>' + opcao.data('preco') + '</td>';
                        colunas += '<td><button type="button" class="btn btn-danger btn-xs" onclick="remover(this)">Excluir <span class="glyphicon glyphicon-trash"></span></button></td>';
                        colunas += '</tr>';
                        linha.append(colunas);
                        $("#tabela").append(linha);
                        $("#quantidade").val(null);
                        $("#valorTotal").text(valorTotal);
                    }
                };

                remover = function (item) {
                    var valorTotal = parseFloat($("#valorTotal").text());
                    var tr = $(item).closest('tr');
                    var quantidade = tr.find('td[data-quantidade]').data('quantidade');
                    var preco = tr.find('td[data-preco]').data('preco');
                    valorTotal = valorTotal - (quantidade * preco);
                    $("#valorTotal").text(valorTotal);
                    tr.remove();
                }

                $("#cliente").change(function () {
                    $("#tabela tr").remove();
                    var linha = $("<tr>");
                    var colunas = "";
                    colunas += '<td style="visibility:hidden;">Código</td>';
                    colunas += '<td>Produto</td>';
                    colunas += '<td>Quantidade</td>';
                    colunas += '<td>Preço Unit.</td>';
                    colunas += '<td>Ação</td>';
                    colunas += '</tr>';
                    linha.append(colunas);
                    $("#tabela").append(linha);
                    $("#valorTotal").text(0);
                });

                finalizar = function () {
                    var itensProdutos = "";
                    var itensQuantidades = "";
                    var itensPrecos = "";
                    $("#tabela").find('tr').next('tr').each(function () {
                        itensProdutos = itensProdutos + $(this).find('td[data-cod-produto]').data('cod-produto') + ";";
                        itensQuantidades = itensQuantidades + $(this).find('td[data-quantidade]').data('quantidade') + ";";
                        itensPrecos = itensPrecos + $(this).find('td[data-preco]').data('preco') + ";";
                    });

                    $.ajax({
                        url: 'EfetuarPedidoController?acao=confirmar',
                        method: 'POST',
                        data: {cliente: $("#cliente").val(),
                            cupom: $("#cupom").val(),
                            itensProdutos: itensProdutos,
                            itensQuantidades: itensQuantidades,
                            itensPrecos: itensPrecos,
                            valorTotal: parseFloat($("#valorTotal").text())},
                        success: function (resposta) {
                            alert("Numero do Pedido = " + resposta);
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
                    <h1 class="header">Efetuar Pedido</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="cliente">Cliente</label>
                        <select class="form-control" id="cliente" name="cliente">
                            <c:forEach items="${clientes}" var="umCliente">
                                <option value="${umCliente.id}">${umCliente.nome}</option>  
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="produto">Produto</label>
                        <select class="form-control" id="produto" name="produto">
                            <c:forEach items="${produtos}" var="umProduto">
                                <option value="${umProduto.id}" 
                                        data-nome="${umProduto.nome}"
                                        data-preco="${umProduto.preco}">${umProduto.nome}</option>  
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="quantidade">Quantidade</label>
                        <input type="number" id="quantidade" name="quantidade" class="form-control"><br><br>
                    </div>
                    <div class="form-group">
                        <label for="cupom">Cupom</label>
                        <select class="form-control" id="cupom" name="cupom">

                            <option value="Cupom10">Cupom R$10,00</option> 
                            <option value="Cupom15">Cupom R$15,00</option> 

                        </select>
                    </div>
                    <button onclick="adicionar()" type="button" class="btn btn-primary">Adicionar Item</button>
                    <br><br>
                    <center>
                        <button onclick="finalizar()" type="button" class="btn btn-success">Finalizar Pedido</button>
                    </center>
                </div>
                <div class="col-sm-6">
                    <div class="row">
                        <h2 align="right">
                            Valor Total - R$
                            <label id="valorTotal" name="valorTotal">0</label>
                        </h2>
                    </div>
                    <div class="row">
                        <table class="table table-striped table-bordered table-hover table-condensed" id="tabela" name="tabela">
                            <tr>
                                <td style="visibility:hidden;">Código</td>
                                <td>Produto</td>
                                <td>Quantidade</td>
                                <td>Preço Unit.</td>
                                <td>Ação</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>