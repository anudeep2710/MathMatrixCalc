public class RowVector {
    private int dimensions;
    private double[] elements;

    // Constructor
    public RowVector(int dimensions) {
        this.dimensions = dimensions;
        this.elements = new double[dimensions];
    }

    // Getter method for element at specified index
    public double get(int index) {
        if (index < 0 || index >= dimensions) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return elements[index];
    }

    // Setter method for element at specified index
    public void set(int index, double value) {
        if (index < 0 || index >= dimensions) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        elements[index] = value;
    }

    // Method to append a value to the vector
    public void append(double value) {
        double[] newElements = new double[dimensions + 1];
        System.arraycopy(elements, 0, newElements, 0, dimensions);
        newElements[dimensions] = value;
        elements = newElements;
        dimensions++;
    }

    // Method to prepend a value to the vector
    public void prepend(double value) {
        double[] newElements = new double[dimensions + 1];
        newElements[0] = value;
        System.arraycopy(elements, 0, newElements, 1, dimensions);
        elements = newElements;
        dimensions++;
    }

    // Method to create a slice of the vector
    public RowVector slice(int start, int end) {
        if (start < 0 || end >= dimensions || start > end) {
            throw new IllegalArgumentException("Invalid start or end index");
        }
        RowVector slicedVector = new RowVector(end - start + 1);
        for (int i = start; i <= end; i++) {
            slicedVector.set(i - start, elements[i]);
        }
        return slicedVector;
    }

    // Method to create a copy of the vector
    public RowVector copy() {
        RowVector copiedVector = new RowVector(dimensions);
        System.arraycopy(elements, 0, copiedVector.elements, 0, dimensions);
        return copiedVector;
    }

    // Method to compute the dot product with another vector
    public double dotProduct(RowVector other) {
        if (this.dimensions != other.dimensions) {
            throw new IllegalArgumentException("Vectors must have the same dimensions for dot product");
        }
        double dotProduct = 0;
        for (int i = 0; i < dimensions; i++) {
            dotProduct += this.elements[i] * other.get(i);
        }
        return dotProduct;
    }
}
