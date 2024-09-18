/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author gustavogoncalves
 */
import dao.MatriculaDAO;
import model.Matricula;
import dao.ResidenciaDAO;
import model.Residencia;
import dao.ResidenteDAO;
import model.Residente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Date;
import java.sql.Connection;

public class CadastroMatricula extends JFrame {
    private Connection connection;
    
    private JComboBox<Residente> residenteComboBox;
    private JComboBox<Residencia> residenciaComboBox;
    private JTextField statusField;
    private JTextField dataInicioField;
    private JTextField dataConclusaoField;
    private JTextField dataDesligamentoField;
    private JButton salvarButton;

    public CadastroMatricula(Connection connection) {
        this.connection = connection;
        
        setTitle("Cadastro de Matrícula");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Buscar Residentes e Residências para popular os combo boxes
        List<Residente> residentes = buscarResidentes();
        List<Residencia> residencias = buscarResidencias();

        // Combo box de Residente
        residenteComboBox = new JComboBox<>(residentes.toArray(new Residente[0]));
        add(new JLabel("Selecione o Residente:"));
        add(residenteComboBox);

        // Combo box de Residência
        residenciaComboBox = new JComboBox<>(residencias.toArray(new Residencia[0]));
        add(new JLabel("Selecione a Residência:"));
        add(residenciaComboBox);

        // Campo para status da matrícula
        statusField = new JTextField(20);
        add(new JLabel("Status da Matrícula:"));
        add(statusField);

        // Campo para data de início da matrícula
        dataInicioField = new JTextField(20);
        add(new JLabel("Data de Início da Matrícula (yyyy-mm-dd):"));
        add(dataInicioField);

        // Campo para data de conclusão prevista
        dataConclusaoField = new JTextField(20);
        add(new JLabel("Data de Conclusão Prevista (yyyy-mm-dd):"));
        add(dataConclusaoField);

        // Campo para data de desligamento
        dataDesligamentoField = new JTextField(20);
        add(new JLabel("Data de Desligamento (yyyy-mm-dd):"));
        add(dataDesligamentoField);

        // Botão de salvar
        salvarButton = new JButton("Salvar Matrícula");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarMatricula();
            }
        });
        add(salvarButton);
    }

    private List<Residente> buscarResidentes() {
        ResidenteDAO residenteDAO = new ResidenteDAO();
        return residenteDAO.listarTodosResidentes(); // Método que busca todos os residentes com todos os dados necessários
    }

    private List<Residencia> buscarResidencias() {
        ResidenciaDAO residenciaDAO = new ResidenciaDAO();
        return residenciaDAO.listarTodasResidencias(); // Método que busca todas as residências com todos os dados necessários
    }

    private void salvarMatricula() {
        try {
            Residente residenteSelecionado = (Residente) residenteComboBox.getSelectedItem();
            Residencia residenciaSelecionada = (Residencia) residenciaComboBox.getSelectedItem();
            String statusMatricula = statusField.getText();

            // Converter Strings para java.sql.Date
            java.sql.Date dataInicioMatricula = java.sql.Date.valueOf(dataInicioField.getText());
            java.sql.Date dataConclusaoPrevista = java.sql.Date.valueOf(dataConclusaoField.getText());
            java.sql.Date dataDesligamento = dataDesligamentoField.getText().isEmpty() ? null : java.sql.Date.valueOf(dataDesligamentoField.getText());

            if (residenteSelecionado == null || residenciaSelecionada == null) {
                JOptionPane.showMessageDialog(this, "Selecione um residente e uma residência!");
                return;
            }

            // Verificar se a matrícula já existe para esse residente e residência
            MatriculaDAO matriculaDAO = new MatriculaDAO();
            boolean matriculaExiste = matriculaDAO.matriculaExiste(residenteSelecionado.getIdResidente(), residenciaSelecionada.getIdResidencia());

            if (matriculaExiste) {
                JOptionPane.showMessageDialog(this, "Matrícula já existe para este residente e residência.");
                return;
            }

            // Criar uma nova matrícula e salvar no banco
            Matricula matricula = new Matricula();
            matricula.setIdResidente(residenteSelecionado.getIdResidente());
            matricula.setIdResidencia(residenciaSelecionada.getIdResidencia());
            matricula.setStatusMatricula(statusMatricula);
            matricula.setDataInicioMatricula(dataInicioMatricula);
            matricula.setDataConclusaoPrevistaMatricula(dataConclusaoPrevista);
            matricula.setDataDesligamentoMatricula(dataDesligamento);

            matriculaDAO.cadastrarMatricula(matricula);

            JOptionPane.showMessageDialog(this, "Matrícula cadastrada com sucesso!");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use o formato yyyy-mm-dd.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar a matrícula: " + e.getMessage());
        }
    }
}

