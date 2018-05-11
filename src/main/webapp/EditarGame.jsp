<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Filial</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo Fulano de Tal</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="cliente.jsp"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="games.jsp"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="consoles.jsp"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="acessorios.jsp"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="actionFigure.jsp"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="funcionarios.jsp"><li id="listaMenu"><img src="_imagens/funcionario.png">Funcionários</li></a>
                    <a href="filiais.jsp"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="venda.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="relatorio.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <c:forEach items="${obterGame}" var="game">
            <form action="${pageContext.request.contextPath}/EditarGame" method="post" id="formularioCadastro">
                <input type="hidden" value="${game.id}" name="id">
                <input type="text" value="${game.nome}" name="txtNome" placeholder="NOME" class="formulario">
                </br></br>
                <input type="text" value="${game.desenvolvedora}" name="txtDesenvolvedora" placeholder="DESENVOLVEDORA" class="formulario">
                </br></br>
                <select name="Plataforma" class="formSelect">
                    <option value="${game.plataforma}">${game.plataforma}</option>
                    <option value="PC">PC</option>
                    <option value="PS3">PS3</option>
                    <option value="PS4">PS4</option>
                    <option value="XBOX 360">XBOX 360</option>
                    <option value="XBOX ONE">XBOX ONE</option>
                </select>
                <input type="text" value="${game.quantidade}" name="txtQuantidade" placeholder="QUANTIDADE" class="formSelect">
                </br></br>
                <input type="text" value="${game.precoCompra}" name="txtPrecoCompra" placeholder="PREÇO COMPRA" class="formSelect">
                <input type="text" value="${game.precoVenda}" name="txtPrecoVenda" placeholder="PREÇO VENDA" class="formSelect">
                </br></br>
                <input type="text" value="${game.classIndicativa}" name="txtClassificacao" placeholder="CLASSIFICAÇÃO INDICATIVA" class="formSelect">
                <fieldset class="categoria">
                    <legend>Categorias</legend>
                    <div>
                        <input type="checkbox" id="acao" name="catAcao" value="acao">
                        <label for="acao">Ação</label>
                        <input type="checkbox" id="aventura" name="catAventura" value="aventura">
                        <label for="aventura">Aventura</label>
                    </div>
                    <div>
                        <input type="checkbox" id="estrategia" name="catEstrategia" value="estrategia">
                        <label for="estrategia">Estratégia</label>
                        <input type="checkbox" id="rpg" name="catRPG" value="rpg">
                        <label for="rpg">RPG</label>
                    </div>
                    <div>
                        <input type="checkbox" id="esportes" name="catEsportes" value="esportes">
                        <label for="esportes">Esportes</label>
                        <input type="checkbox" id="simulacao" name="catSimulacao" value="simulacao">
                        <label for="simulacao">Simulação</label>
                    </div>
                </fieldset>
                <br>
                <input type="submit" onclick="alert('Game alterado com sucesso!')" value="SALVAR" id="botao">
            </form>
            </c:forEach>
        </div>
    </body>
</html>
