package joymeter.aliduanxin.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

public interface MessageSendService {
	public  SendSmsResponse sendSms(String number,String customer);
}
