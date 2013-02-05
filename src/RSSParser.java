import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class RSSParser {
	
	String naverRSSUrl = "http://newssearch.naver.com/search.naver?where=rss&query=";
	
	public NodeList naverRssParser( String issue_name ) throws IOException, ParserConfigurationException, SAXException {
		System.out.println(issue_name);
		URL url = new URL( naverRSSUrl + issue_name.replaceAll(" ","+") );
		BufferedReader br = new BufferedReader( new InputStreamReader( url.openStream() ) );
		
		String content = new String();
		String line;
		while( (line = br.readLine()) != null ) {
			content += line.replaceAll("> <", "><");
		}
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse( new ByteArrayInputStream( content.getBytes() ) );
		
		return doc.getElementsByTagName("item");
	}
	
}
