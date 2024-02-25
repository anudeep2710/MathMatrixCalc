public class DeterminantCalculator {
    private Matrix matrix;

    // Constructor
    public DeterminantCalculator(Matrix matrix) {
        this.matrix = matrix;
    }

    // Method to calculate the determinant
    public double calculateDeterminant() {
        // Implement the logic to calculate the determinant
        return MatrixOperations.calculateDeterminant(matrix);
    }
}
