package Pantallas;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;


	public Menu(JFrame marco) {
		setBackground(new Color(253, 254, 222));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trazabilidad Noticias falsas");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 37));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setBounds(176, 33, 450, 75);
		add(lblNewLabel);
		
		JButton FakeNew = new JButton("Fake News");
		FakeNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FakeNewConsulta FNConsulta = new FakeNewConsulta(marco);
				marco.setContentPane(FNConsulta);
				marco.validate();
			}
		});
		FakeNew.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		FakeNew.setBounds(294, 113, 141, 29);
		add(FakeNew);
		
		JButton refutadores = new JButton("Refutadores");
		refutadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefutadorConsulta Rconsulta = new RefutadorConsulta(marco);
				marco.setContentPane(Rconsulta);
				marco.validate();
			}
		});
		
		refutadores.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		refutadores.setBounds(294, 174, 141, 29);
		add(refutadores);
		
		JButton refutaciones = new JButton("Refutaciones");
		refutaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// modificar luego 
				RefutacionesConsulta Rconsulta = new RefutacionesConsulta(marco);
				marco.setContentPane(Rconsulta);
				marco.validate();
			}
		});
		refutaciones.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		refutaciones.setBounds(294, 236, 141, 29);
		add(refutaciones);
		/*
		JButton divulgacion = new JButton("Divulgaciones");
		divulgacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefutacionesConsulta Rconsulta = new RefutacionesConsulta();
				marco.setContentPane(Rconsulta);
				marco.validate();
			}
		});
		divulgacion.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		divulgacion.setBounds(284, 299, 170, 29);
		add(divulgacion);
		*/
		JButton reportes = new JButton("Reportes");
		reportes.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		reportes.setBounds(294, 369, 141, 29);
		add(reportes);

	}
}

