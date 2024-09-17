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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Conexao {

    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:postgresql://10.86.33.178:5432/residenciasSMS";
    // Usuário do banco de dados
    private static final String USER = "postgres";
    
    public static Connection conectar() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a senha do banco de dados: ");
        String password = scanner.nextLine();  // Solicita a senha

        Connection connection = null;
        try {
            // Carregar o driver do PostgreSQL
            Class.forName("org.postgresql.Driver");
            
            // Estabelecer a conexão com o banco de dados
            connection = DriverManager.getConnection(URL, USER, password);
            System.out.println("Conexão estabelecida com sucesso!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do PostgreSQL não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;
        }
        
        return connection;
    }
}
