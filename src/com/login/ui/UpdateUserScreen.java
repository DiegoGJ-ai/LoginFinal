package com.login.ui;

import com.login.database.BaseDeDatos;
import com.login.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserScreen extends JFrame {
    private Usuario usuario;
    private JTextField txtNombre, txtApellido, txtTelefono, txtEmail;
    private UserListScreen userListScreen;

    public UpdateUserScreen(Usuario usuario, UserListScreen userListScreen) {
        this.usuario = usuario;
        this.userListScreen = userListScreen;

        setTitle("Actualizar Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Actualizar Usuario");
        lblTitle.setBounds(120, 10, 200, 30);
        add(lblTitle);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 50, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField(usuario.getNombre());
        txtNombre.setBounds(150, 50, 180, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(50, 100, 100, 25);
        add(lblApellido);

        txtApellido = new JTextField(usuario.getApellido());
        txtApellido.setBounds(150, 100, 180, 25);
        add(txtApellido);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 150, 100, 25);
        add(lblTelefono);

        txtTelefono = new JTextField(usuario.getTelefono());
        txtTelefono.setBounds(150, 150, 180, 25);
        add(txtTelefono);

        JLabel lblEmail = new JLabel("Correo:");
        lblEmail.setBounds(50, 200, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField(usuario.getEmail());
        txtEmail.setBounds(150, 200, 180, 25);
        add(txtEmail);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(150, 300, 100, 30);
        add(btnActualizar);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });
    }

    private void actualizarUsuario() {
        usuario.setNombre(txtNombre.getText());
        usuario.setApellido(txtApellido.getText());
        usuario.setTelefono(txtTelefono.getText());
        usuario.setEmail(txtEmail.getText());

        BaseDeDatos.actualizarUsuario(usuario);
        userListScreen.cargarUsuarios(); // Actualizar la tabla en UserListScreen
        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
        dispose(); // Cerrar ventana de actualización
    }
}
