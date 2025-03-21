package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Objetos.Audiovisual;

public class MAudiovisualDAO {
	
	public boolean  CrearAudiovisual(Audiovisual medio )
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
			/*Primero se insertan los datos a la tabla madre (hablamos de la tabla medios ejjd) para poder insertar los datos en la tabla de mediosaudiovisuales,
			ya que esta necesita ser igual al idMedio que el de la tabla medios, osea q para ser creada en mediosaudiovisuales primero debe ser creada en medios (utilizan el mismo id como pk)
			*/
			String sql = "INSERT INTO medios ( `nombreMedio`, `tipoMedio`, `usuarioPeriodista`, `fechaDivulgacion`, `seRetracto`)\r\n"
					+ "VALUES (?, ?, ?, ?, ?);\r\n"
					+ "INSERT INTO mediosaudiovisuales (`idMedio`, `nombrePrograma`, `minutosDedicados`, `rating`)\r\n"
					+ "VALUES ((SELECT idMedio FROM medios WHERE nombreMedio = ?;), ?, ?, ?);";
						
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1,medio.getNombreMedio());
			stmt.setString(2,medio.getTipoMedio());
			stmt.setString(3,medio.getUsuarioPeriodista());
			stmt.setDate(4,java.sql.Date.valueOf(medio.getFechaDivulgacion()));
			stmt.setBoolean(5,medio.isSeRetracto());
			stmt.setString(6,medio.getNombreMedio());
			stmt.setString(7,medio.getNombrePrograma());
			stmt.setInt(8,medio.getMinutosDedicados());
			stmt.setFloat(9,medio.getRating());

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
	
	public boolean  ModificarAudiovisual(Audiovisual medio )
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
			/*
			 Para modificar absolutamente todo del medio audiovisual, debo también modificar desde la tabla madre (para modificar el usuario/periodista, la fecha divulgada, etc.)
			 para modificar el medio audiovisual debo hacerlo por medio del idMedio, ya que así la BD sabe de qué registro le estoy modificando de la tabla medios y mediosaudiovisuales.
			*/
			String sql = "UPDATE medios SET `usuarioPeriodista` = ?, `fechaDivulgacion` = ?, `seRetracto` = ? WHERE nombreMedio = ?; \r\n"
					+ "UPDATE mediosaudiovisuales SET `nombrePrograma` = ?, `minutosDedicados` = ?, `rating` = ? WHERE `idMedio` = (SELECT idMedio FROM medios WHERE nombreMedio = ?);";			

			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1,medio.getUsuarioPeriodista());
			stmt.setDate(2, java.sql.Date.valueOf(medio.getFechaDivulgacion()));
			stmt.setBoolean(3, medio.isSeRetracto());
			//cuarto parametro  que es con el que se filtra por medio de la clausula where de medios
			stmt.setString(4,medio.getNombreMedio());
			stmt.setString(5,medio.getNombrePrograma());
			stmt.setInt(6,medio.getMinutosDedicados());
			stmt.setFloat(7,medio.getRating());
			//octavo parametro  que es con el que se filtra por medio de la clausula where de mediosescritos
			stmt.setString(8,medio.getNombreMedio());

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
	
	public boolean  EliminarAudiovisual(Audiovisual medio)
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
			/*
			 Como en los demás, se necesitan ambas tablas para eliminar un registro, ya que se necesita eliminar por completo
			 (porque para crear un medio audiovisual antes se lo debe crear en la tabla medios, pasa lo mismo al eliminar, solo q se elimina primero la tabla hija)
			*/
			String sql = "DELETE FROM mediosaudiovisuales WHERE idMedio = (SELECT idMedio FROM medios WHERE nombreMedio = ?);\r\n"
					+ "DELETE FROM medios WHERE nombreMedio = ?;";

			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1, medio.getNombreMedio());
			stmt.setString(2, medio.getNombreMedio());

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
	
	public ArrayList<Audiovisual> ConsultasAudiovisuales(){

		String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
		String usr = "root";
		String pass = "admin";

		ArrayList<Audiovisual> medios = new ArrayList<>();
		
		Connection c = null;
		try {
			//1. Establecer conexión 
			c = DriverManager.getConnection(url, usr, pass);

			//2. Ejecutar instrucciones
			//Utilizar un join para juntar la tabla medios y mediosaudiovisuales
			String sql = "SELECT * FROM medios m join mediosaudiovisuales mau ON m.idMedio = mau.idMedio;";

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//3. Procesar resultado 
			while(rs.next()) {
				String nombreMedio = rs.getString("nombreMedio");
				String usuarioPeriodista =rs.getString("usuarioPeriodista");
				LocalDate fechaDivulgacion =rs.getDate("fechaDivulgacion").toLocalDate();
				boolean seRetracto =rs.getBoolean("seRetracto");
				String nombrePrograma =rs.getString("nombrePrograma");
				int minutosDedicados =rs.getInt("minutosDedicados");
				float rating =rs.getFloat("rating");

				Audiovisual m = new Audiovisual(nombreMedio, usuarioPeriodista, fechaDivulgacion, seRetracto, nombrePrograma, minutosDedicados, rating);
				
				medios.add(m);
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
		return medios;
		}

}
