package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ViewAgregarEmpleado {

	private JFrame frame;

// hagan esto sencillo, solo es para que el usuario meta los datos del empleado que quiere agregar.
	// podriamos usar la funcionalidad para que agregue una foto pero obvio es mas trabajo
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAgregarEmpleado window = new ViewAgregarEmpleado();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewAgregarEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void hacerVisible() {
		// TODO Auto-generated method stub
		
	}

}
