/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.modelo;

/**
 *
 * @author Daniel
 */
public class EmpleadoModel {

    /**
     * @return the turno
     */
    public TurnoModel getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(TurnoModel turno) {
        this.turno = turno;
    }

    public EmpleadoModel() {
    }

    public EmpleadoModel(int empleadoId, String nombre, String apellidoPaterno, String apellidoMaterno, String mail, String telefono, TipoEmpleadoModel tipoEmpleado, TurnoModel turno) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.mail = mail;
        this.telefono = telefono;
        this.tipoEmpleado = tipoEmpleado;
        this.turno = turno;
    }

    
    
    /**
     * @return the tipoEmpleado
     */
    public TipoEmpleadoModel getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * @param tipoEmpleado the tipoEmpleado to set
     */
    public void setTipoEmpleado(TipoEmpleadoModel tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
   
    /**
     * @return the empleadoId
     */
    public int getEmpleadoId() {
        return empleadoId;
    }

    /**
     * @param empleadoId the empleadoId to set
     */
    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    private int empleadoId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String mail;
    private String telefono;
    
    private TipoEmpleadoModel tipoEmpleado;
    private TurnoModel turno;
}
