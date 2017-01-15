package com.jackie.data_storage05.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jackie.data_storage05.R;
import com.jackie.data_storage05.entity.Student;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class MainActivity extends Activity {

    private TextView textView;
    private EditText editText;
    private Button button_SDR;
    private Button button_SDW;
    private Button button_pull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);
        editText = (EditText) findViewById(R.id.et);
        button_SDR = (Button) findViewById(R.id.btn_sdR);
        button_SDW = (Button) findViewById(R.id.btn_sdW);
        button_pull = (Button) findViewById(R.id.btn_pull);

        button_pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //usePull();
                //textView.setText(getResources().getString(R.xml.student));
            }
        });


    }

    private List<Student> usePull() {
        //得到资源
        List<Student> students = null;
        Student student = null;
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;

        try {
            //inputStream = assetManager.open("student.xml");

            Toast.makeText(MainActivity.this, getResources().getXml(R.xml.student).getText(), Toast.LENGTH_SHORT).show();
            XmlPullParser xmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParser.setInput(inputStream, "UTF-8");
            for (int eventType = xmlPullParser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = xmlPullParser.next()) {

                String tagName = xmlPullParser.getName();

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        students = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        if (tagName.equals("student")) {
                            student = new Student();
                            String name = xmlPullParser.getAttributeValue("", "name");
                            String gender = xmlPullParser.getAttributeValue("", "gender");
                            String id = xmlPullParser.getAttributeValue("", "id");
                            student.setName(name);
                            student.setGender(gender);
                            student.setId(Integer.parseInt(id));
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (tagName.equals("student")) {
                            String content = xmlPullParser.getText();
                            student.setContent(content);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        students.add(student);
                        student = null;
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        Toast.makeText(MainActivity.this, "解析完成！", Toast.LENGTH_SHORT).show();
                        break;
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }
}

