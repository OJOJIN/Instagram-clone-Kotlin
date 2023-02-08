package gachon.third.umc.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> ProfileMyFeedFragment()
            1 -> ProfileAnotherFeedFragment()
            else -> ProfileMyFeedFragment()
        }
    }

}