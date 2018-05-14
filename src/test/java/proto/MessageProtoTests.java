package proto;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.whirly.imserver.constant.Constants;
import com.whirly.imserver.model.proto.MessageProto;
import com.whirly.util.SystemInfo;

public class MessageProtoTests {
	public static void main(String[] args) {
		String sessionid = "111";
		SystemInfo syso = SystemInfo.getInstance();
		MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
		builder.setVersion("1.0");
		builder.setDeviceId(syso.getMac());
		builder.setCmd(Constants.CmdType.BIND);
		builder.setSender(sessionid);
		builder.setReceiver(sessionid);
		builder.setMsgtype(Constants.ProtobufType.SEND);
		builder.setFlag(1);
		builder.setPlatform(syso.getSystem());
		builder.setPlatformVersion(syso.getSystemName());
		builder.setToken(sessionid);
		builder.setAppKey("123");
		builder.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		builder.setSign("123");

		System.out.println(builder.toString());

	}
}
