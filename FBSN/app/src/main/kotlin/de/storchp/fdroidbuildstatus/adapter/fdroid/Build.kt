package de.storchp.fdroidbuildstatus.adapter.fdroid

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Build @JvmOverloads constructor(
    val versionCode: String,
    var versionName: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Build

        if (versionCode != other.versionCode) return false

        return true
    }

    override fun hashCode(): Int {
        return versionCode.hashCode()
    }
}