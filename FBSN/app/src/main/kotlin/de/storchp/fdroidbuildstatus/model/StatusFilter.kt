package de.storchp.fdroidbuildstatus.model

import de.storchp.fdroidbuildstatus.R

enum class StatusFilter(
    val isActive: Boolean,
    val isBuildFilter: Boolean,
    val isAppFilter: Boolean,
    val iconRes: Int,
    val textRes: Int
) {

    NONE(false, false, false, 0, R.string.status_filter),

    MISSING_BUILD(
        true,
        true,
        false,
        R.drawable.ic_questionmark_outline_24px,
        R.string.bottom_bar_missing
    ),

    FAILED_BUILD(true, true, false, R.drawable.ic_error_outline_24px, R.string.bottom_bar_failed),

    SUCCESSFUL_BUILD(true, true, false, R.drawable.ic_check_24px, R.string.bottom_bar_success),

    DISABLED(
        true,
        false,
        true,
        R.drawable.ic_disabled_24dp,
        R.string.bottom_bar_disabled
    ),

    ARCHIVED(true, false, true, R.drawable.ic_archive_24, R.string.bottom_bar_archived),

    NEEDS_UPDATE(
        true,
        false,
        true,
        R.drawable.ic_upgrade_black_24dp,
        R.string.bottom_bar_needs_update
    ),

    NO_PACKAGES(true, false, true, R.drawable.ic_no_packages_24, R.string.bottom_bar_no_packages),

    NO_UPDATE_CHECK(
        true,
        false,
        true,
        R.drawable.ic_no_update_check_24,
        R.string.bottom_bar_no_update_check
    );

}