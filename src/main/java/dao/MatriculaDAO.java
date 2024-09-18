package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gustavogoncalves
 */
import model.Matricula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {
    private Connection connection; // Conexão fornecida pela TelaPrincipal

    // Construtor para receber a conexão
    public MatriculaDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE: Insere uma nova matrícula no banco de dados
    public void cadastrarMatricula(Matricula matricula) {
        String sql = "INSERT INTO matriculas (id_residente, id_residencia, status_matricula, data_inicio_matricula, data_conclusao_prevista_matricula, data_desligamento_matricula) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

    // READ: Buscar matrículas por múltiplos filtros (Id, Residente, Residência, Status, Data de Início e Data de Conclusão)
    public List<Matricula> buscarMatriculasPorFiltros(String id, String idResidente, String idResidencia, String status, String dataInicio, String dataConclusao) {
        List<Matricula> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM matriculas WHERE 1=1");

        // Adiciona condições de filtro ao SQL
        if (!id.isEmpty()) {
            sql.append(" AND id_matricula = ?");
        }
        if (!idResidente.isEmpty()) {
            sql.append(" AND id_residente = ?");
        }
        if (!idResidencia.isEmpty()) {
            sql.append(" AND id_residencia = ?");
        }
        if (!status.isEmpty()) {
            sql.append(" AND status_matricula ILIKE ?");
        }
        if (!dataInicio.isEmpty()) {
            sql.append(" AND data_inicio_matricula >= ?");
        }
        if (!dataConclusao.isEmpty()) {
            sql.append(" AND data_conclusao_prevista_matricula <= ?");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            int index = 1;

            // Define parâmetros no PreparedStatement
            if (!id.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(id)); // Converte o id para inteiro
            }
            if (!idResidente.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(idResidente)); // Converte o idResidente para inteiro
            }
            if (!idResidencia.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(idResidencia)); // Converte o idResidencia para inteiro
            }
            if (!status.isEmpty()) {
                stmt.setString(index++, "%" + status + "%"); // ILIKE usa % para busca parcial
            }
            if (!dataInicio.isEmpty()) {
                stmt.setDate(index++, java.sql.Date.valueOf(dataInicio)); // Converte a data de início para java.sql.Date
            }
            if (!dataConclusao.isEmpty()) {
                stmt.setDate(index++, java.sql.Date.valueOf(dataConclusao)); // Converte a data de conclusão para java.sql.Date
            }

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
            System.out.println("Erro ao buscar matrículas: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE: Atualiza uma matrícula existente
    public void atualizarMatricula(Matricula matricula) {
        String sql = "UPDATE matriculas SET id_residente = ?, id_residencia = ?, status_matricula = ?, data_inicio_matricula = ?, data_conclusao_prevista_matricula = ?, data_desligamento_matricula = ? WHERE id_matricula = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMatricula);
            stmt.executeUpdate();
            System.out.println("Matrícula deletada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar matrícula: " + e.getMessage());
        }
    }
    
    // CHECK: Verifica se a matrícula já existe para o residente e a residência especificados
    public boolean matriculaExiste(int idResidente, int idResidencia) {
        String sql = "SELECT COUNT(*) FROM matriculas WHERE id_residente = ? AND id_residencia = ?";
        boolean existe = false;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idResidente);
            stmt.setInt(2, idResidencia);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                existe = (count > 0);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar existência de matrícula: " + e.getMessage());
        }

        return existe;
    }
}

