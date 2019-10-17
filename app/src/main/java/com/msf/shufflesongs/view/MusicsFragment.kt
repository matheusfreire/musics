package com.msf.shufflesongs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.msf.shufflesongs.MusicsRecyclerViewAdapter
import com.msf.shufflesongs.R
import com.msf.shufflesongs.databinding.FragmentMusicsListBinding
import com.msf.shufflesongs.util.ItemDecoration
import com.msf.shufflesongs.viewmodel.MusicViewModel

class MusicsFragment : Fragment() {

    private lateinit var binding: FragmentMusicsListBinding
    private lateinit var viewModel: MusicViewModel

    private val columnCount = 1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_musics_list, container, false)
        with(binding.recyclerViewFeed) {
            addItemDecoration(ItemDecoration(columnCount, dpToPx()))
        }
        (requireActivity() as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(requireActivity())[MusicViewModel::class.java]
        return binding.root
    }

    private fun dpToPx(dp: Int = 10): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    override fun onResume() {
        super.onResume()
        setVisibilityViews(false)
        viewModel.liveDataMusicResponse.observe(this, Observer {
            it?.let {
                if (it.resultCount > 0) {
                    val onlyList = it.results.filter { it.wrapperType.equals("track") }
                    binding.recyclerViewFeed.adapter = MusicsRecyclerViewAdapter(onlyList)
                    setVisibilityViews(true)
                } else {
                    showEmptyMessage(requireActivity().getString(R.string.somenthing_wrong))
                }
            }.run {
                showEmptyMessage(requireActivity().getString(R.string.somenthing_wrong))
            }
        })
        viewModel.liveDataMutableMessageError.observe(this, Observer {
            it?.let{
                showEmptyMessage(it)
            }
        })
        viewModel.getMusic()
    }

    private fun setVisibilityViews(visibilityOfRecycler: Boolean){
        binding.progressLoading.visibility = if(visibilityOfRecycler) View.GONE else View.VISIBLE
        binding.recyclerViewFeed.visibility = if(visibilityOfRecycler) View.VISIBLE else View.GONE
    }

    private fun showEmptyMessage(message: String) {
        binding.progressLoading.visibility = View.GONE
        binding.errorMessage.text = message
        binding.errorMessage.visibility = View.VISIBLE
    }
}
