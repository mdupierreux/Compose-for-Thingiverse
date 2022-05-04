package be.maximedupierreux.browserforthingiverse.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tags (

  @SerialName("name"         ) var name        : String? = null,
  @SerialName("tag"          ) var tag         : String? = null,
  @SerialName("url"          ) var url         : String? = null,
  @SerialName("count"        ) var count       : Int?    = null,
  @SerialName("things_url"   ) var thingsUrl   : String? = null,
  @SerialName("absolute_url" ) var absoluteUrl : String? = null

)