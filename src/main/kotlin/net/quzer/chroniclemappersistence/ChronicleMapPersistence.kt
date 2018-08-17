package net.quzer.chroniclemappersistence

import com.googlecode.cqengine.ConcurrentIndexedCollection
import com.googlecode.cqengine.index.compound.CompoundIndex
import com.googlecode.cqengine.index.unique.UniqueIndex
import com.googlecode.cqengine.persistence.wrapping.WrappingPersistence
import com.googlecode.cqengine.query.QueryFactory.*
import net.openhft.chronicle.set.ChronicleSet
import net.openhft.chronicle.set.ChronicleSetBuilder

fun main(args: Array<String>) {
    val set = ChronicleSetBuilder.of<CarValue>(CarValue::class.java)
            .entries(50_000)
            .create() as ChronicleSet<CarValue>

    set.add(CarValueBuilder.getCar(1, "ford", "focus", listOf("black", "fwd")))

    val cqe = ConcurrentIndexedCollection<CarValue>(WrappingPersistence.aroundCollectionOnPrimaryKey<CarValue, Long>(set, id))
    cqe.addIndex(UniqueIndex.onAttribute(id))
    cqe.addIndex(
            CompoundIndex.onAttributes(
                    CompoundIndexMapFactory(),
                    StoredSetBasedResultSetFactory(),
                    make, model, features
            )
    )
    cqe.addAll(listOf(
            CarValueBuilder.getCar(2, "toyota", "prius", listOf("silver", "fwd", "hybrid")),
            CarValueBuilder.getCar(3, "honda", "civic", listOf("white", "fwd")),
            CarValueBuilder.getCar(4, "ford", "explorer", listOf("blue", "awd")),
            CarValueBuilder.getCar(5, "tesla", "model3", listOf("red", "awd", "electric"))
    ))

    val results = cqe.retrieve(and(equal(make, "ford"), equal(model, "focus"), equal(features, "black")))

    for (c in results) {
        println(c)
    }
}

private val id = attribute<CarValue, Long>(CarValue::class.java, Long::class.java, "id") { car -> car.id }
private val make = attribute<CarValue, String>(CarValue::class.java, String::class.java, "make") { car -> car.make }
private val model = attribute<CarValue, String>(CarValue::class.java, String::class.java, "model") { car -> car.model }
//private val features = attribute<CarValue, String, List<String>>(CarValue::class.java, String::class.java, "features") { car -> IntRange(0, car.featuresLength).map { car.getFeatureAt(it) } }
private val features = attribute<CarValue, String, List<String>>(CarValue::class.java, String::class.java, "features") { car -> car.features }


