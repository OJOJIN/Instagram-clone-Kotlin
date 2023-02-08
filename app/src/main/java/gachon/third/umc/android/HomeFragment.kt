package gachon.third.umc.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gachon.third.umc.android.Constants.*
import gachon.third.umc.android.databinding.FragmentHomeBinding


class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding

    val storyList: ArrayList<StoryData> = arrayListOf()
    val storyRVAdapter = StoryRVAdapter(storyList)

    private val feedList: ArrayList<FeedData> = arrayListOf()
    val feedRVAdapter = FeedRVAdapter(feedList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        storyList.apply{
            add(StoryData(R.drawable.ic_add_new_story,"내 스토리", StoryViewType.add, R.drawable.img_story_main_1))
            add(StoryData(R.drawable.ic_profile_default,"또르", StoryViewType.story, R.drawable.img_story_main_2))
            add(StoryData(R.drawable.ic_profile_default,"참이슬", StoryViewType.story, R.drawable.img_story_main_3))
            add(StoryData(R.drawable.ic_profile_default,"사람1", StoryViewType.story, R.drawable.img_story_main_4))
            add(StoryData(R.drawable.ic_profile_default,"뚜껑", StoryViewType.story, R.drawable.img_story_main_5))
            add(StoryData(R.drawable.ic_profile_default,"삼출이", StoryViewType.story, R.drawable.img_story_main_6))
            add(StoryData(R.drawable.ic_profile_default,"고앵이", StoryViewType.story, R.drawable.img_story_main_7))
            add(StoryData(R.drawable.ic_profile_default,"맛도리", StoryViewType.story, R.drawable.img_story_main_8))
            add(StoryData(R.drawable.ic_profile_default,"멍멍이", StoryViewType.story, R.drawable.img_story_main_1))
            add(StoryData(R.drawable.ic_profile_default,"name9", StoryViewType.story, R.drawable.img_story_main_2))
        }

        binding.rvStory.adapter = storyRVAdapter

        feedList.apply{
            add(FeedData(R.drawable.ic_profile_default,"ojin_y",R.drawable.feed_img_1,"좋아요 24개",
                "ojin_y 안녕하세요 가천대학교 UMC 펭귄입니다!","댓글 5개 모두 보기", "10월 20일"))
            add(FeedData(R.drawable.ic_profile_default,"name2",R.drawable.feed_img_3,"좋아요 62개",
                "name2 으아아아아아아아아아","댓글 9개 모두 보기", "10월 21일"))
            add(FeedData(R.drawable.ic_profile_default,"name3",R.drawable.feed_img_4,"좋아요 81개",
                "name3 드디어 끝났다아ㅏㅏㅏㅏ","댓글 19개 모두 보기", "10월 24일"))
        }

        storyRVAdapter.setOnItemClickListener(object : StoryRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: StoryData, pos : Int) {
                val intent = Intent(activity, ActivityStory::class.java)
                intent.putExtra("profileImg", data.profileImg)
                intent.putExtra("userName", data.name)
                intent.putExtra("time", pos)
                intent.putExtra("postImg", data.postImg)

                startActivity(intent)
//                Intent(this@HomeFragment, ActivityStory::class.java).apply {
//                    putExtra("data", data)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                }.run { startActivity(this) }
            }
//            override fun onItemClick(v: View, data: StoryData, pos : Int) {
//                Intent(activity, ActivityStory::class.java).apply {
//                    putExtra("profileImg", data.profileImg)
//                    putExtra("userName", data.name)
//                    putExtra("time", pos)
//                    putExtra("postImg", data.postImg)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                }.run { startActivity(this) }
//            }
        })

        binding.rvFeed.adapter = feedRVAdapter
        return binding.root
    }

}