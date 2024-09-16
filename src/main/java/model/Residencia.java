package model;


import java.time.Duration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gustavogoncalves
 */
public class Residencia {
    private int idResidencia;
    private String nomeResidencia;
    private String apelidoResidencia;
    private String categoriaResidencia;
    private Duration duracaoResidencia;

    // Construtor
    public Residencia() {}

    // Getters e Setters
    public int getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(int idResidencia) {
        this.idResidencia = idResidencia;
    }

    public String getNomeResidencia() {
        return nomeResidencia;
    }

    public void setNomeResidencia(String nomeResidencia) {
        this.nomeResidencia = nomeResidencia;
    }

    public String getApelidoResidencia() {
        return apelidoResidencia;
    }

    public void setApelidoResidencia(String apelidoResidencia) {
        this.apelidoResidencia = apelidoResidencia;
    }

    public String getCategoriaResidencia() {
        return categoriaResidencia;
    }

    public void setCategoriaResidencia(String categoriaResidencia) {
        this.categoriaResidencia = categoriaResidencia;
    }

    public Duration getDuracaoResidencia() {
        return duracaoResidencia;
    }

    public void setDuracaoResidencia(Duration duracaoResidencia) {
        this.duracaoResidencia = duracaoResidencia;
    }
}    

