import java.sql.*;
import java.util.*;

public class KlantDaoImpl implements KlantDao {

	Connection connection = null;

	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			if (connection == null) {
				String dbURL = "jdbc:mysql://localhost:3306/opdracht1";
				String username = "root";
				String password = "";

				connection = DriverManager.getConnection(dbURL, username, password);
				System.out.println("Verbinding is gemaakt");
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		System.out.println("Connection succesful");

		return connection;

	}

	@Override
	public void insert(Klant klant) {

		List<Klant> klanten = new ArrayList<>();
		klanten.add(klant);

		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into klant(klant_id, voornaam, achternaam) values (?, ?, ?)");
			preparedStatement.setInt(1, klant.getKlant_id());
			preparedStatement.setString(2, klant.getVoornaam());
			preparedStatement.setString(3, klant.getAchternaam());

			int rowsInserted = preparedStatement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("Een nieuwe klant is succesvol toegevoegd aan de database");
			}

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(klant);
		System.out.println("Klant succesvol toegevoegd");
	}

	@Override
	public List<Klant> selectAll() {
		List<Klant> klanten = new ArrayList<Klant>();

		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM klant");

			Klant klant;
			while (resultSet.next()) {
				klant = new Klant();
				klant.setKlant_id(resultSet.getInt("Klant_id"));
				klant.setVoornaam(resultSet.getString("voornaam"));
				klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
				klant.setAchternaam(resultSet.getString("achternaam"));
				klant.setEmail(resultSet.getString("email"));

				klanten.add(klant);
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(klanten);
		return klanten;
	}

	@Override
	public Klant getKlant(int klant_id) {

		Klant klant = new Klant();
		ResultSet result;
		String sql = "SELECT * FROM klant WHERE Klant_id= " + klant_id;

		try {
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				klant.setKlant_id(klant_id);
				klant.setVoornaam(result.getString(2));
				klant.setTussenvoegsel(result.getString(4));
				klant.setAchternaam(result.getString(3));
				klant.setEmail(result.getString(5));

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		System.out.println(klant);
		return klant;
	}

	@Override
	public Klant getKlant(String voornaam) {

		Klant klant = null;
		ResultSet result;

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM klant WHERE voornaam=?");
			statement.setString(1, voornaam);
			result = statement.executeQuery();

			while (result.next()) {
				klant = new Klant();
				klant.setKlant_id(result.getInt(1));
				klant.setVoornaam(result.getString(2));
				klant.setTussenvoegsel(result.getString(4));
				klant.setAchternaam(result.getString(3));
				klant.setEmail(result.getString(5));

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		System.out.println(klant);
		return klant;
	}

	@Override
	public void update(Klant klant) {

		List<Klant> klanten = new ArrayList<>();
		klanten.add(klant);

		try {

			PreparedStatement statement = connection.prepareStatement(
					"UPDATE Klant SET voornaam=?, tussenvoegsel=?, achternaam=?, email=? WHERE Klant_id=?");
			statement.setString(1, klant.getVoornaam());
			statement.setString(2, klant.getTussenvoegsel());
			statement.setString(3, klant.getAchternaam());
			statement.setString(4, klant.getEmail());
			statement.setInt(5, klant.getKlant_id());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("De gegevens van de bestaande klant zijn aangepast!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Klant klant) {
		try {

			PreparedStatement statement = connection.prepareStatement("DELETE FROM Klant WHERE Klant_id=?");
			statement.setInt(1, klant.getKlant_id());

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("De klant is gewist uit de database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("Verbinding is gesloten");
			}
		} catch (Exception e) {
			// do nothing
		}
	}
}