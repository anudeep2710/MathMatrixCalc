import java.util.List;

public class EigenvalueDecomposer {
    private Matrix matrix;

    // Constructor
    public EigenvalueDecomposer(Matrix matrix) {
        this.matrix = matrix;
    }

    // Method to decompose eigenvalues
    public List<Double> decomposeEigenvalues() {
        // Implement the logic to decompose eigenvalues
        return MatrixOperations.decomposeEigenvalues(matrix);
    }
}
