package vista;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaPrincipal() {

        setTitle("GameHub");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("GAMEHUB", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JTable tabla = new JTable();
        JScrollPane scroll = new JScrollPane(tabla);

        panelPrincipal.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        panelBotones.add(new JButton("Buscar"));
        panelBotones.add(new JButton("Rankings"));
        panelBotones.add(new JButton("Historial"));
        panelBotones.add(new JButton("Descargas"));

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }
}