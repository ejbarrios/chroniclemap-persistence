package net.quzer.chroniclemappersistence;

import com.googlecode.cqengine.index.compound.support.CompoundValueTuple;
import com.googlecode.cqengine.index.support.Factory;
import com.googlecode.cqengine.resultset.stored.StoredResultSet;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import java.util.concurrent.ConcurrentMap;

public class CompoundIndexMapFactory implements Factory<ConcurrentMap<CompoundValueTuple<CarValue>, StoredResultSet<CarValue>>> {
    private final ChronicleMapBuilder<CompoundValueTuple<CarValue>, StoredResultSet<CarValue>> builder =
            ChronicleMapBuilder.of(
                    (Class<CompoundValueTuple<CarValue>>)(Class<?>)CompoundValueTuple.class,
                    (Class<StoredResultSet<CarValue>>)(Class<?>)StoredResultSet.class)
            .keyMarshaller(new CompoundValueTupleSerialization())
            .valueMarshaller(new StoredResultSetSerialization());

    @Override
    public ConcurrentMap<CompoundValueTuple<CarValue>, StoredResultSet<CarValue>> create() {
        return builder.create();
    }
}

