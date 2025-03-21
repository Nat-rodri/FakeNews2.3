package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Objetos.Categoria;
import Objetos.FakeNew;
import Objetos.MedioOrigen;

public class FakeNewDAO {
	public boolean  Crear(FakeNew fakeNew )

	{
		String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
		String password ="admin";
		String ussers= "root";

		Connection c = null;

		int filasAfectadas= 0;
		try {
			//1.Establecer conexion con la base de datos

			c = DriverManager.getConnection(url,ussers,password);

			//2.Paso ejecutar instruccion 
			/*
			 Utilicé subconsultas para poder guardar el id de la categoria y el medio mediante sus nombres :)
			 */
			String sql = "INSERT INTO fakenews (`titulo`, `descripcion`, `creador`, `fechaAparicion`, `idCategoria`, `idMedioOrigen`) VALUES (?, ?, ?, ?, ?, ?);";
						
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1,fakeNew.getTitulo());
			stmt.setString(2,fakeNew.getDescripcion());
			stmt.setString(3,fakeNew.getCreador());
			stmt.setDate(4,java.sql.Date.valueOf(fakeNew.getFechaAparicion()));

			Categoria cat = new Categoria(fakeNew.getCategoria().getNombreCategoria());
			CategoriaDAO cDao = new CategoriaDAO();
			stmt.setInt(5,cDao.traerIdCategoria(cat));
			
			MedioOrigen med = new MedioOrigen(fakeNew.getMedioOrigen().getNombre());
			MedioOrigenDAO mDao = new MedioOrigenDAO();
			stmt.setInt(6,mDao.traerIdMedioOrigen(med));

			filasAfectadas = stmt.executeUpdate();

			//3.Evaluar Resultado
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally 
		{
			//4.Cerrar sesion
			if (c != null)
			{
				try{
					c.close();
					}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return filasAfectadas == 1;
	}

public boolean  Modificar(FakeNew fakeNew)
{
	//Ubicacion del motor de la base de datos 

	String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
	String password ="admin";
	String ussers= "root";

	Connection c = null;

	int filasAfectadas= 0;
	try {
		//1.Establecer conexion con la base de datos

		c = DriverManager.getConnection(url,ussers,password);
		
		System.out.println("conecto");

		//2.Paso ejecutar instruccion 
		String sql = "UPDATE fakenews SET `descripcion` = ? , `creador` = ? , `fechaAparicion` = ?, `idCategoria` = ?, `idMedioOrigen` = ? WHERE `titulo` = ?;";			

		PreparedStatement stmt = c.prepareStatement(sql);

		stmt.setString(1,fakeNew.getDescripcion());
		stmt.setString(2,fakeNew.getCreador());
		stmt.setDate(3, java.sql.Date.valueOf(fakeNew.getFechaAparicion()));
		//Traer el ID de categoría
		Categoria cat = new Categoria(fakeNew.getCategoria().getNombreCategoria());
		CategoriaDAO cDao = new CategoriaDAO();
		stmt.setInt(4,cDao.traerIdCategoria(cat));
		//Traer el ID de medioOrigen
		MedioOrigen med = new MedioOrigen(fakeNew.getMedioOrigen().getNombre());
		MedioOrigenDAO mDao = new MedioOrigenDAO();
		stmt.setInt(5,mDao.traerIdMedioOrigen(med));
		
		stmt.setString(6,fakeNew.getTitulo());

		filasAfectadas = stmt.executeUpdate();

		//3.Evaluar Resultado
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally 
	{
		//4.Cerrar sesion
		if (c != null)
		{
			try{
				c.close();
				}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	return filasAfectadas == 1;
}

public boolean  Eliminar(FakeNew fakeNew)
{
	//Ubicacion del motor de la base de datos 

	String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
	String password ="admin";
	String ussers= "root";

	Connection c = null;

	int filasAfectadas= 0;
	try {
		//1.Establecer conexion con la base de datos

		c = DriverManager.getConnection(url,ussers,password);

		System.out.println("conecto");

		//2.Paso ejecutar instruccion 
		String sql = "DELETE FROM fakenews WHERE titulo = ?;";

		PreparedStatement stmt = c.prepareStatement(sql);

		stmt.setString(1, fakeNew.getTitulo());

		filasAfectadas = stmt.executeUpdate();

		//3.Evaluar Resultado
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally 
	{
		//4.Cerrar sesion
		if (c != null)
		{
			try{
				c.close();
				}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	return filasAfectadas == 1;
}

public ArrayList<FakeNew> Consultas(){

	String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
	String usr = "root";
	String pass = "admin";

	ArrayList<FakeNew> fakenews = new ArrayList<>();
	
	Connection c = null;
	try {
		//1. Establecer conexión 
		c = DriverManager.getConnection(url, usr, pass);

		//2. Ejecutar instrucciones
		/*
		 Esto fue lo más dificil...
		 Utilizamos una consulta mediante el join ese para mostrar todo lo de las fakenews sin mostrar las fks, sino sus nombres, además de mostrar el tipo de medio q es su medio de origen UnU
		 */
		String sql = "SELECT  f.titulo, f.descripcion, f.creador, f.fechaAparicion, c.nombreCategoria, m.nombreMedioOrigen FROM fakenews f JOIN medioorigen m ON f.idMedioOrigen = m.idMedioOrigen JOIN categorias c ON f.idCategoria = c.idCategoria;";

		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//3. Procesar resultado 
		while(rs.next()) {
			String titulo = rs.getString("titulo");
			String descripcion = rs.getString("descripcion");
			String creador = rs.getString("creador");
			LocalDate fechaAparicion = rs.getDate("fechaAparicion").toLocalDate();
			String cat = rs.getString("nombreCategoria");
			String med= rs.getString("nombreMedioOrigen");
			
			Categoria categoria = new Categoria(cat);
			MedioOrigen medioOrigen = new MedioOrigen(med);
			
			FakeNew f = new FakeNew(titulo, descripcion, creador, fechaAparicion, categoria, medioOrigen);
			
			fakenews.add(f);
		}
	}catch(SQLException e) {
	} finally {
		//4. Cerrar conexión
		if(c != null) {
			try {
				c.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return fakenews;
	}

public FakeNew ConsultaTitulo(String titulo) {
	String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
	String usr = "root";
	String pass = "admin";
	
	FakeNew fakeNew = null;
	
	Connection c = null;
	try {
		//1. Establecer conexión 
		c = DriverManager.getConnection(url, usr, pass);

		//2. Ejecutar instrucciones
		/*
		 Esto fue lo más dificil...
		 Utilizamos una consulta mediante el join ese para mostrar todo lo de las fakenews sin mostrar las fks, sino sus nombres, además de mostrar el tipo de medio q es su medio de origen UnU
		 */
		String sql = "SELECT  f.titulo, f.descripcion, f.creador, f.fechaAparicion, c.nombreCategoria, m.nombreMedioOrigen FROM fakenews f JOIN medioorigen m ON f.idMedioOrigen = m.idMedioOrigen JOIN categorias c ON f.idCategoria = c.idCategoria WHERE f.titulo = ?;";		
		PreparedStatement stmt = c.prepareStatement(sql);

		stmt.setString(1, titulo);

		ResultSet rs = stmt.executeQuery();
		
		//3. Procesar resultado 
		if (rs.next()) {
			String titulol = rs.getString("titulo");
			String descripcion = rs.getString("descripcion");
			String creador = rs.getString("creador");
			LocalDate fechaAparicion = rs.getDate("fechaAparicion").toLocalDate();
			String cat = rs.getString("nombreCategoria");
			String med = rs.getString("nombreMedioOrigen");
			
			Categoria categoria = new Categoria(cat);
			MedioOrigen medioOrigen = new MedioOrigen(med);
	
			fakeNew = new FakeNew(titulol, descripcion, creador, fechaAparicion, categoria, medioOrigen);
		}
	}catch(SQLException e) {
	} finally {
		//4. Cerrar conexión
		if(c != null) {
			try {
				c.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return fakeNew;
}

public int traerIdFakeNew( FakeNew fake)
{
	String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
	String password ="admin";
	String ussers= "root";
	 
	int id  = 0 ;
	
	Connection c = null;
	try {
		//1. Establecer conexión 
		c = DriverManager.getConnection(url, ussers, password);

		//2. Ejecutar instrucciones
		/*
		 Esto fue lo más dificil...
		 Utilizamos una consulta mediante el join ese para mostrar todo lo de las fakenews sin mostrar las fks, sino sus nombres, además de mostrar el tipo de medio q es su medio de origen UnU
		 */
		String sql = "SELECT idFakenews FROM fakenews WHERE titulo = ?;";
		
		PreparedStatement stmt = c.prepareStatement(sql);

		stmt.setString(1, fake.getTitulo());

		ResultSet rs = stmt.executeQuery();
		
		//3. Procesar resultado 
		if (rs.next()) {
			id = rs.getInt("idFakenews");
		}
	}catch(SQLException e) {
	} finally {
		//4. Cerrar conexión
		if(c != null) {
			try {
				c.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return id;
}

}