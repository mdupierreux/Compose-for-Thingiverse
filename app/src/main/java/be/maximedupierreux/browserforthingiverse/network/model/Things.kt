package be.maximedupierreux.browserforthingiverse.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Things (
  val total : Int?,
  val hits  : List<Thing>
)