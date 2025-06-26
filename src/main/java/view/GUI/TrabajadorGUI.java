package view.GUI;

import controller.TrabajadorController;
import controller.TrabajadorRepository;
import model.Trabajador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TrabajadorGUI extends JFrame {

    private final TrabajadorController trabajadorController;

    private JTextField idField, nombreField, apellidoField, rutField, isapreField, afpField;
    private JButton addButton, updateButton, deleteButton, clearButton;

    private JTable trabajadorTable;
    private DefaultTableModel tableModel;

    public TrabajadorGUI(TrabajadorController trabajadorController) {
        this.trabajadorController = trabajadorController;
        initComponents();
        loadTableData();
    }

    /**
     * Inicia y configura los componentes de la GUI.
     */
    private void initComponents() {
        setTitle("Gestión de Trabajadores");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10,10));

        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.NORTH);

        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Panel del formulario
     * @return JPanel con campos de texto y tags.
     */
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos Trabajador"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Fila 1 (ID/Nombre)
        gbc.gridx = 0; gbc. gridy = 0; panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; idField = new JTextField(15); idField.setEditable(false); panel.add(idField, gbc);
        gbc.gridx = 2; gbc.gridy = 0; panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 3; nombreField = new JTextField(15); panel.add(nombreField, gbc);

        //Fila 2 (Apellido/RUT)
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; apellidoField = new JTextField(15); panel.add(apellidoField, gbc);
        gbc.gridx = 2; gbc.gridy = 1; panel.add(new JLabel("RUT:"), gbc);
        gbc.gridx = 3; rutField = new JTextField(15); panel.add(rutField, gbc);

        //Fila 3 (Isapre/AFP)
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Isapre:"), gbc);
        gbc.gridx = 1; isapreField = new JTextField(15); panel.add(isapreField, gbc);
        gbc.gridx = 2; gbc.gridy = 2; panel.add(new JLabel("AFP:"), gbc);
        gbc.gridx = 3; afpField = new JTextField(15); panel.add(afpField, gbc);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Trabajadores"));

        String[] columnNames = {"ID", "Nombre", "Apellido", "RUT", "Isapre", "AFP"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        trabajadorTable = new JTable(tableModel);

        trabajadorTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && trabajadorTable.getSelectedRow() != -1) {
                int selectedRow = trabajadorTable.getSelectedRow();
                idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                nombreField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                apellidoField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                rutField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                isapreField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                afpField.setText(tableModel.getValueAt(selectedRow, 5).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(trabajadorTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        addButton = new JButton("Agregar");
        updateButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");
        clearButton = new JButton("Limpiar");

        ButtonActionListener listener = new ButtonActionListener();
        addButton.addActionListener(listener);
        updateButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        clearButton.addActionListener(listener);

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        return panel;
    }

    private void loadTableData() {
        tableModel.setRowCount(0);

        HashMap<Integer, Trabajador> trabajadores = trabajadorController.obtenerTrabajadores();
        for (Map.Entry<Integer, Trabajador> entry : trabajadores.entrySet()) {
            int id = entry.getKey();
            Trabajador t = entry.getValue();
            tableModel.addRow(new Object[]{id, t.getNombre(), t.getApellido(), t.getRut(), t.getIsapre(), t.getAfp()});
        }
    }

    private void clearFields() {
        idField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        rutField.setText("");
        isapreField.setText("");
        afpField.setText("");
        trabajadorTable.clearSelection();
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == addButton) {
                    trabajadorController.crearTrabajador(
                            nombreField.getText(),
                            apellidoField.getText(),
                            rutField.getText(),
                            isapreField.getText(),
                            afpField.getText()
                    );
                    JOptionPane.showMessageDialog(TrabajadorGUI.this, "Trabajador agregado con éxito.");
                } else if (e.getSource() == updateButton) {
                    int id = Integer.parseInt(idField.getText());
                    trabajadorController.actualizarTrabajador(
                            id,
                            nombreField.getText(),
                            apellidoField.getText(),
                            rutField.getText(),
                            isapreField.getText(),
                            afpField.getText()
                    );
                    JOptionPane.showMessageDialog(TrabajadorGUI.this, "Trabajador actualizado con éxito.");
                } else if (e.getSource() == deleteButton) {
                    int id = Integer.parseInt(idField.getText());
                    trabajadorController.eliminarTrabajador(id);
                    JOptionPane.showMessageDialog(TrabajadorGUI.this, "Trabajador eliminado con éxito.");
                } else if (e.getSource() == clearButton) {
                    clearFields();
                }

                loadTableData();
                clearFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TrabajadorGUI.this, "Por favor, seleccione un trabajador de la tabla.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(TrabajadorGUI.this, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TrabajadorGUI.this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



}
