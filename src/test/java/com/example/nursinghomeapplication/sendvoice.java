package com.example.nursinghomeapplication;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/4/6
 * \* 时间: 9:00
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;



//接口类型：互亿无线语音通知接口。
//账户注册：请通过该地址开通账户http://user.ihuyi.com/register.html
//注意事项：
//（1）调试期间，请仔细阅读接口文档；
//（2）请使用APIID（查看APIID请登录用户中心->语音通知->帐户及签名设置->APIID）及 APIkey来调用接口；
//（3）该代码仅供接入互亿无线语音通知接口参考使用，客户可根据实际需要自行编写；

public class sendvoice {

    private static String Url = "http://api.vm.ihuyi.com/webservice/voice.php?method=Submit";

    public static void main(String [] args) {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        //client.getParams().setContentCharset("GBK");
        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "VM72158349"),//用户名是登录用户中心->语音通知->帐户参数设置->APIID
                new NameValuePair("password", "78492f3b3cfc455a06bf3b7e5b653cd9"),//查看密码请登录用户中心->语音通知->帐户参数设置->APIKEY
                new NameValuePair("mobile", "15290698853"),//手机号码
                new NameValuePair("content", "您的订单号是：0648。已由顺风快递发出，请注意查收。"),
        };

        method.setRequestBody(data);

        try {
            client.executeMethod(method);

            String SubmitResult = method.getResponseBodyAsString();

            //System.out.println(SubmitResult);

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String voiceid = root.elementText("voiceid");

            System.out.println(code);
            System.out.println(msg);
            System.out.println(voiceid);

            if("2".equals(code)){
                System.out.println("短信提交成功");
            }

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
