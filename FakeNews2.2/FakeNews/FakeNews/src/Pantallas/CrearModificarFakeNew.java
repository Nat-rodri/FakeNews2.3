package Pantallas;

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.time.LocalDate;

	import javax.swing.DefaultComboBoxModel;
	import javax.swing.JButton;

	import javax.swing.JFrame;
	import javax.swing.JComboBox;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;

	import DAO.CategoriaDAO;
	import DAO.FakeNewDAO;
	import DAO.MedioOrigenDAO;
import DAO.RefutacionDAO;
import DAO.RefutadorDAO;
	import Objetos.Categoria;
	import Objetos.FakeNew;
	import Objetos.MedioOrigen;
import Objetos.Refutacion;
import Objetos.Refutador;

public class CrearModificarFakeNew extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField titulo;
	private JTextField descripcion;
	private JTextField fechaAparicion;
	private JTextField creador;
	private JTextField fechaRefutada;
	private JTextField fuenteEvidencia;
	private JComboBox<String> categoria;
	private JComboBox<String> refutadorFNs;
	private JLabel lblTitulo;
	private JComboBox<String> MedioOrigenFNS;
	private JComboBox<String> orgOficial;
	
	private JButton crear = new JButton("CREAR");
	private JButton modificar = new JButton("MODIFICAR");
	private JButton crarRefu;

	public CrearModificarFakeNew(JFrame marco) {
	setBackground(new Color(0, 64, 0));
	setLayout(null);
	
	titulo = new JTextField();
	titulo.setBounds(406, 103, 178, 20);
	add(titulo);
	titulo.setColumns(10);
	
	descripcion = new JTextField();
	descripcion.setBounds(406, 134, 178, 20);
	add(descripcion);
	descripcion.setColumns(10);
	
	fuenteEvidencia = new JTextField();
	fuenteEvidencia.setColumns(10);
	fuenteEvidencia.setBounds(406, 356, 178, 20);
	add(fuenteEvidencia);
	
	JLabel lblFakeNews = new JLabel("Fake News");
	lblFakeNews.setHorizontalAlignment(SwingConstants.CENTER);
	lblFakeNews.setForeground(Color.WHITE);
	lblFakeNews.setFont(new Font("Georgia", Font.ITALIC, 29));
	lblFakeNews.setBackground(Color.WHITE);
	lblFakeNews.setBounds(242, 29, 259, 70);
	add(lblFakeNews);
	
	lblTitulo = new JLabel("Titulo:");
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitulo.setForeground(Color.WHITE);
	lblTitulo.setFont(new Font("Georgia", Font.ITALIC, 14));
	lblTitulo.setBounds(317, 95, 67, 35);
	add(lblTitulo);
	
	fechaAparicion = new JTextField();
	fechaAparicion.setColumns(10);
	fechaAparicion.setBounds(406, 192, 178, 20);
	add(fechaAparicion);
	
	creador = new JTextField();
	creador.setColumns(10);
	creador.setBounds(406, 164, 178, 20);
	add(creador);
	
	JLabel lblDescripcion_1 = new JLabel("Descripcion:");
	lblDescripcion_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblDescripcion_1.setForeground(Color.WHITE);
	lblDescripcion_1.setFont(new Font("Georgia", Font.ITALIC, 14));
	lblDescripcion_1.setBounds(307, 126, 98, 35);
	add(lblDescripcion_1);
	
	JLabel lblDescripcion_1_1 = new JLabel("Creador:");
	lblDescripcion_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblDescripcion_1_1.setForeground(Color.WHITE);
	lblDescripcion_1_1.setFont(new Font("Georgia", Font.ITALIC, 14));
	lblDescripcion_1_1.setBounds(327, 156, 78, 35);
	add(lblDescripcion_1_1);
	
	JLabel lblDescripcion_1_1_1 = new JLabel("Fecha de aparicion:");
	lblDescripcion_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblDescripcion_1_1_1.setForeground(Color.WHITE);
	lblDescripcion_1_1_1.setFont(new Font("Georgia", Font.ITALIC, 14));
	lblDescripcion_1_1_1.setBounds(262, 184, 143, 35);
	add(lblDescripcion_1_1_1);
	
	/*
	//CATEGORIA JCOMBOBOX
	*/
	DefaultComboBoxModel<String> categorias = new DefaultComboBoxModel<>();
	CategoriaDAO cDao = new CategoriaDAO();
	//ES COMO AGREGAR DATOS A LA TABLA :D
	for (Categoria cate  : cDao.TraerCategorias()) {
	// Agregar datos al JComboBox
	categorias.addElement(cate.getNombreCategoria());
	}
	//En los parametros se pone el nombre de la variable DefaultComboBoxModel para que aparezca en la pagina (o algo así)
	categoria = new JComboBox<String>(categorias);
	categoria.setBounds(406, 223, 178, 22);
	add(categoria);
	
	/*
	//REFUTADOR JCOMBOBOX
	*/
	DefaultComboBoxModel<String> refutadores = new DefaultComboBoxModel<>();
	RefutadorDAO refDao = new RefutadorDAO();
	//ES COMO AGREGAR DATOS A LA TABLA :D
	for (Refutador refu  : refDao.TraerRefutadores()) {
	// Agregar datos al JComboBox
	refutadores.addElement(refu.getNombre());
	}
	//En los parametros se pone el nombre de la variable DefaultComboBoxModel para que aparezca en la pagina (o algo así)
	refutadorFNs = new JComboBox<String>(refutadores);
	refutadorFNs.setBounds(406, 387, 178, 22);
	add(refutadorFNs);
	
	//ORGOFF JCOMBOBOX
	DefaultComboBoxModel<String> orgOf = new DefaultComboBoxModel<>();
	orgOf.addElement("true");
	orgOf.addElement("false");
	orgOficial = new JComboBox<String>(orgOf);
	orgOficial.setBounds(406, 287, 178, 22);
	add(orgOficial);
	
	/*
	//MEDIO ORIGEN JCOMBOBOX
	*/
	DefaultComboBoxModel<String> medioCmboxModel = new DefaultComboBoxModel<>();
	//ES COMO AGREGAR DATOS A LA TABLA :D
	MedioOrigenDAO medOrDao = new MedioOrigenDAO();
	for (MedioOrigen med  : medOrDao.TraerTiposMedios()) {
	// Agregar datos al JComboBox
	medioCmboxModel.addElement(med.getNombre());
	}
	MedioOrigenFNS = new JComboBox<String>(medioCmboxModel);
	MedioOrigenFNS.setBounds(406, 320, 178, 22);
	add(MedioOrigenFNS);
	
	JLabel lblDescripcion_1_2 = new JLabel("Categoria:");
	lblDescripcion_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblDescripcion_1_2.setForeground(Color.WHITE);
	lblDescripcion_1_2.setFont(new Font("Georgia", Font.ITALIC, 14));
	lblDescripcion_1_2.setBounds(317, 216, 87, 35);
	add(lblDescripcion_1_2);
	
	JButton volver = new JButton("Volver");
	volver.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	marco.setContentPane(new FakeNewConsulta(marco));
	marco.validate();
	}
	});
	volver.setBounds(29, 449, 89, 23);
	add(volver);
	
	crear.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	//Modificar las variables JTextField en texto (no en string pq nos da una cosa rara jejejd)
	//OBtien los datos q colocaste en el textfield CAMPOS  importante el getText()
	
	String tituloFNs = titulo.getText();
	String descripcionFNs = descripcion.getText();
	String creadorFNs = creador.getText();
	String fechaTexto = fechaAparicion.getText();
	String categoriaFNs = (String)categoria.getSelectedItem();
	String fechaTextoref = fechaRefutada.getText();
	String provOrgOf = (String)orgOficial.getSelectedItem();
	String medioOrigen= (String)MedioOrigenFNS.getSelectedItem();
	String refutadorFN = (String)refutadorFNs.getSelectedItem();
	String fuente = fuenteEvidencia.getText();
	
	if (!tituloFNs.isEmpty() && !descripcionFNs.isEmpty() && !creadorFNs.isEmpty() && !fechaTexto.isEmpty() && !categoriaFNs.isEmpty() && !fechaTextoref.isEmpty() && !provOrgOf.isEmpty() && !medioOrigen.isEmpty() && !refutadorFN.isEmpty() && !fuente.isEmpty()) {
	LocalDate fechaAparicionFNs = LocalDate.parse(fechaTexto);
	boolean provieneOrgOficial = Boolean.parseBoolean(provOrgOf);
	LocalDate fechaRefutada = LocalDate.parse(fechaTextoref);
	
	//Crear la FakeNew
	Categoria cate = new Categoria(categoriaFNs);
	MedioOrigen medOri = new MedioOrigen(medioOrigen);
	FakeNew FNs = new FakeNew(tituloFNs,descripcionFNs,creadorFNs,fechaAparicionFNs,cate,medOri);
	// Hacemos un objeto para llamar a un metodo de  la clase de DAO 
	// el metodo  Crear es del DAO 
	FakeNewDAO FNsDao = new FakeNewDAO();
	FNsDao.Crear(FNs);
	
	Refutador ref = new Refutador(refutadorFN);
	Refutacion refu = new Refutacion(fechaRefutada, fuente, provieneOrgOficial, ref, FNs);
	// Hacemos un objeto para llamar a un metodo de  la clase de DAO 
		// el metodo  Crear es del DAO 
	RefutacionDAO rDao = new RefutacionDAO();
	rDao.Crear(refu);
	
	marco.setContentPane(new FakeNewConsulta(marco));
	marco.validate();
	}
	}
	});
	crear.setBounds(307, 449, 89, 23);
	add(crear);
	
	modificar.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		String tituloF = titulo.getText();
		String descF = descripcion.getText();
		String creadorF = creador.getText();
		String fechaAparicionF = fechaAparicion.getText();
		LocalDate FecAp = LocalDate.parse(fechaAparicionF);
		String categoriaF = (String)categoria.getSelectedItem();
		String fechaRefutadaF = fechaRefutada.getText();
		LocalDate FecRef = LocalDate.parse(fechaRefutadaF);
		String orgOficialF = (String)orgOficial.getSelectedItem();
		boolean provieneOrgOficial = Boolean.parseBoolean(orgOficialF);
		String MedioOrigen = (String)MedioOrigenFNS.getSelectedItem();
		String fuenteEvidenciaF = fuenteEvidencia.getText();
		String refutador = (String)refutadorFNs.getSelectedItem();
		
		Categoria cate = new Categoria(categoriaF);
		MedioOrigen med = new MedioOrigen(MedioOrigen);
		FakeNew fake = new FakeNew(tituloF, descF, creadorF, FecAp, cate, med);
		
		FakeNewDAO fDao = new FakeNewDAO();
		fDao.Modificar(fake);
		
		Refutador ref = new Refutador(refutador);
		
		Refutacion refu = new Refutacion(FecRef, fuenteEvidenciaF, provieneOrgOficial, ref, fake);
		RefutacionDAO rDao = new RefutacionDAO();
		rDao.Modificar(refu);
		
		marco.setContentPane(new FakeNewConsulta(marco));
		marco.validate();
	}
	});
	modificar.setBounds(457, 449, 107, 23);
	add(modificar);
	
	JLabel medioOrigenLB = new JLabel("Fecha refutada:");
	medioOrigenLB.setHorizontalAlignment(SwingConstants.CENTER);
	medioOrigenLB.setForeground(Color.WHITE);
	medioOrigenLB.setFont(new Font("Georgia", Font.ITALIC, 14));
	medioOrigenLB.setBounds(262, 248, 143, 35);
	add(medioOrigenLB);
	
	fechaRefutada = new JTextField();
	fechaRefutada.setColumns(10);
	fechaRefutada.setBounds(406, 256, 178, 20);
	add(fechaRefutada);
	
	JLabel provOrgOf = new JLabel("¿Proviene de una organizacion oficial?:");
	provOrgOf.setHorizontalAlignment(SwingConstants.CENTER);
	provOrgOf.setForeground(Color.WHITE);
	provOrgOf.setFont(new Font("Georgia", Font.ITALIC, 14));
	provOrgOf.setBounds(136, 279, 269, 35);
	add(provOrgOf);
	
	JLabel lblMedioOrigen = new JLabel("Medio Origen:");
	lblMedioOrigen.setHorizontalAlignment(SwingConstants.CENTER);
	lblMedioOrigen.setForeground(Color.WHITE);
	lblMedioOrigen.setFont(new Font("Georgia", Font.ITALIC, 14));
	lblMedioOrigen.setBounds(278, 312, 143, 35);
	add(lblMedioOrigen);
	
	JLabel lblRefutador = new JLabel("Refutador:");
	lblRefutador.setHorizontalAlignment(SwingConstants.CENTER);
	lblRefutador.setForeground(Color.WHITE);
	lblRefutador.setFont(new Font("Georgia", Font.ITALIC, 14));
	lblRefutador.setBounds(278, 380, 143, 35);
	add(lblRefutador);
	
	JLabel lblFuenteDeEvidencia = new JLabel("Fuente de evidencia:");
	lblFuenteDeEvidencia.setHorizontalAlignment(SwingConstants.CENTER);
	lblFuenteDeEvidencia.setForeground(Color.WHITE);
	lblFuenteDeEvidencia.setFont(new Font("Georgia", Font.ITALIC, 14));
	lblFuenteDeEvidencia.setBounds(262, 352, 143, 35);
	add(lblFuenteDeEvidencia);
	
	crarRefu = new JButton("Crear Refutador");
	crarRefu.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CrearModificar crearModif = new CrearModificar(marco);
			crearModif.Crear();
			marco.setContentPane(crearModif);
			marco.validate();
		}
	});
	crarRefu.setBounds(595, 387, 150, 23);
	add(crarRefu);
	
	

	}
	
	public void Modificar(String titulo, String descripcion, String fechaAparicion, String creador, String categoria, String MedioOrigenFNS, String refutadorFNs, String fechaRefutada, boolean orgOficial, String fuenteEvidencia) {
		this.titulo.setText(titulo);
	    this.descripcion.setText(descripcion);
	    this.fechaAparicion.setText(fechaAparicion);
	    this.creador.setText(creador);
	    this.categoria.setSelectedItem(categoria);
	    this.refutadorFNs.setSelectedItem(refutadorFNs);
	    this.MedioOrigenFNS.setSelectedItem(MedioOrigenFNS);
	    this.fechaRefutada.setText(fechaRefutada);
	    this.orgOficial.setSelectedItem(orgOficial);
	    this.fuenteEvidencia.setText(fuenteEvidencia);
	    
	    lblTitulo.setVisible(false);
	    this.titulo.setVisible(false);
	    crear.setVisible(false); //Ocultar botón "Crear"
	    modificar.setVisible(true); //Mostrar botón "Modificar"
	    

	}
	public void Crear() {
		
	    crear.setVisible(true); //Ocultar botón "Crear"
	    modificar.setVisible(false); //Mostrar botón "Modif "guardar""

	}


}