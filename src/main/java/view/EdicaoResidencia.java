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

public class EdicaoResidencia extends JFrame {
    private JTextField nomeField;
    private JTextField apelidoField;
    private JTextField categoriaField;
    private JButton salvarButton;
    private ResidenciaDAO residenciaDAO;
    private int residenciaId;

    public EdicaoResidencia(int residenciaId) {
        this.residenciaId = residenciaId;
        residenciaDAO = new ResidenciaDAO();

        setTitle("Edição de Residência");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        Residencia residencia = residenciaDAO.buscarResidenciaPorId(residenciaId);

        if (residencia != null) {
            // Campos de texto
            nomeField = new JTextField(residencia.getNomeResidencia());
            apelidoField = new JTextField(residencia.getApelidoResidencia());
            categoriaField = new JTextField(residencia.getCategoriaResidencia());

            salvarButton = new JButton("Salvar Alterações");
            salvarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    atualizarResidencia();
                }
            });

            add(new JLabel("Nome:"));
            add(nomeField);
            add(new JLabel("Apelido:"));
            add(apelidoField);
            add(new JLabel("Categoria:"));
            add(categoriaField);
            add(new JLabel(""));
            add(salvarButton);
        } else {
            JOptionPane.showMessageDialog(this, "Residência não encontrada.");
            dispose();
        }
    }

    private void atualizarResidencia() {
        try {
            Residencia residencia = new Residencia();
            residencia.setIdResidencia(residenciaId); // ID da residência que será atualizada
            residencia.setNomeResidencia(nomeField.getText()); // Atualiza o nome
            residencia.setApelidoResidencia(apelidoField.getText()); // Atualiza o apelido
            residencia.setCategoriaResidencia(categoriaField.getText()); // Atualiza a categoria

            // Chama o DAO para atualizar as informações no banco de dados
            residenciaDAO.atualizarResidencia(residencia);

            // Exibe uma mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Dados da residência atualizados com sucesso!");
            dispose(); // Fecha a janela atual após a atualização
        } catch (NumberFormatException e) {
            // Exibe uma mensagem de erro se ocorrer um problema com o número (não relevante aqui, mas por precaução)
            JOptionPane.showMessageDialog(this, "Verifique os dados inseridos.");
        }
    }
}

