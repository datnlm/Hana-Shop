/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.daos.ProductDAO;
import dat.dto.ProductDTO;
import java.io.IOException;
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
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private static final String URL = "shopping.jsp";
    private static final int QUANTITY_PRODUCT = 20;
    private static final Logger LOGGER = Logger.getLogger(SearchController.class);

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
        String url = URL;
        try {
            int index = 1;
            HttpSession session = request.getSession();
            String productName = request.getParameter("txtNameProduct");
            String maxPrice = request.getParameter("txtMaxPrice");
            String minPrice = request.getParameter("txtMinPrice");
            String category = request.getParameter("txtCategory");
            if (productName == null && maxPrice == null && minPrice == null || category == null) {
                productName = "";
                maxPrice = "";
                minPrice = "";
                category = "";
            }
            String tmp = request.getParameter("txtIndex");
            if (tmp != null) {
                index = Integer.parseInt(tmp);
            }
            ProductDAO dao = new ProductDAO();
            List<String> listCategory = dao.getCategory();
            List<ProductDTO> list = dao.getProduct(productName, maxPrice, minPrice, category, (index - 1) * QUANTITY_PRODUCT);
            if ((!minPrice.isEmpty() && maxPrice.isEmpty()) || (minPrice.isEmpty() && !maxPrice.isEmpty())) {
                request.setAttribute("ERROR", "Please input min price and max price");
            } else if (list != null || !list.isEmpty()) {
                session.setAttribute("LIST_PRODUCT", list);
                session.setAttribute("LIST_CATEGORY", listCategory);
                session.setAttribute("CATEGORY", category);
                int numberPage = dao.getPage(productName, maxPrice, minPrice, category);
                request.setAttribute("NUMBER_PAGE", numberPage);
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
