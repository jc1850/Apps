package de.storchp.fdroidbuildstatus.model

import de.storchp.fdroidbuildstatus.R

enum class BuildStatus(val iconRes: Int, private val colorRes: Int?) {

    MISSING(R.drawable.ic_questionmark_outline_24px, R.color.buildStatusMissing),

    FAILED(R.drawable.ic_error_outline_24px, R.color.buildStatusFailed),

    SUCCESS(R.drawable.ic_check_24px, R.color.buildStatusSuccess);

    fun getIconColorRes(defaultColor: Int): Int {
        return colorRes ?: defaultColor
    }
}