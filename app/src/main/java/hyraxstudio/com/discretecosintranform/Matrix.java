package hyraxstudio.com.discretecosintranform;

import java.util.Scanner;

/**
 * Created by truon on 16/10/2016.
 */
public class Matrix {
    private int row;
    private int col;
    private Double[][] matrix;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        matrix = new Double[row][col];
    }

    public Matrix() {
    }

    public Matrix(String matrixString) {
        String[] lines = matrixString.split(System.getProperty("line.separator"));
        this.row = lines.length;
        this.col = lines[0].split(" ").length;
        this.matrix = new Double[this.getRow()][this.getCol()];
        for (int i = 0; i < lines.length; i++) {
            String oneLine[] = lines[i].split(" ");
            for (int j = 0; j < oneLine.length; j++) {
                this.matrix[i][j] = Double.valueOf(oneLine[j]);
            }
        }
    }

    public void input(String matrixString) {
        String[] lines = matrixString.split(System.getProperty("line.separator"));
        this.row = lines.length;
        this.col = lines[0].split(" ").length;
        this.matrix = new Double[this.getRow()][this.getCol()];
        for (int i = 0; i < lines.length; i++) {
            String oneLine[] = lines[i].split(" ");
            for (int j = 0; j < oneLine.length; j++) {
                this.matrix[i][j] = Double.valueOf(oneLine[j]);
            }
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix integrated(Matrix matrix) {
        Matrix matrixResult = new Matrix(this.row, matrix.col);
        for(int i = 0; i < this.row; i++) {
            for(int j = 0; j < matrix.col; j++) {
                matrixResult.matrix[i][j] = 0.0;
                for(int k = 0; k < matrix.row; k++) {
                    matrixResult.matrix[i][j] += this.getMatrix()[i][k] * matrix.getMatrix()[k][j];
                }
            }
        }
        return matrixResult;
    }

    public Matrix matrixCrowePosition() {
        Matrix matrixResult = new Matrix(this.row, this.col);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                matrixResult.matrix[i][j] = this.matrix[j][i];
            }
        }
        return matrixResult;
    }

    public Matrix createMatrixT() {
        Matrix matrixResult = new Matrix(this.row, this.col);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (i == 0) {
                    matrixResult.matrix[i][j] = 1.0 / Math.pow(this.matrix.length, 0.5);
                } else if (i > 0) {
                    matrixResult.matrix[i][j] = Math.pow(2.0 / this.matrix.length, 0.5) * Math.cos(((2.0 * j + 1.0)
                            * i * Math.PI)/(2.0 * this.matrix.length));
                }
            }
        }
        return matrixResult;
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        int maxLen = 0;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (String.valueOf(this.matrix[i][j].intValue()).toCharArray().length > maxLen) {
                    maxLen = String.valueOf(this.matrix[i][j].intValue()).toCharArray().length;
                }
            }
        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                for (int k = 0; k < maxLen - String.valueOf(this.matrix[i][j].intValue()).toCharArray().length; k++) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(this.matrix[i][j].intValue()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void round() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.matrix[i][j] = Double.valueOf(Math.round(this.matrix[i][j]));
            }
        }
    }

    public Matrix transformDCT() {
        Matrix maTranT = this.createMatrixT();
        Matrix maTranCV = maTranT.matrixCrowePosition();
        Matrix matrixDCT = maTranT.integrated(this).integrated(maTranCV);
        matrixDCT.round();
        return matrixDCT;
    }
}

