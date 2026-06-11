package interfaz;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.Empleado;
import presenter.AgregarIncompatibilidadPresenter;

public class ViewAgregarIncompatibilidad {

	private JFrame frame;
	private JList<Empleado> list_1;
	private JList<Empleado> list_2;
	@SuppressWarnings("unused")
	private JButton btnSalir;
	@SuppressWarnings("unused")
	private JButton btnAgregar;
	private AgregarIncompatibilidadPresenter presenter;

	
	public ViewAgregarIncompatibilidad() {
		initialize();
	}
	public void setPresenter(AgregarIncompatibilidadPresenter presenter) {
        this.presenter = presenter;
    }

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 162, 162);
		frame.getContentPane().add(scrollPane);
		
		list_1 = new JList();
		scrollPane.setViewportView(list_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(241, 33, 162, 162);
		frame.getContentPane().add(scrollPane_1);
		
		list_2 = new JList();
		scrollPane_1.setViewportView(list_2);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(e-> presenter.salir()
		);
		btnSalir.setBounds(107, 227, 89, 23);
		frame.getContentPane().add(btnSalir);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(229, 227, 89, 23);
		frame.getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(e -> presenter.agregarPulsado());
	}
	//getters
	public Empleado getEmpleadoSeleccionado1() { return list_1.getSelectedValue(); }
	public Empleado getEmpleadoSeleccionado2() { return list_2.getSelectedValue(); }
	//setters
	public void setModelEmpleados1(DefaultListModel<Empleado> model) { list_1.setModel(model); }
	public void setModelEmpleados2(DefaultListModel<Empleado> model) { list_2.setModel(model); }
	//metodos
	public void hacerVisible() { frame.setVisible(true); }
	public void cerrar() { frame.dispose(); }
	public void mostrarMensaje(String msj) { JOptionPane.showMessageDialog(frame, msj); }

}
