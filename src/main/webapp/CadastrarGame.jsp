<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Cadastro de Games</title>
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
                    <a href="clientes.jsp"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
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
            <form action="${pageContext.request.contextPath}/CadastrarGame" method="post" id="formularioCadastro">
                <input type="text" name="txtNome" placeholder="NOME" class="formulario">
                </br></br>
                <input type="text" name="txtDesenvolvedora" placeholder="DESENVOLVEDORA" class="formulario">
                </br></br>
                <select name="Plataforma" class="formSelect">
                    <option value="null">PLATAFORMA</option>
                    <option value="PC">PC</option>
                    <option value="PS3">PS3</option>
                    <option value="PS4">PS4</option>
                    <option value="XBOX 360">XBOX 360</option>
                    <option value="XBOX ONE">XBOX ONE</option>
                </select>
                <input type="text" name="txtQuantidade" placeholder="QUANTIDADE" class="formSelect">
                </br></br>
                <input type="text" name="txtPrecoCompra" placeholder="PREÇO COMPRA" class="formSelect">
                <input type="text" name="txtPrecoVenda" placeholder="PREÇO VENDA" class="formSelect">
                </br></br>
                <input type="text" name="txtClassificacao" placeholder="CLASSIFICAÇÃO INDICATIVA" class="formSelect">
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
                <input type="submit" onclick="alert('Game cadastrado com sucesso!')" value="SALVAR" id="botao">
            </form>
        </div>
    </body>
</html>