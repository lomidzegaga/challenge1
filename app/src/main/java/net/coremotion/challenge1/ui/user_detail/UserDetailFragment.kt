package net.coremotion.challenge1.ui.user_detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.coremotion.challenge1.common.Resource
import net.coremotion.challenge1.databinding.FragmentUserDetailBinding
import net.coremotion.challenge1.extensions.setImage
import net.coremotion.challenge1.ui.base.BaseFragment

@AndroidEntryPoint
class UserDetailFragment :
    BaseFragment<FragmentUserDetailBinding>(FragmentUserDetailBinding::inflate) {

    private val viewModel: UserDetailViewModel by viewModels()
    private val args: UserDetailFragmentArgs by navArgs()


    override fun start() {
        viewModel.getUserDetail(args.userId)
        setListeners()
        observes()
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getUserDetail(args.userId)
        }
    }

    private fun observes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userDetailFlow.collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        binding.swipeRefresh.isRefreshing = false
                        binding.tvName.text =
                            it.data?.data?.firstName.plus(" ").plus(it.data?.data?.lastName)
                        binding.profileImage.setImage(it.data?.data?.avatar)
                    }
                    Resource.Status.ERROR -> {
                        binding.swipeRefresh.isRefreshing = false
                    }
                    Resource.Status.LOADING -> {
                        binding.swipeRefresh.isRefreshing = true
                    }
                }
            }
        }
    }


}