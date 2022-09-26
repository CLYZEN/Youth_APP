package com.example.youthapp.WorkModel;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "dhsOpenEmpInfo")
public class  ShowWork {
    @PropertyElement
    private String empBusiNm;
    @PropertyElement
    private String empWantedHomepgDetail;
    @PropertyElement
    private String regLogImgNm;
    @PropertyElement
    private Integer empWantedStdt;
    @PropertyElement
    private String empWantedTypeNm;
    @PropertyElement
    private Integer empSeqno;
    @PropertyElement
    private Integer empWantedEndt;
    @PropertyElement
    private String coClcdNm;
    @PropertyElement
    private String empWantedMobileUrl;
    @PropertyElement
    private String empWantedTitle;
    public String getEmpBusiNm() {
        return empBusiNm;
    }
    public void setEmpBusiNm(String empBusiNm) {
        this.empBusiNm = empBusiNm;
    }
    public String getEmpWantedHomepgDetail() {
        return empWantedHomepgDetail;
    }
    public void setEmpWantedHomepgDetail(String empWantedHomepgDetail) {
        this.empWantedHomepgDetail = empWantedHomepgDetail;
    }
    public String getRegLogImgNm() {
        return regLogImgNm;
    }
    public void setRegLogImgNm(String regLogImgNm) {
        this.regLogImgNm = regLogImgNm;
    }
    public Integer getEmpWantedStdt() {
        return empWantedStdt;
    }
    public void setEmpWantedStdt(Integer empWantedStdt) {
        this.empWantedStdt = empWantedStdt;
    }
    public String getEmpWantedTypeNm() {
        return empWantedTypeNm;
    }
    public void setEmpWantedTypeNm(String empWantedTypeNm) {
        this.empWantedTypeNm = empWantedTypeNm;
    }
    public Integer getEmpSeqno() {
        return empSeqno;
    }
    public void setEmpSeqno(Integer empSeqno) {
        this.empSeqno = empSeqno;
    }
    public Integer getEmpWantedEndt() {
        return empWantedEndt;
    }
    public void setEmpWantedEndt(Integer empWantedEndt) {
        this.empWantedEndt = empWantedEndt;
    }
    public String getCoClcdNm() {
        return coClcdNm;
    }
    public void setCoClcdNm(String coClcdNm) {
        this.coClcdNm = coClcdNm;
    }
    public String getEmpWantedMobileUrl() {
        return empWantedMobileUrl;
    }
    public void setEmpWantedMobileUrl(String empWantedMobileUrl) {
        this.empWantedMobileUrl = empWantedMobileUrl;
    }
    public String getEmpWantedTitle() {
        return empWantedTitle;
    }
    public void setEmpWantedTitle(String empWantedTitle) {
        this.empWantedTitle = empWantedTitle;
    }
}