package com.example.youthapp.CultureModel;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "dbs")
public class ShowListRoot {
    @Element(name = "db")
    private List<ShowList> showList;

    public List<ShowList> getShowList() {
        return showList;
    }

    public void setShowList(List<ShowList> showList) {
        this.showList = showList;
    }
}
