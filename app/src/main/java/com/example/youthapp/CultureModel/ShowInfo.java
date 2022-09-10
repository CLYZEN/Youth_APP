package com.example.youthapp.CultureModel;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Path;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;


@Xml(name = "db")
public class ShowInfo {
    @PropertyElement
    private String prfpdfrom;
    @PropertyElement
    private String fcltynm;




    @Element(name = "styurls")
    private Styurls styurls;


    @PropertyElement
    private String prfpdto;
    @PropertyElement
    private String pcseguidance;
    @PropertyElement
    private String dtguidance;
    @PropertyElement
    private String prfcrew;
    @PropertyElement
    private String prfcast;
    @PropertyElement
    private String prfage;
    @PropertyElement
    private String prfstate;
    @PropertyElement
    private String prfruntime;
    @PropertyElement
    private String openrun;
    @PropertyElement
    private String entrpsnm;
    @PropertyElement
    private String mt10id;
    @PropertyElement
    private String sty;
    @PropertyElement
    private String mt20id;
    @PropertyElement
    private String prfnm;
    @PropertyElement
    private String genrenm;
    @PropertyElement
    private String poster;


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

    //imageUrl
    public Styurls getStyurls() {
        return styurls;
    }

    public void setStyurls(Styurls styurls) {
        this.styurls = styurls;
    }


    public String getPrfpdto() {
        return prfpdto;
    }
    public void setPrfpdto(String prfpdto) {
        this.prfpdto = prfpdto;
    }
    public String getPcseguidance() {
        return pcseguidance;
    }
    public void setPcseguidance(String pcseguidance) {
        this.pcseguidance = pcseguidance;
    }
    public String getDtguidance() {
        return dtguidance;
    }
    public void setDtguidance(String dtguidance) {
        this.dtguidance = dtguidance;
    }
    public String getPrfcrew() {
        return prfcrew;
    }
    public void setPrfcrew(String prfcrew) {
        this.prfcrew = prfcrew;
    }
    public String getPrfcast() {
        return prfcast;
    }
    public void setPrfcast(String prfcast) {
        this.prfcast = prfcast;
    }
    public String getPrfage() {
        return prfage;
    }
    public void setPrfage(String prfage) {
        this.prfage = prfage;
    }
    public String getPrfstate() {
        return prfstate;
    }
    public void setPrfstate(String prfstate) {
        this.prfstate = prfstate;
    }
    public String getPrfruntime() {
        return prfruntime;
    }
    public void setPrfruntime(String prfruntime) {
        this.prfruntime = prfruntime;
    }
    public String getOpenrun() {
        return openrun;
    }
    public void setOpenrun(String openrun) {
        this.openrun = openrun;
    }
    public String getEntrpsnm() {
        return entrpsnm;
    }
    public void setEntrpsnm(String entrpsnm) {
        this.entrpsnm = entrpsnm;
    }
    public String getMt10id() {
        return mt10id;
    }
    public void setMt10id(String mt10id) {
        this.mt10id = mt10id;
    }
    public String getSty() {
        return sty;
    }
    public void setSty(String sty) {
        this.sty = sty;
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
}
