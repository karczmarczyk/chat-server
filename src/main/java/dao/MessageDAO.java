package dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import pl.karczmarczyk.Message;
import pl.karczmarczyk.utils.DBUtil;
import pl.karczmarczyk.utils.SafeStatement;

/**
 *
 * @author mateusz
 */
public class MessageDAO {
    
    private static String insertSql = "INSERT INTO public.message(\n" +
"            from_user_id, to_user_id, created, read_time, content)\n" +
"    VALUES (?, ?, ?, ?, ?);";
    
    public static void insert (Message message) throws SQLException, NamingException, ClassNotFoundException {
	
	Connection conn = DBUtil.getConnection();
	
	SafeStatement statement = new SafeStatement(conn,insertSql);
	statement.setInt(    1,	Integer.valueOf(message.getFrom())   );
	statement.setInt(    2,	Integer.valueOf(message.getTo())     );
	statement.setLocalDateTime( 3, message.getDateCreated()	     );
	statement.setString( 4,	null				     ); 
	statement.setString( 5, message.getContent()		     );
	statement.execute();
	statement.close();
    }
}
