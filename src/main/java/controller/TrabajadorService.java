package controller;

import model.Trabajador;

import java.util.HashMap;

public interface TrabajadorService {
    void crearTrabajador(Trabajador trabajador);

    Trabajador obtenerTrabajador(int id);

    HashMap<Integer, Trabajador> obtenerTrabajadores();

    void actualizarTrabajador(int id, Trabajador trabajador);

    void eliminarTrabajador(int id);
}