package controller;

import model.Trabajador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TrabajadorControllerTest {
    private TrabajadorController trabajadorController;

    @BeforeEach
    void setUp() {
        TrabajadorRepository trabajadorRepository = new TrabajadorRepository();
        TrabajadorService trabajadorService = new TrabajadorServiceImpl(trabajadorRepository);
        trabajadorController = new TrabajadorController(trabajadorService);
    }

    @Test
    void crearTrabajador() {
        trabajadorController.crearTrabajador("amadeus", "salieri", "12345678-9", "Colmena", "UNO AFP");
        Trabajador trabajador = trabajadorController.obtenerTrabajador(1);
        assertNotNull(trabajador);
    }

    @Test
    void crearTrabajadorNombreNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trabajadorController.crearTrabajador(null, "salieri", "12345678-9", "Colmena", "UNO AFP");
        });
        assertEquals("El nombre no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void crearTrabajadorNombreVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trabajadorController.crearTrabajador("", "salieri", "12345678-9", "Colmena", "UNO AFP");
        });
        assertEquals("El nombre no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void obtenerTrabajador() {
        trabajadorController.crearTrabajador("amadeus", "salieri", "12345678-9", "Colmena", "UNO AFP");
        Trabajador trabajador = trabajadorController.obtenerTrabajador(1);
        assertEquals("amadeus", trabajador.getNombre());
        assertEquals("salieri", trabajador.getApellido());
        assertEquals("12345678-9", trabajador.getRut());
        assertEquals("Colmena", trabajador.getIsapre());
        assertEquals("UNO AFP", trabajador.getAfp());
    }

    @Test
    void obtenerTrabajadorInexistente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trabajadorController.obtenerTrabajador(1);
        });
        assertEquals("El trabajador no existe", exception.getMessage());
    }

    @Test
    void obtenerTrabajadores() {
        trabajadorController.crearTrabajador("amadeus", "salieri", "12345678-9", "Colmena", "UNO AFP");
        trabajadorController.crearTrabajador("balatro", "balatrez", "23456789-0", "Banmedica", "UNO AFP");
        HashMap<Integer, Trabajador> trabajadores = trabajadorController.obtenerTrabajadores();
        assertEquals(2, trabajadores.size());
    }

    @Test
    void actualizarTrabajador() {
        trabajadorController.crearTrabajador("amadeus", "salieri", "12345678-9", "Colmena", "UNO AFP");
        trabajadorController.actualizarTrabajador(1, "balatro", "salieri", "12345678-9", "Colmena", "UNO AFP");
        Trabajador trabajador = trabajadorController.obtenerTrabajador(1);
        assertNotNull(trabajador);
        assertEquals("balatro", trabajador.getNombre());
    }

    @Test
    void actualizarTrabajadorInexistente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trabajadorController.actualizarTrabajador(1, "amadeus", "salieri", "12345678-9", "Colmena", "UNO AFP");
        });
        assertEquals("El trabajador no existe", exception.getMessage());
    }

    @Test
    void actualizarTrabajadorNombreNulo() {
        trabajadorController.crearTrabajador("amadeus", "salieri", "12345678-9", "Colmena", "UNO AFP");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trabajadorController.actualizarTrabajador(1, null, "salieri", "12345678-9", "Colmena", "UNO AFP");
        });
        assertEquals("El nombre no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void actualizarTrabajadorNombreVacio() {
        trabajadorController.crearTrabajador("amadeus", "salieri", "12345678-9", "Colmena", "UNO AFP");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trabajadorController.actualizarTrabajador(1, "", "salieri", "12345678-9", "Colmena", "UNO AFP");
        });
        assertEquals("El nombre no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void eliminarTrabajador() {
        trabajadorController.crearTrabajador("amadeus", "salieri", "12345678-9", "Colmena", "UNO AFP");
        trabajadorController.eliminarTrabajador(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trabajadorController.obtenerTrabajador(1);
        });
        assertEquals("El trabajador no existe", exception.getMessage());
    }

    @Test
    void eliminarTrabajadorInexistente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trabajadorController.eliminarTrabajador(1);
        });
        assertEquals("El trabajador no existe", exception.getMessage());
    }
}