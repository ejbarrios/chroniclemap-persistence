package net.quzer.chroniclemappersistence;

import com.googlecode.cqengine.index.support.Factory;
import com.googlecode.cqengine.resultset.stored.StoredResultSet;
import com.googlecode.cqengine.resultset.stored.StoredSetBasedResultSet;
import net.openhft.chronicle.set.ChronicleSetBuilder;

public class StoredSetBasedResultSetFactory implements Factory<StoredResultSet<CarValue>> {
    private final ChronicleSetBuilder<CarValue> builder = ChronicleSetBuilder.of(CarValue.class);

    @Override
    public StoredResultSet<CarValue> create() {
        return new StoredSetBasedResultSet<>(builder.create());
    }
}
