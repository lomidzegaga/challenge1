package net.coremotion.challenge1.ui.users.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import net.coremotion.challenge1.common.Resource
import net.coremotion.challenge1.domain.model.Users
import net.coremotion.challenge1.domain.reposoitory.UserRepository

class UsersPagingSource(private val userRepository: UserRepository) :
    PagingSource<Int, Users.Data>() {
    override fun getRefreshKey(state: PagingState<Int, Users.Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Users.Data> {
        val pageNumber = params.key ?: 1

        val response = userRepository.getUsers(pageNumber)
        return when (response.status) {
            Resource.Status.SUCCESS -> {
                var preview: Int? = null
                var next: Int? = null

                if (response.data!!.totalPages!! > pageNumber)
                    next = pageNumber + 1

                if (pageNumber != 1)
                    preview = pageNumber - 1
                LoadResult.Page(data = response.data.data!!, prevKey = preview, nextKey = next)
            }
            Resource.Status.ERROR -> {
                LoadResult.Error(Throwable())
            }
            Resource.Status.LOADING -> {
                LoadResult.Error(Throwable())
            }
        }
    }
}