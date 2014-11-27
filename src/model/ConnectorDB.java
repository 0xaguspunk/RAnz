package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * S'encarrega de conectar amb la base de dades i controlar els diferents
 * errors de connexió que poden sorgir
 * 
 * @author Ranz
 */
public class ConnectorDB {
	static String userName;
	static String password;
	static String db;
	static int port;
	static String url = "jdbc:mysql://localhost";
	static Connection conn = null;
	static Statement s;
    
	/**
	 * Constructor de la classe
	 * 
	 * @param usr String amb el nom d'usuari
	 * @param pass String amb la password
	 * @param db String amb el nom de la BBDD
	 * @param port Número de port de connexió
	 */
	public ConnectorDB(String usr, String pass, String db, int port) {
		ConnectorDB.userName = usr;
		ConnectorDB.password = pass;
		ConnectorDB.db = db;
		ConnectorDB.port = port;
		ConnectorDB.url += ":"+port+"/";
		ConnectorDB.url += db;
	}

	/**
	 * Indica si ens hem pogut connectar correctament o no a la BBDD
	 */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("Conexió a base de dades "+url+" ... Ok");
            }
        }
        catch(SQLException ex) {
            System.out.println("Problema al connecta-nos a la BBDD --> "+url);
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }

    }
    
    /**
     * Insereix a la BBDD la query que se li passa
     * 
     * @param query String amb la query
     */
    public void insertQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Inserir --> " + ex.getSQLState());
            System.out.println("Problema al Inserir --> " + ex.getMessage());
        }
    }
    
    /**
     * Actualitza a la BBDD la query que se li passa
     * 
     * @param query String amb la query
     */
    public void updateQuery(String query){
    	 try {
             s =(Statement) conn.createStatement();
             s.executeUpdate(query);

         } catch (SQLException ex) {
             System.out.println("Problema al Modificar --> " + ex.getSQLState());
         }
    }
    
    /**
     * Elimina de la BBDD la query que se li passa
     * 
     * @param query String amb la query
     */
    public void deleteQuery(String query){
    	 try {
             s =(Statement) conn.createStatement();
             s.executeUpdate(query);
             
         } catch (SQLException ex) {
             System.out.println("Problema al Eliminar --> " + ex.getSQLState());
         }
    	
    }
    
    /**
     * Selecciona de la BBDD la informació que se li demana a la query que se li passa
     * 
     * @param query String amb la query
     */
    public ResultSet selectQuery(String query){
    	ResultSet rs = null;
    	 try {
             s =(Statement) conn.createStatement();
             rs = s.executeQuery (query);
             
         } catch (SQLException ex) {
             System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
         }
		return rs;
    }
    
    /**
     * S'encarrega de tancar la connexió amb la BBDD i controlar si s'ha pogut tancar correctament
     */
    public void disconnect(){
    	try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
		}
    }

}