import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Issue {
	
	String selectAll = "select * from issue"; 
	String insertOne = "insert into issue (issue_code, issue_name) values (?, ?)";
	
	public Map<Integer, String> getIssueMap( Connection con ) throws SQLException {
		PreparedStatement psmt = con.prepareStatement( selectAll );
		ResultSet rs = psmt.executeQuery();
		
		Map<Integer, String> issueMap = new HashMap<Integer, String>();
		while( rs.next() ) {
			issueMap.put( rs.getInt("issue_code"), rs.getString("issue_name") );
		}
		
		return issueMap;
	}
	
}
