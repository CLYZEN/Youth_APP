package com.example.youthapp.Service;



import com.example.youthapp.PolicyModel.EmpsInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PolicyService {
    @GET("/opi/empList.do?openApiVlak=b085acc02467eae4262a16d2&display=100")
    Call<EmpsInfo> getPolicyTitle();
}
