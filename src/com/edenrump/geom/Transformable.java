package com.edenrump.geom;

import com.edenrump.geom.vectors.Point;

public interface Transformable {

    void move(Point vector);

    void rotate(float angle);

    void scale(Point vector);
}
