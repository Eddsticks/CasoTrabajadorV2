package model;

public class Persona {
    private String nombre;
    private String apellido;
    private String rut;

    public Persona(String nombre, String apellido, String rut) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}