<%-- 
    Document   : cliente
    Created on : 27-06-2020, 22:05:16
    Author     : Daniel
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<title>Empleado</title>
<html>    
     <%@include file="/part/head.jsp" %>
    
    <body>
        <div class="container">
            <%@include file="/part/header.jsp" %>

            <form action="empleadoController.do"  method="POST">
                <div class="container shadow">
                    
                    <div class="card shadow w-50 mx-auto" >
                        <div class="card-header bg-dark text-white">
                            
                            <h4>
                                Empleado
                            </h4>
                            
                        </div>					
                        <div class="card-body">	
                            <input type="hidden" name="hidId" value="${empleado.empleadoId}" >
                            <div class="form-group">
                                Nombre 
                                <input type="text" name="txtNombre" value="${empleado.nombre}"  class="form-control" maxlength="45"  required >				
                            </div>
                            <div class="form-group">
                                Apellido Paterno
                                <input type="text" name="txtApellidoPaterno" value="${empleado.apellidoPaterno}" class="form-control" maxlength="45" required>
                            </div>                           
                            
                            <div class="form-group">
                                Apellido Materno
                                <input type="text" name="txtApellidoMaterno" value="${empleado.apellidoMaterno}"  class="form-control" maxlength="45" required>
                            </div>                           
                            
                            <div class="form-group">
                                Mail
                                <input type="email" name="txtMail" value="${empleado.mail}" class="form-control" maxlength="45" required>
                            </div>   
                            
                            <div class="form-group">
                                Telefono
                                <input type="text" name="txtTelefono" value="${empleado.telefono}" class="form-control" maxlength="45" required>
                            </div>   
                            
                            <div class="form-group">
                                Tipo Empleado
                                <select name="cboTipoEmpleado" class="form-control" required onchange="submit()" >
                                    
                                    <option value=""></option>
                                    <c:forEach  var="te"  items="${tiposEmpleado}" >
                                        
                                        <option value="${te.tipoEmpleadoId}"  
                                                ${te.tipoEmpleadoId == empleado.getTipoEmpleado().getTipoEmpleadoId() ? ' selected ' : ' '}>
                                        
                                            
                                            ${te.descripcion}
                                        </option>
                                    
                                    </c:forEach>
                                    
                                </select>
                                
                            </div>
                            
                            <div class="form-group">
                                Turno
                                <select name="cboTurno" class="form-control" required  >
                                    
                                    <option value=""></option>
                                    <c:forEach  var="t"  items="${turnos}" >
                                        
                                        <option value="${t.turnoId}"
                                            ${t.turnoId == empleado.getTurno().getTurnoId() ? ' selected ' : ' '  } >
                                            ${t.descripcion}
                                        </option>
                                    
                                    </c:forEach>
                                    
                                </select>
                                
                                
                            </div>
                                                       
                            
                            <hr>
                            
                            <div class="form-group text-center row">
                                
                                <div class="col-6">                                
                                    <input type="submit" value="Grabar" name="action" class="btn btn-primary w-100"/>
                                </div>
                                <div class="col-6">                                
                                    <a class="btn btn-warning w-100" href="empleadoController.do?action=listar" >Volver a Empleados</a> 
                                </div>                                
                            </div>
                            
                            <div class="${estiloMensaje}" role="alert">
                                ${mensaje}                                
                            </div>
                            
                        </div>
                    </div>    
                </div>                   

            </form>

             <%@include file="/part/footer.jsp" %>
        </div>
    </body>
</html>


