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

public class EdicaoMatricula extends JFrame {
    private JTextField idResidenteField;
    private JTextField idResidenciaField;
    private JTextField statusField;
    private JTextField dataInicioField;
    private JTextField dataConclusaoField;
    private JTextField dataDesligamentoField;
    private JButton salvarButton;
    private MatriculaDAO matriculaDAO;
    private int matriculaId;

    public EdicaoMatricula(int matriculaId, Connection connection) {
        this.matriculaId = matriculaId;
        matriculaDAO = new MatriculaDAO(connection); // Passa a conexão para o DAO

        setTitle("Edição de Matrícula");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        Matricula matricula = matriculaDAO.buscarMatriculaPorId(matriculaId);

        if (matricula != null) {
            // Campos de texto
            idResidenteField = new JTextField(String.valueOf(matricula.getIdResidente()));
            idResidenciaField = new JTextField(String.valueOf(matricula.getIdResidencia()));
            statusField = new JTextField(matricula.getStatusMatricula());
            dataInicioField = new JTextField(matricula.getDataInicioMatricula().toString()); // Considerando que é um objeto Date
            dataConclusaoField = new JTextField(matricula.getDataConclusaoPrevistaMatricula().toString()); // Considerando que é um objeto Date
            dataDesligamentoField = new JTextField(matricula.getDataDesligamentoMatricula().toString()); // Considerando que é um objeto Date

            salvarButton = new JButton("Salvar Alterações");
            salvarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    atualizarMatricula();
                }
            });

            add(new JLabel("ID Residente:"));
            add(idResidenteField);
            add(new JLabel("ID Residência:"));
            add(idResidenciaField);
            add(new JLabel("Status:"));
            add(statusField);
            add(new JLabel("Data Início:"));
            add(dataInicioField);
            add(new JLabel("Data Conclusão:"));
            add(dataConclusaoField);
            add(new JLabel("Data Desligamento:"));
            add(dataDesligamentoField);
            add(new JLabel("")); // Espaço vazio
            add(salvarButton);
        } else {
            JOptionPane.showMessageDialog(this, "Matrícula não encontrada.");
            dispose();
        }
    }

    private void atualizarMatricula() {
        try {
            Matricula matricula = new Matricula();
            matricula.setIdMatricula(matriculaId); // ID da matrícula que será atualizada
            matricula.setIdResidente(Integer.parseInt(idResidenteField.getText())); // Atualiza o ID do residente
            matricula.setIdResidencia(Integer.parseInt(idResidenciaField.getText())); // Atualiza o ID da residência
            matricula.setStatusMatricula(statusField.getText()); // Atualiza o status
            matricula.setDataInicioMatricula(java.sql.Date.valueOf(dataInicioField.getText())); // Atualiza a data de início
            matricula.setDataConclusaoPrevistaMatricula(java.sql.Date.valueOf(dataConclusaoField.getText())); // Atualiza a data de conclusão
            matricula.setDataDesligamentoMatricula(java.sql.Date.valueOf(dataDesligamentoField.getText())); // Atualiza a data de desligamento

            // Chama o DAO para atualizar as informações no banco de dados
            matriculaDAO.atualizarMatricula(matricula);

            // Exibe uma mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Dados da matrícula atualizados com sucesso!");
            dispose(); // Fecha a janela atual após a atualização
        } catch (NumberFormatException e) {
            // Exibe uma mensagem de erro se ocorrer um problema com a conversão de números
            JOptionPane.showMessageDialog(this, "Verifique os dados inseridos.");
        } catch (IllegalArgumentException e) {
            // Exibe uma mensagem de erro se ocorrer um problema com o formato da data
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Utilize o formato yyyy-MM-dd.");
        }
    }
}
