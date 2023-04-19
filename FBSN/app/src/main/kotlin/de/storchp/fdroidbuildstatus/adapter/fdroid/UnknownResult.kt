package de.storchp.fdroidbuildstatus.adapter.fdroid

import java.util.*

class UnknownResult : RunningResult() {
    override fun getMaxBuildTimeReached(): Boolean {
        return false
    }

    override fun getBuildItems(): Set<BuildItem?> {
        return Collections.emptySet()
    }

}