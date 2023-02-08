import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import gachon.third.umc.android.*
import gachon.third.umc.android.databinding.FragmentSignInEmailBinding
import retrofit2.Call
import retrofit2.Callback

class BlankFragment2: Fragment() {

    private lateinit var viewBinding: FragmentSignInEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSignInEmailBinding.inflate(inflater, container, false)

        val id = arguments?.getString("id")
        val pw = arguments?.getString("pw")
        val name = arguments?.getString("name")

        Toast.makeText(context, id + " = " + pw + " = " + name, Toast.LENGTH_SHORT)
            .show()

        viewBinding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(viewBinding.edtEmail.length() > 0){
                    viewBinding.btnNext.setBackgroundResource(R.drawable.button_background_2)
                }
                else{
                    viewBinding.btnNext.setBackgroundResource(R.drawable.button_background_3)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        viewBinding.btnNext.setOnClickListener {
            if( viewBinding.edtEmail.length()>0){
                val intent = Intent(context, LoginPageActivity::class.java)

                if(id != null && pw != null && name != null){
                    val userBody = UserBody(name, id, viewBinding.edtEmail.text.toString(), pw)

                    RetrofitBuilder.api.signUp(userBody).enqueue(object : Callback<Response> {
                        override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                            if(response.isSuccessful) {
                                val responseData= response.body()

                                if(responseData!=null){
                                    Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" +
                                            "code: ${responseData.code}\nmessage: ${responseData.message}\n" +
                                            "result: ${responseData.result}\n")
                                    if(responseData.isSuccess){
                                        //이전까지 쌓았던 activity stack을 비워줌
                                        intent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(intent)
                                    }
                                    else{
                                        Toast.makeText(context, responseData.message, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                            else {
                                Log.w("Retrofit","Response Not Successfull ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            Log.e("Retrofit","Error!",t)
                        }

                    })
                }

            }
        }

        viewBinding.btnEmailStatus.setOnClickListener {
            viewBinding.edtEmail.text = null
        }

        return viewBinding.root
    }
}