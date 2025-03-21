package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Objetos.FakeNew;
import Objetos.Refutacion;
import Objetos.Refutador;

public class RefutacionDAO {
	
	public boolean  Crear(Refutacion refutacion )

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
			String sql = "INSERT INTO refutaciones (`fechaRefutada`, `fuenteEvidencia`, `provOrgOficial`, `idRefutador`, `idFakenews`) VALUES (?, ?, ?, ?, ?);";
						
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setDate(1,java.sql.Date.valueOf(refutacion.getFechaRefutada()));
			stmt.setString(2, refutacion.getFuenteEvidencia());
			stmt.setBoolean(3,refutacion.isProvOrgOficial());
			
			Refutador ref = new Refutador(refutacion.getRefutador().getNombre());
			RefutadorDAO refDao = new RefutadorDAO();
			stmt.setInt(4,refDao.traerIdRefutador(ref));
			
			FakeNew FN = new FakeNew(refutacion.getFakenew().getTitulo());
			FakeNewDAO fakeDao = new FakeNewDAO();
			stmt.setInt(5,fakeDao.traerIdFakeNew(FN));

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
	
	public boolean  Modificar(Refutacion refutacion)
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
			 Utilicé subconsultas para poder modificar la categoria y el medio por sus nombres, recordamos q los nombres son claves únicas :)
			 */
			String sql = "UPDATE refutaciones SET `fechaRefutada` = ? , `fuenteEvidencia` = ? , `provOrgOficial` = ?, `idRefutador` = ? WHERE `idFakenews` = ?;";			

			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setDate(1, java.sql.Date.valueOf(refutacion.getFechaRefutada()));
			stmt.setString(2, refutacion.getFuenteEvidencia());
			stmt.setBoolean(3, refutacion.isProvOrgOficial());
			
			Refutador ref = new Refutador(refutacion.getRefutador().getNombre());
			RefutadorDAO refDao = new RefutadorDAO();
			stmt.setInt(4,refDao.traerIdRefutador(ref));
			
			//Condición
			FakeNew FN = new FakeNew(refutacion.getFakenew().getTitulo());
			FakeNewDAO fakeDao = new FakeNewDAO();
			stmt.setInt(5,fakeDao.traerIdFakeNew(FN));

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
	
	public boolean  Eliminar(Refutacion refutacion)
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
			String sql = "DELETE FROM refutaciones WHERE idFakenews = ?;";

			PreparedStatement stmt = c.prepareStatement(sql);

			FakeNew FN = new FakeNew(refutacion.getFakenew().getTitulo());
			FakeNewDAO fakeDao = new FakeNewDAO();
			stmt.setInt(1,fakeDao.traerIdFakeNew(FN));

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
	
	public ArrayList<Refutacion> Consultas(){

		String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
		String usr = "root";
		String pass = "admin";

		ArrayList<Refutacion> refutaciones = new ArrayList<>();
		
		Connection c = null;
		try {
			//1. Establecer conexión 
			c = DriverManager.getConnection(url, usr, pass);

			//2. Ejecutar instrucciones
			/*
			 ...
			 Utilizamos una consulta mediante el join para mostrar todo lo de la refutacion sin mostrar las fks, sino sus nombres y titulos :D
			 */
			String sql = "SELECT r.fechaRefutada, r.fuenteEvidencia, r.provOrgOficial, f.titulo, ref.nombre FROM refutaciones r join fakenews f ON r.idFakenews = f.idFakenews JOIN refutadores ref ON r.idRefutador = ref.idRefutador;";

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//3. Procesar resultado 
			while(rs.next()) {
				LocalDate fechaRefutada = rs.getDate("fechaRefutada").toLocalDate();
				String fuenteEvidencia = rs.getString("fuenteEvidencia");
				boolean provOrgOficial = rs.getBoolean("provOrgOficial");
				String titulo = rs.getString("titulo");
				String nombre = rs.getString("nombre");
				
				Refutador ref = new Refutador(nombre);
				FakeNew fn = new FakeNew(titulo);
				//todo este lio unu
				Refutacion r = new Refutacion(fechaRefutada, fuenteEvidencia, provOrgOficial, ref, fn);
				
				refutaciones.add(r);
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
		return refutaciones;
		}
	
	public Refutacion TraerRefutacionPorFN(FakeNew fakenew) {
		
		String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
		String usr = "root";
		String pass = "admin";

		Refutacion refu = null;
		
		Connection c = null;
		try {
			//1. Establecer conexión 
			c = DriverManager.getConnection(url, usr, pass);

			//2. Ejecutar instrucciones
			/*
			 ...
			 Utilizamos una consulta mediante el join para mostrar todo lo de la refutacion sin mostrar las fks, sino sus nombres y titulos :D
			 */
			String sql = "SELECT r.fechaRefutada, r.fuenteEvidencia, r.provOrgOficial, ref.nombre FROM refutaciones r join fakenews f ON r.idFakenews = f.idFakenews JOIN refutadores ref ON r.idRefutador = ref.idRefutador WHERE r.idFakenews = ?;";
			
			PreparedStatement stmt = c.prepareStatement(sql);

			FakeNewDAO fDao = new FakeNewDAO();
			stmt.setInt(1,fDao.traerIdFakeNew(fakenew));

			ResultSet rs = stmt.executeQuery();
			//3. Procesar resultado 
			while(rs.next()) {
				LocalDate fecRefutada = rs.getDate("fechaRefutada").toLocalDate();
				String fuenteEvi = rs.getString("fuenteEvidencia");
				boolean provOrgOficial = rs.getBoolean("provOrgOficial");
				String nombre = rs.getString("nombre");
				
				Refutador ref = new Refutador(nombre);
				
				//todo este lio unu
				refu = new Refutacion(fecRefutada, fuenteEvi, provOrgOficial, ref, fakenew);
				
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
		return refu;
	}
}
