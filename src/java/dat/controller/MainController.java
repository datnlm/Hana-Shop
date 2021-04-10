/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author macbook
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String LOGIN = "login.jsp";
    private static final String SUMBIT_LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String SEARCH = "SearchController";
    private static final String CONTINUE_SHOPPING = "shopping.jsp";
    private static final String ADD = "AddController";
    private static final String VIEW_CART = "viewCart.jsp";
    private static final String UPDATE = "UpdateController";
    private static final String DELETE = "DeleteController";
    private static final String SAVE = "SaveController";
    private static final String UPDATE_PRODUCT = "UpdateProductController";
    private static final String DELETE_PRODUCT = "DeleteProductController";
    private static final String PAGING = "ManagerController";
    private static final String ADD_PRODUCT = "AddProductController";
    private static final String VIEW_HISTORY = "ViewHistoryController";
    private static final String VIEW_DETAIL_HISTORY = "ViewDetailHistoryController";
    private static final String CREATE_PRODUCT = "create.jsp";
    private static final String BACK_MANAGER = "ManagerController";
    private static final Logger LOGGER = Logger.getLogger(MainController.class);

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
            String action = request.getParameter("btnAction");
            if ("HomePage".equals(action)) {
                url = SEARCH;
            } else if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("SumbitLogin".equals(action)) {
                url = SUMBIT_LOGIN;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Search".equals(action)) {
                url = SEARCH;
            } else if ("NumberPage".equals(action)) {
                url = SEARCH;
            } else if ("ContinueShopping".equals(action)) {
                url = CONTINUE_SHOPPING;
            } else if ("Add".equals(action)) {
                url = ADD;
            } else if ("viewCart".equals(action)) {
                url = VIEW_CART;
            } else if ("Update".equals(action)) {
                url = UPDATE;
            } else if ("Delete".equals(action)) {
                url = DELETE;
            } else if ("Save".equals(action)) {
                url = SAVE;
            } else if ("UpdateProduct".equals(action)) {
                url = UPDATE_PRODUCT;
            } else if ("DeleteProduct".equals(action)) {
                url = DELETE_PRODUCT;
            } else if ("Paging".equals(action)) {
                url = PAGING;
            } else if ("AddProduct".equals(action)) {
                url = ADD_PRODUCT;
            } else if ("LoginFirst".equals(action)) {
                url = LOGOUT;
            } else if ("ViewHistory".equals(action)) {
                url = VIEW_HISTORY;
            } else if ("ViewDetail".equals(action)) {
                url = VIEW_DETAIL_HISTORY;
            } else if ("BackCart".equals(action)) {
                url = VIEW_CART;
            } else if ("BackOrders".equals(action)) {
                url = VIEW_HISTORY;
            } else if("SearchOrder".equals(action)){
                url = VIEW_HISTORY;
            } else if("CreateProduct".equals(action)){
                url = CREATE_PRODUCT;
            } else if("BackToHome".equals(action)){
                url = BACK_MANAGER;
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
