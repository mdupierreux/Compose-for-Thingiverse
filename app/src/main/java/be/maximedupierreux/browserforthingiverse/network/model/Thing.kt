package be.maximedupierreux.browserforthingiverse.network.model

import be.maximedupierreux.browserforthingiverse.network.model.Creator
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thing (

  @SerialName("id"            ) var id           : Int?            = null,
  @SerialName("name"          ) var name         : String?         = null,
  @SerialName("url"           ) var url          : String?         = null,
  @SerialName("public_url"    ) var publicUrl    : String?         = null,
  @SerialName("created_at"    ) var createdAt    : String?         = null,
  @SerialName("thumbnail"     ) var thumbnail    : String?         = null,
  @SerialName("preview_image" ) var previewImage : String?         = null,
  @SerialName("creator"       ) var creator      : Creator?        = Creator(),
  @SerialName("is_private"    ) var isPrivate    : Boolean?         = null,
  @SerialName("is_purchased"  ) var isPurchased  : Boolean?         = null,
  @SerialName("is_published"  ) var isPublished  : Boolean?         = null,
  @SerialName("comment_count" ) var commentCount : Int?         = null,
  @SerialName("make_count"    ) var makeCount    : Int?            = null,
  @SerialName("like_count"    ) var likeCount    : Int?         = null,
  @SerialName("tags"          ) var tags         : ArrayList<Tags> = arrayListOf(),
  @SerialName("is_nsfw"       ) var isNsfw       : Boolean?        = null

)