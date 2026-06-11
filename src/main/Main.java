package main;

import java.awt.EventQueue;

import interfaz.ViewMenu;
import presenter.MenuPresenter;

public class Main {

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   
                    ViewMenu vistaPrincipal = new ViewMenu();
                    
                    
                    @SuppressWarnings("unused")
					MenuPresenter presentador = new MenuPresenter(vistaPrincipal);
                    
                    
                    vistaPrincipal.hacerVisible();
                    
                } catch (Exception e) {
                    System.out.println("Ocurrió un error al iniciar la aplicación:");
                    e.printStackTrace();
                }
            }
        });
    }
}