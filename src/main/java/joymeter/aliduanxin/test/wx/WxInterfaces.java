package joymeter.aliduanxin.test.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/joymeter")
public class WxInterfaces {
	@RequestMapping("/wxdata")
	public void getResult(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("result"));
		System.out.println(request.getParameter("longitude"));
		System.out.println(request.getParameter("latitude"));
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print("ok");
		writer.flush();
		writer.close();
	}
	@RequestMapping("/getBaseStations")
	public void getBaseStation(HttpServletRequest request,HttpServletResponse response) {
		System.out.println(request.getParameter("longitude"));
		System.out.println(request.getParameter("latitude"));
		JSONObject json = new JSONObject();
        List<JSONObject> list = new ArrayList<>();
    	PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int length=5;
        for (int i = 0; i < length; i++) {
			JSONObject json2 = new JSONObject();
			json2.put("id", i);
			json2.put("name","基站"+i+"号");
			json2.put("longitude", "120.155"+i+"67");
			json2.put("latitude", "30.2740"+i+"98");

			list.add(json2);
			json.put("json", json2);
		}
  
        writer.print(list.toString());
		writer.flush();
		writer.close();
		
	}
}
