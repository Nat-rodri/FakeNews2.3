package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Objetos.Categoria;

public class CategoriaDAO {
	
	
	public int traerIdCategoria( Categoria cate)
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
			String sql = "SELECT idCategoria FROM Categorias  WHERE  nombreCategoria = ?";
			
			PreparedStatement stmt = c.prepareStatement(sql);
	
			stmt.setString(1, cate.getNombreCategoria());
	
			ResultSet rs = stmt.executeQuery();
			
			//3. Procesar resultado 
			if (rs.next()) {
				id = rs.getInt("idCategoria");
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
	
	public ArrayList<Categoria> TraerCategorias(){

		String url = "jdbc:mysql://localhost:3306/tpfinal_2024";
		String usr = "root";
		String pass = "admin";

		ArrayList<Categoria> categorias = new ArrayList<>();
		
		Connection c = null;
		try {
			//1. Establecer conexión 
			c = DriverManager.getConnection(url, usr, pass);

			//2. Ejecutar instrucciones
			String sql = "SELECT nombreCategoria FROM categorias;";

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			//3. Procesar resultado 
			while(rs.next()) {
				String nombreCategoria = rs.getString("nombreCategoria");
				
				Categoria categoria = new Categoria(nombreCategoria);
				
				categorias.add(categoria);
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
		return categorias;
		}
}