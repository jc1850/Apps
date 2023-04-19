package de.storchp.fdroidbuildstatus.adapter.fdroid

import com.google.gson.*
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type
import java.util.*
import java.util.stream.Collectors

@JsonAdapter(FailedBuilds.Deserializer::class)
class FailedBuilds {
    private val failedBuildItems: MutableSet<FailedBuildItem?> = HashSet()
    fun getFailedBuildItems(): Set<FailedBuildItem?> {
        return failedBuildItems
    }

    class Deserializer : JsonDeserializer<FailedBuilds?> {
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): FailedBuilds? {
            return try {
                val failedBuilds = FailedBuilds()
                if (json.isJsonArray) {
                    val array = json.asJsonArray
                    for (i in 0 until array.size()) {
                        val element = array[i]
                        if (element.isJsonArray) {
                            val elements = element.asJsonArray
                            failedBuilds.failedBuildItems.add(
                                FailedBuildItem(
                                    elements[0].asString,
                                    elements[1].asString
                                )
                            )
                        }
                    }
                } else if (json.isJsonObject) {
                    val `object` = json.asJsonObject
                    failedBuilds.failedBuildItems.addAll(`object`.entrySet().stream()
                        .map { (key, element): Map.Entry<String?, JsonElement> ->
                            if (element.isJsonArray && element.asJsonArray.size() > 0) {
                                return@map FailedBuildItem(
                                    key!!,
                                    element.asJsonArray[0].asString
                                )
                            }
                            null
                        }
                        .filter { obj: FailedBuildItem? -> Objects.nonNull(obj) }
                        .collect(Collectors.toList()))
                }
                failedBuilds
            } catch (e: Exception) {
                null
            }
        }
    }
}