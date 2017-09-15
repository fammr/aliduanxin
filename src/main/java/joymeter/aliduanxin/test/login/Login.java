package joymeter.aliduanxin.test.login;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import joymeter.aliduanxin.common.util.HttpUtil;
import joymeter.aliduanxin.common.util.ImageCodeCreate;
import joymeter.aliduanxin.common.util.MerchantApiUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/test")
public class Login {
	/**
	 *登陆成功返回成功页面，登陆失败返回原来
	 * @param req
	 * @param resp
	 * @param map
	 * @return
	 */
@RequestMapping("/login.action")
public String login(HttpServletRequest req,HttpServletRequest resp,Map<String,Object> map) {
    String code = req.getParameter("code");

    System.out.println(req.getSession().getAttribute("code"));
    if(code.equals(req.getSession().getAttribute("code"))) {
    	HttpSession session = req.getSession();
    	//登陆成功后将code从session中清除
    	session.removeAttribute("code");
    	System.out.println("登陆成功");
    	return "success";
    }
    else {
    	System.out.println("登陆失败");
    	return "redirect:/test/index.action";
    }
	
}
/**
 * 生成验证码并发送给手机，将code存到session中
 * @param req
 * @param resp
 * @throws IOException
 */
@RequestMapping("/codeCreate.action")
public void imageCodeCreate(HttpServletRequest req,HttpServletResponse resp) throws IOException {
	
	String key = "123456789";
	String code = (String) ImageCodeCreate.createImageCode().get("code");
	String number=req.getParameter("number");
    String url = "http://localhost:8080/aliduanxin/joymeter/messageSend";
    JSONObject json = new JSONObject();
    json.put("number", number);
    json.put("code", code);
    json.put("nonceStr", MerchantApiUtil.getNonceStr());
    json.put("timeStamp", MerchantApiUtil.getTimeStamp());
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("number", number);
    map.put("code", code);
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
//    if(json.getString("Code").equals("OK")) {
    	HttpSession session = req.getSession();
    	session.setAttribute("code", code);
//        System.out.println(123);
//		}
}
/**
 * 清楚session中的某个属性
 * @param req
 * @param resp
 */
@RequestMapping("/sessionRemove.action")
public void sessionRemove(HttpServletRequest req,HttpServletResponse resp) {
	String codeName = req.getParameter("codeName");
	req.getSession().removeAttribute(codeName);
	
}
@RequestMapping("/index.action")
public String index() {
	return "login";
}
}
