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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ConsultaMatricula extends JFrame {
    private Connection connection; // Conexão passada pela TelaPrincipal
    private JTextField idField;
    private JTextField idResidenteField;
    private JTextField idResidenciaField;
    private JTextField statusField;
    private JTextField dataInicioField;
    private JTextField dataConclusaoField;
    private JButton pesquisarButton;
    private JButton editarButton;
    private JTable matriculasTable;
    private MatriculaDAO matriculaDAO;
    private List<Matricula> matriculas;
    private int matriculaSelecionadaId = -1;

    public ConsultaMatricula(Connection connection) {
        this.connection = connection;
        this.matriculaDAO = new MatriculaDAO(connection); // Passa a conexão para o DAO

        setTitle("Consulta de Matrícula");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de pesquisa
        JPanel pesquisaPanel = new JPanel();
        pesquisaPanel.setLayout(new GridLayout(7, 2)); // Atualizado para 7 linhas

        idField = new JTextField(20);
        idResidenteField = new JTextField(20);
        idResidenciaField = new JTextField(20);
        statusField = new JTextField(20);
        dataInicioField = new JTextField(20);
        dataConclusaoField = new JTextField(20);

        pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarMatriculas();
            }
        });

        pesquisaPanel.add(new JLabel("ID Matrícula:"));
        pesquisaPanel.add(idField);
        pesquisaPanel.add(new JLabel("ID Residente:"));
        pesquisaPanel.add(idResidenteField);
        pesquisaPanel.add(new JLabel("ID Residência:"));
        pesquisaPanel.add(idResidenciaField);
        pesquisaPanel.add(new JLabel("Status:"));
        pesquisaPanel.add(statusField);
        pesquisaPanel.add(new JLabel("Data Início:"));
        pesquisaPanel.add(dataInicioField);
        pesquisaPanel.add(new JLabel("Data Conclusão:"));
        pesquisaPanel.add(dataConclusaoField);
        pesquisaPanel.add(new JLabel("")); // Espaço vazio
        pesquisaPanel.add(pesquisarButton);

        add(pesquisaPanel, BorderLayout.NORTH);

        // Tabela de matrículas
        matriculasTable = new JTable();
        matriculasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matriculasTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = matriculasTable.getSelectedRow();
                if (selectedRow >= 0) {
                    matriculaSelecionadaId = (int) matriculasTable.getValueAt(selectedRow, 0);
                }
            }
        });

        add(new JScrollPane(matriculasTable), BorderLayout.CENTER);

        // Botão de edição
        JPanel botoesPanel = new JPanel();
        editarButton = new JButton("Editar Dados Cadastrados");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (matriculaSelecionadaId != -1) {
                    new EdicaoMatricula(matriculaSelecionadaId, connection).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ConsultaMatricula.this, "Selecione uma matrícula para editar!");
                }
            }
        });
        
        botoesPanel.add(editarButton);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    private void pesquisarMatriculas() {
        String id = idField.getText();
        String idResidente = idResidenteField.getText();
        String idResidencia = idResidenciaField.getText();
        String status = statusField.getText();
        String dataInicio = dataInicioField.getText();
        String dataConclusao = dataConclusaoField.getText();

        matriculas = matriculaDAO.buscarMatriculasPorFiltros(id, idResidente, idResidencia, status, dataInicio, dataConclusao);
        atualizarTabelaMatriculas();
    }

    private void atualizarTabelaMatriculas() {
        String[] colunas = {"ID", "ID Residente", "ID Residência", "Status", "Data Início", "Data Conclusão Prevista", "Data Desligamento"};

        if (matriculas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma matrícula encontrada.");
            matriculasTable.setModel(new DefaultTableModel(new Object[0][colunas.length], colunas));
        } else {
            Object[][] dados = new Object[matriculas.size()][colunas.length];
            for (int i = 0; i < matriculas.size(); i++) {
                Matricula matricula = matriculas.get(i);
                dados[i] = new Object[] {
                    matricula.getIdMatricula(),
                    matricula.getIdResidente(),
                    matricula.getIdResidencia(),
                    matricula.getStatusMatricula(),
                    matricula.getDataInicioMatricula(),
                    matricula.getDataConclusaoPrevistaMatricula(),
                    matricula.getDataDesligamentoMatricula()
                };
            }
            matriculasTable.setModel(new DefaultTableModel(dados, colunas));
        }
    }
}

