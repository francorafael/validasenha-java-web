$(document).ready(function () {
    $('.input-senha').keyup(function(){
        var senha = $('#senha').val();
        $.ajax({
            type: 'POST',            
            data: "senha="+senha,  
            url: './SrvValidaSenha',  
            success: function(data){                          
                var res = data;                            
                //console.log(res);
                $('#porcentagem').text(res.porcentagem);                                
                $('#mensagem').text(res.complexidade);
                $('#mensagem').removeClass();
                $('#mensagem').addClass(res.classe);
            }
        });
    });
});