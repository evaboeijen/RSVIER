package connectivity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import dao.ArtikelDaoImpl;
import menu.DBConnectivityManagement;
import menu.DBKeuzeMenu;
import menu.HoofdMenu;

public abstract class DBConnectionMethods {
	int keuzeCP = 0;  
	private static final Logger logger =  LoggerFactory.getLogger(DBConnectionMethods.class);
	static Connection connection = null;
	static HikariDataSource ds = null;
	static C3P0PooledConnectionPool C3POds = null;
	Scanner input =  new Scanner (System.in);
	
	String dbHostname = null;
	int dbPort = 0;
	String dbUsername = null;
	String dbPassword =  null;
	String dbURL = null;
	
	
	public static Connection getConnectionStatus() {
		return connection;
	}

	public ComboPooledDataSource setupC3POCP() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setMinPoolSize(5);                                     
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		return cpds;
	}

		public Connection C3POCPInput() {

			
			System.out.print("Voer database hostname in: ");
			String dbHostName = input.next();
			System.out.print("Database port: ");
			dbPort = input.nextInt();
			input.nextLine();
			System.out.print("Database gebruikersnaam: ");
			dbUsername = input.nextLine();
			System.out.print("Wachtwoord: ");
			dbPassword = input.nextLine();
			ComboPooledDataSource cpds = setupC3POCP();
			//cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
			cpds.setJdbcUrl(dbURL);
			cpds.setUser(dbUsername);                                  
			cpds.setPassword(dbPassword);
			
			try{
				connection = cpds.getConnection();
			}catch (SQLException e) {
					logger.warn("SQL exception voor C3POCPInput methode");
		            e.printStackTrace();
			}
			return connection;
		}

		public Connection C3POCPEva() {
			//cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
			ComboPooledDataSource cpds = setupC3POCP();
			cpds.setJdbcUrl(dbURL);
			cpds.setUser(dbUsername);                                  
			cpds.setPassword(dbPassword);
			
			try{
				connection = cpds.getConnection();
			}catch (SQLException e) {
					logger.warn("SQL exception voor C3POCPEva methode");
		            e.printStackTrace();
			}
			return connection;
		}

		public Connection C3POCPAgung() {
			//cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
			ComboPooledDataSource cpds = setupC3POCP();
			cpds.setJdbcUrl(dbURL);
			cpds.setUser(dbUsername);                                  
			cpds.setPassword(dbPassword);
			
			try{
				connection = cpds.getConnection();
			}catch (SQLException e) {
					logger.warn("SQL exeption voor C3POCAgung methode");
		            e.printStackTrace();
			}
			return connection;
		}

		public Connection C3POCPJesse() {
			//cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
			ComboPooledDataSource cpds = setupC3POCP();
			cpds.setJdbcUrl(dbURL);
			cpds.setUser(dbUsername);                                  
			cpds.setPassword(dbPassword);
			
			try{
				connection = cpds.getConnection();
			}catch (SQLException e) {
					logger.warn("SQL exeption voor C3POCJesse methode");
		            e.printStackTrace();
			}
			return connection;
		}

	public HikariConfig setupHikariCP() {
		HikariConfig config = new HikariConfig();
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		return config;
}

		public Connection HikariCPInput(){
			System.out.print("Voer database hostname in: ");
			dbHostname = input.next();
			System.out.print("Database port: ");
			dbPort = input.nextInt();
			input.nextLine();
			System.out.print("Database gebruikersnaam: ");
			dbUsername = input.nextLine();
			System.out.print("Wachtwoord: ");
			dbPassword = input.nextLine();
			dbURL = ("jdbc:mysql://localhost:" + dbPort + "/" + dbHostname);
			
			HikariConfig config = setupHikariCP();
			config.setJdbcUrl(dbURL);
			config.setUsername(dbUsername);
			config.setPassword(dbPassword);
			HikariDataSource ds = new HikariDataSource(config);
			try{
				connection = ds.getConnection();
			}catch (SQLException e) {
					logger.warn("SQL exeption voor HikariCPEva methode");
		            e.printStackTrace();
			}
			return connection;
		}

		public Connection HikariCPEva(){
			HikariConfig config = setupHikariCP();
			config.setJdbcUrl(dbURL);
			config.setUsername(dbUsername);
			config.setPassword(dbPassword);
			HikariDataSource ds = new HikariDataSource(config);
			try{
				connection = ds.getConnection();
			}catch (SQLException e) {
					logger.warn("SQL exeption voor HikariCPEva methode");
		            e.printStackTrace();
			}
			return connection;
		}

		public Connection HikariCPAgung(){
			HikariConfig config = setupHikariCP();
			config.setJdbcUrl(dbURL);
			config.setUsername(dbUsername);
			config.setPassword(dbPassword);
			HikariDataSource ds = new HikariDataSource(config);
			try{
				connection = ds.getConnection();
			}catch (SQLException e) {
					logger.warn("SQL exeption voor HikariCPAgung methode");
		            e.printStackTrace();
			}
			return connection;
		}

		public Connection HikariCPJesse(){
			HikariConfig config = setupHikariCP();
			config.setJdbcUrl(dbURL);
			config.setUsername(dbUsername);
			config.setPassword(dbPassword);
			HikariDataSource ds = new HikariDataSource(config);
			try{
				connection = ds.getConnection();
			}catch (SQLException e) {
					logger.warn("SQL exeption voor HikariCPJesse methode");
		            e.printStackTrace();
			}
			return connection;
		}

	


}
