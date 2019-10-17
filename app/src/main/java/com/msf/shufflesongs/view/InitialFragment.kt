package com.msf.shufflesongs.view


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.msf.shufflesongs.R
import com.msf.shufflesongs.databinding.FragmentInitialBinding

class InitialFragment : Fragment() {

    private lateinit var binding: FragmentInitialBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_initial, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
        val runnable = Runnable { binding.imageView.findNavController().navigate(R.id.action_initialFragment_to_musicsFragment) }

        Handler().postDelayed(runnable, 3000)

    }


}
