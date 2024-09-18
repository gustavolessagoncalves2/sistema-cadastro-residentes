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
import java.sql.Connection;

public class CadastroResidente extends JFrame {
    private Connection connection; // Conexão recebida da TelaPrincipal
    
    private JTextField nomeField, cpfField, rgField, crmField, emailField, telefoneField, idUnidadeField;

    public CadastroResidente(Connection connection) {
        this.connection = connection;
        
        setTitle("Cadastro de Residente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas a janela atual

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Campos do formulário
        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("RG:"));
        rgField = new JTextField();
        panel.add(rgField);

        panel.add(new JLabel("CRM:"));
        crmField = new JTextField();
        panel.add(crmField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        panel.add(telefoneField);

        // Botão de salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarResidente();
            }
        });
        panel.add(btnSalvar);

        add(panel);
    }

    private void salvarResidente() {
        // Captura os dados preenchidos nos campos
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String rg = rgField.getText();
        String crm = crmField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();

        // Validação básica
        if (nome.isEmpty() || cpf.isEmpty() || rg.isEmpty() || crm.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
            return;
        }

        // Criação de um objeto Residente e chamada do DAO para salvar
        Residente residente = new Residente();
        residente.setNomeResidente(nome);
        residente.setCpfResidente(cpf);
        residente.setRgResidente(rg);
        residente.setCrmResidente(crm);
        residente.setEmailResidente(email);
        residente.setTelefoneResidente(telefone);

        // Passa a conexão ao DAO
        ResidenteDAO residenteDAO = new ResidenteDAO(connection);
        try {
            residenteDAO.cadastrarResidente(residente);
            // Mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Residente cadastrado com sucesso!");
        } catch (Exception ex) {
            // Mensagem de erro
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar residente: " + ex.getMessage());
        }

        // Limpa os campos
        nomeField.setText("");
        cpfField.setText("");
        rgField.setText("");
        crmField.setText("");
        emailField.setText("");
        telefoneField.setText("");
    }
}
