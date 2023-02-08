package gachon.third.umc.android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var api: ApiInsta

    init{

        //api주소 등록
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kimmarie.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //app Service를 통해 이용 가능
        api = retrofit.create(ApiInsta::class.java)
    }
}