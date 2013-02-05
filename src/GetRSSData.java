import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class GetRSSData {

	public static void main(String[] args) {
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/sbd", "root", "raison0919" );
			
			Map<Integer, String> issueMap = (new Issue()).getIssueMap( con );
			Iterator<Integer> iter = issueMap.keySet().iterator();
			RSSParser parser = new RSSParser();
			News news = new News();
			NodeList itemList;
			while( iter.hasNext() ) {
				int issue_code = iter.next();
				itemList = parser.naverRssParser( issueMap.get( issue_code ) );
				news.insertIssue( con, issue_code, itemList );
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
