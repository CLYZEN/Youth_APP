package com.example.youthapp.CultureModel;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Path;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.ArrayList;
import java.util.List;
@Xml(name = "styurls")
public class Styurls {


    @PropertyElement(name = "styurl")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //    @PropertyElement(name = "styurl")
//    private String styurl;    //styurls로 반환
//
//    public String getStyurl() {
//        return styurl;
//    }
//
//    public void setStyurl(String styurl) {
//        this.styurl = styurl;
//    }





    //    private List<styurls> styurls = new ArrayList<styurls>();
//
//    public List<styurls> getstyurls() {
//        return styurls;
//    }
//
//    public void setstyurls(List<styurls> styurls) {
//        this.styurls = styurls;
//    }
}