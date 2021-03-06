/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict;

import bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;


/**
 *
 * @author Alpha
 */
@WebServlet(name = "DoLoginServlet", urlPatterns = {"/DoLoginServlet"})
public class DoLoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WSPU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            
            String _request = request.getParameter("AAA");
            String userID = new String(Base64.decodeBase64(_request));
            
            String _request_ = request.getParameter("BBB");
            String userPassword = new String(Base64.decodeBase64(_request_));

            //String userID = request.getParameter("userID");
            //String userPassword = request.getParameter("userPassword");
            User tmp = new User();
            tmp.setUserID(userID);
            tmp.setPassword(userPassword);
            System.out.println("*****" + tmp.getUserID());
            
            User user = entityManager.find(User.class, tmp.getUserID());
            if (user.getPassword().equals(tmp.getPassword())) {
                entityManager.getTransaction().commit();
                entityManager.close();
                entityManagerFactory.close();
                request.getSession(true).setAttribute("user", user.getUserName());
            }

        } catch (Exception e) {
            System.out.println("****ERROR:****"+e.getMessage());
        }
        response.sendRedirect("index.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
