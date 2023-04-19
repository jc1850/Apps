package de.storchp.fdroidbuildstatus.model

import de.storchp.fdroidbuildstatus.R
import de.storchp.fdroidbuildstatus.utils.FormatUtils
import java.util.stream.Collectors

data class App @JvmOverloads constructor(
    var id: String,
    var name: String? = null,
    var favourite: Boolean = false,
    var sourceCode: String? = null,
    var disabled: Boolean = false,
    var needsUpdate: Boolean = false,
    var archived: Boolean = false,
    var noPackages: Boolean = false,
    var noUpdateCheck: Boolean = false,

    // contains all builds of an app, lazy loaded
    var appBuilds: Set<AppBuild> = HashSet()
) {

    /**
     * Returns the name if given otherwise the id
     */
    val displayName: String?
        get() = if (FormatUtils.isEmpty(name)) {
            id
        } else name

    val favouriteIcon: Int
        get() = if (favourite) R.drawable.ic_favourite_24px else R.drawable.ic_no_favourite_24px

    val fdroidUri: String
        get() = getFdroidUri(id)

    fun getMetadataUri(metadataLinkType: MetadataLinkType): String {
        return metadataLinkType.metadataBaseUri + "master/metadata/" + id + ".yml"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as App

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun hasNoBuildStatusProperties(): Boolean {
        return !disabled && !archived && !noPackages && !noUpdateCheck && !needsUpdate
    }

    val appBuildsByVersionCodeAndStatus: Map<String, List<AppBuild>>
        get() = appBuilds.stream()
            .collect(
                Collectors.groupingBy { obj: AppBuild -> obj.versionCodeAndStatusKey }
            )

    companion object {
        fun getFdroidUri(id: String?): String {
            return "https://f-droid.org/packages/$id"
        }
    }
}