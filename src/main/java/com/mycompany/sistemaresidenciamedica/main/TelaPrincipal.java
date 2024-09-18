/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaresidenciamedica.main;

/**
 *
 * @author gustavogoncalves
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.CadastroMatricula;
import view.CadastroResidencia;
import view.CadastroResidente;
import view.ConsultaResidente;
import view.ConsultaResidencia; 

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Cadastro de Médicos Residentes");
        setSize(400, 400); // Aumentei o tamanho para acomodar o novo botão
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1)); // Dividindo a tela em 5 linhas para os botões

        // Botão para cadastro de residente
        JButton btnCadastroResidente = new JButton("Cadastrar Residente");
        btnCadastroResidente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroResidente().setVisible(true); // Abrir a tela de cadastro de residente
            }
        });
        add(btnCadastroResidente);

        // Botão para cadastro de residência
        JButton btnCadastroResidencia = new JButton("Cadastrar Residência");
        btnCadastroResidencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroResidencia().setVisible(true); // Abrir a tela de cadastro de residência
            }
        });
        add(btnCadastroResidencia);

        // Botão para cadastro de matrícula
        JButton btnCadastroMatricula = new JButton("Cadastrar Matrícula");
        btnCadastroMatricula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroMatricula().setVisible(true); // Abrir a tela de cadastro de matrícula
            }
        });
        add(btnCadastroMatricula);

        // Botão para consulta de residente
        JButton btnConsultaResidente = new JButton("Consultar Residente");
        btnConsultaResidente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaResidente().setVisible(true); // Abrir a tela de consulta de residente
            }
        });
        add(btnConsultaResidente);

        // Novo botão para consulta de residência
        JButton btnConsultaResidencia = new JButton("Consultar Residência");
        btnConsultaResidencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaResidencia().setVisible(true); // Abrir a tela de consulta de residência
            }
        });
        add(btnConsultaResidencia);

        // Botão para sair
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o sistema
            }
        });
        add(btnSair);
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}

