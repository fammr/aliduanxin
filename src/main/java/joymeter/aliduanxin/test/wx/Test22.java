package joymeter.aliduanxin.test.wx;

import joymeter.aliduanxin.common.util.HttpUtil;
import net.sf.json.JSONObject;

public class Test22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("meterNo", "0000");
		json.put("balance", "100");
		json.put("access_token", "joy000001");
		System.out.println(json);
		json = HttpUtil.httpRequest("http://localhost/ZuoQuan_WeChat/api/unbalance", "POST", json.toString());
		System.out.println(json);
	}

}
