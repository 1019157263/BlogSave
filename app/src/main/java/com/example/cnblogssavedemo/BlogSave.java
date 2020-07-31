package com.example.cnblogssavedemo;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BlogSave {


    String INDEX="https://www.cnblogs.com/ksxh/p/13407783.html";

    String COOKIE="_ga=GA1.2.1336682018.1579423832; __gads=ID=c7d07240001f7cc8:T=1579423831:S=ALNI_MZyzgABwW6JmdUS6oYTtW-vgiQnfg; _gid=GA1.2.602734631.1596157181; .Cnblogs.AspNetCore.Cookies=CfDJ8K5MrGQfPjpFvRyctF-QEQdOe8ljh05_s7eS4rHciONwGEHrjFBMXhsXTI5_XBY2qfvNgUP1q_j-ovwIeBI1Gr64zUojv7OG8MKTAm9MreWVsjYGyqi-8i3bgq8wBYev8nRPzpB8lakR_JimN8j8i4mm5ieR_ZNZ7k9w68c6s5tHglu1Og-4jUlt0WEYJZHl6Mq0dtsyuPd66NyEhoD0Xi07ZQdZUQeckaETmI5rLeWa2YmFNfdj83gaAEkPtpy8jdwlCT7v1vPrQFvL8AlNTOHHqJGfudl5Y1w1inxAy9URZDpSa5gJGwu894fY8dEh2f04KQq6j4IvL4arRWKhZVD2OHMlccSocZniOlOMmFNLQxdHL6QtE8XtXfZFwU1_uC3s4qmSr9UyBoTE49ZGlImz_MblqbaPvQLMtaISaShpaop-T5PllDW7CvTo_ZSThQL-ZS27BFOSpP6y-1PsjRuB7cHC1YfGJm_lmF9AQHC8Tfa_WJggPn731LrIbZA5buScRRILT6qC2gkEmSIQT5bg7KZFBKOjLyVlWy2nYX4iniiYOYANRyq5zTiFm4D3mA; .CNBlogsCookie=70787F940761A32224E96E44F8910A0103BF53A88A7B25D99A427E7026FC64C884362DE9498C4C307542213942E970E76E544505172FAE89B59A98E0900235959AE45D861CFC21DD1B3625D1980CC5FBF350715C; _gat_gtag_UA_48445196_1=1";
    String x_blog_id="350087";

    public String get(){

        System.out.println("博客园界面白嫖技术");
        OkHttpClient client=new OkHttpClient();
        Request req= new Request.Builder()
                .url(INDEX)
                .get()
                .build();

        Response response= null;
        try {
            response = client.newCall(req).execute();
            final String str=response.body().string();
//            //System.out.println(str);


            String html = str;
            JXDocument underTest = JXDocument.create(html);
            String xpath = "//*[@id=\"cnblogs_post_body\"]/text()";
            JXNode node = underTest.selNOne(xpath);
            System.out.println(node.toString());
            return node.toString().replace("|换行|","\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }

    private static String getValueByXpath(String xPath, String html) {
        TagNode tagNode = new HtmlCleaner().clean(html);
        String value = null;
        try {
            Document doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
            XPath xpath = XPathFactory.newInstance().newXPath();
            value = (String) xpath.evaluate(xPath, doc, XPathConstants.STRING);
        } catch (Exception e) {
            //System.out.println("Extract value error. " + e.getMessage());
            e.printStackTrace();
        }
        return value;
    }


    public void push(String DATA){

        String API_Url="https://i.cnblogs.com/api/posts";
        OkHttpClient client=new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {

            json.put("id", "13407783");
            json.put("postType", "1");
            json.put("accessPermission", "0");
            json.put("title", "小行星");
            json.put("url", "//www.cnblogs.com/ksxh/p/13407783.html");
            json.put("postBody", DATA);
            json.put("categoryIds", "[]");
            json.put("inSiteCandidate", "False");
            json.put("inSiteHome", "False");
            json.put("siteCategoryId", "None");
            json.put("blogTeamIds", "[]");
            json.put("isPublished", "True");
            json.put("displayOnHomePage", "True");
            json.put("isAllowComments", "True");
            json.put("includeInMainSyndication", "True");
            json.put("isPinned", "False");
            json.put("isOnlyForRegisterUser", "False");
            json.put("isUpdateDateAdded", "False");
            json.put("entryName", "None");
            json.put("description", "''");
            json.put("tags", "[]");
            json.put("password", "None");
            json.put("datePublished", "2020-07-31T01:01:00.000Z");
            json.put("isMarkdown", "False");
            json.put("isDraft", "False");
            json.put("autoDesc", "大行星2");
            json.put("changePostType", "False");
            json.put("blogId", "350087");
            json.put("author", "sunny开始学坏");
            json.put("removeScript", "False");
            json.put("ip", "None");
            json.put("changeCreatedTime", "False");
            json.put("canChangeCreatedTime", "False");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        String j=String.valueOf(json).replace("\"None\""," null")
                .replace("\"False\"","false")
                .replace("\"True\"","true")
                .replace("\"[]\"","[]")
//                .replace("\\","\"\"")
//                .replaceAll("\\\\","")
                .replaceAll("\"([0-9]*)\"","$1");

        //System.out.println("+\n");
        //System.out.println(j);
        //System.out.println("+\n");
        RequestBody requestBody = RequestBody.create(JSON, j);


        Request req= new Request.Builder()
                .addHeader("content-type","application/json")
                .addHeader("cookie",COOKIE)
                .addHeader("x-blog-id",x_blog_id)
                .url(API_Url)
                .post(requestBody)
                .build();


        Response response;

        {
            try {
                response = client.newCall(req).execute();
                final String str=response.body().string();
                //System.out.println(str);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }


    }
//        String url="http://127.0.0.1:8888/admin";



