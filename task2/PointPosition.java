package ru.job4j.task;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointPosition {
    public static void main(String[] args) {
        String circlePath = args[0];
        String pointsPath = args[1];
        Circle circle = readCircle(circlePath);
        List<Point> points = readPoints(pointsPath);
        for (Point p : points) {
            System.out.println(pointPosition(p, circle));
        }
    }

    public static int pointPosition(Point p, Circle c) {
        float d = Point.distance(p, c.getCenter());
        float r = c.radius;
        if (d == r) {
            return 0;
        }
        return d < r ? 1 : 2;
    }

    public static Circle readCircle(String path) {
        Circle circle = null;
        try (Scanner scanner = new Scanner(Paths.get(path))) {
            float x = scanner.nextFloat();
            float y = scanner.nextFloat();
            float r = scanner.nextFloat();
            circle = new Circle(new Point(x, y), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return circle;
    }

    public static List<Point> readPoints(String path) {
        List<Point> points = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(path))) {
            while (scanner.hasNextFloat()) {
                float x = scanner.nextFloat();
                float y = scanner.nextFloat();
                points.add(new Point(x, y));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    private static class Circle {
        private final Point center;
        private final float radius;

        public Circle(Point center, float radius) {
            this.center = center;
            this.radius = radius;
        }

        public Point getCenter() {
            return center;
        }
    }

    private static class Point {
        private final float x;
        private final float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public static float distance(Point p1, Point p2) {
            float x1 = p1.getX();
            float y1 = p1.getY();
            float x2 = p2.getX();
            float y2 = p2.getY();
            x1 -= x2;
            y1 -= y2;
            return (float) Math.sqrt(x1 * x1 + y1 * y1);
        }
    }
}

