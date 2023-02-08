import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.SignInVPAdapter
import gachon.third.umc.android.databinding.FragmentSignLastBinding

class FragmentSignLast: Fragment() {

    private lateinit var viewBinding: FragmentSignLastBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSignLastBinding.inflate(inflater, container, false)

        val id = arguments?.getString("id")
        val pw = arguments?.getString("pw")
        val name = arguments?.getString("name")

        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("pw",pw)
        bundle.putString("name", name)

        val signInVPAdapter = SignInVPAdapter(this)
        viewBinding.vpMain.adapter = signInVPAdapter

        val fragmentB = BlankFragment2()
        fragmentB.arguments = bundle


        signInVPAdapter.setBundle(fragmentB)
        val tabTitleArray = arrayOf(
            "전화번호",
            "이메일",
        )

        TabLayoutMediator(viewBinding.tabMain, viewBinding.vpMain) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()


        return viewBinding.root
    }
}