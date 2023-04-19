package de.storchp.fdroidbuildstatus.adapter.fdroid

import de.storchp.fdroidbuildstatus.model.BuildStatus
import java.util.*

open class BuildItem(val id: String, val versionCode: String, val status: BuildStatus) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BuildItem) return false
        val buildItem = other
        return id == buildItem.id && versionCode == buildItem.versionCode
    }

    override fun hashCode(): Int {
        return Objects.hash(id, versionCode)
    }
}