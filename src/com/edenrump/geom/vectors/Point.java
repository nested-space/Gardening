package com.edenrump.geom.vectors;

public interface Point {

    float[] getCoordinates();

    float distanceToPoint(Point point);

    void set(Point point);

    int getDimensions();
}
