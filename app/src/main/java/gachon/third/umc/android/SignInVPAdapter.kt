package gachon.third.umc.android

import BlankFragment
import BlankFragment2
import FragmentSignLast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SignInVPAdapter(signInEmailActivity: FragmentSignLast): FragmentStateAdapter(signInEmailActivity) {
    override fun getItemCount(): Int = 2

    lateinit var emailFragment: BlankFragment2

    fun setBundle(fragment: BlankFragment2){
        emailFragment = fragment
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> BlankFragment()
            1 -> emailFragment
            else -> BlankFragment()
        }
    }

}