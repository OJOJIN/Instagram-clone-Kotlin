package gachon.third.umc.android

data class Response(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: UserData

) {
    data class UserData(
        val userIdx: Int,
        val jwt: String
    )
}

data class UserBody(
    val userName: String,
    val userId: String,
    val userEmail: String,
    val userPwd: String,
)

data class LoginBody(
    val userInfo: String,
    val userPwd: String
)

data class userInfoResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: userInfoData
) {
    data class userInfoData(
        val userIdx: Int,
        val userID: String,
        val userName: String,
        val userIntro: String,
        val userWebsite: String,
        val userProfileImg: String,
        val postNum: Int,
        val followerNum: Int,
        val followingNum: Int,
    )
}

data class UserInfoEditResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: String
)

data class UserInfoBody(
    val userName: String,
    val userId: String,
    val userIntro: String,
    val userWebsite: String,
)