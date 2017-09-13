package joymeter.aliduanxin.serviceImp;
import org.springframework.stereotype.Service;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import joymeter.aliduanxin.common.BaseConfig;
import joymeter.aliduanxin.service.MessageSendService;
@Service
public class MessageSendServiceImp implements MessageSendService{
	
	public  SendSmsResponse sendSms(String number,String code) {
        String modelCode  = "SMS_80190341";
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", BaseConfig.accessKeyId, BaseConfig.accessKeySecret);
        try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", BaseConfig.product, BaseConfig.domain);
		} catch (ClientException e) {
			e.printStackTrace();
		}
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(number);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("浙江超仪");
        //必填:短信模板-可在短信控制台中找到
//        request.setTemplateCode("SMS_80190342");
        request.setTemplateCode(BaseConfig.loginModelCode);
      
        request.setTemplateParam("{ \"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse=null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return sendSmsResponse;
    }
}
