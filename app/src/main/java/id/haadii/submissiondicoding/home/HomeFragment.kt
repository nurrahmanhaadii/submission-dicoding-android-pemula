package id.haadii.submissiondicoding.home

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.haadii.submissiondicoding.R
import kotlinx.android.synthetic.main.home_fragment.*
import id.haadii.submissiondicoding.detail.DetailActivity

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        initObserver()

        viewModel.getBookItem()

    }

    private fun initObserver() {
        viewModel.onBookListChangeObserver.observe(this, Observer {
            rv_home.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = BookAdapter(viewModel.bookList) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("books", it)
                    startActivity(intent)
                }
            }
            pb_home.visibility = View.GONE
        })
    }

}
