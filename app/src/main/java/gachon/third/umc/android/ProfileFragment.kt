package gachon.third.umc.android

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment: Fragment() {

    lateinit var binding: FragmentProfileBinding

    lateinit var mainActivity: MainActivity

    val storyList: ArrayList<StoryData> = arrayListOf()

    val storyRVAdapter = StoryRVAdapter(storyList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): androidx.constraintlayout.widget.ConstraintLayout? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnProfileEdit.setOnClickListener {
            val intent = Intent(activity, ProfileEditActivity::class.java)
            intent.putExtra("name",binding.textProfileStatus.text.toString())
            intent.putExtra("userName",binding.textProfileName.text.toString())
            intent.putExtra("userInfo",binding.textProfileInfo.text.toString())

            resultLauncher.launch(intent)
        }


        val profileVPAdapter = ProfileVPAdapter(this)
        binding.vpProfile.adapter = profileVPAdapter

        val tabTitleArray = arrayOf(
            R.drawable.ic_postgrid,
            R.drawable.ic_person,
        )

        TabLayoutMediator(binding.profileTab, binding.vpProfile) { tab, position ->
            tab.setIcon(tabTitleArray[position])

        }.attach()

        storyList.apply{
            add(StoryData(R.drawable.ic_plus_3,"신규", Constants.StoryViewType.profileStoryAdd, 0))
            add(StoryData(R.drawable.ic_profle_story_nt, "", Constants.StoryViewType.profileStory, 0))
            add(StoryData(R.drawable.ic_profle_story_nt,"", Constants.StoryViewType.profileStory, 0))
            add(StoryData(R.drawable.ic_profle_story_nt,"", Constants.StoryViewType.profileStory, 0))
            add(StoryData(R.drawable.ic_profle_story_nt,"", Constants.StoryViewType.profileStory, 0))
            add(StoryData(R.drawable.ic_profle_story_nt,"", Constants.StoryViewType.profileStory, 0))
        }

        binding.rvProfileStory.adapter = storyRVAdapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        RetrofitBuilder.api.getUserInfo(MyApplication.sharedPrefs.getString("userJWT", "")).enqueue(object : Callback<userInfoResponse> {
            override fun onResponse(
                call: Call<userInfoResponse>,
                response: Response<userInfoResponse>,
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()

                    if (responseData != null) {
                        Log.d("Retrofit",
                            "UserInfoResponse\nSuccess : ${responseData.isSuccess}\n" +
                                    "code: ${responseData.code}\nmessage: ${responseData.message}\n" +
                                    "result: ${responseData.result}\n")

                        if (responseData.isSuccess) {
                            binding.textProfileName.text = responseData.result.userID
                            binding.textProfileStatus.text = responseData.result.userName
                            binding.textProfileInfo.text = responseData.result.userIntro
                            binding.tvPostCnt.text = responseData.result.postNum.toString()
                            binding.tvFollower.text = responseData.result.followerNum.toString()
                            binding.tvFollowing.text = responseData.result.followingNum.toString()

                            if(responseData.result.userIntro == ""){
                                binding.textProfileInfo.visibility= GONE
                            }
                        } else {
                            Toast.makeText(mainActivity,
                                responseData.message,
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    Log.w("Retrofit", "Response Not Successfull ${response.code()}")
                }
            }

            override fun onFailure(call: Call<userInfoResponse>, t: Throwable) {
                Log.e("Retrofit", "Error in userInfoAPI!", t)
            }

        })
    }


    val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val name = result.data?.getStringExtra("name")?:""
            val userName = result.data?.getStringExtra("userName")?:""
            val userInfo = result.data?.getStringExtra("userInfo")?:""

//            binding.textProfileName.text = name
//            binding.textProfileStatus.text = userName
//            binding.textProfileInfo.text = userInfo
//            if(binding.textProfileStatus.text.toString() == ""){
//                binding.textProfileStatus.visibility= GONE
//            }else {
//                binding.textProfileStatus.visibility = VISIBLE
//            }
//
//            if(binding.textProfileInfo.text.toString() == ""){
//                binding.textProfileInfo.visibility= GONE
//            }else {
//                binding.textProfileInfo.visibility = VISIBLE
//            }

        }
    }

}