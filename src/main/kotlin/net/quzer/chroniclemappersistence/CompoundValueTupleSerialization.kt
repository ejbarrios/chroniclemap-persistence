package net.quzer.chroniclemappersistence

import com.googlecode.cqengine.index.compound.support.CompoundValueTuple
import net.openhft.lang.io.Bytes
import net.openhft.lang.io.serialization.BytesMarshaller

class CompoundValueTupleSerialization : BytesMarshaller<CompoundValueTuple<CarValue>> {
    override fun write(bytes: Bytes, e: CompoundValueTuple<CarValue>) {
        bytes.writeList(e.attributeValues.toList())
    }

    override fun read(bytes: Bytes): CompoundValueTuple<CarValue> {
        val attributeValueList = mutableListOf<Any>()
        bytes.readList(attributeValueList, Any::class.java)
        return CompoundValueTuple(attributeValueList)
    }

    override fun read(bytes: Bytes, e: CompoundValueTuple<CarValue>?): CompoundValueTuple<CarValue> {
        // can't reuse CompoundValueTuple objects, it has no setters
        return read(bytes)
    }

}