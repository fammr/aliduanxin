package joymeter.aliduanxin.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



/**
 * 验证码图片生成工具
 * 
 * @author 王斌
 *
 */
public class ImageCodeCreate {
   /**
    * 返回的map中存放图片和验证码
    * @王斌
    */
	public static Map<String,Object> createImageCode(){
		Map<String,Object> map = new HashMap<String,Object>();
		int width = 60,height = 20 ;  
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB) ;  
          
        Graphics g = image.getGraphics() ;  
          
        //生成随机数  
        Random random = new Random() ;  
        String s = "" ;  
        for(int i=0;i<4;i++){  
            s += random.nextInt(10) ;  
        }  
          map.put("code", s);
        
          
        //随机生成颜色  Color color =  new Color(255,255,255) ;     random.nextInt(256)的值范围是0~255  
        Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)) ;  
          
        //把随机数写到图片上  
        String a = null ;  
        Font font = new Font(a,Font.ITALIC,18) ;  
        g.setColor(color) ;  
        g.setFont(font);  
        g.drawString(s,10,height-5) ;  
        g.dispose() ;    //关闭画笔  
        map.put("image", image);
        return map;
	}
	
}
