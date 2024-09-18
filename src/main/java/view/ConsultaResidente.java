/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author gustavogoncalves
 */
import dao.ResidenteDAO;
import model.Residente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;

public class ConsultaResidente extends JFrame {
    private Connection connection;
    
    private JTextField idField;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField rgField;
    private JTextField crmField;
    private JTextField emailField;
    private JButton pesquisarButton;
    private JButton editarButton;
    private JTable residentesTable;
    private ResidenteDAO residenteDAO;
    private List<Residente> residentes;
    private int residenteSelecionadoId = -1;

    public ConsultaResidente(Connection connection) {
        this.connection = connection;
        this.residenteDAO = new ResidenteDAO(connection); // Passa a conexão para o DAO
        
        setTitle("Consulta de Residente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de pesquisa
        JPanel pesquisaPanel = new JPanel();
        pesquisaPanel.setLayout(new GridLayout(7, 2)); // Uma linha para cada campo de pesquisa
        
        idField = new JTextField(20);
        nomeField = new JTextField(20);
        cpfField = new JTextField(20);
        rgField = new JTextField(20);
        crmField = new JTextField(20);
        emailField = new JTextField(20);
        
        pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarResidentes();
            }
        });

        pesquisaPanel.add(new JLabel("ID:"));
        pesquisaPanel.add(idField);
        pesquisaPanel.add(new JLabel("Nome:"));
        pesquisaPanel.add(nomeField);
        pesquisaPanel.add(new JLabel("CPF:"));
        pesquisaPanel.add(cpfField);
        pesquisaPanel.add(new JLabel("RG:"));
        pesquisaPanel.add(rgField);
        pesquisaPanel.add(new JLabel("CRM:"));
        pesquisaPanel.add(crmField);
        pesquisaPanel.add(new JLabel("Email:"));
        pesquisaPanel.add(emailField);
        pesquisaPanel.add(new JLabel("")); // Espaço vazio
        pesquisaPanel.add(pesquisarButton);
        
        add(pesquisaPanel, BorderLayout.NORTH);

        // Tabela de residentes
        residentesTable = new JTable();
        residentesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        residentesTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = residentesTable.getSelectedRow();
                if (selectedRow >= 0) {
                    residenteSelecionadoId = (int) residentesTable.getValueAt(selectedRow, 0);
                }
            }
        });

        add(new JScrollPane(residentesTable), BorderLayout.CENTER);

        // Botão de edição
        JPanel botoesPanel = new JPanel();
        editarButton = new JButton("Editar Dados Cadastrados");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (residenteSelecionadoId != -1) {
                    new EdicaoResidente(residenteSelecionadoId, connection).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ConsultaResidente.this, "Selecione um residente para editar!");
                }
            }
        });

        botoesPanel.add(editarButton);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    private void pesquisarResidentes() {
        String id = idField.getText();
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String rg = rgField.getText();
        String crm = crmField.getText();
        String email = emailField.getText();
        
        residentes = residenteDAO.buscarResidentesPorFiltros(id, nome, cpf, rg, crm, email);
        atualizarTabelaResidentes();
    }

    private void atualizarTabelaResidentes() {
        String[] colunas = {"ID", "Nome", "CPF", "RG", "CRM", "Email", "Telefone"};
        
        if (residentes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum residente encontrado.");
            residentesTable.setModel(new DefaultTableModel(new Object[0][colunas.length], colunas));
        } else {
            Object[][] dados = new Object[residentes.size()][colunas.length];
            for (int i = 0; i < residentes.size(); i++) {
                Residente residente = residentes.get(i);
                dados[i] = new Object[]{
                    residente.getIdResidente(),
                    residente.getNomeResidente(),
                    residente.getCpfResidente(),
                    residente.getRgResidente(),
                    residente.getCrmResidente(),
                    residente.getEmailResidente(),
                    residente.getTelefoneResidente()
                };
            }
            residentesTable.setModel(new DefaultTableModel(dados, colunas));
        }
    }
}
