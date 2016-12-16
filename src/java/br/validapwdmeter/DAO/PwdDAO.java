/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.validapwdmeter.DAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rafael.franco
 */
public class PwdDAO {
    
    //MINIMO DE 8 CARACTERES
    //CONTER 3/4 DOS SEGUINTES ITENS
    //LETRAS - MAIUSCULAS - MINUSCULAS - NUMEROS E SIMBOLOS 
    
    //REGRAS ADICÕES
    //*NUMEROS DE CARACTERER +(n*4)
    //*LETRAS MAIUSCULAS +(len-n)*2)    
    //*LETRAS MINUSCULAS +(len-n)*2)
    //NUMEROS +(n*4)
    //SIMBOLOS *(n*6)
    //MEDIA DE NUMEROS OU SIMBOLOS +(n*2)
    //REQUISITOS +(n*2)
    
    //REGRAS DEDUÇÕES
    //*SOMENTE LETRAS -n
    //*SOMENTE NUMBEROS -n
    //*REPETICAO DE CARACTERES (CASE INSENSITIVE) -
    //*LETRAS MAIUSCULAS CONSECTIVAS -(n*2)    
    //*LETRAS MINUSCULAS CONSECTIVAS -(n*2)
    //*NUMEROS CONSECUTIVOS -(n*2)
    //*LETRAS SEQUENCIAS -(n*3)
    //*NUMEROS SEQUENCIAS -(n*3)
    //*SIMBOLOS SEQUENCIAS -(n*3)
    
    private Integer tamanhoSenha = 8;
    private String numeros = "0123456789";    
    private String alfabeto = "abcdefghijklmnopqrstuvwxyz";    
    private String simbolos = "!@#$%^&*()";
    private Integer MAIUSCULO_CONSECUTIVAS = 2;    
    private Integer MINUSCULO_CONSECUTIVAS = 2;    
    private Integer numerosConsecutivas = 2;    
    private Integer simbolosConsecutivos = 2; 
    private Integer sequenciaLetras = 3;
    private Integer sequenciaNumeros = 3;    
    private Integer sequenciaSimbolos = 3;
    private Integer mNumeros = 4;
    private Integer mSimbolos = 6;    
    private Integer mNumerosOuSimbolos = 2;
    private Integer requisitoCaracteres = 0;
    private Integer maiusculasBonus;    
    private Integer minusculasBonus;
    private Integer numeroBonus;    
    private Integer numeroOuSimbolo;    
    private Integer simboloBonus;
     
    //VALIDAR A SENHA
    public String validar(String senha)
    {

        return (calcularPontuacao(totalBonusAdicao(senha) + totalBonusDeducao(senha))); 
    }
    
    //TESTANDO OS VALORES
    public String testandoValores(String senha)
    {
        return "ADIÇÃO: <br />"+
                "<br />Number of Character: "+ (int)numeroCaracteres(senha)+
                "<br />Uppercase Letters: " + (int)caracteresMaiusculos(senha)+
                "<br />Lowercase Letters: " + (int)caracteresMinusculos(senha)+
                "<br />Numbers:  " + (int)aNumeros(senha)+
                "<br />Symbols:  " + (int)simbolos(senha)+
                "<br />Middle Numbers of Symbols:  " + (int)MediaNumerosOuSimbolos(senha)+
                "<br />Requirements:  " + (int)requisitos(senha)+
                "<br /><hr /><br />"+
                "DEDUÇÃO: <br />"+
                "<br />Letters Only:  " + (int)somenteLetras(senha)+
                "<br />Numbers Only:  " + (int)somenteNumeros(senha)+
                "<br />Repeat Characters (Case Insensitive):  " + (int)repeticaoCaracteres(senha)+
                "<br />Consecutive Uppercase Letters:  " + (int)maiusculasConsecutivas(senha)+
                "<br />Consecutive Lowercase Letters:  " + (int)minusculasConsecutivas(senha)+
                "<br />Consecutive Numbers:  " + (int)numerosConsecutivos(senha)+
                "<br />Sequential Letters:  " + (int)letrasSequencias(senha)+
                "<br />Sequential Numbers:   " + (int)numerosSequencias(senha)+
                "<br />Sequential Symbols:   " + (int)simbolosSequencias(senha)+
                "<br /><hr /><br />"+
                "VALORES: <br />"+ (calcularPontuacao(totalBonusAdicao(senha) + totalBonusDeducao(senha)))
                ;
    }
      
    //VERIFICAR O BONUS - ADIÇÃO
    private int totalBonusAdicao(String senha) {
        return numeroCaracteres(senha) 
                   + caracteresMaiusculos(senha)
                   + caracteresMinusculos(senha)
                   + aNumeros(senha)
                   + simbolos(senha)
                   + MediaNumerosOuSimbolos(senha)
                   + requisitos(senha);
    }
    
    //VERIFICAR O BONUS - DEDUÇÃO
    private int totalBonusDeducao(String senha) {
        return somenteLetras(senha)
                   + somenteNumeros(senha)
                   + repeticaoCaracteres(senha)
                   + maiusculasConsecutivas(senha)
                   + minusculasConsecutivas(senha)
                   + numerosConsecutivos(senha)
                   + simbolosConsecutivos(senha)
                   + letrasSequencias(senha)
                   + numerosSequencias(senha)
                   + simbolosSequencias(senha);
	}
    
    /* 
        INICIO ADIÇÃO
    */
    
    //NUMEROS DE CARACTERER +(n*4)
    public int numeroCaracteres(String senha) {
        return senha.length() * 4;
    }
    
    //LETRAS MAIUSCULAS +(len-n)*2)    
    public int caracteresMaiusculos(String senha) {
        
        maiusculasConsecutivas(senha);
        
        if(maiusculasBonus == 0)
            maiusculasBonus = maiusculasBonus;
        else
           maiusculasBonus = ((senha.length() - maiusculasBonus) * 2);
        
        return maiusculasBonus;
    }
    
    //LETRAS MINUSCULAS +(len-n)*2)    
    public int caracteresMinusculos(String senha) {
        
        minusculasConsecutivas(senha);
        
        if(minusculasBonus == 0)
            minusculasBonus = minusculasBonus;
        else
           minusculasBonus = ((senha.length() - minusculasBonus) * 2);
        
        return minusculasBonus;
    }
    
    //NUMEROS +(n*4)
    public int aNumeros(String senha) {
        
        int bonus = 0;
        numerosConsecutivos(senha);
        
        if (numeroBonus > 0 && numeroBonus < senha.length()) {
            bonus = numeroBonus * mNumeros;
        }
        
        return bonus;
    }
    
    //SIMBOLOS *(n*6)
    public int simbolos(String senha) {
        
        int bonus = 0;
        simbolosConsecutivos(senha);

        if (simboloBonus > 0) {	
                bonus = simboloBonus * mSimbolos;
        }
        return bonus;
    }
    
    //MEDIA DE NUMEROS OU SIMBOLOS +(n*2)
    public int MediaNumerosOuSimbolos(String senha) {
        
        int bonus = 0; 
        numerosConsecutivos(senha);
        simbolosConsecutivos(senha);

        if (numeroOuSimbolo > 0) {	
            bonus = numeroOuSimbolo * mNumerosOuSimbolos;
        }
        return bonus;
    }
    
    //REQUISITOS +(n*2)
    public int requisitos(String senha) {
        
        int resultado = 0;

        numerosConsecutivos(senha);
        maiusculasConsecutivas(senha);
        minusculasConsecutivas(senha);
        simbolosConsecutivos(senha);

        int[] arrChars = { 
                senha.length(),
                maiusculasBonus,
                minusculasBonus,
                numeroBonus,
                simboloBonus 
            };

        String[] arrCharsIds = {"nLength","nAlphaUC","nAlphaLC","nNumber","nSymbol"};
        int arrCharsLen = arrChars.length;

        for (int c = 0; c < arrCharsLen; c++) {
            int minVal = 0;

            if (arrCharsIds[c] == "nLength") { 
                    minVal = tamanhoSenha - 1; 
            }

            if (arrChars[c] == minVal + 1) {
                    requisitoCaracteres++;
            } else if(arrChars[c] > minVal + 1) {
                    requisitoCaracteres++;
            }
        }

        int nMinReqChars = 0;

        if (senha.length() >= tamanhoSenha) {
            nMinReqChars = 3; 
        } else { 
            nMinReqChars = 4;
        }

        if (requisitoCaracteres > nMinReqChars) {
            resultado = requisitoCaracteres * 2; 
        }
        
        return resultado;
        
    }
    
    /* 
        FIM ADIÇÃO
    */
    
    /* 
        INICIO DEDUÇÃO
    */
    
    //SOMENTE LETRAS -n
    public int somenteLetras(String senha) {
            
        Integer tam = senha.length();
        Pattern ptn = Pattern.compile("[a-zA-Z]{" + tam + "}");
        Matcher mtc = ptn.matcher(senha);
        
        if(mtc.matches()) {
            return - tam;
        }

        return 0;
    }
    
    //SOMENTE NUMEROS -n
    public int somenteNumeros(String senha)
    {
        Integer tam = senha.length();
        Pattern ptn = Pattern.compile("[0-9]{" + tam + "}");
        Matcher mtc = ptn.matcher(senha);
        
        if(mtc.matches()) {
            return - tam;
        }

        return 0;
    }
    
    //REPETICAO DE CARACTERES (CASE INSENSITIVE) -
    public int repeticaoCaracteres(String senha)
    {
        int tamanho = senha.length();
        double reduz = 0.0;
        int unicos = 0;
        int repetidos = 0;
        boolean encontrou = false;
        
        for(int a = 0; a < tamanho; a++) {
            
            for(int b = 0; b < tamanho; b++) {
                if(senha.charAt(a) == senha.charAt(b) && a != b) {
                    encontrou = true;
                    reduz += Math.abs((double)tamanho / (b-a));
                }
            }
            
            if(encontrou) {
                repetidos++;
                unicos = tamanho - repetidos;
                reduz = (unicos > 0)?Math.ceil(reduz/unicos):Math.ceil(reduz); 
            }
            
        }

        return (int)-reduz; 
    }
        
    //MAIUSCULAS CONSECUTIVAS -(n*2) 
    public int maiusculasConsecutivas(String senha) {
               
        Integer tamanho = senha.length();
        Integer tmp = 0;
        Integer maiusculasConsecutivasAux = 0;
        maiusculasBonus = 0;

        for (int i = 0; i < tamanho; i++) {
                String c = String.valueOf(senha.charAt(i)); 

                if (Pattern.compile("[A-Z]", Pattern.DOTALL).matcher(c).matches()) {
                        if (i != 0) {
                                if ((tmp + 1) == i) { 
                                        maiusculasConsecutivasAux++; 
                                } 
                        }
                        tmp = i;
                        maiusculasBonus++;
                }
        }

        return -(maiusculasConsecutivasAux * MAIUSCULO_CONSECUTIVAS);
        
    }
    
    //MINUSCULAS CONSECUTIVAS -(n*2) 
    public int minusculasConsecutivas(String senha) {
        
        Integer tamanho = senha.length();
        Integer tmp = 0;
        Integer minusculasConsecutivasAux = 0;
        minusculasBonus = 0;

        for (int i = 0; i < tamanho; i++) {
                String c = String.valueOf(senha.charAt(i)); 

                if (Pattern.compile("[a-z]", Pattern.DOTALL).matcher(c).matches()) {
                        if (i != 0) {
                                if ((tmp + 1) == i) { 
                                        minusculasConsecutivasAux++; 
                                } 
                        }
                        tmp = i;
                        minusculasBonus++;
                }
        }

        return -(minusculasConsecutivasAux * MAIUSCULO_CONSECUTIVAS);
        
    }
    
    //NUMEROS CONSECUTIVOS -(n*2)
    public int numerosConsecutivos(String senha) {
        
        int tamanho = senha.length();
        int temp = 0;
        int numerosConsecutivosAux = 0;
        numeroBonus = 0;
        numeroOuSimbolo = 0;

        for (int i = 0; i < tamanho; i++) {
            
            String c = String.valueOf(senha.charAt(i)); 

            if (Pattern.compile("[0-9]", Pattern.DOTALL).matcher(c).matches()) {
                if (i > 0 && i < (tamanho - 1)) { 
                    numeroOuSimbolo++; 
                }

                if (i != 0) {
                    if ((temp + 1) == i) { 
                        numerosConsecutivosAux++; 
                    } 
                }
                
                temp = i;
                
                numeroBonus++;
            }
        }

        return -(numerosConsecutivas * numerosConsecutivosAux);
    }
    
    //SIMBOLOS CONSECUTIVOS -(n*1)
    public int simbolosConsecutivos(String senha) {
    
        int tamanho = senha.length();
        int temp = 0;
        int simbolosConsecutivosAux = 0;
        simboloBonus = 0;

        if(numeroOuSimbolo == null) 
        {
            numeroOuSimbolo = 0;
        }

        for (int i = 0; i < tamanho; i++) {
            
            String c = String.valueOf(senha.charAt(i)); 

            if (Pattern.compile("[^a-zA-Z0-9_]", Pattern.DOTALL).matcher(c).matches()) {
                if (i > 0 && i < (tamanho - 1)) { 
                    numeroOuSimbolo++; 
                }

                if (i != 0) {
                    if ((temp + 1) == i) { 
                        simbolosConsecutivosAux++; 
                    } 
                }
                temp = i;
                simboloBonus++;
            }
        }

        return -(simbolosConsecutivos * simbolosConsecutivosAux);
    }
    
    //LETRAS SEQUENCIAS -(n*3)
    public int letrasSequencias(String senha) {
        
        int sequenciais = 0;

        for (int i = 0; i < (alfabeto.length() - 3); i++) {
            StringBuffer sFwd = new StringBuffer(alfabeto.substring(i, i + 3));
            String trio = sFwd.toString();
            String trioRev = sFwd.reverse().toString();
            if (senha.toLowerCase().indexOf(trio) != -1 || senha.toLowerCase().indexOf(trioRev) != -1) { 
                    sequenciais++;
            }
        }

        return -(sequenciaLetras * sequenciais);
    }
        
    //NUMEROS SEQUENCIAS -(n*3)
    public int numerosSequencias(String senha) {
        
        int sequenciais = 0;

        for (int i = 0; i < (numeros.length() - 2); i++) {
            StringBuffer sFwd = new StringBuffer(numeros.substring(i, i + 3));
            String trio = sFwd.toString();
            String trioRev = sFwd.reverse().toString();
            if (senha.toLowerCase().indexOf(trio) != -1 || senha.toLowerCase().indexOf(trioRev) != -1) { 
                sequenciais++;
            }
        }

        return -(sequenciaNumeros * sequenciais);
        
    }
    
    //SIMBOLOS SEQUENCIAS -(n*3)
    public int simbolosSequencias(String senha) {
        
        Integer sequenciais = 0;
		
        for (int i = 0; i < (simbolos.length() - 2); i++) {
            StringBuffer sFwd = new StringBuffer(simbolos.substring(i, i + 3));
            String trio = sFwd.toString();
            String trioRev = sFwd.reverse().toString();
            if (senha.toLowerCase().indexOf(trio) != -1 || senha.toLowerCase().indexOf(trioRev) != -1) { 
                    sequenciais++;
            }
        }

        return -(sequenciais * sequenciaSimbolos);
        
    }
    
    /* 
        FIM DEDUÇÃO
    */
        
    public String calcularPontuacao(int porcentagem) {
		
        if(porcentagem <= 0) {
            porcentagem = 0;
            return "{ \"porcentagem\":\"" +porcentagem+ "%\", \"complexidade\":\"Muito Fraca\", \"classe\":\"label label-danger\" }";
        }     
        else if(porcentagem >= 0 && porcentagem < 20) {

                return "{ \"porcentagem\":\"" +porcentagem+ "%\", \"complexidade\":\"Muito Fraca\", \"classe\":\"label label-danger\" }";
        } else if(porcentagem >= 20 && porcentagem < 40){
                return "{ \"porcentagem\":\"" + porcentagem + "%\", \"complexidade\":\"Fraca\", \"classe\":\"label label-warning\" }";
        } else if(porcentagem >= 40 && porcentagem < 60){
                return "{ \"porcentagem\":\"" + porcentagem + "%\", \"complexidade\":\"Boa\", \"classe\":\"label label-info\" }";
        } else if(porcentagem >= 60 && porcentagem < 80){
                return "{ \"porcentagem\":\"" + porcentagem + "%\", \"complexidade\":\"Forte\", \"classe\":\"label label-info\" }";
        } else {
            if (porcentagem >= 100)
                porcentagem = 100;
            else
                porcentagem = porcentagem;

            return "{ \"porcentagem\":\"" + porcentagem + "%\", \"complexidade\":\"Muito Forte\", \"classe\":\"label label-success\"  }";
        }
    }
    

}
