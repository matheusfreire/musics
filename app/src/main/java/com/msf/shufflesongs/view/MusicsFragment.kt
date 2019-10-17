package com.msf.shufflesongs.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.msf.shufflesongs.MusicsRecyclerViewAdapter
import com.msf.shufflesongs.R
import com.msf.shufflesongs.databinding.FragmentMusicsListBinding
import com.msf.shufflesongs.model.Music
import com.msf.shufflesongs.util.ItemDecoration
import com.msf.shufflesongs.viewmodel.MusicViewModel
import java.util.*
import kotlin.random.Random

class MusicsFragment : Fragment() {

    private lateinit var binding: FragmentMusicsListBinding
    private lateinit var viewModel: MusicViewModel

    private val JOHN_DOLLAR_ID = 909253
    private val CHARLINE_CHEWIE_ID = 1171421960
    private val BLOCO_TOTI_ID = 358714030
    private val MC_ARIANNE_ID = 1419227
    private val DECIMAIS_ID = 264111789

    private var myList: MutableList<Int> = mutableListOf(JOHN_DOLLAR_ID, CHARLINE_CHEWIE_ID, BLOCO_TOTI_ID, MC_ARIANNE_ID, DECIMAIS_ID)

    private val columnCount = 1

    private lateinit var onlyList: MutableList<Music>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


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
        viewModel.liveDataMusicResponse.observe(this, Observer {
            it?.let {
                if (it.resultCount > 0) {
                    onlyList = it.results.filter { results -> results.wrapperType.equals("track") }.toMutableList()
                    createAdapter()
                } else {
                    showEmptyMessage(requireActivity().getString(R.string.no_music_founded))
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
        getMusics(myList)
    }

    private fun getMusics(listId: List<Int>){
        setVisibilityViews(false)
        viewModel.getMusic(listId)
    }

    private fun createAdapter(){
        onlyList.shuffle(Random(128))
        binding.recyclerViewFeed.adapter = MusicsRecyclerViewAdapter(onlyList)
        setVisibilityViews(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shuffle_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_shuffle -> {
                setVisibilityViews(false)
                createAdapter()
            }
        }
        return true
    }


    private fun setVisibilityViews(visibilityOfRecycler: Boolean){
        binding.progressLoading.visibility = if(visibilityOfRecycler) View.GONE else View.VISIBLE
        binding.recyclerViewFeed.visibility = if(visibilityOfRecycler) View.VISIBLE else View.GONE
        binding.errorMessage.visibility = View.GONE
    }

    private fun showEmptyMessage(message: String) {
        binding.progressLoading.visibility = View.GONE
        binding.errorMessage.text = message
        binding.errorMessage.visibility = View.VISIBLE
    }
}
