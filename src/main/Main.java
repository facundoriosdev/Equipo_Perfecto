package main;

import java.awt.EventQueue;

import interfaz.ViewMenu;
import presenter.MenuPresenter;

public class Main {

    public static void main(String[] args) {
        // Ejecutamos la interfaz gráfica en el hilo seguro de Swing (Event Dispatch Thread)
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 1. Instanciamos la Vista principal (la pantalla)
                    ViewMenu vistaPrincipal = new ViewMenu();
                    
                    // 2. Instanciamos el Presentador principal y le pasamos la vista
                    // (Recordá que el MenuPresenter ya tiene el this.view.setPresenter(this) en su constructor)
                    @SuppressWarnings("unused")
					MenuPresenter presentador = new MenuPresenter(vistaPrincipal);
                    
                    // 3. Encendemos la pantalla
                    vistaPrincipal.hacerVisible();
                    
                } catch (Exception e) {
                    System.out.println("Ocurrió un error al iniciar la aplicación:");
                    e.printStackTrace();
                }
            }
        });
    }
}