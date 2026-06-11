package interfaz;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Roles;
import presenter.AgregarEmpleadoPresenter;

public class ViewAgregarEmpleado {

	private JFrame frame;
	private JTextField jtxt_nombreEmp;
	private JComboBox<Roles> jcbox_Roles;
	private AgregarEmpleadoPresenter presenter;
	private JSpinner spinner_calificacion;

// hagan esto sencillo, solo es para que el usuario meta los datos del empleado que quiere agregar.
	// podriamos usar la funcionalidad para que agregue una foto pero obvio es mas
	// trabajo
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

	public ViewAgregarEmpleado() {
		initialize();
	}

	public void setPresenter(AgregarEmpleadoPresenter presenter) {
		this.presenter = presenter;
	}

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		jtxt_nombreEmp = new JTextField();
		jtxt_nombreEmp.setBounds(30, 149, 86, 20);
		frame.getContentPane().add(jtxt_nombreEmp);
		jtxt_nombreEmp.setColumns(10);

		jcbox_Roles = new JComboBox(Roles.values()); //tiene pinta de que algo pasa aca pero no se que 
		jcbox_Roles.setBounds(165, 148, 94, 22);
		frame.getContentPane().add(jcbox_Roles);

		JButton Boton_cancelar = new JButton("Cancelar");
		Boton_cancelar.addActionListener(e -> {
			if (presenter != null)
				presenter.cancelarPulsado();
		});
		Boton_cancelar.setBounds(77, 211, 89, 23);
		frame.getContentPane().add(Boton_cancelar);

		JButton boton_agregar = new JButton("Agregar");
		boton_agregar.addActionListener(e -> {
			if (presenter != null)
				presenter.agregarPulsado(getNombre(),getRolSeleccionado(),getCalificacionInt());

		});
		boton_agregar.setBounds(242, 211, 89, 23);
		frame.getContentPane().add(boton_agregar);

		JLabel lbl_ingNombre = new JLabel("Nombre");
		lbl_ingNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ingNombre.setBounds(30, 124, 86, 14);
		frame.getContentPane().add(lbl_ingNombre);

		JLabel lbl_ingRol = new JLabel("Rol");
		lbl_ingRol.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ingRol.setBounds(165, 123, 94, 14);
		frame.getContentPane().add(lbl_ingRol);

		JLabel lbl_ingCalificacion = new JLabel("Calificacion");
		lbl_ingCalificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ingCalificacion.setBounds(291, 124, 86, 14);
		frame.getContentPane().add(lbl_ingCalificacion);

		JLabel lbl_textoTitulo = new JLabel("Ingrese los datos del nuevo empleado a ingresar");
		lbl_textoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_textoTitulo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_textoTitulo.setBounds(10, 28, 414, 69);
		frame.getContentPane().add(lbl_textoTitulo);
		
		spinner_calificacion = new JSpinner();
		spinner_calificacion.setBounds(301, 149, 76, 20);
		frame.getContentPane().add(spinner_calificacion);
	}

	public void hacerVisible() {
	    this.frame.setVisible(true);
	}

	// getters
	public String getNombre() {
		return jtxt_nombreEmp.getText().trim();
	}

	public int getCalificacionInt() { return (int) spinner_calificacion.getValue();}
	
	public Roles getRolSeleccionado() { return (Roles) jcbox_Roles.getSelectedItem(); }

	public void mostrarError(String msj) {
        JOptionPane.showMessageDialog(frame, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }
	public void cerrar() { this.frame.dispose(); }
}

