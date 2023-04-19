package de.storchp.fdroidbuildstatus.model

enum class BuildlogType(val extension: String?) {
    ASK(null), LOG(".log"), GZ(".log.gz");

}