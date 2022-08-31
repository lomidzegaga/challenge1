package net.coremotion.challenge1.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import net.coremotion.challenge1.domain.reposoitory.UserRepository
import net.coremotion.challenge1.ui.users.source.UsersPagingSource
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun usersFlow() =
        Pager(
            config = PagingConfig(pageSize = 1, maxSize = 200),
            pagingSourceFactory = { UsersPagingSource(userRepository) })
            .flow.cachedIn(viewModelScope)
}