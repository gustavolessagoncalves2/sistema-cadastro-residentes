/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author gustavogoncalves
 */
import dao.ResidenciaDAO;
import database.Conexao;
import model.Residencia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;

public class ConsultaResidencia extends JFrame {
    private Connection connection;
    
    private JTextField idField;
    private JTextField nomeField;
    private JTextField apelidoField;
    private JTextField categoriaField;
    private JButton pesquisarButton;
    private JButton editarButton;
    private JTable residenciasTable;
    private ResidenciaDAO residenciaDAO;
    private List<Residencia> residencias;
    private int residenciaSelecionadaId = -1;

    public ConsultaResidencia(Connection connection) {
        this.connection = connection;

        setTitle("Consulta de Residência");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        residenciaDAO = new ResidenciaDAO();
        
        // Painel de pesquisa
        JPanel pesquisaPanel = new JPanel();
        pesquisaPanel.setLayout(new GridLayout(5, 2)); // Uma linha para cada campo de pesquisa
        
        idField = new JTextField(20);
        nomeField = new JTextField(20);
        apelidoField = new JTextField(20);
        categoriaField = new JTextField(20);
        
        pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarResidencias();
            }
        });

        pesquisaPanel.add(new JLabel("ID:"));
        pesquisaPanel.add(idField);
        pesquisaPanel.add(new JLabel("Nome:"));
        pesquisaPanel.add(nomeField);
        pesquisaPanel.add(new JLabel("Apelido:"));
        pesquisaPanel.add(apelidoField);
        pesquisaPanel.add(new JLabel("Categoria:"));
        pesquisaPanel.add(categoriaField);
         pesquisaPanel.add(new JLabel("")); // Espaço vazio
        pesquisaPanel.add(pesquisarButton);
        
        add(pesquisaPanel, BorderLayout.NORTH);

        // Tabela de residências
        residenciasTable = new JTable();
        residenciasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        residenciasTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = residenciasTable.getSelectedRow();
                if (selectedRow >= 0) {
                    residenciaSelecionadaId = (int) residenciasTable.getValueAt(selectedRow, 0);
                }
            }
        });

        add(new JScrollPane(residenciasTable), BorderLayout.CENTER);

        // Botão de edição
        JPanel botoesPanel = new JPanel();
        editarButton = new JButton("Editar Dados Cadastrados");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (residenciaSelecionadaId != -1) {
                    new EdicaoResidencia(residenciaSelecionadaId).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ConsultaResidencia.this, "Selecione uma residência para editar!");
                }
            }
        });

        botoesPanel.add(editarButton);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    private void pesquisarResidencias() {
        String id = idField.getText();
        String nome = nomeField.getText();
        String apelido = apelidoField.getText();
        String categoria = categoriaField.getText();
        
        residencias = residenciaDAO.buscarResidenciasPorFiltros(id, nome, apelido, categoria);
        atualizarTabelaResidencias();
    }

    private void atualizarTabelaResidencias() {
        String[] colunas = {"ID", "Nome", "Apelido", "Categoria"};
        
        if (residencias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma residência encontrada.");
            residenciasTable.setModel(new DefaultTableModel(new Object[0][colunas.length], colunas));
        } else {
            Object[][] dados = new Object[residencias.size()][colunas.length];
            for (int i = 0; i < residencias.size(); i++) {
                Residencia residencia = residencias.get(i);
                dados[i] = new Object[]{
                    residencia.getIdResidencia(),
                    residencia.getNomeResidencia(),
                    residencia.getApelidoResidencia(),
                    residencia.getCategoriaResidencia()
                };
            }
            residenciasTable.setModel(new DefaultTableModel(dados, colunas));
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = Conexao.conectar(); // Cria a conexão
            new ConsultaResidente(connection).setVisible(true); // Passa a conexão para a tela de consulta
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }     
}
