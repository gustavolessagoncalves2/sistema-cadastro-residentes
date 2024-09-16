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

public class EdicaoResidente extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField rgField;
    private JTextField crmField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JTextField idUnidadeField;
    private JButton salvarButton;
    private ResidenteDAO residenteDAO;
    private int residenteId;

    public EdicaoResidente(int residenteId) {
        this.residenteId = residenteId;
        residenteDAO = new ResidenteDAO();
        
        setTitle("Edição de Residente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        Residente residente = residenteDAO.buscarResidentePorId(residenteId);

        if (residente != null) {
            // Campos de texto
            nomeField = new JTextField(residente.getNomeResidente());
            cpfField = new JTextField(residente.getCpfResidente());
            rgField = new JTextField(residente.getRgResidente());
            crmField = new JTextField(residente.getCrmResidente());
            emailField = new JTextField(residente.getEmailResidente());
            telefoneField = new JTextField(residente.getTelefoneResidente());
            idUnidadeField = new JTextField(String.valueOf(residente.getIdUnidade()));

            salvarButton = new JButton("Salvar Alterações");
            salvarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    atualizarResidente();
                }
            });

            add(new JLabel("Nome:"));
            add(nomeField);
            add(new JLabel("CPF:"));
            add(cpfField);
            add(new JLabel("RG:"));
            add(rgField);
            add(new JLabel("CRM:"));
            add(crmField);
            add(new JLabel("Email:"));
            add(emailField);
            add(new JLabel("Telefone:"));
            add(telefoneField);
            add(new JLabel("ID Unidade:"));
            add(idUnidadeField);
            add(new JLabel(""));
            add(salvarButton);
        } else {
            JOptionPane.showMessageDialog(this, "Residente não encontrado.");
            dispose();
        }
    }

    private void atualizarResidente() {
        try {
            Residente residente = new Residente();
            residente.setIdResidente(residenteId);
            residente.setNomeResidente(nomeField.getText());
            residente.setCpfResidente(cpfField.getText());
            residente.setRgResidente(rgField.getText());
            residente.setCrmResidente(crmField.getText());
            residente.setEmailResidente(emailField.getText());
            residente.setTelefoneResidente(telefoneField.getText());
            residente.setIdUnidade(Integer.parseInt(idUnidadeField.getText()));

            residenteDAO.atualizarResidente(residente);
            JOptionPane.showMessageDialog(this, "Dados do residente atualizados com sucesso!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Unidade deve ser um número.");
        }
    }
}