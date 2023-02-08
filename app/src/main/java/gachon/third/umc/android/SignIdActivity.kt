package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.databinding.ActivitySignIdBinding

class SignIdActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivitySignIdBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)


        viewBinding.edtId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(viewBinding.edtId.length() > 0){
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
            if(viewBinding.edtId.length() > 0 && viewBinding.edtId.length()>0){
                val intent = Intent(this, SignPwActivity::class.java)
                intent.putExtra("id",viewBinding.edtId.text.toString())
                startActivity(intent)
            }
        }
    }
}