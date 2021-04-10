/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.daos.ProductDAO;
import dat.dto.ProductDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author macbook
 */
@WebServlet(name = "ManagerController", urlPatterns = {"/ManagerController"})
public class ManagerController extends HttpServlet {

    public static final String ERROR = "invalid.html";
    public static final String SUCCESS = "manager.jsp";
    public static final int QUANTITY_PRODUCT = 20;
    private static final Logger LOGGER = Logger.getLogger(ManagerController.class);

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
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int index = 1;
            String tmp = request.getParameter("txtIndex");
            if (tmp != null) {
                index = Integer.parseInt(tmp);
            }
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list = dao.getAll((index - 1) * QUANTITY_PRODUCT);
            List<String> listStatus = new ArrayList<>();
            listStatus.add("active");
            listStatus.add("inActive");
            List<String> listCategory = dao.getCategory();

            int paging = dao.getPageManager();
            if (list != null && !list.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("LIST", list);
                request.setAttribute("NUMBER_PAGE", paging);
                session.setAttribute("LIST_CATEGORY", listCategory);
                session.setAttribute("LIST_STATUS", listStatus);
                url = SUCCESS;
            }
        } catch (Exception e) {
            LOGGER.error("Error at: ", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
