package com.jackiee.net_02;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by baigu on 2016/5/12.
 */
public class HttpHelper {

    public static HttpClient httpClient;

    static {
        httpClient = new DefaultHttpClient();
    }

    /**
     * 构造一个httpclient的get方法
     */
    public static String getWeb(String string) {
        String str = "";
        HttpGet httpGet = new HttpGet(string);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                str = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
