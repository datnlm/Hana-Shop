/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.daos.ProductDAO;
import dat.dto.CartDTO;
import dat.dto.ProductDTO;
import java.io.IOException;
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
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String URL = "viewCart.jsp";
    private static final Logger LOGGER = Logger.getLogger(UpdateController.class);

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
            String productID = request.getParameter("txtProductID");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            ProductDTO product = new ProductDTO();
            if (quantity < 0) {
                request.setAttribute("ERROR", "Quantity is cannot < 0");
            } else {
                if (cart != null) {
                    for (ProductDTO dto : cart.getCart().values()) {
                        if (dto.getProductID().equals(productID)) {
                            product = new ProductDTO(productID, dto.getProductName(), dto.getPrice(), quantity, "", "", "active");
                        }
                    }
                    ProductDAO dao = new ProductDAO();
                    int quantityStock = dao.getQuantity(productID);
                    if (quantityStock >= quantity) {
                        cart.update(product);
                        session.setAttribute("CART", cart);
                    } else {
                        request.setAttribute("ERROR", "Sorry, you can only purchase up to " + quantityStock + " " + product.getProductName());
                    }
                }
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
