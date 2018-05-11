<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Login</title>
        <link rel="stylesheet" type="text/css" href="_css/estiloIndex.css">
    </head>
    <body>
    <center>
        <p>Jupiter</p>
        

        <div id="login">
            <form action="${pageContext.request.contextPath}/Login" method="post">
            <span> Usuário ou Senha inválido! </span>
                <input type="text" name="username" placeholder="Nome de usuário" id="formLogin"><br><hr>
                <input type="password" name="senha" placeholder="Senha" id="formLogin">
                
                <input type="submit" value="ENTRAR" id="botao"> 
            </form>			
        </div>
    </center>
</body>
</html>