package testes;

import javax.swing.JOptionPane;
import database.Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gustavogoncalves
 */

//public class TesteConexao {
//    public static void main(String[] args) {
//        try {
//            Connection conn = Conexao.conectar();
//            System.out.println("Conexão bem-sucedida!");
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println("Falha na conexão: " + e.getMessage());
//        }
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao2 extends JFrame {
    private JButton testarConexaoButton;
    private JTextArea resultadoTextArea;

    public TesteConexao2() {
        setTitle("Teste de Conexão com o Banco de Dados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criar o botão para testar a conexão
        testarConexaoButton = new JButton("Testar Conexão");
        testarConexaoButton.addActionListener(e -> testarConexao());

        // Criar a área de texto para exibir o resultado
        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);
        resultadoTextArea.setLineWrap(true);
        resultadoTextArea.setWrapStyleWord(true);

        // Adicionar componentes à janela
        add(testarConexaoButton, BorderLayout.NORTH);
        add(new JScrollPane(resultadoTextArea), BorderLayout.CENTER);
    }

    private void testarConexao() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Conectar ao banco de dados
                Connection connection = Conexao.conectar();
                if (connection != null) {
                    // Exibir mensagem de sucesso
                    resultadoTextArea.setText("Conexão estabelecida com sucesso!");
                    // Fechar a conexão
                    connection.close();
                }
            } catch (SQLException e) {
                // Exibir mensagem de erro
                resultadoTextArea.setText("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        // Garantir que o código Swing seja executado no Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            TesteConexao2 frame = new TesteConexao2();
            frame.setVisible(true);
        });
    }
}

