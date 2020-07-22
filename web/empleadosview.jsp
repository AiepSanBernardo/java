<%-- 
    Document   : empleadoview
    Created on : 30-06-2020, 19:03:25
    Author     : Daniel
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<title>Empleados</title>
<html>    
    <%@include file="/part/head.jsp" %>

    <body>
        <div class="container shadow">            
            <%@include file="/part/header.jsp" %>

            <div class="card shadow w-100 mx-auto" >

                <div class="card-header bg-dark text-white">
                    <h4>Listado de Empleados</h4>
                </div>	

                <div class="card-body">						                            	
                    <div style="overflow: auto;">
                        <table class="table  table-stripper hover w-100">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Nombre</th>
                                    <th>Apellido Paterno</th>
                                    <th>Apellido Materno</th>
                                    <th>Mail</th>
                                    <th>Telefono</th>                                
                                    <th>Tipo Empleado</th>
                                    <th>Turno</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="empleado" items="${empleados}">                                
                                    <tr>    
                                        <td>${empleado.empleadoId}</td>
                                        <td>${empleado.nombre}</td>
                                        <td>${empleado.apellidoPaterno}</td>
                                        <td>${empleado.apellidoMaterno}</td>
                                        <td>${empleado.mail}</td>
                                        <td>${empleado.telefono}</td>    
                                        <td>${empleado.tipoEmpleado.descripcion}</td>    
                                        <td>${empleado.turno.descripcion}</td>    

                                        <td>
                                            <a href="empleadoController.do?action=eliminar&id=${empleado.empleadoId}"
                                               class="btn btn-danger" onclick=" return confirm('¿Seguro que desea eliminar?)')">Elimina</a> 
                                        </td>  
                                        <td>
                                            <a href="empleadoController.do?action=editar&id=${empleado.empleadoId}"
                                               class="btn btn-warning" >Editar</a>                                                                                                               
                                        </td>

                                    </tr>                            
                                </c:forEach>
                            </tbody>

                            <div class="${estiloMensaje}" role="alert">
                                ${mensaje}                                
                            </div>

                        </table>  
                    </div>
                    <a class="btn btn-primary" href="empleadoController.do?action=nuevo">Nuevo</a>
                </div>
                <div>

                </div>

            </div>          
        </div>
    </body>
</html>
