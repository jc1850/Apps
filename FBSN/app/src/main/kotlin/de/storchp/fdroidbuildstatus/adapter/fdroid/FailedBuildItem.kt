package de.storchp.fdroidbuildstatus.adapter.fdroid

import de.storchp.fdroidbuildstatus.model.BuildStatus

class FailedBuildItem(id: String, versionCode: String) : BuildItem(
    id, versionCode, BuildStatus.FAILED
)