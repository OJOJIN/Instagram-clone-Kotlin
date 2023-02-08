import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.FragmentSignInPhoneBinding

class BlankFragment: Fragment() {

    private lateinit var viewBinding: FragmentSignInPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSignInPhoneBinding.inflate(inflater, container, false)


        return viewBinding.root
    }
}