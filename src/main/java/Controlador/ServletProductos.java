/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ProductosCRUD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import Modelo.Productos;

/**
 *
 * @author User
 */
public class ServletProductos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private int id;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
            /* TODO output your page here. You may use following sample code. */
            if(op.equals("listar")){
                List<Productos> misProductos = ProductosCRUD.getProductos();
                request.setAttribute("productos", misProductos);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
            }
            if(op.equals("insert1")){
                request.getRequestDispatcher("insert.jsp").forward(request, response);
            }
            if(op.equals("insert2")){
                Productos miProducto = new Productos();
                miProducto.setNombre(request.getParameter("nombre"));
                miProducto.setImagen(request.getParameter("imagen"));
                miProducto.setCategoria(request.getParameter("categoria"));
                String precio=request.getParameter("precio");
                miProducto.setPrecio(Float.parseFloat(precio));
                ProductosCRUD.insertaProducto(miProducto);
                out.println("<h1>Se ha insertado el producto</h1>");
                out.println("<a href='index.html'>Volver a index</a>");
            }
            if(op.equals("borrar")){
                if(ProductosCRUD.destroyProducto(Integer.parseInt(request.getParameter("id")))>0){
                    out.println("<h1>Se ha borrado el producto</h1>");
                    out.println("<a href='index.html'>Volver a index</a>");
                }else{
                    out.println("<h1>No se ha borrado el producto</h1>");
                    out.println("<a href='index.html'>Volver a index</a>");
                }
            }
            if(op.equals("update1")){
                int id = Integer.parseInt(request.getParameter("id"));
                Productos miProducto = ProductosCRUD.getProducto(id);
                request.setAttribute("producto", miProducto);
                this.id = miProducto.getId();
                request.getRequestDispatcher("update.jsp").forward(request, response);
            }
            if(op.equals("update2")){
                Productos miProducto = new Productos();
                miProducto.setId(this.id);
                miProducto.setNombre(request.getParameter("nombre"));
                miProducto.setImagen(request.getParameter("imagen"));
                miProducto.setCategoria(request.getParameter("categoria"));
                String precio=request.getParameter("precio");
                miProducto.setPrecio(Float.parseFloat(precio));
                ProductosCRUD.actualizaProducto(miProducto);
                //Con el metodo merge que utilizamos en insertar tambien actualiza
                //ProductosCRUD.insertaProducto(miProducto);
                request.setAttribute("mensaje", "El producto se ha actualizado");
                request.setAttribute("producto", miProducto);
                request.getRequestDispatcher("update.jsp").forward(request, response);
                //out.println("<h1>Se ha editado el producto</h1>");
                //out.println("<a href='index.html'>Volver a index</a>");
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
