package be.maximedupierreux.browserforthingiverse.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Creator (

  @SerialName("id"                 ) var id               : Int?     = null,
  @SerialName("name"               ) var name             : String?  = null,
  @SerialName("first_name"         ) var firstName        : String?  = null,
  @SerialName("last_name"          ) var lastName         : String?  = null,
  @SerialName("url"                ) var url              : String?  = null,
  @SerialName("public_url"         ) var publicUrl        : String?  = null,
  @SerialName("thumbnail"          ) var thumbnail        : String?  = null,
  @SerialName("count_of_followers" ) var countOfFollowers : Int?     = null,
  @SerialName("count_of_following" ) var countOfFollowing : Int?     = null,
  @SerialName("count_of_designs"   ) var countOfDesigns   : Int?     = null,
  @SerialName("accepts_tips"       ) var acceptsTips      : Boolean? = null,
  @SerialName("is_following"       ) var isFollowing      : Boolean? = null,
  @SerialName("location"           ) var location         : String?  = null,
  @SerialName("cover"              ) var cover            : String?  = null

)