/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okey101;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author adem
 */

// Connectie met database gegevens en update/selectRecords
public class Okey101 {

        public static final String DB_DRIVER = "oracle.jdbc.Driver";
	public static final String DB_CONNECTION = "jdbc:mysql://localhost:8889/okey101";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "karakan";

	public static void main(String[] argv) {
            
            

		try {
                        updateRecordToTable();
			selectRecordsFromDbUserTable();
                        

		} catch (SQLException e) {

			System.out.println(e.getMessage());
  }
}
        
        
        // Maak connectie met database
private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

// Punten updaten van een speler
private static void updateRecordToTable() throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
                
                Scanner scanner = new Scanner(System.in);
           
                System.out.print("Hoeveel punten: ");
                int punten = scanner.nextInt(); 

		String updateTableSQL = "UPDATE spelers SET punten = punten + ? "
				                  + " WHERE ID = ?";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			
                        preparedStatement.setInt(1, punten);
			preparedStatement.setInt(2, 1);

			// execute update SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is updated to spelers table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			

		}

	}

  
// Selecteer bepaalde gegeven van Database en sluit connectie       
private static void selectRecordsFromDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT * from spelers";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {
                            
                                String naam = rs.getString("naam");
				int punten = rs.getInt("punten");
                                
                                System.out.println("naam : " + naam);
				System.out.println("punten : " + punten);
				
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}


}

