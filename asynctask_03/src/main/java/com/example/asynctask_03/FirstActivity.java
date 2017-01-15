package com.example.asynctask_03;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by baigu on 2016/5/16.
 */
public class FirstActivity extends Activity {

    private Button btn_dom;
    private Button btn_sax;
    private Button btn_pull;
    private Button btn_xml;
    private Button btn_json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_dom = (Button) findViewById(R.id.btn_dom);
        btn_sax = (Button) findViewById(R.id.btn_SAX);
        btn_pull = (Button) findViewById(R.id.btn_PULL);
        btn_xml = (Button) findViewById(R.id.btn_XML);
        btn_json = (Button)findViewById(R.id.btn_json);

        btn_dom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = xmlDom().toString();
                Toast.makeText(FirstActivity.this, string, Toast.LENGTH_SHORT).show();
            }
        });

        btn_sax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, xmlSax().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, xmlPull().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        btn_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, jsonOut().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Student> xmlDom() {

        InputStream inputStream = null;
        List<Student> students = new ArrayList<Student>();

        try {
            inputStream = getAssets().open("students.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            NodeList nodeList = document.getElementsByTagName("stu");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Student student = new Student();
                Element element = (Element) nodeList.item(i);
                String id = element.getAttribute("id");
                //Toast.makeText(FirstActivity.this, "id = " + id, Toast.LENGTH_SHORT).show();
                student.setId(Integer.parseInt(id));

                for (int j = 0; j < element.getChildNodes().getLength(); j++) {
                    Node node = element.getChildNodes().item(j);
                    if (Node.ELEMENT_NODE == node.getNodeType()) {
                        Element ele = (Element) node;
                        if ("name".equals(ele.getNodeName())) {
                            String content = ele.getTextContent();
                            student.setName(content);
                        } else if ("gender".equals(ele.getNodeName())) {
                            student.setGender(Boolean.parseBoolean(ele.getTextContent()));
                        } else if ("birthday".equals(ele.getNodeName())) {
                            student.setBirthday(new Date(Long.parseLong(ele.getTextContent())));
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

    private List<Student> xmlSax() {

        List<Student> students = null;
        InputStream inputStream = null;


        try {
            inputStream = getAssets().open("students.xml");
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            MyHandler myHandler = new MyHandler();
            saxParser.parse(inputStream, myHandler);
            students = myHandler.getStudents();
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

    private List<Student> xmlPull() {
        List<Student> students = null;
        Student student = null;
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("students.xml");
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(inputStream, "UTF-8");
            for (int eventType = xmlPullParser.getEventType(); (eventType != XmlPullParser.END_DOCUMENT); eventType = xmlPullParser.next()) {
                String tagName = xmlPullParser.getName();

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        students = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("stu".equals(tagName)) {
                            student = new Student();
                            String id = xmlPullParser.getAttributeValue(null, "id");
                            student.setId(Integer.parseInt(id));
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
                        if ("stu".equals(tagName)) {
                            students.add(student);
                            student = null;
                        }
                        break;
                    default:
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

    private List<Book> jsonOut(){
        List<Book> books = new ArrayList<>();
        Book book = null;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(getAssets().open("bood.json").toString());
            for (int i = 0 ; i < jsonArray.length(); i ++){
                book = new Book();
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                book.setId(Integer.parseInt(jsonObject.optString("id")));
                book.setName(jsonObject.optString("name"));
                book.setVersion(Float.parseFloat(jsonObject.optString("version")));
                books.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}


class MyHandler extends DefaultHandler {

    private List<Student> students;
    private Student student;
    private String tagName;

    public List<Student> getStudents() {
        return students;

    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        students = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if ("stu".equals(localName)) {
            students.add(student);
            student = null;
        }
        tagName = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("stu".equals(localName)) {
            student = new Student();
            String id = attributes.getValue("", "id");
            student.setId(Integer.parseInt(id));
        }
        tagName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String content = new String(ch, start, length);
        if ("name".equals(tagName)) {
            student.setName(content);
        } else if ("gender".equals(tagName)) {
            student.setGender(Boolean.parseBoolean(content));
        } else if ("birthday".equals(tagName)) {
            student.setBirthday(new Date(Long.parseLong(content)));
        }
    }
}