package pl.karczmarczyk.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;
import pl.karczmarczyk.config.Config;

/**
 *
 * @author mateusz
 */
public class DBUtil {

    /**
     * Pobranie połączenia 
     *
     * @return connection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws NamingException, SQLException, ClassNotFoundException {
	return getConnectionLocal();
    }

    /**
     * Pobranie połączenia lokalnie
     *
     * @return connection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static Connection getConnectionLocal() throws SQLException, ClassNotFoundException {
	Class.forName("org.postgresql.Driver");
	String user = Config.getAsString("chat.db.user");
	String pass = Config.getAsString("chat.db.pass");
	String url = Config.getAsString("chat.db.url");
	Connection conn = DriverManager.getConnection(url, user, pass);
	return conn;
    }
}
