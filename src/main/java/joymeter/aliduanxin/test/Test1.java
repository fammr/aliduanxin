package joymeter.aliduanxin.test;

import java.util.HashMap;
import java.util.Map;

import joymeter.aliduanxin.common.util.HttpUtil;
import joymeter.aliduanxin.common.util.MerchantApiUtil;
import net.sf.json.JSONObject;
/**
 * 实现发送短信的小demo
 * 需要参数：
 * number:电话号码
 * code:验证码
 * nonceStr:随机字符串
 * timeStamp:时间戳
 * key:密钥
 * 将以上参数进行字典序排序
 * 将所有参数字符串拼接成一个字符串进行MD5加密得到sign
 * 将number、code、nonceStr、timeStamp、sign数据通过json格式提交给请求url
 * 将返回一个json格式的数据，通过json中的Code和message判断是否成功
 * 
 * 
 * 
 */

public class Test1 {
	   private  static String key="123456789"; //模拟key值

			public static void main(String[] args) {
		
				   //请求的URL地址
		            String url = "http://localhost:8080/aliduanxin/joymeter/messageSend";
//		            String url="http://localhost:8080/aliduanxin/joymeter/messageSend";
		            JSONObject json = new JSONObject();
		            json.put("number", "18720057281");
		            json.put("code", "0000");
		            json.put("nonceStr", MerchantApiUtil.getNonceStr());
		            json.put("timeStamp", MerchantApiUtil.getTimeStamp());
		            Map<String,Object> map = new HashMap<String,Object>();
		            map.put("number", "18720057281");
		            map.put("code", "0000");
		            //随机字符串
		            map.put("nonceStr", json.getString("nonceStr"));
		            //时间戳
		            map.put("timeStamp", json.getString("timeStamp"));
		            //生成签名

		            String sign = MerchantApiUtil.getSign(map, key);
		            json.put("sign", sign);

		            String outStr = json.toString();
		            json=  HttpUtil.httpRequest(url, "POST",outStr);
			        System.out.println(json);
			   
		}
			}
