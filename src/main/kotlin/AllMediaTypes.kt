data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: Int,
    val originalWidth: Int,
    val originalHeight: Int,
    val copyType: String,
    val url: String
) : Attachments {
    override val type: String = "photo"
    private val photo: InternalPhoto = InternalPhoto(
        id = this.id,
        albumId = this.albumId,
        ownerId = this.ownerId,
        userId = this.userId,
        text = this.text,
        date = this.date,
        originalWidth = this.originalWidth,
        originalHeight = this.originalHeight,
        copyType = this.copyType,
        url = this.url
    )

    data class InternalPhoto(
        private val id: Int,
        private val albumId: Int,
        private val ownerId: Int,
        private val userId: Int,
        private val text: String,
        private val date: Int,
        private val originalWidth: Int,
        private val originalHeight: Int,
        private val copyType: String,
        private val url: String
    ) {
        private val sizes = arrayOf(PhotoSizes(copyType, url, originalWidth, originalHeight))

        data class PhotoSizes(
            private val copyType: String,
            private val url: String,
            private val width: Int,
            private val height: Int
        )
    }
}

data class PostedPhoto(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String,
) : Attachments {
    override val type: String = "posted_photo"
    private val internalPostedPhoto = InternalPostedPhoto(id, ownerId, photo130, photo604)

    data class InternalPostedPhoto(
        private val id: Int,
        private val userId: Int,
        private val photo130: String,
        private val photo604: String,
    )
}

data class Graffiti(
    private val id: Int,
    private val ownerId: Int,
    private val photo130: String,
    private val photo604: String,
) : Attachments {
    override val type: String = "graffiti"
    internal val internalGraffiti = InternalGraffiti(id, ownerId, photo130, photo604)

    data class InternalGraffiti(
        private val id: Int,
        private val ownerId: Int,
        private val photo130: String,
        private val photo604: String,
    )
}

data class App(
    private val id: Int,
    private val name: Int,
    private val photo130: String,
    private val photo604: String,
) : Attachments {
    override val type: String = "app"
    internal val internalApp = InternalApp(id, name, photo130, photo604)

    data class InternalApp(
        private val id: Int,
        private val name: Int,
        private val photo130: String,
        private val photo604: String,
    )
}

data class PrettyCards(
    private val cardId: String,
    private val  linkUrl: String,
    private val title: String,
    private val width: Int,
    private val height: Int
) : Attachments {
    override val type: String = "pretty_cards"
    internal val postedPhoto = InternalPrettyCards(cardId, linkUrl, title, width, height)

    data class InternalPrettyCards(
        private val cardId: String,
        private val linkUrl: String,
        private val title: String,
        private val width: Int,
        private val height: Int
    ) {
        var images = arrayOf<Images>(Images(linkUrl, width, height))
        data class Images(
            private val url: String,
            private val width: Int,
            private val height: Int
        )
    }
}