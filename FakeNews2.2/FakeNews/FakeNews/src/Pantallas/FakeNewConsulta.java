package Pantallas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DAO.FakeNewDAO;
import DAO.RefutacionDAO;
import Objetos.FakeNew;
import Objetos.Refutacion;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FakeNewConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private JTextField buscarTitulo;
	private DefaultTableModel tablaFakeNew;

	/**
	 * Create the panel.
	 */
	public FakeNewConsulta(JFrame marco) {
		setBackground(new Color(254, 252, 237));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 128, 769, 258);
		add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		tablaFakeNew = new DefaultTableModel(null, new String[] {"Titulo", "Descripcion", "Creador", "Fecha de aparicion", "Categoria", "Medio de Origen", "Refutador"});
		tabla.setModel(tablaFakeNew);
		scrollPane.setViewportView(tabla);
		
		tablaFakeNew.setRowCount(0);
		// Traer datos de las fakenews para mostrar en la tabla.
		FakeNewDAO fDao = new FakeNewDAO();
		for (FakeNew fake  : fDao.Consultas()) {
			RefutacionDAO ref = new RefutacionDAO();
			// Agregar una fila a la tabla por cada fakeNew.
			tablaFakeNew.addRow(new Object[] {fake.getTitulo(), fake.getDescripcion(), fake.getCreador(), fake.getFechaAparicion(), fake.getCategoria().getNombreCategoria(), fake.getMedioOrigen().getNombre(), ref.TraerRefutacionPorFN(fake).getRefutador().getNombre()});
		}
		
		
		JButton modif = new JButton("Modificar");
		modif.setFont(new Font("Georgia", Font.ITALIC, 14));
		modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaElegida = tabla.getSelectedRow();
				if (filaElegida != -1) {
					//La columna q queremos mostrar (por ahora es prueba)
					int columnaTitulo = 0;
					int columnaDesc = 1;
					int columnaCreador = 2;
					int columnaFecAparicion = 3;
					int columnaCate = 4;
					int columnaMedio = 5;
					int columnaRefutador = 6;
					//Obtiene elv alor de cada campo dependiendo de su  num de fila y num de columnaaa 
					String titulo = tabla.getValueAt(filaElegida, columnaTitulo).toString();
					String descripcion = tabla.getValueAt(filaElegida, columnaDesc).toString();
					String creador = tabla.getValueAt(filaElegida, columnaCreador).toString();
					String fecAparicion = tabla.getValueAt(filaElegida, columnaFecAparicion).toString();
					String categoria = tabla.getValueAt(filaElegida, columnaCate).toString();
					String medioOrigen = tabla.getValueAt(filaElegida, columnaMedio).toString();
					String refutador = tabla.getValueAt(filaElegida, columnaRefutador).toString();
					
					FakeNew fN = new FakeNew(titulo);
					
					RefutacionDAO rDao = new RefutacionDAO();
					Refutacion ref = rDao.TraerRefutacionPorFN(fN);
					// Mostrar la pantalla de modificación con los datos
		            CrearModificarFakeNew crearModif = new CrearModificarFakeNew(marco);
					crearModif.Modificar(titulo, descripcion, fecAparicion, creador , categoria, medioOrigen, refutador, ref.getFechaRefutada().toString(), ref.isProvOrgOficial(), ref.getFuenteEvidencia());

					marco.setContentPane(crearModif);
					marco.validate();
			}
			}
		});
		modif.setBounds(344, 457, 121, 34);
		add(modif);
		
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Si hay una fila seleccionada.
				// es el valor de la fila que  fue seleccionado 
				int filaElegida = tabla.getSelectedRow();
				if (filaElegida != -1) {
					//La columna q queremos
					int columnaTitulo = 0;
					//Obtiene el valor de cada campo dependiendo de su  num de fila y num de columnaaa 
					String titulo = tabla.getValueAt(filaElegida, columnaTitulo).toString();
					// Mostrar la pantalla de modificación con los datos
					FakeNew fake = new FakeNew(titulo);
					FakeNewDAO fDao = new FakeNewDAO();
					Refutacion refu = new Refutacion(fake);
					RefutacionDAO rDao = new RefutacionDAO();
					rDao.Eliminar(refu);
					fDao.Eliminar(fake);
					tablaFakeNew.setRowCount(0);
					// Traer datos para mostrar en tabla.
					for (FakeNew f  : fDao.Consultas()) {
						RefutacionDAO ref = new RefutacionDAO();
						// Agregar una fila a la tabla por cada fakeNew.
						tablaFakeNew.addRow(new Object[] {f.getTitulo(), f.getDescripcion(), f.getCreador(), f.getFechaAparicion(), f.getCategoria().getNombreCategoria(), f.getMedioOrigen().getNombre(), ref.TraerRefutacionPorFN(f).getRefutador().getNombre()});
					}
				}     
			}
		});
		eliminar.setFont(new Font("Georgia", Font.ITALIC, 14));
		eliminar.setBounds(594, 457, 121, 34);
		add(eliminar);
		
		JButton crear = new JButton("Crear");
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearModificarFakeNew crearModif = new CrearModificarFakeNew(marco);
				crearModif.Crear();

				marco.setContentPane(crearModif);
				marco.validate();
			}
		});
		crear.setFont(new Font("Georgia", Font.ITALIC, 12));
		crear.setBounds(100, 457, 121, 34);
		add(crear);
		
		JLabel lblBuscar = new JLabel("Buscar por titulo:");
		lblBuscar.setForeground(new Color(255, 255, 255));
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setFont(new Font("Georgia", Font.ITALIC, 14));
		lblBuscar.setBounds(39, 64, 259, 70);
		add(lblBuscar);
		
		buscarTitulo = new JTextField();
		buscarTitulo.setColumns(10);
		buscarTitulo.setBounds(243, 90, 207, 20);
		add(buscarTitulo);
		
		JButton btnBuscar = new JButton("Buscar\r\n");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloFNs = buscarTitulo.getText();
				
				if (!tituloFNs.isEmpty()) {
				//Crear la FakeNew
				// Hacemos un objeto para llamar a un metodo de  la clase de DAO 
				// el metodo  Crear es del DAO 
				FakeNewDAO FNsDao = new FakeNewDAO();
				FakeNew f = FNsDao.ConsultaTitulo(tituloFNs);
				
				tablaFakeNew.setRowCount(0);
				RefutacionDAO ref = new RefutacionDAO();
				if(f != null) {
					// Agregar una fila a la tabla por cada fkNew.
					tablaFakeNew.addRow(new Object[] {f.getTitulo(), f.getDescripcion(), f.getCreador(), f.getFechaAparicion(), f.getCategoria().getNombreCategoria(), f.getMedioOrigen().getNombre(), ref.TraerRefutacionPorFN(f).getRefutador().getNombre()});
				
				}
				}
			}
		});
		btnBuscar.setFont(new Font("Georgia", Font.ITALIC, 14));
		btnBuscar.setBounds(506, 78, 108, 32);
		add(btnBuscar);
		
		JButton btnAsociar = new JButton("Asociar");
		btnAsociar.setFont(new Font("Georgia", Font.ITALIC, 14));
		btnAsociar.setBounds(344, 412, 121, 34);
		add(btnAsociar);
		
		JButton btnVerDivulgaciones = new JButton("Ver divulgaciones");
		btnVerDivulgaciones.setFont(new Font("Georgia", Font.ITALIC, 14));
		btnVerDivulgaciones.setBounds(88, 412, 147, 34);
		add(btnVerDivulgaciones);
		
		JButton btnVerRefutacion = new JButton("Ver refutacion");
		btnVerRefutacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaElegida = tabla.getSelectedRow();
				if (filaElegida != -1) {
					//La columna q queremos mostrar (por ahora es prueba)
					int columnaTitulo = 0;
					//Obtiene elv alor de cada campo dependiendo de su  num de fila y num de columnaaa 
					String titulo = tabla.getValueAt(filaElegida, columnaTitulo).toString();
					
					FakeNew fN = new FakeNew(titulo);
					
					RefutacionDAO rDao = new RefutacionDAO();
					Refutacion ref = rDao.TraerRefutacionPorFN(fN);
					// Mostrar la pantalla de modificación con los datos
		            ModificarRefutacion crearModif = new ModificarRefutacion(marco);
					crearModif.Modificar(titulo,ref.getFechaRefutada().toString(), ref.getFuenteEvidencia(), ref.isProvOrgOficial(), ref.getRefutador().getNombre());

					marco.setContentPane(crearModif);
					marco.validate();
			}
			}
		});
		btnVerRefutacion.setFont(new Font("Georgia", Font.ITALIC, 14));
		btnVerRefutacion.setBounds(581, 412, 147, 34);
		add(btnVerRefutacion);
		
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
		
		JLabel lblNewLabel = new JLabel("FAKE NEW");
		lblNewLabel.setBounds(323, 53, 186, 14);
		add(lblNewLabel);
		
		

		
		}
	}