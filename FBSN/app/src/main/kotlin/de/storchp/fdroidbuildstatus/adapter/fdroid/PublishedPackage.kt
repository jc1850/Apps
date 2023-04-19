package de.storchp.fdroidbuildstatus.adapter.fdroid

data class PublishedPackage @JvmOverloads constructor(
    var packageName: String? = null,
    var suggestedVersionCode: String? = null,
    var packages: List<PublishedPackage> = ArrayList()
) {

    data class PublishedPackage @JvmOverloads constructor(
        var versionName: String? = null,
        var versionCode: String? = null
    )
}