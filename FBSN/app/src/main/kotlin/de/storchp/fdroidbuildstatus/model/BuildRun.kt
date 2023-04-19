package de.storchp.fdroidbuildstatus.model

import java.util.*

data class BuildRun @JvmOverloads constructor(
    var endTimestamp: Long = 0,
    var startTimestamp: Long = 0,
    var lastModified: Date = Date(),
    var buildCycle: BuildCycle = BuildCycle.NONE,
    var lastUpdated: Date = Date(),
    var isMaxBuildTimeReached: Boolean = false,
    var subcommand: String? = null,
    var successCount: Int = 0,
    var failedCount: Int = 0
) {
    fun getNumberOfBuildsText(): String {
        return String.format("%d / %d", successCount, failedCount)
    }

}