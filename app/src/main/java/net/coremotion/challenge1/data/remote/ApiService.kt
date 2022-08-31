package net.coremotion.challenge1.data.remote

import net.coremotion.challenge1.common.ApiEndpoints
import net.coremotion.challenge1.domain.model.UserDetail
import net.coremotion.challenge1.domain.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiEndpoints.USERS)
    suspend fun getUsers(@Query("page") page: Int): Response<Users>

    @GET(ApiEndpoints.USER_DETAIL)
    suspend fun getUserDetail(@Path("id") id: Int): Response<UserDetail>

}