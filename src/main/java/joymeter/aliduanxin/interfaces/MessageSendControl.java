package joymeter.aliduanxin.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import joymeter.aliduanxin.common.BaseConfig;
import joymeter.aliduanxin.common.util.MerchantApiUtil;
import joymeter.aliduanxin.service.MessageSendService;
import net.sf.json.JSONObject;
@Controller
@RequestMapping(value = "/joymeter")
public class MessageSendControl {
@Autowired
private MessageSendService messageSendServiceImp;
	 @RequestMapping("/messageSend")
	public void MessageSend(HttpServletRequest request,HttpServletResponse response) {
	        String line = null;
	        StringBuffer jb = new StringBuffer();
	
	        BufferedReader reader = null;
	        try {
	        	 reader = request.getReader();
	           while ((line = reader.readLine()) != null)
	             jb.append(line);
	           
	        } catch (Exception e) { 
	                e.printStackTrace();
	        }
	        System.out.println(jb.toString());
	        try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        JSONObject json =  JSONObject.fromObject(jb.toString());
	        String number = json.getString("number");
	        String code = json.getString("code");
	        String sign = json.getString("sign");
	        String nonceStr = json.getString("nonceStr");
	        String timeStamp = json.getString("timeStamp");
	        Map<String,Object> map = new HashMap<String,Object>();
	        map.put("number", number);
	        map.put("code", code);
	        map.put("nonceStr", nonceStr);
	        map.put("timeStamp", timeStamp);
//	         String sign2 = WxSign.createSign(map, key);
	        String sign2 = MerchantApiUtil.getSign(map, BaseConfig.key); 

	        if(!sign.equals(sign2)) {
	        	json.put("Code", "fail");
	        	json.put("message", "签名错误");
	        }else {
	        
				//调用发送短信的方法并返回sendSmsresponse
	        SendSmsResponse	sendSmsresponse = messageSendServiceImp.sendSms(number, code);

			//将返回结果封装到json发送给调用接口者处理
	        json.put("Code", sendSmsresponse.getCode());
	        json.put("Message", sendSmsresponse.getMessage());
	        json.put("RequestId",sendSmsresponse.getRequestId());
	        json.put("BizId", sendSmsresponse.getBizId());
	        }

			PrintWriter writer=null;
			try {
				writer = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.print(json.toString());
			writer.flush();
			writer.close();

     }
}
