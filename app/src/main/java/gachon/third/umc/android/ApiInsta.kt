package gachon.third.umc.android

import retrofit2.Call
import retrofit2.http.*

interface ApiInsta {

    @GET("mypage/profile")
    fun getPostList(): Call<Response>


    @GET("post/home.")
    fun getUserProfile(): Call<Response>

    @POST("user/join")
    fun signUp(@Body params: UserBody): Call<Response>

    @POST("user/login")
    fun login(@Body params: LoginBody): Call<Response>

    @GET("mypage/profile")
    fun getUserInfo(@Header("X-ACCESS-TOKEN") accessToken : String): Call<userInfoResponse>


    @PATCH("user/mod")
    fun editUserInfo(@Header("X-ACCESS-TOKEN") accessToken : String,
                     @Body params: UserInfoBody): Call<UserInfoEditResponse>

}