package com.example.youthapp.CultureModel;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "db")
public class ShowList {
    @PropertyElement
    private String prfpdfrom;
    @PropertyElement
    private String fcltynm;
    @PropertyElement
    private String openrun;
    @PropertyElement
    private String prfpdto;
    @PropertyElement
    private String mt20id;
    @PropertyElement
    private String prfnm;
    @PropertyElement
    private String genrenm;
    @PropertyElement
    private String poster;
    @PropertyElement
    private String prfstate;



    public String getPrfpdfrom() {
        return prfpdfrom;
    }
    public void setPrfpdfrom(String prfpdfrom) {
        this.prfpdfrom = prfpdfrom;
    }
    public String getFcltynm() {
        return fcltynm;
    }
    public void setFcltynm(String fcltynm) {
        this.fcltynm = fcltynm;
    }
    public String getOpenrun() {
        return openrun;
    }
    public void setOpenrun(String openrun) {
        this.openrun = openrun;
    }
    public String getPrfpdto() {
        return prfpdto;
    }
    public void setPrfpdto(String prfpdto) {
        this.prfpdto = prfpdto;
    }
    public String getMt20id() {
        return mt20id;
    }
    public void setMt20id(String mt20id) {
        this.mt20id = mt20id;
    }
    public String getPrfnm() {
        return prfnm;
    }
    public void setPrfnm(String prfnm) {
        this.prfnm = prfnm;
    }
    public String getGenrenm() {
        return genrenm;
    }
    public void setGenrenm(String genrenm) {
        this.genrenm = genrenm;
    }
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getPrfstate() {
        return prfstate;
    }
    public void setPrfstate(String prfstate) {
        this.prfstate = prfstate;
    }
}