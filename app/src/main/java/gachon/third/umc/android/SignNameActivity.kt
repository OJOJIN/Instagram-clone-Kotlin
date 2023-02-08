package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.databinding.ActivitySignIdBinding
import gachon.third.umc.android.databinding.ActivitySignNameBinding

class SignNameActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivitySignNameBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)


        viewBinding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(viewBinding.edtName.length() > 0){
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
            if(viewBinding.edtName.length() > 0 && viewBinding.edtName.length()>0){
                val extra = intent.extras
                val id = extra!!["id"] as String
                val pw = extra!!["pw"] as String

                val intent = Intent(this, SignEmailActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                intent.putExtra("name", viewBinding.edtName.text.toString())

                startActivity(intent)
            }
        }
    }
}