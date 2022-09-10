package com.example.youthapp.Service;

import com.example.youthapp.WorkModel.ShowWorkRoot;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WorkService {

    @GET("/opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey=WNL7SKV2HA20MJBO3P8G82VR1HK&callTp=L&returnType=XML&startPage=1&display=100")
    Call<ShowWorkRoot> getWorkTitle();
}
