package com.edenrump.geom.vectors;

import com.edenrump.geom.Transformable;

public class Point2d implements Point, Transformable {

    private float x, y;

    public Point2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float[] getCoordinates() {
        float[] coords = new float[2];
        coords[0] = x;
        coords[1] = y;
        return coords;
    }

    @Override
    public float distanceToPoint(Point p) {
        if (p == null) throw new IllegalArgumentException("Cannot calculate distance to null");
        if (p.getDimensions() != 2) throw new IllegalArgumentException("Point provided is not two dimensional");
        return distanceToPoint((Point2d) p);
    }

    @Override
    public void move(Point vector) {
        if (vector == null) throw new IllegalArgumentException("Cannot calculate movement for null point provided");
        if (vector.getDimensions() != 2) throw new IllegalArgumentException("Point provided is not two dimensional");
        move((Point2d) vector);
    }

    public void rotate(double angle){
        double cosTheta = Math.cos(angle);
        double sinTheta = Math.sin(angle);
        float cacheX = this.x;
        this.x = (float) (this.x * cosTheta - this.y * sinTheta);
        this.y = (float) (this.y * cosTheta + cacheX * sinTheta);
    }

    @Override
    public void rotate(float angle) {
        rotate((double) angle);
    }

    @Override
    public void scale(Point p) {
        if (p.getDimensions() != 2)
            throw new IllegalArgumentException("Cannot scale 2d point with vector that is not 2d");

        this.x = this.x * p.getCoordinates()[0];
        this.y = this.y * p.getCoordinates()[1];
    }

    @Override
    public void set(Point p) {
        if (p == null) throw new IllegalArgumentException("Cannot set a two dimensional point to null");
        if (p.getDimensions() != 2)
            throw new IllegalArgumentException("Cannot set a two-dimensional point using object that is not Point2d");
        set((Point2d) p);
    }

    @Override
    public int getDimensions() {
        return 2;
    }

    private float distanceToPoint(Point2d p) {
        return (float) Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    }

    private void move(Point2d vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    private void set(Point2d p) {
        this.x = p.x;
        this.y = p.y;
    }
}
