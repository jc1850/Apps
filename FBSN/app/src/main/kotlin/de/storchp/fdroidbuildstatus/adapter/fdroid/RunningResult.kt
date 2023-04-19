package de.storchp.fdroidbuildstatus.adapter.fdroid

import java.util.*

abstract class RunningResult {
    val fdroiddata = FdroidData()
    val endTimestamp: Long = 0
    val startTimestamp: Long = 0
    var lastModified: Date? = null
    val subcommand: String? = null

    abstract fun getMaxBuildTimeReached(): Boolean

    abstract fun getBuildItems(): Set<BuildItem?>

}