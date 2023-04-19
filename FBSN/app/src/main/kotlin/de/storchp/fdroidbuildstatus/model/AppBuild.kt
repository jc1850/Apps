package de.storchp.fdroidbuildstatus.model

import de.storchp.fdroidbuildstatus.utils.FormatUtils

data class AppBuild @JvmOverloads constructor(
    val versionCode: String,
    var versionName: String? = null,
    var buildCycle: BuildCycle = BuildCycle.NONE,
    var status: BuildStatus = BuildStatus.MISSING,
    var dataCommitId: String? = null
) : Comparable<AppBuild> {

    val fullVersion: String
        get() = FormatUtils.formatVersion(versionCode, versionName)

    fun getMetadataUri(metadataLinkType: MetadataLinkType, id: String): String {
        return metadataLinkType.metadataBaseUri + (if (dataCommitId == null) "master" else dataCommitId) + "/metadata/" + id + ".yml"
    }

    override fun compareTo(other: AppBuild): Int {
        val buildCycleCompare = buildCycle.compareTo(other.buildCycle)
        return if (buildCycleCompare != 0) {
            buildCycleCompare
        } else -Integer.compare(
            versionCode.toInt(),
            other.versionCode.toInt()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AppBuild

        if (versionCode != other.versionCode) return false
        if (buildCycle != other.buildCycle) return false

        return true
    }

    override fun hashCode(): Int {
        var result = versionCode.hashCode()
        result = 31 * result + buildCycle.hashCode()
        return result
    }

    val versionCodeAndStatusKey: String
        get() = versionCode + status.name

}