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
public class TipoEmpleadoModel {

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

    /**
     * @return el identificador unico del tipo de empleado
     */
    public int getTipoEmpleadoId() {
        return tipoEmpleadoId;
    }

    /**
     * @param tipoEmpleadoId the tipoEmpleadoId to set
     */
    public void setTipoEmpleadoId(int tipoEmpleadoId) {
        this.tipoEmpleadoId = tipoEmpleadoId;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
    private int tipoEmpleadoId;
    private String descripcion;
    private TurnoModel turno;
}
