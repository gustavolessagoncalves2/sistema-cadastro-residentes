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
import model.Matricula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    // CREATE: Insere uma nova matrícula no banco de dados
    public void cadastrarMatricula(Matricula matricula) {
        String sql = "INSERT INTO matriculas (id_residente, id_residencia, status_matricula, data_inicio_matricula, data_conclusao_prevista_matricula, data_desligamento_matricula) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula.getIdResidente());
            stmt.setInt(2, matricula.getIdResidencia());
            stmt.setString(3, matricula.getStatusMatricula());
            stmt.setDate(4, matricula.getDataInicioMatricula());
            stmt.setDate(5, matricula.getDataConclusaoPrevistaMatricula());
            stmt.setDate(6, matricula.getDataDesligamentoMatricula());

            stmt.executeUpdate();
            System.out.println("Matrícula cadastrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar matrícula: " + e.getMessage());
        }
    }

    // READ: Buscar matrícula por ID
    public Matricula buscarMatriculaPorId(int idMatricula) {
        String sql = "SELECT * FROM matriculas WHERE id_matricula = ?";
        Matricula matricula = null;

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMatricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("id_matricula"));
                matricula.setIdResidente(rs.getInt("id_residente"));
                matricula.setIdResidencia(rs.getInt("id_residencia"));
                matricula.setStatusMatricula(rs.getString("status_matricula"));
                matricula.setDataInicioMatricula(rs.getDate("data_inicio_matricula"));
                matricula.setDataConclusaoPrevistaMatricula(rs.getDate("data_conclusao_prevista_matricula"));
                matricula.setDataDesligamentoMatricula(rs.getDate("data_desligamento_matricula"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar matrícula: " + e.getMessage());
        }

        return matricula;
    }

    // READ: Buscar todas as matrículas
    public List<Matricula> listarTodasMatriculas() {
        String sql = "SELECT * FROM matriculas";
        List<Matricula> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("id_matricula"));
                matricula.setIdResidente(rs.getInt("id_residente"));
                matricula.setIdResidencia(rs.getInt("id_residencia"));
                matricula.setStatusMatricula(rs.getString("status_matricula"));
                matricula.setDataInicioMatricula(rs.getDate("data_inicio_matricula"));
                matricula.setDataConclusaoPrevistaMatricula(rs.getDate("data_conclusao_prevista_matricula"));
                matricula.setDataDesligamentoMatricula(rs.getDate("data_desligamento_matricula"));

                lista.add(matricula);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar matrículas: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE: Atualiza uma matrícula existente
    public void atualizarMatricula(Matricula matricula) {
        String sql = "UPDATE matriculas SET id_residente = ?, id_residencia = ?, status_matricula = ?, data_inicio_matricula = ?, data_conclusao_prevista_matricula = ?, data_desligamento_matricula = ? WHERE id_matricula = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula.getIdResidente());
            stmt.setInt(2, matricula.getIdResidencia());
            stmt.setString(3, matricula.getStatusMatricula());
            stmt.setDate(4, matricula.getDataInicioMatricula());
            stmt.setDate(5, matricula.getDataConclusaoPrevistaMatricula());
            stmt.setDate(6, matricula.getDataDesligamentoMatricula());
            stmt.setInt(7, matricula.getIdMatricula());

            stmt.executeUpdate();
            System.out.println("Matrícula atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar matrícula: " + e.getMessage());
        }
    }

    // DELETE: Remove uma matrícula do banco de dados
    public void deletarMatricula(int idMatricula) {
        String sql = "DELETE FROM matriculas WHERE id_matricula = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMatricula);
            stmt.executeUpdate();
            System.out.println("Matrícula deletada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar matrícula: " + e.getMessage());
        }
    }
}

