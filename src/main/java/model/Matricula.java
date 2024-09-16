package model;


import java.sql.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gustavogoncalves
 */
public class Matricula {
    private int idMatricula;
    private int idResidente;
    private int idResidencia;
    private String statusMatricula;
    private Date dataInicioMatricula;
    private Date dataConclusaoPrevistaMatricula;
    private Date dataDesligamentoMatricula;    

    // Construtor
    public Matricula() {}

    // Getters e Setters
    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(int idResidente) {
        this.idResidente = idResidente;
    }

    public int getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(int idResidencia) {
        this.idResidencia = idResidencia;
    }

    public String getStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(String statusMatricula) {
        this.statusMatricula = statusMatricula;
    }

    public Date getDataInicioMatricula() {
        return dataInicioMatricula;
    }

    public void setDataInicioMatricula(Date dataInicioMatricula) {
        this.dataInicioMatricula = dataInicioMatricula;
    }

    public Date getDataConclusaoPrevistaMatricula() {
        return dataConclusaoPrevistaMatricula;
    }

    public void setDataConclusaoPrevistaMatricula(Date dataConclusaoPrevistaMatricula) {
        this.dataConclusaoPrevistaMatricula = dataConclusaoPrevistaMatricula;
    }

    public Date getDataDesligamentoMatricula() {
        return dataDesligamentoMatricula;
    }

    public void setDataDesligamentoMatricula(Date dataDesligamentoMatricula) {
        this.dataDesligamentoMatricula = dataDesligamentoMatricula;
    }
}
