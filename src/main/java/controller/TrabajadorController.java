package controller;

import model.Trabajador;

import java.util.HashMap;

public class TrabajadorController {
    private TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    public void crearTrabajador(String nombre, String apellido, String rut, String isapre, String afp) {
        Trabajador trabajador = new Trabajador(nombre, apellido, rut, isapre, afp);
        trabajadorService.crearTrabajador(trabajador);
    }

    public Trabajador obtenerTrabajador(int id) {
        return trabajadorService.obtenerTrabajador(id);
    }

    public HashMap<Integer, Trabajador> obtenerTrabajadores() {
        return trabajadorService.obtenerTrabajadores();
    }

    public void actualizarTrabajador(int id, String nombre, String apellido, String rut, String isapre, String afp) {
        Trabajador trabajador = new Trabajador(nombre, apellido, rut, isapre, afp);
        trabajadorService.actualizarTrabajador(id, trabajador);
    }

    public void eliminarTrabajador(int id) {
        trabajadorService.eliminarTrabajador(id);
    }
}