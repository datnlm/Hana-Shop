/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.daos.ProductDAO;
import dat.dto.ProductDTO;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 *
 * @author macbook
 */
@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {
    
    public static final String ERROR = "manager.jsp";
    public static final String SUCCESS = "ManagerController";
    private static final Logger LOGGER = Logger.getLogger(UpdateProductController.class);

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
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
                
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            String RealPath = getServletContext().getRealPath("/") + "images/" + fileName;
                            File saveFile = new File(RealPath);
                            item.write(saveFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
  
                    }
                }
                String productID = (String) params.get("txtProductID");
                String productName = (String) params.get("txtProductName");
                String description = (String) params.get("txtDescription");
                String priceTMP = (String) params.get("txtPrice");
                String quantityTMP = (String) params.get("txtQuantity");
                String status = (String) params.get("txtStatus");
                int quantity = 0;
                float price = 0;
                if (!priceTMP.trim().isEmpty()) {
                    price = Float.parseFloat(priceTMP);
                }
                if (!priceTMP.trim().isEmpty()) {
                    quantity = Integer.parseInt(quantityTMP);
                }
                if (quantity <= 0) {
                    request.setAttribute("ERROR", "Quantity is cannot <= 0");
                } else if (price <= 0) {
                    request.setAttribute("ERROR", "Quantity is cannot <= 0");
                } else if (productName.trim().isEmpty()) {
                    request.setAttribute("ERROR", "Name of product can not null");
                } else {
                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, fileName, description, status);
                    ProductDAO dao = new ProductDAO();
                    int result = dao.update(dto);
                    if (result != 1) {
                        request.setAttribute("ERROR", "Update error");
                    } else {
                        url = SUCCESS;
                        request.setAttribute("MESSAGE", "Update successful");
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
