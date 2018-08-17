package net.quzer.chroniclemappersistence

import com.googlecode.cqengine.index.compound.support.CompoundValueTuple
import net.openhft.lang.io.Bytes
import net.openhft.lang.io.serialization.BytesMarshaller

class CompoundValueTupleSerialization : BytesMarshaller<CompoundValueTuple<CarValue>> {
    override fun write(bytes: Bytes, e: CompoundValueTuple<CarValue>) {
        bytes.writeInt(e.attributeValues.count())
        e.attributeValues.forEach { bytes.writeUTF(it as String) }
    }

    override fun read(bytes: Bytes): CompoundValueTuple<CarValue> {
        val count = bytes.readInt()
        val attributeValueList = mutableListOf<String?>()
        for (i in 0 until count) {
            attributeValueList.add(bytes.readUTF())
        }
        return CompoundValueTuple(attributeValueList)
    }

    override fun read(bytes: Bytes, e: CompoundValueTuple<CarValue>?): CompoundValueTuple<CarValue> {
        // can't reuse CompoundValueTuple objects, it has no setters
        return read(bytes)
    }

}