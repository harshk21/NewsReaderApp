package com.hk210.newsreaderapp.ui.news

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hk210.newsreaderapp.R
import com.hk210.newsreaderapp.alert.Alert
import com.hk210.newsreaderapp.databinding.NewsFragmentBinding
import com.hk210.newsreaderapp.model.Article
import com.hk210.newsreaderapp.ui.news.adapter.NewsAdapter
import com.hk210.newsreaderapp.utils.NetworkResult
import com.hk210.newsreaderapp.utils.loader.LoaderUtils
import com.hk210.newsreaderapp.utils.network.NetworkConnectivityObserver
import com.hk210.newsreaderapp.utils.network.NetworkDialogUtils
import com.hk210.newsreaderapp.utils.network.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: NewsFragmentBinding? = null
    private val binding: NewsFragmentBinding
        get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var networkConnectivityObserver: NetworkConnectivityObserver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNetworkChange()
    }

    private fun observeNewsResponse() {
        viewModel.newsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Loading -> LoaderUtils.showDialog(requireContext(), false)

                is NetworkResult.Error -> {
                    LoaderUtils.hideDialog()
                    Alert.showSnackBar(binding.root, response.message.toString())
                }

                is NetworkResult.Success -> {
                    LoaderUtils.hideDialog()
                    setNews(response.data?.articles)
                }
            }
        }
    }

    private fun observeNetworkChange() {
        lifecycleScope.launch {
            networkConnectivityObserver.observe().collectLatest { networkStatus ->
                when (networkStatus) {
                    Status.Available -> {
                        observeNewsResponse()
                        NetworkDialogUtils.hideDialog()
                        binding.newsList.visibility = View.VISIBLE
                    }

                    else -> {
                        NetworkDialogUtils.showDialog(requireContext(), false)
                        binding.newsList.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setNews(articles: List<Article?>?) {
        binding.newsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articles?.let {
                NewsAdapter(requireContext(), it) { article ->
                    showArticle(article.url)
                }
            }
        }
    }

    private fun showArticle(url: String?) {
        val intent = CustomTabsIntent.Builder()
            .setUrlBarHidingEnabled(true)
            .build()
        intent.launchUrl(requireActivity(), Uri.parse(url))
    }
}
