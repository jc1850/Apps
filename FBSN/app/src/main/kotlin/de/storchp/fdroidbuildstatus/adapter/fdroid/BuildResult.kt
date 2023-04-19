package de.storchp.fdroidbuildstatus.adapter.fdroid

class BuildResult : RunningResult() {
    private val failedBuilds = FailedBuilds()
    val successfulBuildIds: Set<SuccessBuildIdItem?> = HashSet()
    val isMaxBuildTimeReached = false
    val failedBuildItems: Set<FailedBuildItem?>
        get() = failedBuilds.getFailedBuildItems()
    val allBuilds: List<BuildItem?>
        get() {
            val allBuilds: MutableList<BuildItem?> = ArrayList(failedBuilds.getFailedBuildItems())
            allBuilds.addAll(successfulBuildIds)
            return allBuilds
        }

    override fun getMaxBuildTimeReached(): Boolean {
        return isMaxBuildTimeReached
    }

    override fun getBuildItems(): Set<BuildItem?> {
        return HashSet(allBuilds)
    }

}