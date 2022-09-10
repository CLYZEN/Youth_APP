package com.example.youthapp.PolicyModel;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml
public class EmpsInfo {
    @PropertyElement
    private Integer totalCnt;
    @PropertyElement
    private Integer pageIndex;
    @Element
    private List<Emp> emp;

    public Integer getTotalCnt() {
        return totalCnt;
    }
    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }
    public Integer getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
    public List<Emp> getEmp() {
        return emp;
    }
    public void setEmp(List<Emp> emp) {
        this.emp = emp;
    }
}