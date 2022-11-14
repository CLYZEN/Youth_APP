package com.example.youthapp.Service;



import com.example.youthapp.PolicyModel.EmpsInfo;
import com.tickaroo.tikxml.annotation.Path;

import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PolicyService {
    @GET("/opi/empList.do?openApiVlak=b085acc02467eae4262a16d2")
    Call<EmpsInfo> getPolicyTitle( @Query("bizTycdSel") String policyType,  @Query("srchPolyBizSecd") String local,
                                   @Query("query") String query, @Query("pageIndex") int pageIndex, @Query("display") String display);


}
