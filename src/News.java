import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;


public class News {
	
	String selectAll = "select * from news";
	String selectMaxNewsCode = "select max(news_code) from news where issue_code = ";
	String selectMaxPubDate = "select max(pub_date) from news where issue_code = ";
	String insertOne = "insert into news (issue_code, news_code, title, link, description, pub_date, author, category, thumbnail) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public void insertIssue( Connection con, int issue_code, NodeList itemList ) throws SQLException, DOMException, ParseException {
		PreparedStatement psmt;
		ResultSet rs;
		int max_news_code = 0;
		Date max_pub_date = new Date();
		DateFormat df = new DateFormat();
		
		psmt = con.prepareStatement( selectMaxNewsCode + issue_code );
		rs = psmt.executeQuery();
		while( rs.next() ) {
			max_news_code = rs.getInt(1);
		}
		
		psmt = con.prepareStatement( selectMaxPubDate + issue_code );
		rs = psmt.executeQuery();
		while( rs.next() ) {
			max_pub_date = df.convertBasicDateFormat( rs.getString(1) );
		}
		
		NodeList itemSpecList;
		String date;
		for( int index = 0; index < itemList.getLength(); index++ ) {
			 itemSpecList = itemList.item(index).getChildNodes();
			 date = df.convertNaverRssDateFormat( itemSpecList.item(3).getTextContent() );
			 
			 if( max_pub_date.before( df.convertBasicDateFormat( date ) ) ) {
				 psmt = con.prepareStatement( insertOne );
				 
				 psmt.setInt( 1, issue_code );
				 psmt.setInt( 2, max_news_code + index + 1 );
				 psmt.setString( 3, itemSpecList.item(0).getTextContent() );
				 psmt.setString( 4, itemSpecList.item(1).getTextContent() );
				 psmt.setString( 5, itemSpecList.item(2).getTextContent() );
				 psmt.setString( 6, date );
				 psmt.setString( 7, itemSpecList.item(4).getTextContent() );
				 psmt.setString( 8, itemSpecList.item(5).getTextContent() );
				 psmt.setString( 9, itemSpecList.item(6).getAttributes().getNamedItem("url").getTextContent() );
				 
				 psmt.executeUpdate();
			 }
		}
	}
	
}
