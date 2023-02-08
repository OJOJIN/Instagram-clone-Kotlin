package gachon.third.umc.android

import android.graphics.drawable.Drawable

data class FeedData(
    val profile_icon: Int,
    val profile_name: String,
    val feed_photo: Int,
    val total_like: String,
    val comment_content: String,
    val total_comment: String,
    val post_day: String
)
