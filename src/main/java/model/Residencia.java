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
   
    // Método toString() sobrescrito para representar a residência
    @Override
    public String toString() {
        return nomeResidencia + " (" + apelidoResidencia + ")"; // Retorna o nome da Residência e seu apelido para ser exibido no JComboBox
    }
}    

