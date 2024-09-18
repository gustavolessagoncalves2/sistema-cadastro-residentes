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
import model.Residencia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CadastroResidencia extends JFrame {
    private Connection connection; // Conexão recebida da TelaPrincipal
    private JTextField nomeField, apelidoField, categoriaField;

    public CadastroResidencia(Connection connection) {
        this.connection = connection;

        setTitle("Cadastro de Residência");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas a janela atual

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Campos do formulário
        panel.add(new JLabel("Nome da Residência:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Apelido da Residência:"));
        apelidoField = new JTextField();
        panel.add(apelidoField);

        panel.add(new JLabel("Categoria da Residência:"));
        categoriaField = new JTextField();
        panel.add(categoriaField);

        // Botão de salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarResidencia();
            }
        });
        panel.add(btnSalvar);

        add(panel);
    }

    private void salvarResidencia() {
        // Captura os dados preenchidos nos campos
        String nome = nomeField.getText();
        String apelido = apelidoField.getText();
        String categoria = categoriaField.getText();

        // Validação básica
        if (nome.isEmpty() || apelido.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
            return;
        }

        // Criação de um objeto Residencia e chamada do DAO para salvar
        Residencia residencia = new Residencia();
        residencia.setNomeResidencia(nome);
        residencia.setApelidoResidencia(apelido);
        residencia.setCategoriaResidencia(categoria);

        // Passa a conexão ao DAO
        ResidenciaDAO residenciaDAO = new ResidenciaDAO(connection);
        try {
            residenciaDAO.cadastrarResidencia(residencia);
            // Mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Residência cadastrada com sucesso!");
        } catch (Exception ex) {
            // Mensagem de erro
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar residência: " + ex.getMessage());
        }

        // Limpa os campos
        nomeField.setText("");
        apelidoField.setText("");
        categoriaField.setText("");
    }
}
