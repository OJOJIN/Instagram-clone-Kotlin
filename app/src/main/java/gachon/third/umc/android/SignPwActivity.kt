package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import gachon.third.umc.android.databinding.ActivitySignIdBinding
import gachon.third.umc.android.databinding.ActivitySignPwBinding

class SignPwActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivitySignPwBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)


        viewBinding.edtPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(viewBinding.edtPw.length() > 5){
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
            if(viewBinding.edtPw.length()>5){

                val extra = intent.extras
                val id = extra!!["id"] as String
                val intent = Intent(this, SignNameActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("pw", viewBinding.edtPw.text.toString())
                startActivity(intent)
            }
        }

    }
}