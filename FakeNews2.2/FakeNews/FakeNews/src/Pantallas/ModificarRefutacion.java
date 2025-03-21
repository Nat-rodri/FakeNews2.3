package Pantallas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import DAO.RefutacionDAO;
import DAO.RefutadorDAO;
import Objetos.FakeNew;
import Objetos.Refutacion;
import Objetos.Refutador;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ModificarRefutacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField fechaRefutada;
	private JTextField fuenteEvidencia;
	private JLabel tituloFake;
	private JComboBox<String> orgOficial;
	private JComboBox<String> refutador;

	/**
	 * Create the panel.
	 */
	public ModificarRefutacion(JFrame marco) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Refutacion");
		lblNewLabel.setFont(new Font("Serif", Font.ITALIC, 22));
		lblNewLabel.setBounds(368, 41, 101, 68);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo Fake New:");
		lblNewLabel_1.setBounds(182, 134, 103, 23);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha refutada:");
		lblNewLabel_1_1.setBounds(182, 168, 118, 14);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fuente de evidencia:");
		lblNewLabel_1_1_1.setBounds(165, 205, 135, 14);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("¿Proviene de una organizacion oficial?:");
		lblNewLabel_1_1_1_1.setBounds(54, 243, 256, 14);
		add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Refutador: ");
		lblNewLabel_1_1_1_1_1.setBounds(215, 275, 85, 14);
		add(lblNewLabel_1_1_1_1_1);
		
		tituloFake = new JLabel("");
		tituloFake.setBounds(313, 138, 140, 14);
		add(tituloFake);
		
		fechaRefutada = new JTextField();
		fechaRefutada.setBounds(310, 165, 143, 20);
		add(fechaRefutada);
		fechaRefutada.setColumns(10);
		
		fuenteEvidencia = new JTextField();
		fuenteEvidencia.setColumns(10);
		fuenteEvidencia.setBounds(310, 202, 143, 20);
		add(fuenteEvidencia);
		
		DefaultComboBoxModel<String> orgOf = new DefaultComboBoxModel<>();
		orgOf.addElement("true");
		orgOf.addElement("false");
		orgOficial = new JComboBox<String>(orgOf);
		orgOficial.setBounds(307, 239, 146, 22);
		add(orgOficial);
		
		
		DefaultComboBoxModel<String> refutadores = new DefaultComboBoxModel<>();
		RefutadorDAO refDao = new RefutadorDAO();
		//ES COMO AGREGAR DATOS A LA TABLA :D
		for (Refutador refu  : refDao.TraerRefutadores()) {
		// Agregar datos al JComboBox
		refutadores.addElement(refu.getNombre());
		}
		refutador = new JComboBox<String>(refutadores);
		refutador.setBounds(307, 271, 146, 22);
		add(refutador);
		
		JButton modificar = new JButton("Modificar");
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloF = tituloFake.getText();
				String fechaRefutadaR = fechaRefutada.getText();
				LocalDate FecRef = LocalDate.parse(fechaRefutadaR);
				String fuenteEvidenciaR = fuenteEvidencia.getText();
				String orgOficialR = (String)orgOficial.getSelectedItem();
				boolean provieneOrgOficial = Boolean.parseBoolean(orgOficialR);
				String refutadorR = (String)refutador.getSelectedItem();
				
				Refutador ref = new Refutador(refutadorR);
				FakeNew fake = new FakeNew(tituloF);
				
				Refutacion refu = new Refutacion(FecRef, fuenteEvidenciaR, provieneOrgOficial, ref, fake);
				RefutacionDAO rDao = new RefutacionDAO();
				rDao.Modificar(refu);
				
				marco.setContentPane(new RefutacionesConsulta(marco));
				marco.validate();
			}
		});
		modificar.setBounds(445, 379, 116, 32);
		add(modificar);
		
		JButton btnRefutaciones = new JButton("Refutaciones");
		btnRefutaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new RefutacionesConsulta(marco));
				marco.validate();
			}
		});
		btnRefutaciones.setBounds(182, 379, 116, 32);
		add(btnRefutaciones);

	}
	
	public void Modificar(String tituloFake, String fechaRefutada, String fuenteEvidencia, boolean orgOficial, String refutador) {
		this.tituloFake.setText(tituloFake);
	    this.fechaRefutada.setText(fechaRefutada);
	    this.orgOficial.setSelectedItem(orgOficial);
	    this.fuenteEvidencia.setText(fuenteEvidencia);
	    this.refutador.setSelectedItem(refutador);
	}
	
	
}
