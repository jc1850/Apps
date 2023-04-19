package de.storchp.fdroidbuildstatus.adapter.fdroid

import de.storchp.fdroidbuildstatus.model.BuildStatus

class MissingBuildItem(id: String, versionCode: String) : BuildItem(
    id, versionCode, BuildStatus.MISSING
)