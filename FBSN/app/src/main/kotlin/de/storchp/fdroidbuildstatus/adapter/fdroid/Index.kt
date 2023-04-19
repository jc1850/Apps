package de.storchp.fdroidbuildstatus.adapter.fdroid

import android.text.TextUtils
import de.storchp.fdroidbuildstatus.utils.FormatUtils

data class Index @JvmOverloads constructor(
    var apps: List<App> = ArrayList()
) {

    data class App @JvmOverloads constructor(
        var name: String? = null,
        var packageName: String? = null,
        var sourceCode: String? = null,
        var localized: Map<String, LocalizedName> = HashMap()
    ) {
        val appName: String?
            get() = if (!TextUtils.isEmpty(name)) {
                name
            } else localized.entries.stream()
                .filter { (_, value): Map.Entry<String, LocalizedName> ->
                    !TextUtils.isEmpty(
                        value.name
                    )
                }
                .filter { (key): Map.Entry<String, LocalizedName> -> key.startsWith("en") }
                .map { (_, value): Map.Entry<String, LocalizedName> -> value.name }
                .findFirst()
                .orElseGet {
                    localized.values.stream()
                        .map { obj: LocalizedName -> obj.name }
                        .filter { n: String? -> !TextUtils.isEmpty(n) }
                        .findAny()
                        .orElseGet {
                            FormatUtils.getNameFromSource(
                                sourceCode
                            )
                        }
                }
    }

    data class LocalizedName @JvmOverloads constructor(
        var name: String? = null
    )

}