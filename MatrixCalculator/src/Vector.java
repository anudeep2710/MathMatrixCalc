import java.util.Arrays;

public class Vector {
    private int dimensions;
    private double[] elements;

    // Constructor
    public Vector(int dimensions) {
        this.dimensions = dimensions;
        this.elements = new double[dimensions];
    }

    // Getter methods
    public int getDimensions() {
        return dimensions;
    }

    // Method to set a specific element in the vector
    public void setElement(int index, double value) {
        if (index >= 0 && index < dimensions) {
            elements[index] = value;
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    // Method to get a specific element from the vector
    public double getElement(int index) {
        if (index >= 0 && index < dimensions) {
            return elements[index];
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    // Method to compute the dot product with another vector
    public double dotProduct(Vector other) {
        if (this.dimensions != other.dimensions) {
            throw new IllegalArgumentException("Vectors must have the same dimensions for dot product");
        }
        double dotProduct = 0;
        for (int i = 0; i < dimensions; i++) {
            dotProduct += this.elements[i] * other.getElement(i);
        }
        return dotProduct;
    }

    // Method to compute the magnitude of the vector
    public double magnitude() {
        double sumOfSquares = 0;
        for (double element : elements) {
            sumOfSquares += Math.pow(element, 2);
        }
        return Math.sqrt(sumOfSquares);
    }

    // Method to normalize the vector (i.e., convert it to a unit vector)
    public Vector normalize() {
        double mag = magnitude();
        Vector normalizedVector = new Vector(dimensions);
        for (int i = 0; i < dimensions; i++) {
            normalizedVector.setElement(i, elements[i] / mag);
        }
        return normalizedVector;
    }

    // Method to compute the angle between this vector and another vector
    public double angleBetween(Vector other) {
        double dotProduct = this.dotProduct(other);
        double magnitudeProduct = this.magnitude() * other.magnitude();
        return Math.acos(dotProduct / magnitudeProduct);
    }

    // Method to scalar multiply the vector by a constant
    public Vector scalarMultiply(double scalar) {
        Vector scaledVector = new Vector(dimensions);
        for (int i = 0; i < dimensions; i++) {
            scaledVector.setElement(i, elements[i] * scalar);
        }
        return scaledVector;
    }

    // Method to add another vector to this vector
    public Vector add(Vector other) {
        if (this.dimensions != other.dimensions) {
            throw new IllegalArgumentException("Vectors must have the same dimensions for addition");
        }
        Vector result = new Vector(dimensions);
        for (int i = 0; i < dimensions; i++) {
            result.setElement(i, this.elements[i] + other.getElement(i));
        }
        return result;
    }

    // Method to subtract another vector from this vector
    public Vector subtract(Vector other) {
        if (this.dimensions != other.dimensions) {
            throw new IllegalArgumentException("Vectors must have the same dimensions for subtraction");
        }
        Vector result = new Vector(dimensions);
        for (int i = 0; i < dimensions; i++) {
            result.setElement(i, this.elements[i] - other.getElement(i));
        }
        return result;
    }

    // Method to display the vector as a string
    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
