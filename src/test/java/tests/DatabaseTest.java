package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DatabaseTest {

	@Test
	public void test() throws ClassNotFoundException, SQLException {

		String dbURL = "jdbc:mysql://localhost:3306/EducacionIT"; // Cambia el puerto a 3306
		String username = "root";
		String password = "";
		String query = "SELECT * FROM personas";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Cambia el nombre del driver a la versión correcta
			Connection con = DriverManager.getConnection(dbURL, username, password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("nombre"); // Utiliza el nombre de la columna
				String lastName = rs.getString("apellido");

				System.out.println("Nombre: " + name + "\n" + "Apellido: " + lastName + "\n");
			}

			// Cierra los recursos
			rs.close();
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
