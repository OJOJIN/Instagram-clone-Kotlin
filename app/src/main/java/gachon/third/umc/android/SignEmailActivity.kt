package gachon.third.umc.android

import BlankFragment2
import FragmentSignLast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.databinding.ActivitySignEmailBinding

class SignEmailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivitySignEmailBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val extra = intent.extras
        val id = extra!!["id"] as String
        val pw = extra!!["pw"] as String
        val name = extra!!["name"] as String

        val bundle = Bundle()
        bundle.putString("key", "value")


//        Toast.makeText(this, id + " AAAAAAAAA " + pw + " " + name, Toast.LENGTH_SHORT)
//            .show()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
            R.id.fragment_frame,
            FragmentSignLast().apply { arguments = (Bundle().apply{
                    putString("id", id)
                    putString("pw",pw)
                    putString("name", name)
                })}

        ).commit()


    }
}