package controller;

import model.Trabajador;

import java.util.HashMap;

public class TrabajadorServiceImpl implements TrabajadorService {
    private final TrabajadorRepository trabajadorRepository;

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    @Override
    public void crearTrabajador(Trabajador trabajador) {
        validarTrabajador(trabajador);
        trabajadorRepository.crearTrabajador(trabajador);
    }

    @Override
    public Trabajador obtenerTrabajador(int id) {
        validarId(id);
        return trabajadorRepository.obtenerTrabajador(id);
    }

    @Override
    public HashMap<Integer, Trabajador> obtenerTrabajadores() {
        return trabajadorRepository.obtenerTrabajadores();
    }

    @Override
    public void actualizarTrabajador(int id, Trabajador trabajador) {
        validarId(id);
        validarTrabajador(trabajador);
        trabajadorRepository.actualizarTrabajador(id, trabajador);
    }

    @Override
    public void eliminarTrabajador(int id) {
        validarId(id);
        trabajadorRepository.eliminarTrabajador(id);
    }

    private void validarId(int id) {
        if (!trabajadorRepository.obtenerTrabajadores().containsKey(id)) {
            throw new IllegalArgumentException("El trabajador no existe");
        }
    }

    private void validarTrabajador(Trabajador trabajador) {
        validarNombre(trabajador.getNombre());
        validarApellido(trabajador.getApellido());
        validarRut(trabajador.getRut());
        validarIsapre(trabajador.getIsapre());
        validarAfp(trabajador.getAfp());
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
    }

    private void validarApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
        }
    }

    private void validarRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            throw new IllegalArgumentException("El rut no puede ser nulo o vacío");
        }
    }

    private void validarIsapre(String isapre) {
        if (isapre == null || isapre.isEmpty()) {
            throw new IllegalArgumentException("La isapre no puede ser nula o vacía");
        }
    }

    private void validarAfp(String afp) {
        if (afp == null || afp.isEmpty()) {
            throw new IllegalArgumentException("La AFP no puede ser nula o vacía");
        }
    }
}