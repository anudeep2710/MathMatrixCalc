import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private  JTextField[][] matrixFields1;
    private JTextField[][] matrixFields2;
    private JTextField[][] vectorFields1;
    private JTextField[][] vectorFields2;

    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton determinantButton;
    private JButton inverseButton;
    private JButton dotProductButton;
    private JButton magnitudeButton;
    private JButton normalizeButton;
    private JButton angleBetweenButton;
    private JButton scalarMultiplyButton;
    private JButton addVectorButton;
    private JButton subtractVectorButton;

    private JPanel matrixPanel1;
    private JPanel matrixPanel2;
    private JPanel vectorPanel1;
    private JPanel vectorPanel2;
    private JPanel resultPanel;

    private JLabel messageLabel;

    private UserMatrixFactory matrixFactory;


    private enum OperationType {
        MATRIX, VECTOR
    }

    public Main(OperationType operationType) {
        setTitle(operationType == OperationType.MATRIX ? "Matrix Operations" : "Vector Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame

        // Initialize components
        matrixFactory = new UserMatrixFactory();

        matrixPanel1 = new JPanel();
        matrixPanel2 = new JPanel();
        vectorPanel1 = new JPanel();
        vectorPanel2 = new JPanel();
        resultPanel = new JPanel(new GridLayout(5, 1));
        messageLabel = new JLabel("");

        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        determinantButton = new JButton("Determinant");
        inverseButton = new JButton("Inverse");
        dotProductButton = new JButton("Dot Product");
        magnitudeButton = new JButton("Magnitude");
        normalizeButton = new JButton("Normalize");
        angleBetweenButton = new JButton("Angle Between");
        scalarMultiplyButton = new JButton("Scalar Multiply");
        addVectorButton = new JButton("Add Vector");
        subtractVectorButton = new JButton("Subtract Vector");

        // Initialize matrix and vector fields with dynamic dimensions
        int matrixRows = 3;
        int matrixCols = 3;
        int vectorRows = 3;
        int vectorCols = 1;
        matrixFields1 = new JTextField[matrixRows][matrixCols];
        matrixFields2 = new JTextField[matrixRows][matrixCols];
        vectorFields1 = new JTextField[vectorRows][vectorCols];
        vectorFields2 = new JTextField[vectorRows][vectorCols];

        // Add action listeners to buttons
        addButton.addActionListener(e -> performOperation(operationType == OperationType.MATRIX ? MatrixOperation.ADD : VectorOperation.ADD_VECTOR));
        subtractButton.addActionListener(e -> performOperation(operationType == OperationType.MATRIX ? MatrixOperation.SUBTRACT : VectorOperation.SUBTRACT_VECTOR));
        multiplyButton.addActionListener(e -> performOperation(operationType == OperationType.MATRIX ? MatrixOperation.MULTIPLY : VectorOperation.SCALAR_MULTIPLY));
        determinantButton.addActionListener(e -> performOperation(MatrixOperation.DETERMINANT));
        inverseButton.addActionListener(e -> performOperation(MatrixOperation.INVERSE));
        dotProductButton.addActionListener(e -> performOperation(VectorOperation.DOT_PRODUCT));
        magnitudeButton.addActionListener(e -> performOperation(VectorOperation.MAGNITUDE));
        normalizeButton.addActionListener(e -> performOperation(VectorOperation.NORMALIZE));
        angleBetweenButton.addActionListener(e -> performOperation(VectorOperation.ANGLE_BETWEEN));

        // Add matrix and vector fields to panels based on operation type
        if (operationType == OperationType.MATRIX) {
            addMatrixFields(matrixPanel1, matrixFields1);
            addMatrixFields(matrixPanel2, matrixFields2);
            resultPanel.add(addButton);
            resultPanel.add(subtractButton);
            resultPanel.add(multiplyButton);
            resultPanel.add(determinantButton);
            resultPanel.add(inverseButton);
        } else {
            addMatrixFields(vectorPanel1, vectorFields1);
            addMatrixFields(vectorPanel2, vectorFields2);
            resultPanel.add(dotProductButton);
            resultPanel.add(magnitudeButton);
            resultPanel.add(normalizeButton);
            resultPanel.add(angleBetweenButton);
            resultPanel.add(scalarMultiplyButton);
            resultPanel.add(addVectorButton);
            resultPanel.add(subtractVectorButton);
        }
        resultPanel.add(messageLabel);

        // Add panels to content pane based on operation type
        getContentPane().setLayout(new GridLayout(1, operationType == OperationType.MATRIX ? 4 : 5));
        if (operationType == OperationType.MATRIX) {
            getContentPane().add(matrixPanel1);
            getContentPane().add(matrixPanel2);
        } else {
            getContentPane().add(vectorPanel1);
            getContentPane().add(vectorPanel2);
        }
        getContentPane().add(resultPanel);

        setVisible(true);
    }

    private void performOperation(Operation operation) {
        try {
            if (operation instanceof MatrixOperation) {
                Matrix matrix1 = matrixFactory.createMatrixFromUserInput(matrixFields1);
                Matrix matrix2 = matrixFactory.createMatrixFromUserInput(matrixFields2);
                Matrix result = null;


                switch ((MatrixOperation) operation) {
                    case ADD:
                        result = MatrixOperations.addMatrices(matrix1, matrix2);
                        break;
                    case SUBTRACT:
                        result = MatrixOperations.subtractMatrices(matrix1, matrix2);
                        break;
                    case MULTIPLY:
                        // Placeholder for scalar multiplication, you need to get scalar value from user input
                        result = MatrixOperations.multiplyMatrices(matrix1, matrix2);
                        break;
                    case DETERMINANT:
                        double determinant1 = MatrixOperations.calculateDeterminant(matrix1);
                        double determinant = MatrixOperations.calculateDeterminant(matrix2);
                        messageLabel.setText("Determinant of Matrix 1and2: " + determinant1+" "+determinant);
                        break;
                    case INVERSE:
                        result = MatrixOperations.calculateInverse(matrix1);
                        result = MatrixOperations.calculateInverse(matrix2);

                        break;
                }
                if (result != null) {
                    displayResult(result);
                    messageLabel.setText("result2");
                    displayResult(result);
                }
            } else if (operation instanceof VectorOperation) {
                Vector vector1 = createVectorFromUserInput(vectorFields1);
                Vector vector2 = createVectorFromUserInput(vectorFields2);
                switch ((VectorOperation) operation) {
                    case DOT_PRODUCT:
                        double dotProduct = vector1.dotProduct(vector2);
                        messageLabel.setText("Dot Product: " + dotProduct);
                        break;
                    case MAGNITUDE:
                        double magnitude = vector1.magnitude();
                        double magnitude1 = vector2.magnitude();
                        messageLabel.setText("Magnitude: " + magnitude);
                        messageLabel.setText("Magnitude: " + magnitude1);

                        break;
                    case NORMALIZE:
                        Vector normalizedVector = vector1.normalize();
                        Vector normalizedVector1 = vector2.normalize();
                        displayResult(normalizedVector);
                        messageLabel.setText("vector2");

                        displayResult(normalizedVector1);


                        break;
                    case ANGLE_BETWEEN:
                        double angle = vector1.angleBetween(vector2);
                        messageLabel.setText("Angle Between: " + angle);
                        break;
                    case SCALAR_MULTIPLY:
                        // Placeholder for scalar multiplication, you need to get scalar value from user input

                        double scalar = 5; // Replace 0.0 with actual scalar value
                        Vector scaledVector = vector1.scalarMultiply(scalar);
                        displayResult(scaledVector);
                        break;
                    case ADD_VECTOR:
                        Vector sumVector = vector1.add(vector2);
                        displayResult(sumVector);
                        break;
                    case SUBTRACT_VECTOR:
                        Vector differenceVector = vector1.subtract(vector2);
                        displayResult(differenceVector);
                        break;
                }
            }
        } catch (IllegalArgumentException ex) {
            messageLabel.setText("Error: " + ex.getMessage());
        }
    }

    // Method to create a vector from user input
    private Vector createVectorFromUserInput(JTextField[][] vectorFields) {
        int dimensions = vectorFields.length;
        Vector vector = new Vector(dimensions);
        for (int i = 0; i < dimensions; i++) {
            double value = Double.parseDouble(vectorFields[i][0].getText());
            vector.setElement(i, value);
        }
        return vector;
    }

    private void addMatrixFields(JPanel panel, JTextField[][] matrixFields) {
        panel.setBorder(BorderFactory.createTitledBorder("Matrix/Vector"));
        panel.setLayout(new GridLayout(matrixFields.length, matrixFields[0].length));
        for (int i = 0; i < matrixFields.length; i++) {
            for (int j = 0; j < matrixFields[0].length; j++) {
                JTextField textField = new JTextField();
                matrixFields[i][j] = textField;
                panel.add(textField);
            }
        }
    }

    private void displayResult(Matrix result) {
        // Display the result matrix in a dialog
        JTextArea resultArea = new JTextArea(result.toString());
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        JOptionPane.showMessageDialog(this, scrollPane, "Result", JOptionPane.PLAIN_MESSAGE);
    }
    private void displayResult(Vector result) {
        // Display the result vector in a dialog
        JTextArea resultArea = new JTextArea(result.toString());
        resultArea.setEditable(false); // Make the JTextArea non-editable
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(100, 100)); // Adjust dimensions as needed
        JOptionPane.showMessageDialog(this, scrollPane, "Result", JOptionPane.PLAIN_MESSAGE);
    }



    public static void main(String[] args) {
        // Prompt the user to select matrix or vector operations
        Object[] options = {"Matrix", "Vector"};
        int choice = JOptionPane.showOptionDialog(null, "Select operation type:", "Operation Type", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        OperationType operationType = choice == 0 ? OperationType.MATRIX : OperationType.VECTOR;
        SwingUtilities.invokeLater(() -> new Main(operationType));
    }
}

// Enum for MatrixOperation
enum MatrixOperation implements Operation {
    ADD, SUBTRACT, MULTIPLY, DETERMINANT, INVERSE;
}

// Enum for VectorOperation
enum VectorOperation implements Operation {
    DOT_PRODUCT, MAGNITUDE, NORMALIZE, ANGLE_BETWEEN, SCALAR_MULTIPLY, ADD_VECTOR, SUBTRACT_VECTOR;
}

// Common interface for MatrixOperation and VectorOperation
interface Operation {
}
