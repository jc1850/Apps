package de.storchp.fdroidbuildstatus.adapter.fdroid

class UpdateResult : RunningResult() {
    private val failedBuilds = MissingBuilds()

    val missingBuildItems: Set<MissingBuildItem?>
        get() = failedBuilds.getFailedBuildItems()

    val archivePolicy0: Set<String> = HashSet()
    val disabled: Set<String> = HashSet()
    val needsUpdate: Set<String> = HashSet()
    val noPackages: Set<String> = HashSet()
    val noUpdateCheck: Set<String> = HashSet()
    override fun getMaxBuildTimeReached(): Boolean {
        return false
    }

    override fun getBuildItems(): Set<MissingBuildItem?> {
        return missingBuildItems
    }
}