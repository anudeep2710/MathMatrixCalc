public class InverseCalculator {
    private Matrix matrix;

    // Constructor
    public InverseCalculator(Matrix matrix) {
        this.matrix = matrix;
    }

    // Method to calculate the inverse
    public Matrix calculateInverse() {
        // Implement the logic to calculate the inverse
        return MatrixOperations.calculateInverse(matrix);
    }
}
