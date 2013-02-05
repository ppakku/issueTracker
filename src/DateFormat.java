import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormat {
	
	public String convertNaverRssDateFormat( String pubDate ) throws ParseException {
		SimpleDateFormat naverRssSdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
		Date date = naverRssSdf.parse( pubDate );
		SimpleDateFormat standardSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return standardSdf.format( date );
	}
	
	public Date convertBasicDateFormat( String pub_date ) throws ParseException {
		SimpleDateFormat basicSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if( pub_date != null ) {
			return basicSdf.parse( pub_date );
		} else {
			return new Date(0);
		}
	}
	
}
