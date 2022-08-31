package net.coremotion.challenge1.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetail(
    @Json(name = "dataa")
    val data: Data?,
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "avatar")
        val avatar: String?,
        @Json(name = "email")
        val email: String?,
        @Json(name = "first_name")
        val firstName: String?,
        @Json(name = "pr")
        val id: Int?,
        @Json(name = "last_name")
        val lastName: String?
    )
}
