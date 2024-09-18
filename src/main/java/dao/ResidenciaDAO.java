package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gustavogoncalves
 */
import database.Conexao;
import model.Residencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ResidenciaDAO {
    
    // CREATE: Insere uma nova residência no banco de dados
    public void cadastrarResidencia(Residencia residencia) {
        String sql = "INSERT INTO residencias (nome_residencia, apelido_residencia, categoria_residencia) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, residencia.getNomeResidencia());
            stmt.setString(2, residencia.getApelidoResidencia());
            stmt.setString(3, residencia.getCategoriaResidencia());

            stmt.executeUpdate();
            System.out.println("Residência cadastrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar residência: " + e.getMessage());
        }
    }

    // READ: Buscar residência por ID
    public Residencia buscarResidenciaPorId(int idResidencia) {
        String sql = "SELECT * FROM residencias WHERE id_residencia = ?";
        Residencia residencia = null;

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idResidencia);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                residencia = new Residencia();
                residencia.setIdResidencia(rs.getInt("id_residencia"));
                residencia.setNomeResidencia(rs.getString("nome_residencia"));
                residencia.setApelidoResidencia(rs.getString("apelido_residencia"));
                residencia.setCategoriaResidencia(rs.getString("categoria_residencia"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar residência: " + e.getMessage());
        }

        return residencia;
    }

    // READ: Buscar todas as residências
    public List<Residencia> listarTodasResidencias() {
        String sql = "SELECT * FROM residencias";
        List<Residencia> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Residencia residencia = new Residencia();
                residencia.setIdResidencia(rs.getInt("id_residencia"));
                residencia.setNomeResidencia(rs.getString("nome_residencia"));
                residencia.setApelidoResidencia(rs.getString("apelido_residencia"));
                residencia.setCategoriaResidencia(rs.getString("categoria_residencia"));

                lista.add(residencia);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar residências: " + e.getMessage());
        }

        return lista;
    }

    // READ: Buscar residências por múltiplos filtros (Id, Nome, Apelido, Categoria ou Duração)
    public List<Residencia> buscarResidenciasPorFiltros(String id, String nome, String apelido, String categoria) {
    List<Residencia> lista = new ArrayList<>();
    String sql = "SELECT * FROM residencias WHERE "
               + "(? IS NULL OR id_residencia = ?) "
               + "AND (? IS NULL OR nome_residencia ILIKE ?) "
               + "AND (? IS NULL OR apelido_residencia ILIKE ?) "
               + "AND (? IS NULL OR categoria_residencia ILIKE ?) ";

    try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, id.isEmpty() ? null : id);
        stmt.setString(2, id);
        stmt.setString(3, nome.isEmpty() ? null : nome);
        stmt.setString(4, "%" + nome + "%");
        stmt.setString(5, apelido.isEmpty() ? null : apelido);
        stmt.setString(6, "%" + apelido + "%");
        stmt.setString(7, categoria.isEmpty() ? null : categoria);
        stmt.setString(8, "%" + categoria + "%");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Residencia residencia = new Residencia();
            residencia.setIdResidencia(rs.getInt("id_residencia"));
            residencia.setNomeResidencia(rs.getString("nome_residencia"));
            residencia.setApelidoResidencia(rs.getString("apelido_residencia"));
            residencia.setCategoriaResidencia(rs.getString("categoria_residencia"));
            lista.add(residencia);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao buscar residências: " + e.getMessage());
    }

    return lista;
}
    
    // UPDATE: Atualiza uma residência existente
    public void atualizarResidencia(Residencia residencia) {
        String sql = "UPDATE residencias SET nome_residencia = ?, apelido_residencia = ?, categoria_residencia = ? WHERE id_residencia = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, residencia.getNomeResidencia());
            stmt.setString(2, residencia.getApelidoResidencia());
            stmt.setString(3, residencia.getCategoriaResidencia());
            stmt.setInt(5, residencia.getIdResidencia());

            stmt.executeUpdate();
            System.out.println("Residência atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar residência: " + e.getMessage());
        }
    }

    // DELETE: Remove uma residência do banco de dados
    public void deletarResidencia(int idResidencia) {
        String sql = "DELETE FROM residencias WHERE id_residencia = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idResidencia);
            stmt.executeUpdate();
            System.out.println("Residência deletada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar residência: " + e.getMessage());
        }
    }
}

