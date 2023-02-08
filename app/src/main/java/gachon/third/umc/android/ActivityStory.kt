package gachon.third.umc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import gachon.third.umc.android.databinding.ActivityStoryBinding

class ActivityStory : AppCompatActivity() {
    lateinit var viewBinding: ActivityStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityStoryBinding.inflate(layoutInflater)

        var handler = Handler(Looper.getMainLooper())

        val extra = intent.extras
        val profileImg =     extra!!["profileImg"] as Int
        val userName = extra!!["userName"] as String
        val time = extra!!["time"] as Int
        val storyImg = extra!!["postImg"] as Int

        val timeText = time.toString() + "시간 전"

        viewBinding.imgStoryProfile.setImageResource(profileImg)
        viewBinding.imgStoryMain.setImageResource(storyImg)
        viewBinding.tvStoryName.text = userName
        viewBinding.tvStoryTime.text = timeText
        viewBinding.storySeekBar.isEnabled = false

        setContentView(viewBinding.root)

        var leftTime = 0
        Thread() {
            while(leftTime<=5000) {
                handler.post {
                    viewBinding.storySeekBar.progress = leftTime
                }
                leftTime+=10
                Thread.sleep(10)
            }
            finish()
        }.start()
    }
}