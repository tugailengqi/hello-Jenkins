package com.lengqi.controller;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class html {
    public static void main(String[] args) throws IOException {
        // 直接从字符串中输入 HTML 文档
        String html = "<html><head><title> 开源中国社区 </title></head>"
                + "<body><p> 这里是 jsoup 项目的相关文章 </p></body></html>";
        Document doc = Jsoup.parse(html);

        // 从 URL 直接加载 HTML 文档
        Document doc1 = Jsoup.connect("http://www.oschina.net/").get();
        String title = doc.title();

        Document doc2 = Jsoup.connect("http://www.oschina.net/")
                .data("query", "Java")   // 请求参数
                .userAgent("I ’ m jsoup") // 设置 User-Agent
                .cookie("auth", "token") // 设置 cookie
                .timeout(3000)           // 设置连接超时时间
                .post();                 // 使用 POST 方法访问 URL

        // 从文件中加载 HTML 文档
//        File input = new File("D:/test.html");
//        Document doc = Jsoup.parse(input,"UTF-8","http://www.oschina.net/");
//获取HTML页面的Fav图标
        System.out.println("获取HTML页面的Fav图标");
        String favImage = "Not Found";
        Element element = doc1.head().select("link[href~=.*\\.(ico|png)]").first();
        if (element == null) {
            element = doc1.head().select("meta[itemprop=image]").first();
            if (element != null) {
                favImage = element.attr("content");
            }
        } else {
            favImage = element.attr("href");
        }
        System.out.println(favImage);
        System.out.println("获取HTML页面的Fav图标");
//获取HTML页面中的所有链接
        System.out.println("获取HTML页面中的所有链接");
        Elements links = doc1.select("a[href]");
        for (Element link : links) {
            System.out.println("link : " + link.attr("href"));
            System.out.println("text : " + link.text());
        }
        System.out.println("获取HTML页面中的所有链接");

//解析获取图片
        System.out.println("解析获取图片");
        Elements images = doc1.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        for (Element image : images) {
            System.out.println("src : " + image.attr("src"));
            System.out.println("height : " + image.attr("height"));
            System.out.println("width : " + image.attr("width"));
            System.out.println("alt : " + image.attr("alt"));
        }
        System.out.println("解析获取图片");
// 获取URL的元信息
        System.out.println("获取URL的元信息");
        String description = doc1.select("meta[name=description]").get(0).attr("content");
        System.out.println("Meta description : " + description);

        String keywords = doc1.select("meta[name=keywords]").first().attr("content");
        System.out.println("Meta keyword : " + keywords);
        System.out.println("获取URL的元信息");
//在HTML页面中获取表单属性
        System.out.println("在HTML页面中获取表单属性");
        Element formElement = doc1.getElementById("loginForm");

        Elements inputElements = formElement.getElementsByTag("input");
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");
            System.out.println("Param name: " + key + " \nParam value: " + value);
        }

        System.out.println("在HTML页面中获取表单属性");


    }
}