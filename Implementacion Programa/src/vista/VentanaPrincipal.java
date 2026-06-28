package vista;

import controlador.VideojuegoController;
import estructuras.Videojuego;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//Creacion Ventana Principal

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private final VideojuegoController controller;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private static final String RUTA_CSV = "games_200_extended.csv";

    public VentanaPrincipal() {
        this.controller = new VideojuegoController(RUTA_CSV);

        setTitle("GameHub");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarComponentes();
        cargarDatosEnTabla();
    }

    private void iniciarComponentes() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("GAMEHUB", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        panelPrincipal.add(titulo, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(VideojuegoController.columnasTabla(), 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        panelPrincipal.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(2, 4, 5, 5));

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnRankings = new JButton("Rankings");
        JButton btnHistorial = new JButton("Historial");
        JButton btnDescargas = new JButton("Descargas");

        btnRegistrar.addActionListener(e -> abrirFormularioRegistro());
        btnModificar.addActionListener(e -> abrirFormularioModificacion());
        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnRefrescar.addActionListener(e -> cargarDatosEnTabla());

        // RF02
        btnBuscar.addActionListener(e -> buscarVideojuego());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnRankings);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnDescargas);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void cargarDatosEnTabla() {
        modeloTabla.setRowCount(0);
        Object[][] filas = controller.obtenerFilasParaTabla();

        for (Object[] fila : filas) {
            modeloTabla.addRow(fila);
        }
    }

    private void abrirFormularioRegistro() {

        Videojuego nuevo = pedirDatosVideojuego(null);

        if (nuevo == null)
            return;

        boolean exito = controller.registrarVideojuego(nuevo);

        if (exito) {

            JOptionPane.showMessageDialog(this,
                    "Videojuego registrado correctamente.");

            cargarDatosEnTabla();

        } else {

            JOptionPane.showMessageDialog(this,
                    "No se pudo registrar: ya existe un videojuego con ese ID.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirFormularioModificacion() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(this,
                    "Selecciona primero un videojuego.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        String id = (String) modeloTabla.getValueAt(fila, 0);

        Videojuego actualizado = pedirDatosVideojuego(controller.buscarPorId(id));

        if (actualizado == null)
            return;

        boolean exito = controller.modificarVideojuego(id, actualizado);

        if (exito) {

            JOptionPane.showMessageDialog(this,
                    "Videojuego modificado correctamente.");

            cargarDatosEnTabla();

        } else {

            JOptionPane.showMessageDialog(this,
                    "No se pudo modificar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSeleccionado() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(this,
                    "Selecciona primero un videojuego.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        String id = (String) modeloTabla.getValueAt(fila, 0);

        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Eliminar videojuego con ID " + id + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {

            if (controller.eliminarVideojuego(id)) {

                cargarDatosEnTabla();

            } else {

                JOptionPane.showMessageDialog(this,
                        "No se pudo eliminar.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarVideojuego() {

        String[] opciones = {
                "Nombre",
                "Género",
                "Desarrollador",
                "Editor"
        };

        String criterio = (String) JOptionPane.showInputDialog(
                this,
                "Buscar por:",
                "Motor de Búsqueda AVL",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (criterio == null)
            return;

        String valor = JOptionPane.showInputDialog(this,
                "Ingrese el " + criterio + ":");

        if (valor == null || valor.trim().isEmpty())
            return;

        boolean encontrado = false;

        switch (criterio) {

            case "Nombre":
                encontrado = controller.buscarNombre(valor);
                break;

            case "Género":
                encontrado = controller.buscarGenero(valor);
                break;

            case "Desarrollador":
                encontrado = controller.buscarDesarrollador(valor);
                break;

            case "Editor":
                encontrado = controller.buscarEditor(valor);
                break;
        }

        if (encontrado) {

            JOptionPane.showMessageDialog(this,
                    "Registro encontrado.");

        } else {

            JOptionPane.showMessageDialog(this,
                    "No se encontró el dato.");

        }
    }

    private Videojuego pedirDatosVideojuego(Videojuego existente) {

        JTextField txtId = new JTextField(existente != null ? existente.getId() : "");
        JTextField txtNombre = new JTextField(existente != null ? existente.getNombre() : "");
        JTextField txtGenero = new JTextField(existente != null ? existente.getGenero() : "");
        JTextField txtPlataforma = new JTextField(existente != null ? existente.getPlataforma() : "");
        JTextField txtPuntuacion = new JTextField(existente != null ? String.valueOf(existente.getPuntuacion()) : "");
        JTextField txtDescargas = new JTextField(existente != null ? String.valueOf(existente.getDescargas()) : "");
        JTextField txtFecha = new JTextField(existente != null ? existente.getFechaLanzamiento() : "");
        JTextField txtDesarrollador = new JTextField(existente != null ? existente.getDesarrollador() : "");
        JTextField txtEditor = new JTextField(existente != null ? existente.getEditor() : "");
        JTextField txtPrecio = new JTextField(existente != null ? String.valueOf(existente.getPrecio()) : "");
        JTextField txtModoJuego = new JTextField(existente != null ? existente.getModoJuego() : "");
        JTextField txtMultijugador = new JTextField(existente != null ? existente.getMultijugador() : "");
        JTextField txtTamano = new JTextField(existente != null ? String.valueOf(existente.getTamanoGb()) : "");

        if (existente != null)
            txtId.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("ID:")); panel.add(txtId);
        panel.add(new JLabel("Nombre:")); panel.add(txtNombre);
        panel.add(new JLabel("Género:")); panel.add(txtGenero);
        panel.add(new JLabel("Plataforma:")); panel.add(txtPlataforma);
        panel.add(new JLabel("Puntuación:")); panel.add(txtPuntuacion);
        panel.add(new JLabel("Descargas:")); panel.add(txtDescargas);
        panel.add(new JLabel("Fecha:")); panel.add(txtFecha);
        panel.add(new JLabel("Desarrollador:")); panel.add(txtDesarrollador);
        panel.add(new JLabel("Editor:")); panel.add(txtEditor);
        panel.add(new JLabel("Precio:")); panel.add(txtPrecio);
        panel.add(new JLabel("Modo Juego:")); panel.add(txtModoJuego);
        panel.add(new JLabel("Multijugador:")); panel.add(txtMultijugador);
        panel.add(new JLabel("Tamaño GB:")); panel.add(txtTamano);

        int resultado = JOptionPane.showConfirmDialog(
                this,
                panel,
                existente == null ? "Registrar videojuego" : "Modificar videojuego",
                JOptionPane.OK_CANCEL_OPTION);

        if (resultado != JOptionPane.OK_OPTION)
            return null;

        try {

            return new Videojuego(
                    txtId.getText().trim(),
                    txtNombre.getText().trim(),
                    txtGenero.getText().trim(),
                    txtPlataforma.getText().trim(),
                    Double.parseDouble(txtPuntuacion.getText().trim()),
                    Long.parseLong(txtDescargas.getText().trim()),
                    txtFecha.getText().trim(),
                    txtDesarrollador.getText().trim(),
                    txtEditor.getText().trim(),
                    Double.parseDouble(txtPrecio.getText().trim()),
                    txtModoJuego.getText().trim(),
                    txtMultijugador.getText().trim(),
                    Double.parseDouble(txtTamano.getText().trim())
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Los campos numéricos son inválidos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            return null;
        }
    }
}