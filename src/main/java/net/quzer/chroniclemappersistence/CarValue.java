package net.quzer.chroniclemappersistence;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
public class CarValue implements Serializable {
    public CarValue() {
    }

    //    long getId();
//
//    void setId(long id);
//
//    String getMake();
//
//    void setMake(@MaxUtf8Length(20) String make);
//
//    String getModel();
//
//    void setModel(@MaxUtf8Length(20) String model);
//
//    @Array(length = 20)
//    String getFeatureAt(int index);
//
//    void setFeatureAt(int index, @MaxUtf8Length(20) String feature);
//
//    int getFeaturesLength();
//
//    void setFeaturesLength(int length);
//
    public long id;
    public String make;
    public String model;
    public List<String> features;
}

