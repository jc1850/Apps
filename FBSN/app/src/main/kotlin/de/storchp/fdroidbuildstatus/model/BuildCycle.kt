package de.storchp.fdroidbuildstatus.model

import de.storchp.fdroidbuildstatus.R

enum class BuildCycle(val isUpdatable: Boolean, val iconRes: Int) {
    RUNNING(true, R.drawable.ic_directions_run_24px),
    BUILD(true, R.drawable.ic_build_24),
    UPDATE(true, R.drawable.ic_upgrade_black_24dp),
    NONE(false, 0);
}