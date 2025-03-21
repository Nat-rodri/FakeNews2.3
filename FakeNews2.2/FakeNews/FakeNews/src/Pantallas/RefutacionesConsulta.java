package Pantallas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.RefutacionDAO;
import Objetos.FakeNew;
import Objetos.Refutacion;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RefutacionesConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField buscarTitulo;
	private JTable tabla;
	
	private DefaultTableModel tablaRefutacion;

	/**
	 * Create the panel.
	 */
	public RefutacionesConsulta(JFrame marco) {
		setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Refutaciones");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 25));
		lblNewLabel.setBounds(320, 51, 153, 34);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo de Fake new:");
		lblNewLabel_1.setBounds(149, 109, 133, 14);
		add(lblNewLabel_1);
		
		buscarTitulo = new JTextField();
		buscarTitulo.setBounds(295, 106, 208, 20);
		add(buscarTitulo);
		buscarTitulo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 134, 621, 287);
		add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		tablaRefutacion = new DefaultTableModel(null, new String[] {"Titulo de Fake new", "fecha refutada", "fuente de evidencia", "Es de una Org oficial", "Refutador"});
		tabla.setModel(tablaRefutacion);
		scrollPane.setViewportView(tabla);
		
		tablaRefutacion.setRowCount(0);
		// Traer datos de las fakenews para mostrar en la tabla.
		RefutacionDAO rDao = new RefutacionDAO();
		for (Refutacion ref  : rDao.Consultas()) {
			// Agregar una fila a la tabla por cada fakeNew.
			tablaRefutacion.addRow(new Object[] {ref.getFakenew().getTitulo(), ref.getFechaRefutada(), ref.getFuenteEvidencia(), ref.isProvOrgOficial(), ref.getRefutador().getNombre()});
		}
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloFNs = buscarTitulo.getText();
				
				if (!tituloFNs.isEmpty()) {
				// Hacemos un objeto para llamar a un metodo de  la clase de DAO 
				// el metodo  Crear es del DAO 
				FakeNew f = new FakeNew(tituloFNs);
				RefutacionDAO rDao = new RefutacionDAO();
				Refutacion ref = rDao.TraerRefutacionPorFN(f);
				
				tablaRefutacion.setRowCount(0);
				if(ref != null) {
					// Agregar una fila a la tabla por cada fkNew.
					tablaRefutacion.addRow(new Object[] {ref.getFakenew().getTitulo(), ref.getFechaRefutada(), ref.getFuenteEvidencia(), ref.isProvOrgOficial(), ref.getRefutador().getNombre()});
				
				}
				} else {
					tablaRefutacion.setRowCount(0);
					// Traer datos de las fakenews para mostrar en la tabla.
					RefutacionDAO rDao = new RefutacionDAO();
					for (Refutacion ref  : rDao.Consultas()) {
						// Agregar una fila a la tabla por cada fakeNew.
						tablaRefutacion.addRow(new Object[] {ref.getFakenew().getTitulo(), ref.getFechaRefutada(), ref.getFuenteEvidencia(), ref.isProvOrgOficial(), ref.getRefutador().getNombre()});
					}
				}
			}
		});
		btnNewButton.setBounds(526, 105, 89, 23);
		add(btnNewButton);
		
		
		/*
		JButton B_Modif = new JButton("Modificar");
		B_Modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Si hay una fila seleccionada.
				// es el vlaor de la fila que  fue seleccionado 
				int filaElegida = tabla.getSelectedRow();
				if (filaElegida != -1) {
					//La columna q queremos mostrar (por ahora es prueba)
					int columnaTituloFNs= 0;
					int columnaFechaRefutada = 1;
					int columnaFuenteDeEvidencia = 2;
					int columnaOrgOff =3 ;
					int columnaRefutador = 4;

					
					//Obtiene elv alor de cada campo dependiendo de su  num de fila y num de columnaaa 
					String tituloFNs = tabla.getValueAt(filaElegida, columnaTituloFNs).toString();
					String fechaRefutada = tabla.getValueAt(filaElegida, columnaFechaRefutada).toString();
					String fuenteEvidencia = tabla.getValueAt(filaElegida, columnaFuenteDeEvidencia).toString();
					String orgOficial = tabla.getValueAt(filaElegida, columnaOrgOff).toString();
					String refutadorFNs =  tabla.getValueAt(filaElegida, columnaRefutador).toString();
					
					
					// Mostrar la pantalla de modificación con los datos
		            CrearModificarFakeNew crearModifFNs = new CrearModificarFakeNew(marco);
					crearModifFNs.Mofificar(fechaRefutada, fuenteEvidencia,orgOficial, refutadorFNs);

					marco.setContentPane(crearModifFNs);
					marco.validate();
					
					
					

					
				}  
			}
		});
		*/
		
		JButton B_Modif = new JButton("Modificar");
		B_Modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Si hay una fila seleccionada.
				// es el vlaor de la fila que  fue seleccionado 
				int filaElegida = tabla.getSelectedRow();
				if (filaElegida != -1) {
					//La columna q queremos mostrar (por ahora es prueba)
					int columnaTituloFNs= 0;
					int columnaFechaRefutada = 1;
					int columnaFuenteDeEvidencia = 2;
					int columnaOrgOff =3 ;
					int columnaRefutador = 4;

					
					//Obtiene elv alor de cada campo dependiendo de su  num de fila y num de columnaaa 
					String tituloFNs = tabla.getValueAt(filaElegida, columnaTituloFNs).toString();
					String fechaRefutada = tabla.getValueAt(filaElegida, columnaFechaRefutada).toString();
					String fuenteEvidencia = tabla.getValueAt(filaElegida, columnaFuenteDeEvidencia).toString();
					String orgOficial = tabla.getValueAt(filaElegida, columnaOrgOff).toString();
					boolean provieneOrgOficial = Boolean.parseBoolean(orgOficial);
					String refutadorFNs =  tabla.getValueAt(filaElegida, columnaRefutador).toString();
					
				ModificarRefutacion modifRefutaciones = new ModificarRefutacion(marco);
				modifRefutaciones.Modificar(tituloFNs, fechaRefutada, fuenteEvidencia, provieneOrgOficial, refutadorFNs);
				marco.setContentPane(modifRefutaciones);
				marco.validate();
				}
			}
		});
		B_Modif.setBounds(563, 442, 169, 34);
		add(B_Modif);
		JButton B_volver = new JButton("VOLVER");
		B_volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menup = new Menu(marco);
				marco.setContentPane(menup);
				marco.validate();
			}
		});
		B_volver.setFont(new Font("Georgia", Font.ITALIC, 14));
		B_volver.setBounds(27, 35, 108, 32);
		add(B_volver);
		

	}
}
