package view;

import controller.TrabajadorController;
import model.Trabajador;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TrabajadorView {
    private final Scanner scanner = new Scanner(System.in);
    private final TrabajadorController trabajadorController;

    public TrabajadorView(TrabajadorController trabajadorController) {
        this.trabajadorController = trabajadorController;
    }

    public void menu() {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            String opcion = scanner.nextLine();
            continuar = ejecutarOpcion(opcion);
        }
    }

    private void mostrarMenu() {
        System.out.println("1. Crear trabajador");
        System.out.println("2. Obtener trabajador por ID");
        System.out.println("3. Obtener trabajadores");
        System.out.println("4. Actualizar trabajador");
        System.out.println("5. Eliminar trabajador");
        System.out.println("6. Salir");
    }

    private boolean ejecutarOpcion(String opcion) {
        switch (opcion) {
            case "1":
                crearTrabajador();
                return true;
            case "2":
                obtenerTrabajadorPorId();
                return true;
            case "3":
                obtenerTrabajadores();
                return true;
            case "4":
                actualizarTrabajador();
                return true;
            case "5":
                eliminarTrabajador();
                return true;
            case "6":
                return false;
            default:
                System.out.println("Opción inválida");
                return true;
        }
    }

    private void crearTrabajador() {
        System.out.print("Ingrese el nombre del trabajador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del trabajador: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el rut del trabajador: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese la isapre del trabajador: ");
        String isapre = scanner.nextLine();
        System.out.print("Ingrese la AFP del trabajador: ");
        String afp = scanner.nextLine();
        try {
            trabajadorController.crearTrabajador(nombre, apellido, rut, isapre, afp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtenerTrabajadorPorId() {
        System.out.print("Ingrese el ID del trabajador: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            Trabajador trabajador = trabajadorController.obtenerTrabajador(id);
            mostrarInformacionTrabajador(trabajador);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtenerTrabajadores() {
        HashMap<Integer, Trabajador> trabajadores = trabajadorController.obtenerTrabajadores();
        for (Map.Entry<Integer, Trabajador> entry : trabajadores.entrySet()) {
            int id = entry.getKey();
            Trabajador trabajador = entry.getValue();
            System.out.println("ID: " + id);
            mostrarInformacionTrabajador(trabajador);
        }
    }

    private void actualizarTrabajador() {
        System.out.print("Ingrese el ID del trabajador a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo nombre del trabajador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo apellido del trabajador: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el nuevo rut del trabajador: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese la nueva isapre del trabajador: ");
        String isapre = scanner.nextLine();
        System.out.print("Ingrese la nueva AFP del trabajador: ");
        String afp = scanner.nextLine();
        try {
            trabajadorController.actualizarTrabajador(id, nombre, apellido, rut, isapre, afp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarTrabajador() {
        System.out.print("Ingrese el ID del trabajador a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            trabajadorController.eliminarTrabajador(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarInformacionTrabajador(Trabajador trabajador) {
        System.out.println("Nombre: " + trabajador.getNombre());
        System.out.println("Apellido: " + trabajador.getApellido());
        System.out.println("Rut: " + trabajador.getRut());
        System.out.println("Isapre: " + trabajador.getIsapre());
        System.out.println("AFP: " + trabajador.getAfp());
    }
}