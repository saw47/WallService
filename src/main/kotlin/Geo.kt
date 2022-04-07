data class Geo(
    val type: String? = null,
    val coordinates: String? = null
) {
    val place = Place()

    class Place(
        val latitude: Int? = null,
        val longitude: Int? = null,
        val icon: String? = null,
        val country: String? = null,
        val city: String? = null,
        val id: Int? = null,
        val title: String? = null,
        val created: Int? = null,
        val type: Int? = null,
        val groupId: Int? = null,
        val groupPhoto: String? = null,
        val checkIns: Int? = null,
        val updated: Int? = null,
        val address: Int? = null
    )
}