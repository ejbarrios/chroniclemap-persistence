package net.quzer.chroniclemappersistence;

import java.util.List;

public class CarValueBuilder {
//    static CarValue getCar(long id, String make, String model, List<String> features) {
//        final CarValue car = Values.newHeapInstance(CarValue.class);
//        car.setId(id);
//        car.setMake(make);
//        car.setModel(model);
//        for(int i = 0; i < features.size(); i++) {
//            car.setFeatureAt(i, features.get(i));
//        }
//        car.setFeaturesLength(features.size());
//
//        return car;
//    }
    static CarValue getCar(long id, String make, String model, List<String> features) {
        final CarValue car = new CarValue();
        car.id = id;
        car.make = make;
        car.model = model;
        car.features = features;
        return car;
    }
}
