package database;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gustavogoncalves
 */
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Conexao {
//    private static final String URL = "jdbc:postgresql://localhost:5432/residenciasSMS";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "1234";
//
//    public static Connection conectar() throws SQLException {
//        try {
//            return DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (SQLException e) {
//            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
//            throw e;
//        }
//    }
//}


//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Conexao {
//    private static final String URL = "jdbc:postgresql://10.86.33.178:5432/residenciasSMS";
//    private static final String USER = "postgres";
//    
//    public static Connection conectar() throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Digite a senha do banco de dados: ");
//        String password = scanner.nextLine();  // Solicita a senha
//
//        try {
//            return DriverManager.getConnection(URL, USER, password);
//        } catch (SQLException e) {
//            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
//            throw e;
//        }
//    }
//}

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

//public class Conexao {
//
//    // URL de conexão com o banco de dados
//    private static final String URL = "jdbc:postgresql://10.86.33.178:5432/residenciasSMS";
//    // Usuário do banco de dados
//    private static final String USER = "postgres";
//    
//    public static Connection conectar() throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Digite a senha do banco de dados: ");
//        String password = scanner.nextLine();  // Solicita a senha
//
//        Connection connection = null;
//        try {
//            // Carregar o driver do PostgreSQL
//            Class.forName("org.postgresql.Driver");
//            
//            // Estabelecer a conexão com o banco de dados
//            connection = DriverManager.getConnection(URL, USER, password);
//            System.out.println("Conexão estabelecida com sucesso!");
//            
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver do PostgreSQL não encontrado.");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
//            throw e;
//        }
//        
//        return connection;
//    }
//}

public class Conexao {
    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:postgresql://10.86.33.178:5432/residenciasSMS";
    // Usuário do banco de dados
    private static final String USER = "postgres";

    public static Connection conectar() throws SQLException {
        // Criar um painel para a caixa de diálogo
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Digite a senha do banco de dados:");
        JPasswordField passwordField = new JPasswordField(20);
        panel.add(label);
        panel.add(passwordField);

        // Mostrar a caixa de diálogo
        int option = JOptionPane.showConfirmDialog(null, panel, "Autenticação", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String password = new String(passwordField.getPassword());

            if (password.isEmpty()) {
                throw new SQLException("Senha não fornecida.");
            }

            Connection connection = null;
            try {
                // Carregar o driver do PostgreSQL
                Class.forName("org.postgresql.Driver");

                // Estabelecer a conexão com o banco de dados
                connection = DriverManager.getConnection(URL, USER, password);
                JOptionPane.showMessageDialog(null, "Conexão estabelecida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Driver do PostgreSQL não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                throw e;
            }

            return connection;
        } else {
            throw new SQLException("Senha não fornecida.");
        }
    }
}
