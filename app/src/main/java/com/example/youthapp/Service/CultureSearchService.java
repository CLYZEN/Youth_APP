package com.example.youthapp.Service;

import com.example.youthapp.CultureModel.ShowInfo;
import com.example.youthapp.CultureModel.ShowInfoRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CultureSearchService {

    @GET("/openApi/restful//pblprfr/{cid}?service=d0d8ca2481da4c16ba1dfb87ba045731")
    Call<ShowInfoRoot> getService(@Path("cid") String cid);//@Path("cid") String cid
}
