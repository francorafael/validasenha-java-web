<%-- 
    Document   : index
    Created on : 15/12/2016, 09:27:59
    Author     : rafael.franco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gumga - Valida Senha</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="col-md-12">
            <div class="container">
                <div class="content-1">
                    <p class="title-p p-pd-left">AVALIADOR DE SEGURANÇA DE SENHA</p>
                    <div class="content-2">                        
                        <form action="SrvValidaSenha" method="POST">
                            <div class="form-group">
                                <input id="senha" class="form-control input-senha" type="password" name="senha" placeholder="Senha"/>
                                <div class="space-between-1"></div>
                                <span id="porcentagem" class="label label-default">0%</span>                                
                                <span id="mensagem"></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="assets/js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>        
        <script src="assets/js/validasenha.js" type="text/javascript"></script>

    </body>
</html>
