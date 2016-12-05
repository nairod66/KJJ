/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kjj.web;

import com.atoudeft.jdbc.Connexion;
import com.kjj.entites.Membre;
import com.kjj.entites.MembreFactory;
import com.kjj.implementations.MembreDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dbourcet
 */
public class Inscription extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();
        out.println("servlet inscription. Bienvenue !");*/
        String  aNum = request.getParameter("adr_numero"),
                aRu1 = request.getParameter("adr_rue1"),
                aRu2 = request.getParameter("adr_rue2"),
                aApp = request.getParameter("adr_appartement"),
                aVil = request.getParameter("adr_ville"),
                aCPl = request.getParameter("adr_codePostal"),
                aPce = request.getParameter("adr_province");
        HashMap donneesObligatoires = new HashMap();
        donneesObligatoires.put("usr",request.getParameter("username"));
        donneesObligatoires.put("pwd",request.getParameter("password"));
        donneesObligatoires.put("nom",request.getParameter("nom"));
        donneesObligatoires.put("prn",request.getParameter("prenom"));
        donneesObligatoires.put("ema",request.getParameter("email"));
        Set set = donneesObligatoires.entrySet();
        Iterator itr = set.iterator();
        while(itr.hasNext()) {
            Map.Entry entry = (Map.Entry)itr.next();
            if (entry.getValue().equals("")) {
                request.setAttribute("message", "Les champs obligatoires doivent être complétés");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                r.forward(request, response);
                return;
            }
        }
        
        try {
            Class.forName(this.getServletContext().getInitParameter("piloteJdbc"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = response.getWriter();
        
        Membre m = MembreFactory.getMembre(
                (String)donneesObligatoires.get("usr"), 
                (String)donneesObligatoires.get("pwd"),
                (String)donneesObligatoires.get("nom"),
                (String)donneesObligatoires.get("prn"), 
                (String)donneesObligatoires.get("ema"), aNum, aRu1, aRu2, aApp,
                aVil, aCPl, aPce);
        
        Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
        MembreDao dao = new MembreDao(Connexion.getInstance());
        
        if (!dao.create(m))
        {
            // Erreur lors de la création (membre existant)
            out.print(false);
        }
        else
            // Membre créé
            out.print(true);

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
