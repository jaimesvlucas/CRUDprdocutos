<%-- 
    Document   : listar
    Created on : 5 mar. 2021, 20:52:47
    Author     : User
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Productos> productos = (List<Productos>) request.getAttribute("productos");
        %>
        <table class='table'>
            <tr>
                <th>id</th>
                <th>Nombre</th>
                <th>Imagen</th>
                <th>Categoria</th>
                <th>Precio</th>
                <th>Borrar</th>
            </tr>
                <%for(Productos p: productos){%>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getNombre()%></td>
                        <td><%= p.getImagen()%></td>
                        <td><%= p.getCategoria()%></td>
                        <td><%= p.getPrecio() %></td>
                        <td><a onclick="return confirmation()" class="btn btn-primary" href="ServletProductos?op=borrar&id=<%= p.getId() %>">Borrar</a></td>
                    </tr>
                <%}%>
        </table>
        <script type='text/javascript'>
            function confirmation(){
                if(confirm('Esta seguro de eleminar el registro?')==true){
                    alert('Procedo a borrar');
                    return true;
                }else{
                    alert('Cancelo la eliminacion');
                    return false;
                }
            }
        </script>
    </body>
</html>
