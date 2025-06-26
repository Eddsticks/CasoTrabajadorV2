package launcher;

import controller.TrabajadorController;
import controller.TrabajadorRepository;
import controller.TrabajadorService;
import controller.TrabajadorServiceImpl;
import view.TrabajadorView;

public class Inicio {
    public static void main(String[] args) {
        TrabajadorRepository trabajadorRepository = new TrabajadorRepository();
        TrabajadorService trabajadorService = new TrabajadorServiceImpl(trabajadorRepository);
        TrabajadorController trabajadorController = new TrabajadorController(trabajadorService);
        TrabajadorView trabajadorView = new TrabajadorView(trabajadorController);
        trabajadorView.menu();
    }
}