package de.storchp.fdroidbuildstatus.adapter.gitlab

import android.text.TextUtils
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.storchp.fdroidbuildstatus.utils.FormatUtils
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class Metadata @JsonCreator constructor(
    @param:JsonProperty("SourceCode")
    @get:JsonProperty("SourceCode")
    var sourceCode: String? = null,

    @param:JsonProperty("AutoName")
    @get:JsonProperty("AutoName")
    var autoName: String? = null,

    @param:JsonProperty("Name")
    @get:JsonProperty("Name")
    var name: String? = null,

    @param:JsonProperty("Builds")
    @get:JsonProperty("Builds")
    var builds: Set<Build> = HashSet()
) {

    val highestVersion: Optional<Build>
        get() = builds.stream().max(Comparator.comparing { obj: Build -> obj.versionCodeAsLong })

    val appName: String?
        get() {
            if (!TextUtils.isEmpty(name)) {
                return name
            }
            return if (!TextUtils.isEmpty(autoName) && !autoName!!.startsWith("\${")) {
                autoName
            } else FormatUtils.getNameFromSource(sourceCode)
        }

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Build @JsonCreator constructor(
        @param:JsonProperty("versionCode")
        @get:JsonProperty("versionCode")
        val versionCode: String,

        @param:JsonProperty("versionName")
        @get:JsonProperty("versionName")
        var versionName: String? = null
    ) {

        // ignore
        val versionCodeAsLong: Long
            get() {
                try {
                    return versionCode.toLong()
                } catch (ignored: Exception) {
                    // ignore
                }
                return -1
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Build

            if (versionCode != other.versionCode) return false

            return true
        }

        override fun hashCode(): Int {
            return versionCode.hashCode()
        }
    }
}