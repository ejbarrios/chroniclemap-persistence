package net.quzer.chroniclemappersistence

import net.openhft.chronicle.hash.serialization.BytesReader
import net.openhft.chronicle.hash.serialization.BytesWriter
import net.openhft.lang.io.Bytes

data class Car(
        val id: Long,
        val make: String,
        val model: String,
        val features: List<String>
)


class CarMarshaller : BytesWriter<Car>, BytesReader<Car> {
    override fun read(bytes: Bytes, size: Long, toReuse: Car?): Car {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size(e: Car): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(bytes: Bytes, size: Long): Car {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun write(bytes: Bytes, e: Car) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}