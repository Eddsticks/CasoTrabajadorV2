package launcher;

import controller.TrabajadorController;
import controller.TrabajadorRepository;
import controller.TrabajadorService;
import controller.TrabajadorServiceImpl;
import view.GUI.TrabajadorGUI;
import javax.swing.SwingUtilities;

public class Inicio {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrabajadorRepository trabajadorRepository = new TrabajadorRepository();
            TrabajadorService trabajadorService = new TrabajadorServiceImpl(trabajadorRepository);
            TrabajadorController trabajadorController = new TrabajadorController(trabajadorService);
            TrabajadorGUI gui = new TrabajadorGUI(trabajadorController);
            gui.setVisible(true);
        });
    }
}