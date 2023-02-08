package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import gachon.third.umc.android.databinding.ActivityProfileEditBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Boolean.TRUE

class ProfileEditActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extra = intent.extras
        val name = extra!!["name"] as String
        val userName = extra!!["userName"] as String
        val userInfo = extra!!["userInfo"] as String

        viewBinding.txtProfileName.setText(name)
        viewBinding.txtProfileUserName.setText(userName)
        viewBinding.txtProfileUserInfo.setText(userInfo)


        viewBinding.btnProfileClose.setOnClickListener{
            finish()
        }

        viewBinding.btnProfileUpdate.setOnClickListener{
            if(viewBinding.txtProfileUserName.length() > 0) {
                val intent = Intent(this, MainActivity::class.java)
                val infoBody = UserInfoBody(viewBinding.txtProfileName.text.toString(),
                    viewBinding.txtProfileUserName.text.toString(),
                    viewBinding.txtProfileUserInfo.text.toString(),
                    "")


                RetrofitBuilder.api.editUserInfo(MyApplication.sharedPrefs.getString("userJWT", ""),infoBody).enqueue(object :
                    Callback<UserInfoEditResponse> {
                    override fun onResponse(
                        call: Call<UserInfoEditResponse>,
                        response: Response<UserInfoEditResponse>,
                    ) {
                        if (response.isSuccessful) {
                            val responseData = response.body()

                            if (responseData != null) {
                                Log.d("Retrofit",
                                    "UserInfoEditResponse\nSuccess : ${responseData.isSuccess}\n" +
                                            "code: ${responseData.code}\nmessage: ${responseData.message}\n" +
                                            "result: ${responseData.result}\n")

//                                if (responseData.isSuccess) {
//                                } else {
//                                }
                            }
                        } else {
                            Log.w("Retrofit", "Response Not Successfull ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<UserInfoEditResponse>, t: Throwable) {
                        Log.e("Retrofit", "Error in userInfoAPI!", t)
                    }

                })

                intent.putExtra("name", viewBinding.txtProfileName.text.toString())
                intent.putExtra("userName", viewBinding.txtProfileUserName.text.toString())
                intent.putExtra("userInfo", viewBinding.txtProfileUserInfo.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }


    }
}