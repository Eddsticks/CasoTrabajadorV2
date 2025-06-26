package controller;

import model.Trabajador;

import java.util.HashMap;

public class TrabajadorRepository {
    private final HashMap<Integer, Trabajador> trabajadores = new HashMap<Integer, Trabajador>();
    private int nextId = 1;

    public void crearTrabajador(Trabajador trabajador) {
        int id = nextId;
        nextId++;
        trabajadores.put(id, trabajador);
    }

    public Trabajador obtenerTrabajador(int id) {
        return trabajadores.get(id);
    }

    public HashMap<Integer, Trabajador> obtenerTrabajadores() {
        return new HashMap<>(trabajadores);
    }

    public void actualizarTrabajador(int id, Trabajador trabajador) {
        trabajadores.put(id, trabajador);
    }

    public void eliminarTrabajador(int id) {
        trabajadores.remove(id);
    }
}