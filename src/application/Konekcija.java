package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Potrebno je da skinete sa interneta i dodate u projekat mysql-connector-java na sljedeci nacin
// desni klik na projekat -> Build path -> Add external archives -> odaberete mysql-connector-java.jar

public class Konekcija {

	public static void main(String[] args) {
		
		
		ResultSet resultSet = null;
		try {
			/*
			String connectionUrl =  "jdbc:mysql://localhost:3307/ors1_opp_2021_2022?"
                                 + "user=root&"
                                 + "password=&"
                                 + "encrypt=true&"
                                 + "trustServerCertificate=false&"
                                 // + "loginTimeout=30;"
                                 + "serverTimezone=UTC";
           Connection connection = DriverManager.getConnection(connectionUrl);
		*/
			// Ukoliko koristite XAMPP user i password mozete provjeriti u datoteci C:\xampp\phpMyAdmin\config.inc
			String DB_user = "root";
			String DB_password = "";
			String connectionUrl = "jdbc:mysql://localhost";
			int port = 3306;
			String DB_name = "ors1_opp_2021_2022";
			connectionUrl = connectionUrl + ":" + port + "/" + DB_name;
			
			Connection connection = DriverManager.getConnection(connectionUrl, DB_user, DB_password);
			
			Statement statement = connection.createStatement();

			String selectSql = "SELECT * FROM ucenik";
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString("prezime") + " " + resultSet.getString(2));
            }

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}