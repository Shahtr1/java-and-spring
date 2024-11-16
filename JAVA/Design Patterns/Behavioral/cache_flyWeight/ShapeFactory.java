package Behavioral.cache_flyWeight;

import java.util.HashMap;

public class ShapeFactory {
    private static final HashMap<ShapeType, Shape> shapes = new HashMap<>();

    public static Shape getShape(ShapeType shapeType) {
        Shape shapeImpl = shapes.get(shapeType);

        if (shapeImpl == null) {
            if (shapeType.equals(ShapeType.OVAL_FILL)) {
                shapeImpl = new Oval(true);
            } else if (shapeType.equals(ShapeType.OVAL_NOFILL)) {
                shapeImpl = new Oval(false);
            } else if (shapeType.equals(ShapeType.LINE)) {
                shapeImpl = new Line();
            }

            shapes.put(shapeType, shapeImpl);
        }

        return shapeImpl;
    }

    public static enum ShapeType {
        OVAL_FILL, OVAL_NOFILL, LINE
    }
}
