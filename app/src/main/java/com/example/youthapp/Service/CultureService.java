package com.example.youthapp.Service;

import com.example.youthapp.CultureModel.ShowListRoot;

import retrofit2.Call;
import retrofit2.http.GET;
//https://www.kopis.or.kr/openApi/restful/pblprfr?service=d0d8ca2481da4c16ba1dfb87ba045731&prfstate=02&rows=1000&cpage=1

public interface CultureService {
    @GET("/openApi/restful/pblprfr?service=d0d8ca2481da4c16ba1dfb87ba045731&prfstate=02&rows=1000&cpage=1")
    Call<ShowListRoot> getCultureTitle();
}
