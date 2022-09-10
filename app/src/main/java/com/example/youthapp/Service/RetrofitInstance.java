package com.example.youthapp.Service;

import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import retrofit2.Retrofit;

public class RetrofitInstance {

    private static Retrofit retrofit1 = null;   //policy
    private static Retrofit retrofit2 = null;   //culture title
    private static Retrofit retrofit3 = null;   //culture info
    private static Retrofit retrofit4 = null;   //work

    public static PolicyService getPolicyService(){
        if(retrofit1 == null){
            retrofit1 = new Retrofit
                    .Builder()
                    .baseUrl("https://www.youthcenter.go.kr")
                    .addConverterFactory(TikXmlConverterFactory.create())
                    .build();
        }

        return retrofit1.create(PolicyService.class);
    }

    public static CultureService getCultureService(){
        if (retrofit2 == null){
            retrofit2 = new Retrofit
                    .Builder()
                    .baseUrl("https://www.kopis.or.kr")
                    .addConverterFactory(TikXmlConverterFactory.create())
                    .build();
        }


        return retrofit2.create(CultureService.class);
    }

    public static CultureSearchService getCultureSearchService(){
        if (retrofit3 == null){
            retrofit3= new Retrofit
                    .Builder()
                    .baseUrl("https://www.kopis.or.kr")
                    .addConverterFactory(TikXmlConverterFactory.create())
                    .build();
        }


        return retrofit3.create(CultureSearchService.class);
    }


    public static  WorkService getWorkService(){
        if (retrofit4 == null){
            retrofit4 = new Retrofit
                    .Builder()
                    .baseUrl("http://openapi.work.go.kr")
                    .addConverterFactory(TikXmlConverterFactory.create())
                    .build();
        }
        return retrofit4.create(WorkService.class);
    }

}
