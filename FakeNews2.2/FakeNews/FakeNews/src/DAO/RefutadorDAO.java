package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import Objetos.Refutador;

public class RefutadorDAO {

	public boolean  Crear(Refutador refutador )

	{

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
			String sql = "INSERT INTO `tpfinal_2024`.`refutadores` (`nombre`,`apellido`,`medio`,`apodo`)VALUES(?,?,?,?);";
						
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1,refutador.getNombre());
			stmt.setString(2,refutador.getApellido());
			stmt.setString(3,refutador.getMedio());
			stmt.setString(4, refutador.getApodo());

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

public boolean  Modificar(Refutador refutador )
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
		String sql = "UPDATE `tpfinal_2024`.`refutadores` SET `nombre` = ?, `apellido` = ? ,`medio` = ?, `apodo` = ?  WHERE `nombre` = ?;";			

		PreparedStatement stmt = c.prepareStatement(sql);

		stmt.setString(1,refutador.getNombre());
		stmt.setString(2,refutador.getApellido());
		stmt.setString(3, refutador.getMedio());
		stmt.setString(4, refutador.getApodo());

		//cuarto parametro  qeu es donde  es where 
		stmt.setString(5, refutador.getNombre());

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

public boolean  Eliminar(Refutador refutador) throws RefutadorOcupadoExption
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
		String sql = "DELETE FROM `tpfinal_2024`.`refutadores` WHERE `nombre` = ?;";

		PreparedStatement stmt = c.prepareStatement(sql);

		stmt.setString(1, refutador.getNombre());

		filasAfectadas = stmt.executeUpdate();

		//3.Evaluar Resultado
	} catch (SQLIntegrityConstraintViolationException e) {
		throw new RefutadorOcupadoExption("El refutador está presente en refutaciones existentes.", e);
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

public ArrayList<Refutador> Consultas(){

	String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
	String usr = "root";
	String pass = "admin";

	ArrayList<Refutador> refutadores = new ArrayList<>();
	
	Connection c = null;
	try {
		//1. Establecer conexión 
		c = DriverManager.getConnection(url, usr, pass);

		//2. Ejecutar instrucciones
		String sql = "SELECT * FROM refutadores;";

		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//3. Procesar resultado 
		while(rs.next()) {
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			String medio =rs.getString("medio");
			String apodo = rs.getString("apodo");
			Refutador r = new Refutador(nombre,apellido,medio, apodo);
			
			refutadores.add(r);
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
	return refutadores;
	}

public ArrayList<Refutador> TraerRefutadores(){

	String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
	String usr = "root";
	String pass = "admin";

	ArrayList<Refutador> refutadores = new ArrayList<>();
	
	Connection c = null;
	try {
		//1. Establecer conexión 
		c = DriverManager.getConnection(url, usr, pass);

		//2. Ejecutar instrucciones
		String sql = "SELECT nombre FROM refutadores;";

		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		//3. Procesar resultado 
		while(rs.next()) {
			String nombreRefutador = rs.getString("nombre");
			
			Refutador refutador = new Refutador(nombreRefutador);
			
			refutadores.add(refutador);
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
	return refutadores;
	}

public int traerIdRefutador( Refutador ref)
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
		String sql = "SELECT idRefutador FROM refutadores WHERE nombre = ?;";
		
		PreparedStatement stmt = c.prepareStatement(sql);

		stmt.setString(1,ref.getNombre());

		ResultSet rs = stmt.executeQuery();
		
		//3. Procesar resultado 
		if (rs.next()) {
			id = rs.getInt("idRefutador");
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



	
