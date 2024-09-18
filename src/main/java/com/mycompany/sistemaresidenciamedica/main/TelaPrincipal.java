/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaresidenciamedica.main;

/**
 *
 * @author gustavogoncalves
 */
//import database.Conexao;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.SQLException;
//import view.CadastroMatricula;
//import view.CadastroResidencia;
//import view.CadastroResidente;
//import view.ConsultaResidente;
//import view.ConsultaResidencia; 
//
//public class TelaPrincipal extends JFrame {
//    private Connection connection;
//
//    public TelaPrincipal(Connection connection) {
//        this.connection = connection;
//
//        setTitle("Sistema de Cadastro de Médicos Residentes");
//        setSize(400, 400); // Tamanho ajustado para acomodar os botões
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(5, 1)); // Ajuste para incluir o botão "Sair"
//
//        // Botão para cadastro de residente
//        JButton btnCadastroResidente = new JButton("Cadastrar Residente");
//        btnCadastroResidente.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new CadastroResidente(connection).setVisible(true);
//            }
//        });
//        add(btnCadastroResidente);
//
//        // Botão para cadastro de residência
//        JButton btnCadastroResidencia = new JButton("Cadastrar Residência");
//        btnCadastroResidencia.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new CadastroResidencia(connection).setVisible(true);
//            }
//        });
//        add(btnCadastroResidencia);
//
//        // Botão para cadastro de matrícula
//        JButton btnCadastroMatricula = new JButton("Cadastrar Matrícula");
//        btnCadastroMatricula.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new CadastroMatricula(connection).setVisible(true);
//            }
//        });
//        add(btnCadastroMatricula);
//
//        // Botão para consulta de residente
//        JButton btnConsultaResidente = new JButton("Consultar Residente");
//        btnConsultaResidente.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new ConsultaResidente(connection).setVisible(true);
//            }
//        });
//        add(btnConsultaResidente);
//
//        // Botão para consulta de residência
//        JButton btnConsultaResidencia = new JButton("Consultar Residência");
//        btnConsultaResidencia.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new ConsultaResidencia(connection).setVisible(true);
//            }
//        });
//        add(btnConsultaResidencia);
//
//        // Botão para sair
//        JButton btnSair = new JButton("Sair");
//        btnSair.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if (connection != null && !connection.isClosed()) {
//                        connection.close(); // Fecha a conexão ao sair
//                    }
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//                System.exit(0); // Fecha o sistema
//            }
//        });
//        add(btnSair);
//    }
//
//    public static void main(String[] args) {
//        try {
//            Connection connection = Conexao.conectar(); // Conecta ao banco de dados
//            new TelaPrincipal(connection).setVisible(true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

import database.Conexao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import view.CadastroMatricula;
import view.CadastroResidencia;
import view.CadastroResidente;
import view.ConsultaResidente;
import view.ConsultaResidencia;
import view.ConsultaMatricula; // Importa a nova tela

public class TelaPrincipal extends JFrame {
    private Connection connection;

    public TelaPrincipal(Connection connection) {
        this.connection = connection;

        setTitle("Sistema de Cadastro de Médicos Residentes");
        setSize(400, 450); // Tamanho ajustado para acomodar o novo botão
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1)); // Ajusta para incluir o botão "Sair"

        // Botão para cadastro de residente
        JButton btnCadastroResidente = new JButton("Cadastrar Residente");
        btnCadastroResidente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroResidente(connection).setVisible(true);
            }
        });
        add(btnCadastroResidente);

        // Botão para cadastro de residência
        JButton btnCadastroResidencia = new JButton("Cadastrar Residência");
        btnCadastroResidencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroResidencia(connection).setVisible(true);
            }
        });
        add(btnCadastroResidencia);

        // Botão para cadastro de matrícula
        JButton btnCadastroMatricula = new JButton("Cadastrar Matrícula");
        btnCadastroMatricula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroMatricula(connection).setVisible(true);
            }
        });
        add(btnCadastroMatricula);

        // Botão para consulta de residente
        JButton btnConsultaResidente = new JButton("Consultar Residente");
        btnConsultaResidente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaResidente(connection).setVisible(true);
            }
        });
        add(btnConsultaResidente);

        // Botão para consulta de residência
        JButton btnConsultaResidencia = new JButton("Consultar Residência");
        btnConsultaResidencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaResidencia(connection).setVisible(true);
            }
        });
        add(btnConsultaResidencia);

        // Botão para consulta de matrícula
        JButton btnConsultaMatricula = new JButton("Consultar Matrícula");
        btnConsultaMatricula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaMatricula(connection).setVisible(true);
            }
        });
        add(btnConsultaMatricula);

        // Botão para sair
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close(); // Fecha a conexão ao sair
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.exit(0); // Fecha o sistema
            }
        });
        add(btnSair);
    }

    public static void main(String[] args) {
        try {
            Connection connection = Conexao.conectar(); // Conecta ao banco de dados
            new TelaPrincipal(connection).setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

