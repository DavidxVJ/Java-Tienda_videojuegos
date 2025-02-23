package com.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Juegos {
    //Creacion de la clase Juegos

    private SimpleStringProperty nombre, plataforma, genero, programador;
    private SimpleIntegerProperty precio, anio, id_videojuegos;

    //Constructor de la clase Juegos
    public Juegos(String nombre, String plataforma, String genero, int precio, int anio, String programador, int id_videojuegos) {
        this.nombre = new SimpleStringProperty(nombre);
        this.plataforma = new SimpleStringProperty(plataforma);
        this.genero = new SimpleStringProperty(genero);
        this.precio = new SimpleIntegerProperty(precio);
        this.anio = new SimpleIntegerProperty(anio);
        this.programador = new SimpleStringProperty(programador);
        this.id_videojuegos = new SimpleIntegerProperty(id_videojuegos);
    }
    
    //Metodos get y set de la clase Juegos
    public String getNombre() {
        return nombre.get();
    }
    public SimpleStringProperty nombreProperty() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getPlataforma() {
        return plataforma.get();
    }
    public SimpleStringProperty plataformaProperty() {
        return plataforma;
    }
    public void setPlataforma(String plataforma) {
        this.plataforma = new SimpleStringProperty(plataforma);
    }

    public String getGenero() {
        return genero.get();
    }
    public SimpleStringProperty generoProperty() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = new SimpleStringProperty(genero);
    }

    public int getPrecio() {
        return precio.get();
    }
    public SimpleIntegerProperty precioProperty() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = new SimpleIntegerProperty(precio);
    }

    public int getAnio() {
        return anio.get();
    }
    public SimpleIntegerProperty anioProperty() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = new SimpleIntegerProperty(anio);
    }

    public String getProgramador() {
        return programador.get();
    }
    public SimpleStringProperty programadorProperty() {
        return programador;
    }
    public void setProgramador(String programador) {
        this.programador = new SimpleStringProperty(programador);
    }

    public SimpleIntegerProperty id_videojuegosProperty() {
        return id_videojuegos;
    }
    public int getid_videojuegos() {
        return id_videojuegos.get();
    }
    public void setid_videojuegos(SimpleIntegerProperty id_videojuegos) {
        this.id_videojuegos = id_videojuegos;
    }
}
