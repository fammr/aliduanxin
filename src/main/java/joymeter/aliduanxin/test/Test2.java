/**    
 * 文件名：Test2.java    
 *    
 * 版本信息：    
 * 日期：2017年11月2日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package joymeter.aliduanxin.test;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import joymeter.aliduanxin.common.util.RSACoder;

/**
 * 项目名称：aliduanxin 类名称：Test2 类描述： 创建人：王斌 创建时间：2017年11月2日 下午1:24:51 修改人：王斌
 * 修改时间：2017年11月2日 下午1:24:51 修改备注：
 * 
 * @version
 */
@Controller
public class Test2 {

    /**
     * @throws Exception
     * @方法名: main
     * @方法描述:
     * @参数说明: @param args
     * @返回类型: void
     * @throws:
     * @作者:王斌
     * @时间:2017年11月2日 下午1:24:51
     */

    @RequestMapping("dotest")
    public String dotest(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> map) throws Exception {
        String cjoyId = "2";
        String accountNo = req.getParameter("accountNo");
        String stype = req.getParameter("stype");
        String money = req.getParameter("money");
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI3kxfQR5Z+5uuJzsVI4U6gJDkOtfBc3aQLRALbsrbRaYQgT1R87xxguanayqPus917hAnXi0p3l8c4akXj8SoUCAwEAAQ==";
        String inputStr = String.format("{'client_id':'joy000001','datetime':'%s'}",
                new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()));

        byte[] data = inputStr.getBytes();
        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);
        String access_token = RSACoder.encryptBASE64(encodedData);
        // String time = String.valueOf(System.currentTimeMillis());
        String returnUrl = "http://www.baidu.com";
        map.put("cjoyId", cjoyId);
        map.put("accountNo", accountNo);
        map.put("money", money);
        map.put("access_token", access_token);
        map.put("stype", stype);
        map.put("returnUrl", returnUrl);
        // String url =
        // "http://localhost/wechat_manager/account/recharge?access_token=" +
        // access_token + "&&total_fee="
        // + total_fee + "&&accountNo=" + accountNo + "&&style=" + style +
        // "&&time=" + time + "&&cjoyId=" + cjoyId;
        // HttpUtil.httpRequest(url, "POST");
        return "wechat2";
    }

    public static void main(String[] args) throws Exception {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI3kxfQR5Z+5uuJzsVI4U6gJDkOtfBc3aQLRALbsrbRaYQgT1R87xxguanayqPus917hAnXi0p3l8c4akXj8SoUCAwEAAQ==";
        String sskey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCXHkA2wR1qHd3dRXbQV5lHMaLufv+K7rzONaMXvX5TGkey8uT/mAb/GBBM2SCTmiA0t5J52SqWEEf39kzvVkMT1G35KHwALiMXVhrOWSEhqMqPDMaI3WGz41VYgBAcJwtBbE++XVEX+sDRHlY5tibh4uIei3slxtjqjIQY9SHpFJYDu6LQL9ZQ1FPEjZc7sPGx2y5qvn0KfU5yHavqV3b1LOkqzK/cB0k4XAoEWdlyTY1sqoCo4oM2bgjPuIjKsqHWg8d6kTbI7eGUf6VFwsGPot6FKQMowi+ziE8JF2fhWaBDlJzs9mO7Fmlm1BjoZKSVaP0YHBiLb+LdJ2HJJaJ/AgMBAAECggEAfZvNcDWwh2Xv5l441hAZ/c1KSscRDaBHG3gF6lO9zsGHWzz+D12EY0DTO602oLl5j5gOjm1uMnGyAG+fn0iqVK88AeAewd+Xl2wgt+fp3Bp609CgrV6mOWn6pKc9vMjPWD/5QJg8MZxh7PA0Z5/6TcPSg+UYJVxVLRBDNKN83kjPSzukmAriM1AUi7SvSvsQf3qqCnV9Re3CKWmqbgUwkNPWj06nZqa/WP+B4vVQk+DIvE299afiqDbv7+5psa4l1pkEwbY2PDWo9PKoIQr1gFyU06vIjOnUasbAqm3/DTCF1GpB36AW5WIADKEtlLId6aGpw8OK0udoReh1THvoYQKBgQDbLqR1JC4mELkvDWRKdrSbd0oQNzPLhStTs8exeiSxytIvw/fw5tJtvHDZmz5gsOAbTreFGmfmgtarSxEqGC20ipFw+iPx24bNH28AVehaWNQADJ7d52trd9Zfe1cidTKxQW2tYn3Mg884wvbKvVq4eWAHTwyyGy+BrtyB1yC4JwKBgQCwgLFU9gt4v57ju2AtAwukDL3LJ/R9VyNg/7dJYbwuNVcDtsq2wXuyjX2jzLZI0xdZJe+gP5Dbp8m+PS6rg8djrsXbBhqdaEkQQMoqacpWd+LftdindyV0B2Jd2sXICz2/4wzrAHJ9ATEwjXOYEqjDHyq29LZXkTQGGTnWkfch6QKBgGvnYn9o+G80/HX1mdIu7ms7JvMkuhMP1Gy4rS7qevYsa52oVH/8osvzYXs/uk3PxgSrlB2rCkupThW2NxUKIjYnocKE4v1Gne6N8w+vO1x/Lp93lx6Xt4KI1Ljc9+C24eXx2BTr0BLHMVU4ZlcZMXhwCJ6QhCeKyrCFbXled3UxAoGAWHRmarnUDfOaYW7qSCSalOB9zW58rMQayuXcqT393PqGXSoidWyJ3XXjXU+emuiPbvIAIUM6CZsRE/H9jZ9BMcfJCHcKp5A37ZlKt+7EpIj4fjzYEzU24+s/dOHK9PINRDk5pBxZRCRwznBVWgUf5omcEoyZRVFTzSqfPdVxEOECgYAZROSbcF4aHVf+C61cnHWsyWzY9hTDlOOLr8ssQnET4jfMRWeGTK6Rs9a5UUtwcBb4pKtSip6BIEZXQcl2td5B7S8A/9nz51J01IsToHEL8rT1MB0J249V9vRMrtxWnScw2bHTY0b8jKm95zErf+59WsjorSGBStX9bdHD3gnKmQ==";
        String ssskey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiAu1rVZiAh8I778mHkbvclJaUQrQ6NRHvgm0buzK2rTr11G7QVdJVRwO1tvRRtLBSXWpylXcz4X7YmyHnqLH8QmlUrFVterhSmNbaICQ0RU+3vLQUPg8/+7CHq4Ql0wot6HPkH3hh9oKiXdqzm/vtefw0TurB6hoQxAqbHz+A+K8VYzgrh8r7LErAtheuEq8bWK2X6w229CAH0GKbT5PvsOso+xeEDw9y8cDJxMAO68/SoDOlIewJrhI/QyhhaYjNamskS/bEmb6dgUrsYXvvpNpC7tq1vbcHaqupVTTSRjcljcanPJOqGFZlTMzWUMQdwTq/g2VPCCylhYtjmKEVQIDAQAB";
        String inputStr = String.format("{'client_id':'joy000001','datetime':'%s'}",
                new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()));

        byte[] data = inputStr.getBytes();
        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);
        String access_token = RSACoder.encryptBASE64(encodedData);
        System.out.println(access_token);
        String accountNo = "112233";
        String style = "OFFPAY";
        String styles = "CWM";
        String total_fee = "5";
        String name = "";
        String address = "杭州市西湖区";
        String tel = "18720057281";
        String time = String.valueOf(System.currentTimeMillis());
        String url = "http://zq.qingcailuobo.cn/ZuoQuan_WeChat/wechat/account/getBalance?access_token=" + access_token
                + "&&accountNo=" + accountNo;
        // time = "sdf";
        // String url =
        // "http://localhost/ZuoQuan_WeChat/wechat/account/create?access_token="
        // + access_token + "&&name="
        // + name + "&&address=" + address + "&&tel=" + tel + "&&accountNo=" +
        // accountNo;
        // String url =
        // "http://localhost/ZuoQuan_WeChat/wechat/account/recharge?access_token="
        // + access_token
        // + "&&accountNo=" + accountNo + "&&style=" + style + "&&total_fee=" +
        // total_fee + "&&time=" + time;
        // String url =
        // "http://zq.qingcailuobo.cn/ZuoQuan_WeChat/wechat/account/payment?access_token="
        // + access_token
        // + "&&accountNo=" + accountNo + "&&style=" + styles + "&&total_fee=" +
        // total_fee + "&&time=" + time;
        // String url =
        // "http://zq.qingcailuobo.cn/ZuoQuan_WeChat/wechat/account/notice?access_token="
        // + access_token
        // + "&&accountNo=" + accountNo + "&&time=" + time;
        // System.out.println(HttpUtil.httpRequest(url, "POST"));
        System.out.println(System.currentTimeMillis());
        System.out.println(sskey.length());
        System.out.println(ssskey.length());
    }

}
