package de.storchp.fdroidbuildstatus.adapter.fdroid

import com.google.gson.*
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type
import java.util.*
import java.util.stream.Collectors

@JsonAdapter(MissingBuilds.Deserializer::class)
class MissingBuilds {
    private val missingBuildItems: MutableSet<MissingBuildItem?> = HashSet()
    fun getFailedBuildItems(): Set<MissingBuildItem?> {
        return missingBuildItems
    }

    class Deserializer : JsonDeserializer<MissingBuilds?> {
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): MissingBuilds? {
            return try {
                val missingBuilds = MissingBuilds()
                if (json.isJsonObject) {
                    val `object` = json.asJsonObject
                    missingBuilds.missingBuildItems.addAll(`object`.entrySet().stream()
                        .map { (key, element): Map.Entry<String?, JsonElement> ->
                            if (element.isJsonArray && element.asJsonArray.size() > 0) {
                                return@map MissingBuildItem(
                                    key!!,
                                    element.asJsonArray[0].asString
                                )
                            }
                            null
                        }
                        .filter { obj: MissingBuildItem? -> Objects.nonNull(obj) }
                        .collect(Collectors.toList()))
                }
                missingBuilds
            } catch (e: Exception) {
                null
            }
        }
    }
}