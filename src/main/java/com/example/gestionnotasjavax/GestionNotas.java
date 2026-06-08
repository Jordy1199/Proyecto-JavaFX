package com.example.gestionnotasjavax;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GestionNotas extends Application {

    // === ENCUESTA ===
    private TextField txtNombre;
    private TextField txtEdad;
    private ComboBox<String> cmbCarrera;
    private ComboBox<String> cmbSemestre;
    private ListView<String> listMaterias;
    private Label lblMensajeEncuesta;

    // === CALCULADORA DE PROMEDIO ===
    private TextField txtNota1;
    private TextField txtNota2;
    private TextField txtNota3;
    private ComboBox<String> cmbOperacion;
    private TextField txtResultado;
    private Label lblUltimoCalculo;
    private ObservableList<String> historialDatos;
    private ListView<String> listHistorial;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1a1a2e;");

        root.setTop(crearCabecera());

        HBox paneles = new HBox(15);
        paneles.setPadding(new Insets(15));
        VBox panelIzq = crearPanelEncuesta();
        VBox panelDer = crearPanelCalculos();
        HBox.setHgrow(panelIzq, Priority.ALWAYS);
        HBox.setHgrow(panelDer, Priority.ALWAYS);
        paneles.getChildren().addAll(panelIzq, panelDer);
        root.setCenter(paneles);

        Scene scene = new Scene(root, 900, 620);
        stage.setTitle("Gestión de Notas Estudiantiles");
        stage.setScene(scene);
        stage.show();
    }

    private VBox crearCabecera() {
        VBox cabecera = new VBox(5);
        cabecera.setAlignment(Pos.CENTER);
        cabecera.setPadding(new Insets(20));
        cabecera.setStyle("-fx-background-color: #16213e;");

        Label titulo = new Label("GESTIÓN DE NOTAS");
        titulo.setFont(Font.font("Monospace", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#e94560"));

        Label subtitulo = new Label("Encuesta + Calculadora de Promedio | JavaFX");
        subtitulo.setFont(Font.font("Monospace", 13));
        subtitulo.setTextFill(Color.web("#a8a8b3"));

        cabecera.getChildren().addAll(titulo, subtitulo);
        return cabecera;
    }

    private VBox crearPanelEncuesta() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(15));
        panel.setStyle("-fx-background-color: #16213e; -fx-border-color: #e94560; -fx-border-width: 2; -fx-border-radius: 8;");

        Label titulo = new Label("ENCUESTA ESTUDIANTIL");
        titulo.setFont(Font.font("Monospace", FontWeight.BOLD, 14));
        titulo.setTextFill(Color.web("#e94560"));

        Label lblNombre = new Label("Nombre completo:");
        lblNombre.setTextFill(Color.web("#a8a8b3"));
        txtNombre = new TextField();
        txtNombre.setPromptText("Ej: Juan Pérez");
        txtNombre.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Label lblEdad = new Label("Edad:");
        lblEdad.setTextFill(Color.web("#a8a8b3"));
        txtEdad = new TextField();
        txtEdad.setPromptText("Ej: 20");
        txtEdad.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Label lblCarrera = new Label("Carrera:");
        lblCarrera.setTextFill(Color.web("#a8a8b3"));
        cmbCarrera = new ComboBox<>(FXCollections.observableArrayList(
                "Desarrollo de Software","Ingeniería en Sistemas", "Medicina", "Telecomunicaciones", "Administración"));
        cmbCarrera.setPromptText("-- Seleccione --");
        cmbCarrera.setMaxWidth(Double.MAX_VALUE);
        cmbCarrera.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Label lblSemestre = new Label("Semestre:");
        lblSemestre.setTextFill(Color.web("#a8a8b3"));
        cmbSemestre = new ComboBox<>(FXCollections.observableArrayList(
                "1ro", "2do", "3ro", "4to", "5to", "6to", "7mo", "8vo"));
        cmbSemestre.setPromptText("-- Seleccione --");
        cmbSemestre.setMaxWidth(Double.MAX_VALUE);
        cmbSemestre.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Label lblMaterias = new Label("Materias favoritas (Ctrl+Click):");
        lblMaterias.setTextFill(Color.web("#a8a8b3"));
        listMaterias = new ListView<>(FXCollections.observableArrayList(
                "Matemáticas", "Programación", "Base de Datos", "Física", "Estadística", "Redes"));
        listMaterias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listMaterias.setPrefHeight(120);
        listMaterias.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Button btnRegistrar = new Button("Registrar");
        btnRegistrar.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-cursor: hand;");
        btnRegistrar.setOnAction(e -> registrarEncuesta());

        Button btnLimpiar = new Button("Limpiar");
        btnLimpiar.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white; -fx-cursor: hand;");
        btnLimpiar.setOnAction(e -> limpiarEncuesta());

        HBox botones = new HBox(10, btnRegistrar, btnLimpiar);

        lblMensajeEncuesta = new Label("");
        lblMensajeEncuesta.setTextFill(Color.web("#00ff88"));
        lblMensajeEncuesta.setFont(Font.font("Monospace", 12));

        panel.getChildren().addAll(titulo, lblNombre, txtNombre, lblEdad, txtEdad,
                lblCarrera, cmbCarrera, lblSemestre, cmbSemestre,
                lblMaterias, listMaterias, botones, lblMensajeEncuesta);
        return panel;
    }
    private VBox crearPanelCalculos() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(15));
        panel.setStyle("-fx-background-color: #16213e; -fx-border-color: #0f3460; -fx-border-width: 2; -fx-border-radius: 8;");

        Label titulo = new Label("CALCULADORA DE NOTAS");
        titulo.setFont(Font.font("Monospace", FontWeight.BOLD, 14));
        titulo.setTextFill(Color.web("#00b4d8"));

        Label lblNota1 = new Label("Nota 1:");
        lblNota1.setTextFill(Color.web("#a8a8b3"));
        txtNota1 = new TextField();
        txtNota1.setPromptText("Ej: 8.5");
        txtNota1.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Label lblNota2 = new Label("Nota 2:");
        lblNota2.setTextFill(Color.web("#a8a8b3"));
        txtNota2 = new TextField();
        txtNota2.setPromptText("Ej: 7.0");
        txtNota2.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Label lblNota3 = new Label("Nota 3:");
        lblNota3.setTextFill(Color.web("#a8a8b3"));
        txtNota3 = new TextField();
        txtNota3.setPromptText("Ej: 9.0");
        txtNota3.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Label lblOperacion = new Label("Operación:");
        lblOperacion.setTextFill(Color.web("#a8a8b3"));
        cmbOperacion = new ComboBox<>(FXCollections.observableArrayList(
                "Promedio", "Suma", "Mayor nota", "Menor nota", "Diferencia (N1-N2)"));
        cmbOperacion.setPromptText("-- Seleccione --");
        cmbOperacion.setMaxWidth(Double.MAX_VALUE);
        cmbOperacion.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Label lblResultado = new Label("Resultado:");
        lblResultado.setTextFill(Color.web("#a8a8b3"));
        txtResultado = new TextField();
        txtResultado.setEditable(false);
        txtResultado.setStyle("-fx-background-color: #0f3460; -fx-text-fill: #00ff88; -fx-font-weight: bold;");

        Button btnCalcular = new Button("Calcular");
        btnCalcular.setStyle("-fx-background-color: #00b4d8; -fx-text-fill: white; -fx-cursor: hand;");
        btnCalcular.setOnAction(e -> realizarCalculo());

        Button btnLimpiar = new Button("Limpiar");
        btnLimpiar.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white; -fx-cursor: hand;");
        btnLimpiar.setOnAction(e -> limpiarCalculo());

        Button btnHistorial = new Button("Al historial");
        btnHistorial.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white; -fx-cursor: hand;");
        btnHistorial.setOnAction(e -> agregarHistorial());

        HBox botones = new HBox(10, btnCalcular, btnLimpiar, btnHistorial);

        lblUltimoCalculo = new Label("");
        lblUltimoCalculo.setTextFill(Color.web("#a8a8b3"));
        lblUltimoCalculo.setFont(Font.font("Monospace", 11));

        Label lblHistorial = new Label("HISTORIAL DE OPERACIONES:");
        lblHistorial.setTextFill(Color.web("#00b4d8"));
        lblHistorial.setFont(Font.font("Monospace", FontWeight.BOLD, 12));

        historialDatos = FXCollections.observableArrayList();
        listHistorial = new ListView<>(historialDatos);
        listHistorial.setPrefHeight(130);
        listHistorial.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");

        Button btnBorrarHistorial = new Button("Borrar Historial");
        btnBorrarHistorial.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-cursor: hand;");
        btnBorrarHistorial.setOnAction(e -> historialDatos.clear());

        panel.getChildren().addAll(titulo, lblNota1, txtNota1, lblNota2, txtNota2,
                lblNota3, txtNota3, lblOperacion, cmbOperacion,
                lblResultado, txtResultado, botones, lblUltimoCalculo,
                lblHistorial, listHistorial, btnBorrarHistorial);
        return panel;
    }
    private void registrarEncuesta() {
        String nombre = txtNombre.getText().trim();
        String edad = txtEdad.getText().trim();

        if (nombre.isEmpty() || edad.isEmpty()) {
            lblMensajeEncuesta.setText("⚠ Complete nombre y edad.");
            lblMensajeEncuesta.setTextFill(Color.web("#e94560"));
            return;
        }
        if (cmbCarrera.getValue() == null || cmbSemestre.getValue() == null) {
            lblMensajeEncuesta.setText("⚠ Seleccione carrera y semestre.");
            lblMensajeEncuesta.setTextFill(Color.web("#e94560"));
            return;
        }
        try {
            Integer.parseInt(edad);
        } catch (NumberFormatException ex) {
            lblMensajeEncuesta.setText("⚠ La edad debe ser un número.");
            lblMensajeEncuesta.setTextFill(Color.web("#e94560"));
            return;
        }
        String[] partes = nombre.split(" ");
        String nombreCorto = partes[0];
        lblMensajeEncuesta.setText("✔ Registro guardado para: " + nombreCorto);
        lblMensajeEncuesta.setTextFill(Color.web("#00ff88"));
    }

    private void realizarCalculo() {
        String s1 = txtNota1.getText().trim();
        String s2 = txtNota2.getText().trim();
        String s3 = txtNota3.getText().trim();

        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
            txtResultado.setText("⚠ Ingrese las 3 notas.");
            return;
        }
        if (cmbOperacion.getValue() == null) {
            txtResultado.setText("⚠ Seleccione operación.");
            return;
        }
        try {
            double n1 = Double.parseDouble(s1);
            double n2 = Double.parseDouble(s2);
            double n3 = Double.parseDouble(s3);
            double resultado = 0;
            String operacion = cmbOperacion.getValue();

            switch (operacion) {
                case "Promedio":
                    resultado = (n1 + n2 + n3) / 3.0;
                    break;
                case "Suma":
                    resultado = n1 + n2 + n3;
                    break;
                case "Mayor nota":
                    resultado = Math.max(n1, Math.max(n2, n3));
                    break;
                case "Menor nota":
                    resultado = Math.min(n1, Math.min(n2, n3));
                    break;
                case "Diferencia (N1-N2)":
                    resultado = n1 - n2;
                    break;
            }

            String resTexto = String.format("%.2f", resultado);
            txtResultado.setText(resTexto);
            lblUltimoCalculo.setText("Último cálculo: " + operacion + " = " + resTexto);

        } catch (NumberFormatException ex) {
            txtResultado.setText("⚠ Las notas deben ser números.");
        }
    }

    private void agregarHistorial() {
        String resultado = txtResultado.getText().trim();
        String operacion = cmbOperacion.getValue();
        if (resultado.isEmpty() || operacion == null) return;
        historialDatos.add(0, operacion + " = " + resultado);
    }

    private void limpiarEncuesta() {
        txtNombre.clear();
        txtEdad.clear();
        cmbCarrera.setValue(null);
        cmbSemestre.setValue(null);
        listMaterias.getSelectionModel().clearSelection();
        lblMensajeEncuesta.setText("");
    }

    private void limpiarCalculo() {
        txtNota1.clear();
        txtNota2.clear();
        txtNota3.clear();
        cmbOperacion.setValue(null);
        txtResultado.clear();
        lblUltimoCalculo.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}