package com.jackiee.data_storage02;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by baigu on 2016/5/11.
 */
public class FirstActivity extends Activity {

    private Button btn_pull;
    private TextView textView;
    private Button btn_json;
    private Button btn_dom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_pull = (Button) findViewById(R.id.btn_pull);
        textView = (TextView) findViewById(R.id.tv);
        btn_json = (Button) findViewById(R.id.btn_json);
        btn_dom = (Button) findViewById(R.id.btn_dom);
        btn_pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(xmlPull().toString());
            }
        });
        btn_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_dom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(xmlDom().toString());
            }
        });
    }

    private List<Student> xmlPull() {
        List<Student> students = null;
        Student student = null;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("student.xml");
            xmlPullParser.setInput(inputStream, "UTF-8");
            for (int eventType = xmlPullParser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = xmlPullParser.next()) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        students = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        String tagName = xmlPullParser.getName();
                        if ("stu".equals(tagName)) {
                            student = new Student();
                            student.setId(Integer.parseInt(xmlPullParser.getAttributeValue(null, "id")));
                        } else if (student != null) {
                            String content = xmlPullParser.nextText();
                            if ("name".equals(tagName)) {
                                student.setName(content);
                            } else if ("gender".equals(tagName)) {
                                student.setGender(Boolean.parseBoolean(content));
                            } else if ("birthday".equals(tagName)) {
                                student.setBirthday(new Date(Long.parseLong(content)));
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String tagName2 = xmlPullParser.getName();
                        if ("stu".equals(tagName2)) {
                            students.add(student);
                            student = null;
                        }
                        break;
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    private List<Student> xmlDom() {

        List<Student> students = new ArrayList<>();
        Student student = null;
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("student.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            NodeList nodeList = document.getElementsByTagName("stu");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                student = new Student();
                student.setId(Integer.parseInt(element.getAttribute("id")));
                NodeList childs = element.getChildNodes();
                for (int j = 0; j < childs.getLength(); j++) {
                    Node node = childs.item(j);
                    if (Node.ELEMENT_NODE == node.getNodeType()) {
                        Element element1 = (Element) node;
                        String nodeName = element1.getNodeName();
                        String content = element1.getTextContent();
                        if ("name".equals(nodeName)) {
                            student.setName(content);
                        } else if ("gender".equals(nodeName)) {
                            student.setGender(Boolean.parseBoolean(content));
                        } else if ("birthday".equals(nodeName)) {
                            student.setBirthday(new Date(Long.parseLong(content)));
                        }
                    }
                }
                students.add(student);

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

