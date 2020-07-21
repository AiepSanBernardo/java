/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.controller;

import cl.aiep.empleado.modelo.EmpleadoModel;
import cl.aiep.empleado.dao.EmpleadoDAO;
import cl.aiep.empleado.dao.TipoEmpleadoDAO;
import cl.aiep.empleado.dao.TurnoDAO;
import cl.aiep.empleado.dao.UsuarioDAO;
import cl.aiep.empleado.modelo.TipoEmpleadoModel;
import cl.aiep.empleado.modelo.TurnoModel;
import cl.aiep.empleado.modelo.UsuarioModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class EmpleadoController extends HttpServlet {

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

        String accion = request.getParameter("action");
        String cboTipoEmpleado = request.getParameter("cboTipoEmpleado");

        accion = accion == null ? "" : accion;

        switch (accion) {

            case "index":
                inicio(request, response);
                break;

            case "nuevo":
                nuevoEmpleado(request, response);
                break;

            case "editar":
                editaEmpleado(request, response);
                break;

            case "listar":
                verificaAccesoPermitido(request, response);
                listarEmpleado(request, response);
                break;

            case "Grabar":
                grabarEmpleado(request, response);
                break;

            case "eliminar":
                eliminaCliente(request, response);
                break;

            case "Acceder":
                accederSistema(request, response);
                break;

            default:
                if (cboTipoEmpleado != "") {
                    cambiarTurno(request, response);
                }

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

    private void nuevoEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmpleadoModel empleado = new EmpleadoModel();
        List<TipoEmpleadoModel> tiposEmpleado = new TipoEmpleadoDAO().getTiposEmpleado();

        request.setAttribute("empleado", empleado);
        request.setAttribute("tiposEmpleado", tiposEmpleado);

        request.getRequestDispatcher("empleadoview.jsp").forward(request, response);

    }

    private void editaEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        EmpleadoModel empleado = new EmpleadoDAO().getEmpleado(id);
        List<TipoEmpleadoModel> tiposEmpleado = new TipoEmpleadoDAO().getTiposEmpleado();
        int idTipoEmpleado = empleado.getTipoEmpleado().getTipoEmpleadoId();
        List<TurnoModel> turnos = new TurnoDAO().getTurnos(idTipoEmpleado);

        request.setAttribute("empleado", empleado);
        request.setAttribute("tiposEmpleado", tiposEmpleado);
        request.setAttribute("tipoEmpleado", empleado.getTipoEmpleado().getTipoEmpleadoId());

        request.setAttribute("turnos", turnos);
        request.setAttribute("turno", empleado.getTurno().getTurnoId());

        request.getRequestDispatcher("empleadoview.jsp").forward(request, response);

    }

    private void inicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    private void grabarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje = "";
        String estiloMensaje = "";
        String identificador = request.getParameter("hidId");
        boolean exito = false;
        int id = 0;

        if (identificador != null) {
            id = Integer.parseInt(identificador);
        }

        EmpleadoModel model = new EmpleadoModel();
        model.setEmpleadoId(id);
        model.setNombre(request.getParameter("txtNombre"));
        model.setApellidoPaterno(request.getParameter("txtApellidoPaterno"));
        model.setApellidoMaterno(request.getParameter("txtApellidoMaterno"));
        model.setMail(request.getParameter("txtMail"));
        model.setTelefono(request.getParameter("txtTelefono"));

        int idTipoEmpleado = Integer.parseInt(request.getParameter("cboTipoEmpleado"));
        TipoEmpleadoModel tipoEmpleado = new TipoEmpleadoModel();
        tipoEmpleado.setTipoEmpleadoId(idTipoEmpleado);
        model.setTipoEmpleado(tipoEmpleado);

        int idTurno = Integer.parseInt(request.getParameter("cboTurno"));
        TurnoModel turno = new TurnoModel();
        turno.setTurnoId(idTurno);

        model.setTurno(turno);

        if (model.getEmpleadoId() == 0) {
            EmpleadoDAO dao = new EmpleadoDAO();
            exito = dao.insert(model);

            if (!exito) {
                mensaje = "Error al grabar Empleado";
                estiloMensaje = "alert alert-danger text-center";
            } else {
                mensaje = "Empleado grabado con exito";
                estiloMensaje = "alert alert-success text-center";
            }
        } else {

            EmpleadoDAO dao = new EmpleadoDAO();
            exito = dao.update(model);

            if (!exito) {
                mensaje = "Error al actualizar Empleado";
                estiloMensaje = "alert alert-danger text-center";
            } else {
                mensaje = "Empleado actualizado con exito";
                estiloMensaje = "alert alert-success text-center";
            }
        }

        request.setAttribute("mensaje", mensaje);
        request.setAttribute("estiloMensaje", estiloMensaje);
        request.getRequestDispatcher("empleadoview.jsp").forward(request, response);

    }

    private void listarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<EmpleadoModel> entidades = new EmpleadoDAO().getEmpleados();
        request.setAttribute("empleados", entidades);
        request.getRequestDispatcher("empleadosview.jsp").forward(request, response);

    }

    private void eliminaCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String mensaje = "";
        String estiloMensaje = "";
        boolean exito = new EmpleadoDAO().delEmpleados(id);

        if (exito) {
            mensaje = "Empleado eliminado con exito";
            estiloMensaje = "alert alert-success text-center";
        } else {
            mensaje = "Empleado no pudo ser eliminado";
            estiloMensaje = "alert alert-danger text-center";
        }

        request.setAttribute("mensaje", mensaje);
        request.setAttribute("estiloMensaje", estiloMensaje);
        request.getRequestDispatcher("empleadoController.do?action=listar").forward(request, response);

    }

    private void cambiarTurno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cboTipoEmpleado = request.getParameter("cboTipoEmpleado");
        List<TurnoModel> turnos = new TurnoDAO().getTurnos(Integer.parseInt(cboTipoEmpleado));

        //Mantenemos los datos del empleado para enviar en el request, los sacamos desde los input de la pagina
        EmpleadoModel empleado = new EmpleadoModel();
        int id = Integer.parseInt(request.getParameter("hidId"));
        empleado.setEmpleadoId(id);
        empleado.setNombre(request.getParameter("txtNombre"));
        empleado.setApellidoPaterno(request.getParameter("txtApellidoPaterno"));
        empleado.setApellidoMaterno(request.getParameter("txtApellidoMaterno"));
        empleado.setMail(request.getParameter("txtMail"));
        empleado.setTelefono(request.getParameter("txtTelefono"));

        String idTipoEmpleadoString = request.getParameter("cboTipoEmpleado");
        int idTipoEmpleado = 0;
        if (idTipoEmpleadoString != null) {
            if (idTipoEmpleadoString != "") {
                idTipoEmpleado = Integer.parseInt(idTipoEmpleadoString);
                empleado.setTipoEmpleado(new TipoEmpleadoDAO().getTipoEmpleado(idTipoEmpleado));
            }
        }

        List<TipoEmpleadoModel> tiposEmpleado = new TipoEmpleadoDAO().getTiposEmpleado();

        request.setAttribute("empleado", empleado);
        request.setAttribute("tiposEmpleado", tiposEmpleado);
        request.setAttribute("tipoEmpleado", empleado.getTipoEmpleado().getTipoEmpleadoId());
        request.setAttribute("turnos", turnos);

        request.getRequestDispatcher("empleadoview.jsp").forward(request, response);
    }

    private void accederSistema(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nick = request.getParameter("txtNick");
        String pass = request.getParameter("txtPassword");

        boolean permitido = false;
        boolean recordar = false;

        String mensaje = "";
        String estiloMensaje = "";

        UsuarioModel model = new UsuarioModel();

        if (nick != null && pass != null) {

            model = new UsuarioDAO().getUsuario(nick);

            if (model.getUsuarioId() > 0) {
                permitido = model.getPassword().equals(pass);
            }
        }

        if (permitido) {
            String chkRecordar = request.getParameter("chkRecordar");

            recordar = chkRecordar != null;

            Cookie cookie = new Cookie("EmpleadoUser", nick);
            if (recordar) {
                cookie.setMaxAge(600);
            } else {
                cookie.setMaxAge(-1);
            }

            response.addCookie(cookie);
            response.sendRedirect("empleadoController.do?action=index");

            //request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            mensaje = "Credenciales no validas";
            estiloMensaje = "alert alert-danger text-center";
            model.setNick(nick);
            request.setAttribute("usuario", model);
            request.setAttribute("mensaje", mensaje);
            request.setAttribute("estiloMensaje", estiloMensaje);
            request.getRequestDispatcher("loginview.jsp").forward(request, response);
        }
    }

    private void verificaAccesoPermitido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userCookie = getCookieValue(  request.getCookies(), "EmpleadoUser" ) ;
        if( "".equals(userCookie) ){
            request.getRequestDispatcher("loginview.jsp").forward( request, response);
        }
    }

    private String getCookieValue(Cookie[] cookies, String cookieName) {
        String cookieValue = "";
        Cookie cookie;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }
        return cookieValue;
    }

}
