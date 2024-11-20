package com.login.ui;

import com.login.database.BaseDeDatos;
import com.login.models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserListScreen extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserListScreen() {
        setTitle("Lista de Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Usuarios Registrados");
        lblTitle.setBounds(200, 10, 200, 30);
        add(lblTitle);

        // Tabla de usuarios
        tableModel = new DefaultTableModel(new String[]{"Username", "Nombre", "Apellido", "Teléfono", "Correo"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(20, 50, 540, 200);
        add(scrollPane);

        // Botones de acción
        JButton btnUpdate = new JButton("Actualizar Usuario");
        btnUpdate.setBounds(100, 300, 150, 30);
        add(btnUpdate);

        JButton btnDelete = new JButton("Eliminar Usuario");
        btnDelete.setBounds(300, 300, 150, 30);
        add(btnDelete);

        // Botón para cerrar sesión
        JButton btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBounds(480, 10, 100, 25);
        add(btnLogout);

        // Llenar tabla con usuarios registrados
        cargarUsuarios();

        // Acción de botón "Actualizar Usuario"
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuarioSeleccionado();
            }
        });

        // Acción de botón "Eliminar Usuario"
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuarioSeleccionado();
            }
        });

        // Acción de botón "Cerrar Sesión"
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen().setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
    }

    void cargarUsuarios() { // Cambiado a public
        tableModel.setRowCount(0); // Limpiar tabla
        List<Usuario> usuarios = BaseDeDatos.obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            tableModel.addRow(new Object[]{usuario.getUsername(), usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail()});
        }
    }

    private void actualizarUsuarioSeleccionado() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String username = (String) tableModel.getValueAt(selectedRow, 0);

        Usuario usuario = BaseDeDatos.buscarUsuarioPorUsername(username);
        if (usuario != null) {
            new UpdateUserScreen(usuario, this).setVisible(true); // Abrir pantalla de actualización
        }
    }

    private void eliminarUsuarioSeleccionado() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String username = (String) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            BaseDeDatos.eliminarUsuario(username);
            cargarUsuarios(); // Actualizar la tabla
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserListScreen().setVisible(true));
    }
}
