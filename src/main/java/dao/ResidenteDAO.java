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
import model.Residente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResidenteDAO {
    
    // CREATE: Insere um novo residente no banco de dados
    public void cadastrarResidente(Residente residente) {
        String sql = "INSERT INTO residentes (nome_residente, cpf_residente, rg_residente, crm_residente, email_residente, telefone_residente, id_unidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, residente.getNomeResidente());
            stmt.setString(2, residente.getCpfResidente());
            stmt.setString(3, residente.getRgResidente());
            stmt.setString(4, residente.getCrmResidente());
            stmt.setString(5, residente.getEmailResidente());
            stmt.setString(6, residente.getTelefoneResidente());

            stmt.executeUpdate();
            System.out.println("Residente cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar residente: " + e.getMessage());
        }
    }

    // READ: Buscar residente por ID
    public Residente buscarResidentePorId(int idResidente) {
        String sql = "SELECT * FROM residentes WHERE id_residente = ?";
        Residente residente = null;

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idResidente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                residente = new Residente();
                residente.setIdResidente(rs.getInt("id_residente"));
                residente.setNomeResidente(rs.getString("nome_residente"));
                residente.setCpfResidente(rs.getString("cpf_residente"));
                residente.setRgResidente(rs.getString("rg_residente"));
                residente.setCrmResidente(rs.getString("crm_residente"));
                residente.setEmailResidente(rs.getString("email_residente"));
                residente.setTelefoneResidente(rs.getString("telefone_residente"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar residente: " + e.getMessage());
        }

        return residente;
    }

    // READ: Buscar todos os residentes
    public List<Residente> listarTodosResidentes() {
        String sql = "SELECT * FROM residentes";
        List<Residente> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Residente residente = new Residente();
                residente.setIdResidente(rs.getInt("id_residente"));
                residente.setNomeResidente(rs.getString("nome_residente"));
                residente.setCpfResidente(rs.getString("cpf_residente"));
                residente.setRgResidente(rs.getString("rg_residente"));
                residente.setCrmResidente(rs.getString("crm_residente"));
                residente.setEmailResidente(rs.getString("email_residente"));
                residente.setTelefoneResidente(rs.getString("telefone_residente"));

                lista.add(residente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar residentes: " + e.getMessage());
        }

        return lista;
    }

    // READ: Buscar residentes por múltiplos filtros (Id, Nome, CPF, RG, CRM ou E-mail)
    public List<Residente> buscarResidentesPorFiltros(String id, String nome, String cpf, String rg, String crm, String email) {
        List<Residente> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM residentes WHERE 1=1");

        // Adicionar condições de filtro ao SQL
        if (!id.isEmpty()) {
            sql.append(" AND id_residente = ?");
        }
        if (!nome.isEmpty()) {
            sql.append(" AND nome_residente ILIKE ?");
        }
        if (!cpf.isEmpty()) {
            sql.append(" AND cpf_residente = ?");
        }
        if (!rg.isEmpty()) {
            sql.append(" AND rg_residente = ?");
        }
        if (!crm.isEmpty()) {
            sql.append(" AND crm_residente = ?");
        }
        if (!email.isEmpty()) {
            sql.append(" AND email_residente ILIKE ?");
        }

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            // Definir parâmetros no PreparedStatement
            if (!id.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(id));
            }
            if (!nome.isEmpty()) {
                stmt.setString(index++, "%" + nome + "%"); // ILIKE usa % para busca parcial
            }
            if (!cpf.isEmpty()) {
                stmt.setString(index++, cpf);
            }
            if (!rg.isEmpty()) {
                stmt.setString(index++, rg);
            }
            if (!crm.isEmpty()) {
                stmt.setString(index++, crm);
            }
            if (!email.isEmpty()) {
                stmt.setString(index++, "%" + email + "%"); // ILIKE usa % para busca parcial
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Residente residente = new Residente();
                residente.setIdResidente(rs.getInt("id_residente"));
                residente.setNomeResidente(rs.getString("nome_residente"));
                residente.setCpfResidente(rs.getString("cpf_residente"));
                residente.setRgResidente(rs.getString("rg_residente"));
                residente.setCrmResidente(rs.getString("crm_residente"));
                residente.setEmailResidente(rs.getString("email_residente"));
                residente.setTelefoneResidente(rs.getString("telefone_residente"));

                lista.add(residente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar residentes: " + e.getMessage());
        }

        return lista;
    }
        
    // UPDATE: Atualiza um residente existente
    public void atualizarResidente(Residente residente) {
        String sql = "UPDATE residentes SET nome_residente = ?, cpf_residente = ?, rg_residente = ?, crm_residente = ?, email_residente = ?, telefone_residente = ? WHERE id_residente = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, residente.getNomeResidente());
            stmt.setString(2, residente.getCpfResidente());
            stmt.setString(3, residente.getRgResidente());
            stmt.setString(4, residente.getCrmResidente());
            stmt.setString(5, residente.getEmailResidente());
            stmt.setString(6, residente.getTelefoneResidente());
            stmt.setInt(7, residente.getIdResidente());

            stmt.executeUpdate();
            System.out.println("Residente atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar residente: " + e.getMessage());
        }
    }

    // DELETE: Remove um residente do banco de dados
    public void deletarResidente(int idResidente) {
        String sql = "DELETE FROM residentes WHERE id_residente = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idResidente);
            stmt.executeUpdate();
            System.out.println("Residente deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar residente: " + e.getMessage());
        }
    }
}
