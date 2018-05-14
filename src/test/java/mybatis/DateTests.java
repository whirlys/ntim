package mybatis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.whirly.util.Msg;

public class DateTests {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Date date = sdf.parse("1995.11.07");
		System.out.println(date.toString());

		Msg msg = Msg.success();
		System.out.println(msg.toString());
	}
}
