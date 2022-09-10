package com.example.youthapp.WorkModel;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;
@Xml(name = "dhsOpenEmpInfoList")
public class ShowWorkRoot {
    @PropertyElement
    private Integer total;
    @PropertyElement
    private Integer startPage;
    @Element
    private List<ShowWork> dhsOpenEmpInfo;
    @PropertyElement
    private Integer display;
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getStartPage() {
        return startPage;
    }
    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }
    public List<ShowWork> getDhsOpenEmpInfo() {
        return dhsOpenEmpInfo;
    }
    public void setDhsOpenEmpInfo(List<ShowWork> dhsOpenEmpInfo) {
        this.dhsOpenEmpInfo = dhsOpenEmpInfo;
    }
    public Integer getDisplay() {
        return display;
    }
    public void setDisplay(Integer display) {
        this.display = display;
    }
}
