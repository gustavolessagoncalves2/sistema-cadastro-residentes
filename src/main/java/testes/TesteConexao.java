package testes;


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

public class TesteConexao {
    public static void main(String[] args) {
        try {
            Connection connection = Conexao.conectar();
            // Use a conexão conforme necessário
            
            // Fechar a conexão quando não for mais necessária
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }
}