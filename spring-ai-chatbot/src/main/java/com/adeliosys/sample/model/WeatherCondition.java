package com.adeliosys.sample.model;

import java.util.concurrent.ThreadLocalRandom;

public enum WeatherCondition {
    SUNNY,
    CLOUDY,
    RAINY,
    STORMY;

    public static WeatherCondition random() {
        WeatherCondition[] vals = values();
        return vals[ThreadLocalRandom.current().nextInt(vals.length)];
    }
}
