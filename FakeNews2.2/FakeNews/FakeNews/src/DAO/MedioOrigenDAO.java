package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Objetos.MedioOrigen;

public class MedioOrigenDAO {
	
	public int traerIdMedioOrigen( MedioOrigen med)
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
			String sql = "SELECT  idMedioOrigen FROM medioorigen  WHERE  NombreMedioOrigen = ?";
			
			PreparedStatement stmt = c.prepareStatement(sql);
	
			stmt.setString(1, med.getNombre());
	
			ResultSet rs = stmt.executeQuery();
			
			//3. Procesar resultado 
			if (rs.next()) {
				id = rs.getInt("idMedioOrigen");
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
	
	public ArrayList<MedioOrigen> TraerTiposMedios(){

		String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
		String usr = "root";
		String pass = "admin";

		ArrayList<MedioOrigen> medios = new ArrayList<>();
		
		Connection c = null;
		try {
			//1. Establecer conexión 
			c = DriverManager.getConnection(url, usr, pass);

			//2. Ejecutar instrucciones
			String sql = "SELECT nombreMedioOrigen FROM medioorigen;";

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			//3. Procesar resultado 
			while(rs.next()) {
				String nombreMedioOrigen = rs.getString("nombreMedioOrigen");
				MedioOrigen medio = new MedioOrigen(nombreMedioOrigen);
				
				medios.add(medio);
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
