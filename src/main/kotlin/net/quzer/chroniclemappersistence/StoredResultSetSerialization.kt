package net.quzer.chroniclemappersistence

import com.googlecode.cqengine.resultset.stored.StoredResultSet
import com.googlecode.cqengine.resultset.stored.StoredSetBasedResultSet
import net.openhft.lang.io.Bytes
import net.openhft.lang.io.serialization.BytesMarshaller

class StoredResultSetSerialization : BytesMarshaller<StoredResultSet<CarValue>> {
    override fun read(bytes: Bytes): StoredResultSet<CarValue> {
        return read(bytes, StoredSetBasedResultSet<CarValue>(HashSet<CarValue>()))
    }

    override fun read(bytes: Bytes, e: StoredResultSet<CarValue>?): StoredResultSet<CarValue> {
        val count = bytes.readInt()
        val retrieveCost = bytes.readInt()
        val cars = mutableListOf<CarValue?>()
        for(i in 0 until count) {
            cars.add(bytes.readObject(CarValue::class.java))
        }


        val resultSet: StoredSetBasedResultSet<CarValue> = when {
            e != null -> e as StoredSetBasedResultSet<CarValue>
            else -> StoredSetBasedResultSet(HashSet<CarValue>(), retrieveCost)
        }

        for (car in cars) {
            resultSet.add(car)
        }
        return resultSet
    }

    override fun write(bytes: Bytes, e: StoredResultSet<CarValue>) {
        val resultSet = e as StoredSetBasedResultSet<CarValue>
        bytes.write(resultSet.size())
        bytes.write(resultSet.mergeCost)
        resultSet.forEach { bytes.writeObject(it) }
    }

}