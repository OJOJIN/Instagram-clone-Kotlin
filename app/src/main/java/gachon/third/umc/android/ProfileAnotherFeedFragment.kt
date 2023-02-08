package gachon.third.umc.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gachon.third.umc.android.databinding.FragmentProfileAnotherFeedBinding

class ProfileAnotherFeedFragment: Fragment() {

    lateinit var binding: FragmentProfileAnotherFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileAnotherFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

}