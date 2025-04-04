package com.example.absolute_cinema.data.remoute.dto.MovieDetail
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement


@Serializable(with = WatchabilitySerializer::class)
data class Watchability(
    val items: List<Item>?
)

object WatchabilitySerializer : KSerializer<Watchability?> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Watchability")

    override fun serialize(encoder: Encoder, value: Watchability?) {
        encoder.encodeSerializableValue(Watchability.serializer(), value ?: Watchability(emptyList()))
    }

    override fun deserialize(decoder: Decoder): Watchability? {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw IllegalStateException("This serializer can be used only with Json format")
        val element: JsonElement = jsonDecoder.decodeJsonElement()

        return when (element) {
            is JsonArray -> null
            is JsonObject -> element["items"]?.let { jsonDecoder.json.decodeFromJsonElement(it) }
            else -> null
        }
    }
}