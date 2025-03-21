package Pantallas;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import DAO.RefutadorDAO;
import Objetos.Refutador;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearModificar extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField nombre;
	private JTextField apellido;
	private JTextField apodo;
	

	private JButton crear;
	private JButton modif;
	JLabel nombreR = new JLabel("Nombre");
	JLabel apellidoR = new JLabel("Apellido");
	JLabel apodoR = new JLabel("Apodo");
	private JButton crearFN;
	private JLabel medioR;
	private JTextField medio;

	public CrearModificar(JFrame marco) {
		setLayout(null);

		
		nombreR.setHorizontalAlignment(SwingConstants.CENTER);
		nombreR.setBounds(262, 144, 84, 38);
		add(nombreR);

		nombre = new JTextField();
		nombre.setBounds(362, 153, 207, 20);
		add(nombre);
		nombre.setColumns(10);

		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(362, 202, 207, 20);
		add(apellido);

		
		apellidoR.setHorizontalAlignment(SwingConstants.CENTER);
		apellidoR.setBounds(262, 193, 84, 38);
		add(apellidoR);
//craemos el boton para modificar 
		modif = new JButton("Guardar");
		modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreR = nombre.getText();
				String apellidoR = apellido.getText();
				String medioR = medio.getText();
				String apodoR = apodo.getText();
				
				Refutador ref = new Refutador(nombreR, apellidoR, medioR, apodoR);
				RefutadorDAO rDao = new RefutadorDAO();
				rDao.Modificar(ref);
				marco.setContentPane(new RefutadorConsulta(marco));
				marco.validate();
			}
			
		});

		modif.setBounds(482, 386, 108, 32);
		add(modif);

		crear = new JButton("Crear");
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Modificar las variables JTextField en texto (no en string pq nos da una cosa rara jejejd)
				//OBtien los datos q colocaste en el textfile CAMPOS  importante el getText()
				
				String nombreR = nombre.getText();
				String apellidoR = apellido.getText();
				String medioR = medio.getText();
				String apodoR = apodo.getText();
				if (!nombreR.isEmpty() && !apellidoR.isEmpty() && !medioR.isEmpty()) {
					//Crear al Refutador
					Refutador ref = new Refutador(nombreR, apellidoR, medioR, apodoR);
					//Ingresarlo a la tabla por medio de RefutadorDAO-Crear, y así aparezca cuando se la consulte
					//RefutadorDAO rDao = new RefutadorDAO();
					// asemos un objeto para llamar a un metodo de  la clase de DAO 
					// el metodo  Crear es del DAo 
					RefutadorDAO rDao = new RefutadorDAO();
					rDao.Crear(ref);
					marco.setContentPane(new RefutadorConsulta(marco));
					marco.validate();
				}
			}
		});
		crear.setBounds(305, 387, 108, 32);
		add(crear);

		JButton volv = new JButton("Volver");
		volv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new RefutadorConsulta(marco));
				marco.validate();
			}
		});
		volv.setBounds(55, 415, 81, 27);
		add(volv);
		
		
		apodoR.setHorizontalAlignment(SwingConstants.CENTER);
		apodoR.setBounds(262, 242, 84, 38);
		add(apodoR);
		
		apodo = new JTextField();
		apodo.setColumns(10);
		apodo.setBounds(362, 251, 207, 20);
		add(apodo);
		
		crearFN = new JButton("Crear Fake New");
		crearFN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearModificarFakeNew crearModif = new CrearModificarFakeNew(marco);
				crearModif.Crear();
				marco.setContentPane(crearModif);
				marco.validate();
			}
		});
		crearFN.setBounds(586, 152, 157, 27);
		add(crearFN);
		
		medioR = new JLabel("Medio");
		medioR.setHorizontalAlignment(SwingConstants.CENTER);
		medioR.setBounds(262, 290, 84, 38);
		add(medioR);
		
		medio = new JTextField();
		medio.setColumns(10);
		medio.setBounds(362, 299, 207, 20);
		add(medio);
	}
	
	//Al apretar el boton modificar en la clase "RefutadorConsulta"
	public void Modificar(String nombre, String apellido, String medio, String apodo) {
		this.nombre.setText(nombre);
	    this.apellido.setText(apellido);
	    this.medio.setText(medio);
	    this.apodo.setText(apodo);

	    nombreR.setVisible(false);
	    this.nombre.setVisible(false);
	    crear.setVisible(false); //Ocultar botón "Crear"
	    modif.setVisible(true); //Mostrar botón "Modificar"
	    

	}
	public void Crear() {
		
	    crear.setVisible(true); //Ocultar botón "Crear"
	    modif.setVisible(false); //Mostrar botón "Modif "guardar""

	}
}

