package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.addTextChangedListener
import gachon.third.umc.android.databinding.LoginPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Types.NULL

class LoginPageActivity : AppCompatActivity() {


    private lateinit var viewBinding: LoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        viewBinding = LoginPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)


        viewBinding.edtId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(viewBinding.edtId.length() > 0 && viewBinding.edtPw.length()>0){
                    viewBinding.btnLogin.setBackgroundResource(R.drawable.button_background_2)
                }
                else{
                    viewBinding.btnLogin.setBackgroundResource(R.drawable.button_background_3)
                }
            }
        })
        viewBinding.edtPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(viewBinding.edtId.length() > 0 && viewBinding.edtPw.length()>0){
                    viewBinding.btnLogin.setBackgroundResource(R.drawable.button_background_2)
                }
                else{
                    viewBinding.btnLogin.setBackgroundResource(R.drawable.button_background_3)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        //로그인 버튼
        val id = MyApplication.sharedPrefs.getString("userId","")
        val password = MyApplication.sharedPrefs.getString("passwordNo","")

        if(id.isNotEmpty() && password.isNotEmpty()){
            Log.d("auto-logoin", "login id : ${id}, pw : ${password}\n")
            val loginBody = LoginBody(id, password)

            RetrofitBuilder.api.login(loginBody).enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()

                        if (responseData != null) {

                            Log.d("Retrofit",
                                "Response\nSuccess : ${responseData.isSuccess}\n" +
                                        "code: ${responseData.code}\nmessage: ${responseData.message}\n" +
                                        "result: ${responseData.result}\n")

                            if (responseData.isSuccess) {
                                MyApplication.sharedPrefs.setString("userJWT", responseData.result.jwt)
                                val intent = Intent(this@LoginPageActivity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@LoginPageActivity,
                                    responseData.message,
                                    Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    } else {
                        Log.w("Retrofit", "Response Not Successfull ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Log.e("Retrofit", "Error!", t)
                }

            })
        }
        else{
            viewBinding.btnLogin.setOnClickListener {
                //id랑 pw 받아줌
                val id = viewBinding.edtId.text.toString()
                val pw = viewBinding.edtPw.text.toString()

                //하나 이상의 값이 입력 됐을 때 실행
                if (id.isNotEmpty() && pw.isNotEmpty()) {
                    val loginBody = LoginBody(id, pw)
                    Log.d("Retrofit", "Login: Id = ${id}, PW = ${pw}\n")

                    RetrofitBuilder.api.login(loginBody).enqueue(object : Callback<Response> {
                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            if (response.isSuccessful) {
                                val responseData = response.body()

                                if (responseData != null) {

                                    Log.d("Retrofit",
                                        "Response\nSuccess : ${responseData.isSuccess}\n" +
                                                "code: ${responseData.code}\nmessage: ${responseData.message}\n" +
                                                "result: ${responseData.result}\n")

                                    if (responseData.isSuccess) {
                                        MyApplication.sharedPrefs.setString("userId", id)
                                        MyApplication.sharedPrefs.setString("passwordNo", pw)
                                        MyApplication.sharedPrefs.setString("userJWT", responseData.result.jwt)

                                        val intent = Intent(this@LoginPageActivity, MainActivity::class.java)
                                        startActivity(intent)
                                    } else {
                                        Toast.makeText(this@LoginPageActivity,
                                            responseData.message,
                                            Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            } else {
                                Log.w("Retrofit", "Response Not Successfull ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            Log.e("Retrofit", "Error!", t)
                        }

                    })
                }
                else{
                    Toast.makeText(this, "아디 비번 1자리 이상 눌러줘요", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        //회원가입으로
        viewBinding.txtToSignIn.setOnClickListener{
            val intent = Intent(this, SignIdActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnPwStatus.setOnClickListener {
            if(viewBinding.edtPw.inputType == 1){
                viewBinding.btnPwStatus.setImageResource(R.drawable.ic_pwd_off)
                viewBinding.edtPw.inputType = 0x0000081
                viewBinding.edtPw.setSelection(viewBinding.edtPw.length())
            }
            else{
                viewBinding.btnPwStatus.setImageResource(R.drawable.ic_pwd_on)
                viewBinding.edtPw.inputType = 1
                viewBinding.edtPw.setSelection(viewBinding.edtPw.length())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val extra = intent.extras
        val passId = extra?.getString("id")
        val passPw = extra?.getString("pw")
        val passName = extra?.getString("name")
        val passEmail = extra?.getString("email")

//        if(passEmail != null && passId != null && passPw != null && passName != null){
//            val userBody = UserBody(passId, passPw, passName, passEmail)
//
//            RetrofitBuilder.api.signUp(userBody).enqueue(object : Callback<Response> {
//                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
//                    if(response.isSuccessful) {
//                        val responseData= response.body()
//
//                        if(responseData!=null){
//                            Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" +
//                                    "code: ${responseData.code}\nmessage: ${responseData.message}\n" +
//                                    "result: ${responseData.result}\n")
//                        }
//                    }
//                    else {
//                        Log.w("Retrofit","Response Not Successfull ${response.code()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<Response>, t: Throwable) {
//                    Log.e("Retrofit","Error!",t)
//                }
//
//            })
//            Toast.makeText(this, passId + " " + passPw + " " + passName + " "+ passEmail, Toast.LENGTH_SHORT)
//                .show()
//        }
    }
}