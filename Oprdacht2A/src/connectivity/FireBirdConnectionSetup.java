package connectivity;

import java.sql.Connection;

public class FireBirdConnectionSetup extends DBConnectionMethods{

	@Override
	public Connection C3POCPEva() {
		dbUsername = "SYSDBA";
		dbPassword =  "masterkey";
		dbURL = "jdbc:firebirdsql://localhost:3050/C:/Users/kuija/Dropbox/Java/Praktijk gedeelte/Firebird/eva/RSVIER";
		return super.C3POCPEva();
	}

	@Override
	public Connection C3POCPAgung() {
		dbUsername = "SYSDBA";
		dbPassword =  "masterkey";
		dbURL = "jdbc:firebirdsql://localhost:3050/C:/Users/kuija/Dropbox/Java/Praktijk gedeelte/Firebird/eva/RSVIER";
		return super.C3POCPAgung();
	}

	@Override
	public Connection C3POCPJesse() {
		dbUsername = "SYSDBA";
		dbPassword =  "masterkey";
		dbURL = "jdbc:firebirdsql://localhost:3050/C:/Program Files/Firebird/RSVIER.FDB";
		return super.C3POCPJesse();
	}

	@Override
	public Connection HikariCPEva() {
		dbUsername = "SYSDBA";
		dbPassword =  "masterkey";
		dbURL = "jdbc:firebirdsql://localhost:3050/C:/Users/kuija/Dropbox/Java/Praktijk gedeelte/Firebird/eva/RSVIER";
		return super.HikariCPEva();
	}

	@Override
	public Connection HikariCPAgung() {
		dbUsername = "SYSDBA";
		dbPassword =  "masterkey";
		dbURL = "jdbc:firebirdsql://localhost:3050/C:/Users/kuija/Dropbox/Java/Praktijk gedeelte/Firebird/eva/RSVIER";
		return super.HikariCPAgung();
	}

	@Override
	public Connection HikariCPJesse() {
		dbUsername = "SYSDBA";
		dbPassword =  "masterkey";
		dbURL = "jdbc:firebirdsql://localhost:3050/C:/Program Files/Firebird/RSVIER.FDB";
		return super.HikariCPJesse();
	}
	

}
