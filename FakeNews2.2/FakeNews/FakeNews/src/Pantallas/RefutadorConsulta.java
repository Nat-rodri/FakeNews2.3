package Pantallas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.RefutacionDAO;
import DAO.RefutadorDAO;
import DAO.RefutadorOcupadoExption;
import Objetos.Refutacion;
import Objetos.Refutador;


import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RefutadorConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	//Se crea la variable para crear la tabla
	private DefaultTableModel tablaRefutador;

	//Se crea el marco JFrame para movernos de una pantalla a otra
	
	private JTable tabla;

	public RefutadorConsulta(JFrame marco) {
		setLayout(null);
		JLabel TituloR = new JLabel("Refutadores");
		TituloR.setFont(new Font("Georgia", Font.ITALIC, 27));
		TituloR.setHorizontalAlignment(SwingConstants.CENTER);
		TituloR.setBounds(263, 11, 259, 70);
		add(TituloR);

	//Es como un contenedor q nos permite desplazarnos en la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 62, 481, 258);
		add(scrollPane);

		tabla = new JTable();

		tablaRefutador = new DefaultTableModel(null, new String[] {"Nombre", "Apellido", "Apodo", "Medio Presente"});
		tabla.setModel(tablaRefutador);
		scrollPane.setViewportView(tabla);
		

		tablaRefutador.setRowCount(0);
		// Traer datos del refutador para mostrar en la tabla.
		RefutadorDAO eDao = new RefutadorDAO();
		for (Refutador ref  : eDao.Consultas()) {
			// Agregar una fila a la tabla por cada refutador.
			tablaRefutador.addRow(new Object[] {ref.getNombre(), ref.getApellido(), ref.getApodo(),ref.getMedio()});
		}
		
		JButton modif = new JButton("Modificar");
		modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Si hay una fila seleccionada.
				// es el vlaor de la fila que  fue seleccionado 
				int filaElegida = tabla.getSelectedRow();
				if (filaElegida != -1) {
					//La columna q queremos mostrar (por ahora es prueba)
					int columnaNombre = 0;
					int columnaApellido = 1;
					int columnaApodo = 2;
					int columnaMedio = 3;
					//Obtiene elv alor de cada campo dependiendo de su  num de fila y num de columnaaa 
					String nombre = tabla.getValueAt(filaElegida, columnaNombre).toString();
					String apellido = tabla.getValueAt(filaElegida, columnaApellido).toString();
					String apodo = "";
					if(tabla.getValueAt(filaElegida, columnaApodo) != null) {
						apodo = tabla.getValueAt(filaElegida, columnaApodo).toString();
					}
					String medio = tabla.getValueAt(filaElegida, columnaMedio).toString();
					// Mostrar la pantalla de modificación con los datos
		            CrearModificar crearModif = new CrearModificar(marco);
					crearModif.Modificar(nombre, apellido, medio, apodo);

					marco.setContentPane(crearModif);
					marco.validate();
					
					
				}  
			}
		});
		modif.setBounds(354, 353, 121, 34);
		add(modif);
		
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Si hay una fila seleccionada.
				// es el vlaor de la fila que  fue seleccionado 
				int filaElegida = tabla.getSelectedRow();
				if (filaElegida != -1) {
					//La columna q queremos mostrar (por ahora es prueba)
					int columnaNombre = 0;
					int columnaApellido = 1;
					int columnaApodo = 2;
					int columnaMedio = 3;
					//Obtiene elv alor de cada campo dependiendo de su  num de fila y num de columnaaa 
					String nombre = tabla.getValueAt(filaElegida, columnaNombre).toString();
					String apellido = tabla.getValueAt(filaElegida, columnaApellido).toString();
					String apodo = "";
					if(tabla.getValueAt(filaElegida, columnaApodo) != null) {
						apodo = tabla.getValueAt(filaElegida, columnaApodo).toString();
					}

					String medio = tabla.getValueAt(filaElegida, columnaMedio).toString();
					// Mostrar la pantalla de modificación con los datos
					Refutador ref = new Refutador(nombre,apellido,medio, apodo);
					
					try {
						eDao.Eliminar(ref);
						tablaRefutador.setRowCount(0);
						// Traer datos para mostrar en tabla.
						for (Refutador r : eDao.Consultas()) {
							tablaRefutador.addRow(new Object[] {r.getNombre(),r.getApellido(), r.getApodo(),r.getMedio()});
						}

					} catch (RefutadorOcupadoExption e2) {
						System.out.println(e2.getMessage());
					}
				}     
			}
		});
		eliminar.setBounds(578, 353, 121, 34);
		add(eliminar);
		
		JButton crear = new JButton("Crear");
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Para cancelar el boton guardar 
				CrearModificar crearModif = new CrearModificar(marco);
				crearModif.Crear();
				
				//Esta es una forma de cambiar de pantalla
				//marco.setContentPane(new CrearModificar(marco));es igual al siguiente solo q  el parametro es remplazado con el objeto creando en la linea antwrior ya q cumple  misma funcion 
				marco.setContentPane(crearModif);
				//Para que se cambie se ejecute acciin (? q
				marco.validate();
				
			}
		});
		crear.setBounds(114, 353, 121, 34);
		add(crear);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new Menu(marco));
				marco.validate();
			}
		});
		volver.setBounds(35, 30, 89, 23);
		add(volver);

		
	}
}

