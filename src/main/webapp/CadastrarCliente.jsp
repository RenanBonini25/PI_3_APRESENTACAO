<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Cadastro de clientes</title>
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
                    <a href="usuarios.jsp"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="filiais.jsp"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="venda.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="relatorio.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <form action="${pageContext.request.contextPath}/CadastrarCliente" method="post" id="formularioCadastro">
                <input type="text" name="txtNome" placeholder="NOME" class="formulario">
                </br></br>
                <input type="text" name="txtCpf" placeholder="CPF - 111.111.111-11" maxlength="14" class="formulario">
                </br></br>
                <input type="date" name="dataNascimento" class="formSelect">
                <select name="Sexo" class="formSelect">
                    <option value="null">SEXO</option>
                    <option value="feminino">FEMININO</option>
                    <option value="masculino">MASCULINO</option>
                </select>
                </br></br>
                <select name="EstadoCivil" class="formSelect">
                    <option value="null">ESTADO CIVIL</option>
                    <option value="solteiro">SOLTEIRO(A)</option>
                    <option value="casado">CASADO(A)</option>
                    <option value="viuvo">VIUVO(A)</option>
                </select>
                </br></br>
                <input type="text" name="txtEndereco" placeholder="ENDEREÇO" class="formulario">
                </br></br>
                <input type="text" name="txtComplemento" placeholder="COMPLEMENTO" class="formulario">
                </br></br>
                <input type="text" name="txtCep" placeholder="CEP - 00000-000" maxlength="9" class="formSelect">
                <input type="text" name="txtNumero" placeholder="NÚMERO" maxlength="5" class="formSelect">
                </br></br>
                <input type="text" name="txtBairro" placeholder="BAIRRO" class="formulario">
                </br></br>
                <input type="text" name="txtCidade" placeholder="CIDADE" class="formulario">
                </br></br>
                <select name="estado" class="formSelect">
                    <option value="null">Selecione o Estado (UF)</option>
                    <option value="Acre">Acre</option>
                    <option value="Alagoas">Alagoas</option>
                    <option value="Amapá">Amapá</option>
                    <option value="Amazonas">Amazonas</option>
                    <option value="Bahia">Bahia</option>
                    <option value="Ceara">Ceará</option>
                    <option value="DistritoFederal">Distrito Federal</option>
                    <option value="Espirito Santo">Espírito Santo</option>
                    <option value="Goias">Goiás</option>
                    <option value="Maranhao">Maranhão</option>
                    <option value="MatoGrosso">Mato Grosso</option>
                    <option value="MatoGrossoDoSul">Mato Grosso do Sul</option>
                    <option value="MinasGerais">Minas Gerais</option>
                    <option value="Para">Pará</option>
                    <option value="Paraiba">Paraíba</option>
                    <option value="Parana">Paraná</option>
                    <option value="Pernambuco">Pernambuco</option>
                    <option value="Piaui">Piauí</option>
                    <option value="RioDeJaneiro">Rio de Janeiro</option>
                    <option value="RioGrandeDoSul">Rio Grande do Sul</option>
                    <option value="RioGrandeDoNorte">Rio Grande do Norte</option>
                    <option value="Rondonia">Rondônia</option>
                    <option value="Roraima">Roraima</option>
                    <option value="SantaCatarina">Santa Catarina</option>
                    <option value="SaoPaulo">São Paulo</option>
                    <option value="Sergipe">Sergipe</option>
                    <option value="Tocantins">Tocantins</option>
                </select>
                </br></br>
                <input type="submit" onclick="alert('Cliente cadastrado com sucesso!')" value="SALVAR" id="botao">
            </form>
        </div>
    </body>
</html>
