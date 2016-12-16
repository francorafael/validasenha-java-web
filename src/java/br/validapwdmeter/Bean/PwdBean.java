/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.validapwdmeter.Bean;

/**
 *
 * @author rafael.franco
 */
public class PwdBean {
    
    private String senha;    
    private String mensagem;
    private int porcentagem;
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public int getPorcentagem() {
        return porcentagem;
    }
    
}
