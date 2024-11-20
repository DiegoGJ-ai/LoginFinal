package com.login.ui;

import com.login.database.BaseDeDatos;
import com.login.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterScreen extends JFrame {
    private LoginScreen loginScreen; // Referencia a LoginScreen
    private JTextField txtUsername, txtNombre, txtApellido, txtTelefono, txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JLabel lblErrorUsername, lblErrorNombre, lblErrorApellido, lblErrorTelefono, lblErrorEmail, lblErrorPassword, lblErrorConfirmPassword;

    public RegisterScreen(LoginScreen loginScreen) {
        this.loginScreen = loginScreen; // Guardar la referencia
        inicializarPantalla();
    }

    public RegisterScreen() {
        inicializarPantalla(); // Constructor sin referencia
    }

    private void inicializarPantalla() {
        setTitle("Registro de Usuario");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        

        // Campos y etiquetas
        crearCampoConEtiqueta("Username:", 20, txtUsername = new JTextField(), lblErrorUsername = new JLabel());
        crearCampoConEtiqueta("Nombre:", 70, txtNombre = new JTextField(), lblErrorNombre = new JLabel());
        crearCampoConEtiqueta("Apellido:", 120, txtApellido = new JTextField(), lblErrorApellido = new JLabel());
        crearCampoConEtiqueta("Teléfono:", 170, txtTelefono = new JTextField(), lblErrorTelefono = new JLabel());
        crearCampoConEtiqueta("Email:", 220, txtEmail = new JTextField(), lblErrorEmail = new JLabel());
        crearCampoConEtiqueta("Contraseña:", 270, txtPassword = new JPasswordField(), lblErrorPassword = new JLabel());
        crearCampoConEtiqueta("Confirmar Contraseña:", 320, txtConfirmPassword = new JPasswordField(), lblErrorConfirmPassword = new JLabel());

        // Botón de registro
        JButton btnRegister = new JButton("Registrar");
        btnRegister.setBounds(150, 400, 100, 30);
        add(btnRegister);

        // Acción del botón
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    private void crearCampoConEtiqueta(String etiqueta, int y, JTextField campo, JLabel errorLabel) {
        JLabel lblCampo = new JLabel(etiqueta);
        lblCampo.setBounds(20, y, 150, 25);
        add(lblCampo);

        campo.setBounds(170, y, 180, 25);
        add(campo);

        errorLabel.setBounds(170, y + 25, 180, 15);
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        add(errorLabel);
    }

    private void registrarUsuario() {
        boolean valido = true;

        if (txtUsername.getText().isEmpty()) {
            lblErrorUsername.setText("Este campo es obligatorio");
            valido = false;
        } else {
            lblErrorUsername.setText("");
        }

        if (txtNombre.getText().isEmpty()) {
            lblErrorNombre.setText("Este campo es obligatorio");
            valido = false;
        } else {
            lblErrorNombre.setText("");
        }

        if (txtApellido.getText().isEmpty()) {
            lblErrorApellido.setText("Este campo es obligatorio");
            valido = false;
        } else {
            lblErrorApellido.setText("");
        }

        if (txtTelefono.getText().isEmpty()) {
            lblErrorTelefono.setText("Este campo es obligatorio");
            valido = false;
        } else {
            lblErrorTelefono.setText("");
        }

        if (txtEmail.getText().isEmpty()) {
            lblErrorEmail.setText("Este campo es obligatorio");
            valido = false;
        } else {
            lblErrorEmail.setText("");
        }

        if (new String(txtPassword.getPassword()).isEmpty()) {
            lblErrorPassword.setText("Este campo es obligatorio");
            valido = false;
        } else {
            lblErrorPassword.setText("");
        }

        if (new String(txtConfirmPassword.getPassword()).isEmpty()) {
            lblErrorConfirmPassword.setText("Este campo es obligatorio");
            valido = false;
        } else if (!new String(txtPassword.getPassword()).equals(new String(txtConfirmPassword.getPassword()))) {
            lblErrorConfirmPassword.setText("Las contraseñas no coinciden");
            valido = false;
        } else {
            lblErrorConfirmPassword.setText("");
        }

        if (valido) {
            Usuario usuario = new Usuario(
                txtUsername.getText(),
                txtNombre.getText(),
                txtApellido.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                new String(txtPassword.getPassword())
            );

            if (BaseDeDatos.agregarUsuario(usuario)) {
                JOptionPane.showMessageDialog(this, "Usuario registrado con éxito");
                dispose();
                loginScreen.setVisible(true); // Volver a LoginScreen
            } else {
                JOptionPane.showMessageDialog(this, "El usuario ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterScreen().setVisible(true));
    }
}
