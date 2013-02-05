import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;


public class MySQLConnectionTest {

	public static void main(String[] args) throws ParseException {
		
		Connection c;
		Statement stmt;
		ResultSet rs;
		
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			c = DriverManager.getConnection( "jdbc:mysql://localhost:3306/sbd", "root", "raison0919" );
			stmt = c.createStatement();
			//stmt.executeQuery("set names euckr");
			rs = stmt.executeQuery( "select * from issue" );
			//psmt = c.prepareStatement( "select * from issue" );
			//rs = psmt.executeQuery();
			
			while( rs.next() ) {
				System.out.println( rs.getString(2) );
				System.out.println( new String(rs.getBytes(2)) );
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
