package com.edenrump.geom.shapes;

import com.edenrump.geom.Transformable;
import com.edenrump.geom.vectors.Point;
import com.edenrump.geom.vectors.Point2d;

/**
 * Simple instantiation of Polygon that supports transformations
 * <p>
 * SimplePolygon2d does not cache transformations as a Transformation object, but applies them directly to the points
 */
public class SimplePolygon2d implements Polygon, Transformable {

    private Point2d[] points;

    public SimplePolygon2d(Point... points) {
        this.points = copyPointArrayToPoint2dArray(points);
    }

    @Override
    public Point[] getPoints() {
        return points;
    }

    @Override
    public void setPoints(Point[] newPoints) {
        points = copyPointArrayToPoint2dArray(newPoints);
    }

    @Override
    public void move(Point vector) {
        if (vector.getDimensions() != 2)
            throw new IllegalArgumentException("Non-2d point cannot be used as vector for 2d polygon");

        for (Point2d point : points) {
            point.move(vector);
        }
    }

    public void rotate(double angle) {
        if (angle == 0) return;

        for (Point2d p : points) {
            p.rotate(angle);
        }
    }

    @Override
    public void rotate(float angle) {
        rotate((double) angle);
    }

    @Override
    public void scale(Point vector) {
        if (vector.getDimensions() != 2)
            throw new IllegalArgumentException("Cannot scale 2d polygon with non-2d vector");

        for (Point2d p : points) {
            p.scale(vector);
        }
    }

    private Point2d[] copyPointArrayToPoint2dArray(Point[] points) {
        if (!allPointsAre2d(points))
            throw new IllegalArgumentException("Cannot set points with array containing non-2d points");

        Point2d[] coercedPoints = new Point2d[points.length];
        for (int i = 0; i < points.length; i++) {
            float[] coords = points[i].getCoordinates();
            coercedPoints[i] = new Point2d(coords[0], coords[1]);
        }
        return coercedPoints;
    }

    private boolean allPointsAre2d(Point[] points) {
        if (points == null || points.length == 0) return true;
        for (Point p : points) {
            if(p == null) return false;
            if (p.getDimensions() != 2) return false;
        }
        return true;
    }

}
