package gachon.third.umc.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gachon.third.umc.android.databinding.FragmentProfileMyFeedBinding

class ProfileMyFeedFragment: Fragment() {

    lateinit var binding: FragmentProfileMyFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileMyFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

}