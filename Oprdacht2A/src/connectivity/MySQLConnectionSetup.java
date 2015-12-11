package connectivity;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;

public class MySQLConnectionSetup extends DBConnectionMethods {


	@Override
	public Connection C3POCPEva() {
		dbUsername = "root";
		dbPassword =  "";
		dbURL = "jdbc:mysql://localhost:3306/opdracht1";
		return super.C3POCPEva();
	}

	@Override
	public Connection C3POCPAgung() {
		dbUsername = "root";
		dbPassword =  "mysql";
		dbURL = "jdbc:mysql://localhost:3306/opdracht1";
		return super.C3POCPAgung();
	}

	@Override
	public Connection C3POCPJesse() {
		dbUsername = "root";
		dbPassword =  "JaRsvier15";
		dbURL = "jdbc:mysql://localhost:3308/opdracht1";
		return super.C3POCPJesse();
	}

	@Override
	public Connection HikariCPEva() {
		dbUsername = "root";
		dbPassword =  "";
		dbURL = "jdbc:mysql://localhost:3306/opdracht1";
		return super.HikariCPEva();
	}

	@Override
	public Connection HikariCPAgung() {
		dbUsername = "root";
		dbPassword =  "mysql";
		dbURL = "jdbc:mysql://localhost:3306/opdracht1";
		return super.HikariCPAgung();
	}

	@Override
	public Connection HikariCPJesse() {
		dbUsername = "root";
		dbPassword =  "JaRsvier15";
		dbURL = "jdbc:mysql://localhost:3308/opdracht1";
		return super.HikariCPJesse();
	}
	
	
	

}
