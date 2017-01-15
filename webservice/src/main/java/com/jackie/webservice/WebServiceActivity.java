package com.jackie.webservice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Administrator on 2016/12/4.
 */
public class WebServiceActivity extends Activity {

    private Button button;
    private TextView textView;

    private static final String targetNameSpace = "http://WebXml.com.cn/";
    private static final String WSDL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
    private static final String getSupportProvince = "getSupportProvince";
    private static final String getSupportCity = "getSupportCity";
    private static final String getWeatherbyCityName = "getWeatherbyCityName";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 44) {
                String str = (String) msg.obj;
                textView.setText(str);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webservice);
        button = (Button) findViewById(R.id.btn);
        textView = (TextView)findViewById(R.id.tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 44;
                        message.obj = invokeWebService();
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }


    private String invokeWebService() {

        String string = null;
        //step1
        HttpTransportSE ht = new HttpTransportSE(WSDL);
        ht.debug = true;
        //step2
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //step3
        SoapObject soapObject = new SoapObject(targetNameSpace, getWeatherbyCityName);
        //step4
        soapObject.addProperty("theCityName", "深圳");
        //step5
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        //step6
        try {
            ht.call(targetNameSpace + getWeatherbyCityName, envelope);
            if (envelope.getResponse() != null) {
                //step7
                SoapObject result = (SoapObject) envelope.getResponse();
                string = result.getProperty(10).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return string;
    }
}
