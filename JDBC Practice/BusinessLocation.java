import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessLocation {
	/**
	 * The database connection.
	 */
	private Connection connection;

	public BusinessLocation(String driver, String url, String user,
			String password) {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.err.println("Unable to connect to the database due to " + e);
		}
	}

	public void listBusinessLocations(String business_name, String address,
			String state_name) throws SQLException {

		// SQL query to find Business Locations for given address, state and
		// business-name
		PreparedStatement find_business_location = connection
				.prepareStatement("select distinct bl.id from BusinessLocation bl, Business bus, State s, Building bui "
						+ "where bl.partOf = bus.id and bl.locatedAt = bui.id and bui.partOf = s.id "
						+ "and s.id = bus.id and bus.name = ? and bui.address = ? and s.name = ?;");

		find_business_location.setString(1, business_name);
		find_business_location.setString(2, address);
		find_business_location.setString(3, state_name);
		ResultSet business_locations = find_business_location.executeQuery();

		// SQL query to find connection and network details for a given
		// BusinessLocation id.
		PreparedStatement find_connections = connection
				.prepareStatement("select c.network, c.usage, n.capacity, n.cost, n.type, s.id from Connection c, Network n, State s "
						+ "where c.location = ? and c.network = n.id and n.partOf = s.id and s.name = state_name;");
		ResultSet connections;

		// SQL query to find other networks in same state whose capacity is at
		// least equal to the connection usage and whose type is the same.
		PreparedStatement find_netwroks = connection
				.prepareStatement("select n.id from Network n, State s where n.partOf = s.id and n.id <> ? and s.id = ? and "
						+ "n.capacity >= ? and n.type = ?;");
		ResultSet networks;

		System.out.println("Business Locations for given address, state and business-name");
		System.out
				.println("bid \t usage \t capacity \t cost \t type \t network \t similar networks");

		while (business_locations.next()) {
			int bid = business_locations.getInt(1);
			System.out.print(bid);
			find_connections.setInt(1, bid);
			connections = find_connections.executeQuery();
			while (connections.next()) {
				int nid = connections.getInt(1);
				Double usage = connections.getDouble(2);
				Double capacity = connections.getDouble(3);
				String cost = connections.getString(4);
				String type = connections.getString(5);
				int sid = connections.getInt(6);
				System.out.print("\t" + usage + "\t" + capacity + "\t" + cost
						+ "\t" + type);

				find_netwroks.setInt(1, nid);
				find_netwroks.setInt(2, sid);
				find_netwroks.setDouble(3, usage);
				find_netwroks.setString(4, type);
				networks = find_netwroks.executeQuery();
				System.out.print("\t" + nid);
				while (networks.next()) {
					System.out.print("\t" + networks.getInt(1));
				}
			}
			System.out.println();
		}

	}

	public static void main(String args[]) throws Exception {
		BusinessLocation b = new BusinessLocation("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost/business", "root", "");
		b.listBusinessLocations("?", "?", "?");
		System.out.println("connection successsful");
	}
}
