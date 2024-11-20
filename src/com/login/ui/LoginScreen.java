package com.login.ui;

import com.login.database.BaseDeDatos;
import com.login.models.Usuario;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    public LoginScreen() {
        setTitle("Login de Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUsername = new JLabel("Usuario:");
        lblUsername.setBounds(20, 20, 80, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 20, 160, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(20, 60, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 160, 25);
        add(txtPassword);

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(20, 100, 120, 30);
        add(btnLogin);

        btnRegister = new JButton("Registrarse");
        btnRegister.setBounds(150, 100, 120, 30);
        add(btnRegister);

        btnLogin.addActionListener(new LoginAction());
        btnRegister.addActionListener(e -> new RegisterScreen(this).setVisible(true));
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse.");
                return;
            }

            Usuario usuario = BaseDeDatos.buscarUsuarioPorUsername(username);
            if (usuario != null && usuario.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null, "¡Bienvenido " + usuario.getNombre() + "!");
                new UserListScreen().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));
    }
}
