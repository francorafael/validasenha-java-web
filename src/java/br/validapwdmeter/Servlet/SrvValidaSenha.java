/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.validapwdmeter.Servlet;

import br.validapwdmeter.DAO.PwdDAO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rafael.franco
 */
public class SrvValidaSenha extends HttpServlet {
     private static final long serialVersionUID = 1L;
     
    @Override  
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {  
         validaSenha(request, response);  
    }  
    @Override  
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {  
         validaSenha(request, response);  
    }  
    private void validaSenha(final HttpServletRequest request, final HttpServletResponse response) throws IOException {  
         
        request.setCharacterEncoding("UTF-8");   
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json"); 


        try (PrintWriter out = response.getWriter()) {
          //VAR
          String senha = "";
           try{
                senha = request.getParameter("senha");
                PwdDAO vsddao = new PwdDAO();
                out.print(vsddao.validar(senha));
                out.close();
           } catch(Exception ex){
               out.println(ex.getMessage());
           }
        }     
    } 
}
