/**    
 * 文件名：Test3.java    
 *    
 * 版本信息：    
 * 日期：2017年11月2日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package joymeter.aliduanxin.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：aliduanxin 类名称：Test3 类描述： 创建人：王斌 创建时间：2017年11月2日 下午3:03:04 修改人：王斌
 * 修改时间：2017年11月2日 下午3:03:04 修改备注：
 * 
 * @version
 */
public class Test3 {

    /**
     * @throws InterruptedException
     * @方法名: main
     * @方法描述:
     * @参数说明: @param args
     * @返回类型: void
     * @throws:
     * @作者:王斌
     * @时间:2017年11月2日 下午3:03:04
     */
    public static List<String> remove(List<String> list) {

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (str.equals("")) {
                list.remove(i);
                list = remove(list);
            }

        }
        return list;

    }

    public static void main(String[] args) throws InterruptedException {
        // for (int i = 0; i < 10; i++) {
        // System.out.println(i);
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println(sdf.format(new Date()));
        // Thread.currentThread().sleep(2000);
        // if (i == 3)
        // break;
        // }
        List<String> list = new ArrayList<String>();
        list.add("123");
        list.add("");
        list.add("werw");
        list.add("");
        list.add("sdaf");
        System.out.println(remove(list));
    }

}
