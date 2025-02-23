package com.example;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class PrimaryController implements Initializable {

    //Creacion del TableView catalogo y sus columnas
    @FXML private TableView<Juegos> catalogo = new TableView<>();
    @FXML private TableColumn<Juegos, String> nombreColumn = new TableColumn<>();
    @FXML private TableColumn<Juegos, String> plataformaColumn = new TableColumn<>();
    @FXML private TableColumn<Juegos, String> generoColumn = new TableColumn<>();
    @FXML private TableColumn<Juegos, Integer> precioColumn = new TableColumn<>();
    @FXML private TableColumn<Juegos, Integer> anioColumn = new TableColumn<>();
    @FXML private TableColumn<Juegos, String> programadorColumn = new TableColumn<>();

    //Creacion del TableView carrito y sus columnas
    @FXML private TableView<Juegos> carrito = new TableView<>();
    @FXML private TableColumn<Juegos, String> nombreColumn2 = new TableColumn<>();
    @FXML private TableColumn<Juegos, String> plataformaColumn2 = new TableColumn<>();
    @FXML private TableColumn<Juegos, String> generoColumn2 = new TableColumn<>();
    @FXML private TableColumn<Juegos, Integer> precioColumn2 = new TableColumn<>();
    @FXML private TableColumn<Juegos, Integer> anioColumn2 = new TableColumn<>();
    @FXML private TableColumn<Juegos, String> programadorColumn2 = new TableColumn<>();

    //Creacion del ComboBox de plataformas y generos
    @FXML private ComboBox<String> plataforma;
    @FXML private ComboBox<String> genero;

    //Creacion del textField del total a pagar
    @FXML private TextField total;

    //Declaracion de variables
    int total_carrito = 0;
    String query = null;
    Connection conexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Juegos juego = null;

    //Creacion de las Observable Lists
    ObservableList<Juegos> listaJuegos = FXCollections.observableArrayList();
    ObservableList<Juegos> listaCarrito = FXCollections.observableArrayList();

    //Metodo Main
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            conexion = BD.Conectar(); // Conexion a la Base de Datos
            refresh(); // Generar el catalogo
            refreshCarrito(); // Generar el carrito
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos");
        }
        //Display del catalogo y del carrito
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Juegos,String>>() {
            @Override
            public void handle(CellEditEvent<Juegos, String> t){
                Juegos juegos = t.getRowValue();
                juegos.setNombre(t.getNewValue());
                String query = "UPDATE Catalogo_videojuegos SET nombre = ? WHERE nombre = ?";
                try {
                    preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, t.getNewValue());
                    preparedStatement.setString(2, juegos.getNombre());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error al actualizar el nombre");
                }
            }
        });


        plataformaColumn.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        plataformaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        plataformaColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Juegos,String>>() {
            @Override
            public void handle(CellEditEvent<Juegos, String> t){
                Juegos juegos = t.getRowValue();
                juegos.setPlataforma(t.getNewValue());
                String query = "UPDATE Catalogo_videojuegos SET plataforma = ? WHERE plataforma = ?";
                try {
                    preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, t.getNewValue());
                    preparedStatement.setString(2, juegos.getPlataforma());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error al actualizar la plataforma");
                }
            }
        });

        generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        generoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        generoColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Juegos,String>>() {
            @Override
            public void handle(CellEditEvent<Juegos, String> t){
                Juegos juegos = t.getRowValue();
                juegos.setGenero(t.getNewValue());
                String query = "UPDATE Catalogo_videojuegos SET genero = ? WHERE genero = ?";
                try {
                    preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, t.getNewValue());
                    preparedStatement.setString(2, juegos.getGenero());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error al actualizar el genero");
                }
            }
        });

        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        precioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        precioColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Juegos,Integer>>() {
            @Override
            public void handle(CellEditEvent<Juegos, Integer> t){
                Juegos juegos = t.getRowValue();
                juegos.setPrecio(t.getNewValue());
                String query = "UPDATE Catalogo_videojuegos SET precio = ? WHERE precio = ?";
                try {
                    preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setInt(1, t.getNewValue());
                    preparedStatement.setInt(2, juegos.getPrecio());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error al actualizar el precio");
                }
            }
        });

        anioColumn.setCellValueFactory(new PropertyValueFactory<>("anio"));
        anioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        anioColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Juegos,Integer>>() {
            @Override
            public void handle(CellEditEvent<Juegos, Integer> t){
                Juegos juegos = t.getRowValue();
                juegos.setAnio(t.getNewValue());
                String query = "UPDATE Catalogo_videojuegos SET anio = ? WHERE anio = ?";
                try {
                    preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setInt(1, t.getNewValue());
                    preparedStatement.setInt(2, juegos.getAnio());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error al actualizar el año");
                }
            }
        });
        
        programadorColumn.setCellValueFactory(new PropertyValueFactory<>("programador"));
        programadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        programadorColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Juegos,String>>() {
            @Override
            public void handle(CellEditEvent<Juegos, String> t){
                Juegos juegos = t.getRowValue();
                juegos.setProgramador(t.getNewValue());
                String query = "UPDATE Catalogo_videojuegos SET programador = ? WHERE programador = ?";
                try {
                    preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, t.getNewValue());
                    preparedStatement.setString(2, juegos.getProgramador());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error al actualizar el programador");
                }
            }
        });

        nombreColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, String>("nombre"));
        plataformaColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, String>("plataforma"));
        generoColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, String>("genero"));
        precioColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, Integer>("precio"));
        anioColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, Integer>("anio"));
        programadorColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, String>("programador"));
        
        carrito.setItems(listaCarrito);
        carrito.refresh();
        
        catalogo.setItems(listaJuegos);
        catalogo.refresh();
        
        //Carga datos al ComboBox de Plataformas
        ArrayList<String> lstPlataforma = new ArrayList<String>();
        try{
            String selectSQL = "select distinct(plataforma) from Catalogo_videojuegos;";
            Statement statement = conexion.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while(resultSet.next()){
                lstPlataforma.add(resultSet.getString("plataforma"));
            }
        }catch(SQLException ex){
            System.err.println("Error en el query: " + ex.getMessage());
        }
        this.plataforma.getItems().add("Mostrar todas las plataformas");
        this.plataforma.getItems().addAll(lstPlataforma);
       
        //Cargar datos al ComboBox de Genero
        ArrayList<String> lstGenero = new ArrayList<String>();
        try{
            String selectSQL = "select distinct(genero) from Catalogo_videojuegos;";
            Statement statement = conexion.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while(resultSet.next()){
                lstGenero.add(resultSet.getString("genero"));
            }
        }catch(SQLException ex){
            System.err.println("Error en el query: " + ex.getMessage());
        }
        this.genero.getItems().add("Mostrar todos los géneros");
        this.genero.getItems().addAll(lstGenero);

    }

    //Metodo para filtrar por plataforma
    @FXML
    void filtrar() {
        this.genero.setValue("Mostrar todos los géneros");
        String opcion1=plataforma.getValue();
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        plataformaColumn.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        anioColumn.setCellValueFactory(new PropertyValueFactory<>("anio"));
        programadorColumn.setCellValueFactory(new PropertyValueFactory<>("programador"));
        catalogo.getItems().clear();
        
        ObservableList<Juegos> Juegos = FXCollections.observableArrayList();
        try {
            String query = "select nombre, plataforma, genero, precio, anio, programador, id_videojuegos from catalogo_videojuegos where plataforma='"+opcion1+"';";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Juegos.add(new Juegos (resultSet.getString("nombre"),
                                        resultSet.getString("plataforma"),
                                        resultSet.getString("genero"),
                                        resultSet.getInt("precio"),
                                        resultSet.getInt("anio"),
                                        resultSet.getString("programador"),
                                        resultSet.getInt("id_videojuegos")));              
            } 
        } catch(SQLException ex){
        System.out.println("Error en el query "+ex.getMessage());
        }
        catalogo.setItems(Juegos);

        //Agregado----------------------------------------------------------------------------------------------------
        if(opcion1.equals("Mostrar todas las plataformas")){
            try {
                conexion = BD.Conectar(); // Conexion a la Base de Datos
                refresh(); // Generar el catalogo
                refreshCarrito(); // Generar el carrito
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error al conectar con la base de datos");
            }
            //Display del catalogo y del carrito
            nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    
            plataformaColumn.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
            
            generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
            
            precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
            
            anioColumn.setCellValueFactory(new PropertyValueFactory<>("anio"));
           
            programadorColumn.setCellValueFactory(new PropertyValueFactory<>("programador"));
            
            catalogo.setItems(listaJuegos);
            catalogo.refresh();
        }
    }

    //Metodo para filtrar por genero
    @FXML
    void filtrar2(){
        this.plataforma.setValue("Mostrar todas las plataformas");
        String opcion2 = genero.getValue();
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        plataformaColumn.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        anioColumn.setCellValueFactory(new PropertyValueFactory<>("anio"));
        programadorColumn.setCellValueFactory(new PropertyValueFactory<>("programador"));
        catalogo.getItems().clear();
        
        ObservableList<Juegos> Juegos = FXCollections.observableArrayList();
        try {
            String query = "select nombre, plataforma, genero, precio, anio, programador, id_videojuegos from catalogo_videojuegos where genero='"+opcion2+"';";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Juegos.add(new Juegos (resultSet.getString("nombre"),
                                        resultSet.getString("plataforma"),
                                        resultSet.getString("genero"),
                                        resultSet.getInt("precio"),
                                        resultSet.getInt("anio"),
                                        resultSet.getString("programador"),
                                        resultSet.getInt("id_videojuegos")));                
            } 
        } catch(SQLException ex){
        System.out.println("Error en el query "+ex.getMessage());
        }
        catalogo.setItems(Juegos);

        //Agregado----------------------------------------------------------------------------------------------------
        if(opcion2.equals("Mostrar todos los géneros")){
            try {
                conexion = BD.Conectar(); // Conexion a la Base de Datos
                refresh(); // Generar el catalogo
                refreshCarrito(); // Generar el carrito
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error al conectar con la base de datos");
            }
            //Display del catalogo y del carrito
            nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    
            plataformaColumn.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
            
            generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
            
            precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
            
            anioColumn.setCellValueFactory(new PropertyValueFactory<>("anio"));
           
            programadorColumn.setCellValueFactory(new PropertyValueFactory<>("programador"));
            
            catalogo.setItems(listaJuegos);
            catalogo.refresh();
        }
    }

    //Metodo para
    public void closeApplication(ActionEvent event) throws Exception{
        System.exit(0);
    }

    //Metodo para agregar juego al carrito
    public void agregarCarrito_click(ActionEvent actionEvent){
        Juegos juego = catalogo.getSelectionModel().getSelectedItem();
        nombreColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, String>("nombre"));
        plataformaColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, String>("plataforma"));
        generoColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, String>("genero"));
        precioColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, Integer>("precio"));
        anioColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, Integer>("anio"));
        programadorColumn2.setCellValueFactory(new PropertyValueFactory<Juegos, String>("programador"));
        
        if(catalogo.getSelectionModel().getSelectedItem() == null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor seleccione el juego a comprar");
            alerta.showAndWait();
        }else{
            try{
                String query = "INSERT INTO Carrito (nombre, plataforma, genero, precio, anio, programador) VALUES (?,?,?,?,?,?)";
                preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, juego.getNombre());
                preparedStatement.setString(2, juego.getPlataforma());
                preparedStatement.setString(3, juego.getGenero());
                preparedStatement.setInt(4, juego.getPrecio());
                preparedStatement.setInt(5, juego.getAnio());
                preparedStatement.setString(6, juego.getProgramador());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al agregar el juego al carrito");
            }
            carrito.setItems(listaCarrito);
            carrito.refresh();
            refreshCarrito();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Agregar ítem");
            alert.setContentText("Se ha agregado el item correctamente");
            alert.showAndWait();
        }
    }

    //Metodo para recargar el catalogo de juegos
    private void refresh(){
        try{
            Statement statement = conexion.createStatement();
            String selectSql = "SELECT * FROM Catalogo_videojuegos";
            System.out.println("Iniciando Catalogo");
            resultSet = statement.executeQuery(selectSql);
            while(resultSet.next()){
                juego = new Juegos(resultSet.getString("nombre"), 
                                    resultSet.getString("plataforma"), 
                                    resultSet.getString("genero"), 
                                    resultSet.getInt("precio"),
                                    resultSet.getInt("anio"),
                                    resultSet.getString("programador"),
                                    resultSet.getInt("id_videojuegos"));
                listaJuegos.add(juego);
                System.out.println(juego.getNombre()+" "+juego.getPlataforma()+" "+juego.getGenero()+" "+juego.getPrecio()+" "+juego.getAnio()+" "+juego.getProgramador());
            }
            System.out.println("----------------------------------------------------");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error al refrescar la tabla"); 
        }
    }

    //Metodo para recargar el carrito
    private void refreshCarrito(){
        carrito.getItems().clear();
        total_carrito = 0;
        try{
            Statement statement = conexion.createStatement();
            String selectSql = "SELECT * FROM Carrito";
            resultSet = statement.executeQuery(selectSql);
            while(resultSet.next()){
                juego = new Juegos(resultSet.getString("nombre"), 
                                    resultSet.getString("plataforma"), 
                                    resultSet.getString("genero"), 
                                    resultSet.getInt("precio"),
                                    resultSet.getInt("anio"),
                                    resultSet.getString("programador"),
                                    resultSet.getInt("id_videojuegos"));
                listaCarrito.add(juego);
                System.out.println(juego.getNombre()+" "+juego.getPlataforma()+" "+juego.getGenero()+" "+juego.getPrecio()+" "+juego.getAnio()+" "+juego.getProgramador());
                total_carrito += juego.getPrecio();
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error al refrescar la tabla"); 
        }
        total.setText("$" + total_carrito);
    }
    
    //Metodo para eliminar un juego del carrito
    public void borrar_click(ActionEvent actionEvent){
        Juegos juego = carrito.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM Carrito WHERE id_videojuegos = ?";
        if(carrito.getSelectionModel().getSelectedItem() == null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor seleccione el juego a eliminar");
            alerta.showAndWait();
        }else{
            try {
                preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setInt(1, juego.getid_videojuegos());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al borrar el juego del carrito");
            }
            carrito.setItems(listaCarrito);
            carrito.refresh();
            refreshCarrito();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Eliminar ítem");
            alert.setContentText("Se ha eliminado correctamente el ítem seleccionado");
            alert.showAndWait();
        }
    }

    //Metodo para editar la tabla de catalogo
    @FXML
    public void editar_click(ActionEvent actionEvent) {
        catalogo.setEditable(true);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Modificar datos");
        alert1.setContentText("De ahora en adelante podrá editar los datos del catálogo");
        alert1.showAndWait();
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText(null);
        alert2.setTitle("Modificar datos");
        alert2.setContentText("'Doble click' en el campo que desea editar para modificarlo y 'Enter' para guardar");
        alert2.showAndWait();
    }

    //Metodo para comprar y limpiar el carrito
    @FXML
    public void comprar_click(ActionEvent actionEvent) throws SQLException {
        String countQuery = "Select * from Carrito";
        preparedStatement = conexion.prepareStatement(countQuery);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next() == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No hay ningún juego en el carrito");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Carrito de compras");
            alert.setContentText("Se ha realizado la compra");
            alert.showAndWait();
            String query = "DELETE FROM Carrito";
            try {
                preparedStatement = conexion.prepareStatement(query);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al borrar el carrito");
            }
            refreshCarrito();
        }
    }
    
}