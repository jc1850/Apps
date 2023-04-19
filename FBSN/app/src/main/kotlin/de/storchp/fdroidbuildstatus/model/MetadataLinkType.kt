package de.storchp.fdroidbuildstatus.model

enum class MetadataLinkType(private val path: String?) {
    ASK(null),
    YAML("raw/"),
    GITLAB("blob/");

    val metadataBaseUri: String?
        get() = if (path != null) GITLAB_FDROIDDATA_BASE_URI + path else null

    companion object {
        const val GITLAB_FDROIDDATA_BASE_URI = "https://gitlab.com/fdroid/fdroiddata/-/"
    }

}