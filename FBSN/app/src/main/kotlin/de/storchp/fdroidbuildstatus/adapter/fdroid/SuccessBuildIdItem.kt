package de.storchp.fdroidbuildstatus.adapter.fdroid

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import de.storchp.fdroidbuildstatus.model.BuildStatus
import java.lang.reflect.Type

@JsonAdapter(SuccessBuildIdItem.Deserializer::class)
class SuccessBuildIdItem(id: String, versionCode: String) : BuildItem(
    id, versionCode, BuildStatus.SUCCESS
) {
    class Deserializer : JsonDeserializer<SuccessBuildIdItem?> {
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): SuccessBuildIdItem? {
            return try {
                if (json.isJsonArray && json.asJsonArray.size() > 1) {
                    val elements = json.asJsonArray
                    return SuccessBuildIdItem(elements[0].asString, elements[1].asString)
                }
                null
            } catch (e: Exception) {
                null
            }
        }
    }
}