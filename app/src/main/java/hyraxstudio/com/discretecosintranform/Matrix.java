package hyraxstudio.com.discretecosintranform;

import java.util.Scanner;

/**
 * Created by truon on 16/10/2016.
 */
public class Matrix {
    private int hang;
    private int cot;
    private Double[][] matrix;

    public Matrix(int hang, int cot) {
        this.hang = hang;
        this.cot = cot;
        matrix = new Double[hang][cot];
    }

    public Matrix() {
//        System.out.print("Nhập số hàng: ");
//        this.hang = new Scanner(System.in).nextInt();
//        System.out.print("Nhập số cột: ");
//        this.cot = new Scanner(System.in).nextInt();
//        this.matrix = new Double[this.getHang()][this.getCot()];
//        for (int i = 0; i < this.getHang(); i++) {
//            for (int j = 0; j < this.getCot(); j++) {
//                System.out.print("T[" + i + "]"+"["+ j +"] = ");
//                this.matrix[i][j] = new Scanner(System.in).nextDouble();
//            }
//        }
    }

    public Matrix(String matrixString) {
        String[] lines = matrixString.split(System.getProperty("line.separator"));
        this.hang = lines.length;
        this.cot = lines[0].split(" ").length;
        this.matrix = new Double[this.getHang()][this.getCot()];
        for (int i = 0; i < lines.length; i++) {
            String oneLine[] = lines[i].split(" ");
            for (int j = 0; j < oneLine.length; j++) {
                this.matrix[i][j] = Double.valueOf(oneLine[j]);
            }
        }
    }

    public void scan() {
        this.matrix = new Double[this.getHang()][this.getCot()];
        for (int i = 0; i < this.getHang(); i++) {
            for (int j = 0; j < this.getCot(); j++) {
                System.out.print("T[" + i + "]"+"["+ j +"] = ");
                this.matrix[i][j] = new Scanner(System.in).nextDouble();
            }
        }
    }
    public void input() {
        this.matrix = new Double[this.hang][this.cot];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < this.hang; i++) {
            for (int j = 0; j < this.cot; j++) {
                this.matrix[i][j] = in.nextDouble();
            }
        }
    }

    public void input(String matrixString) {
        String[] lines = matrixString.split(System.getProperty("line.separator"));
        this.hang = lines.length;
        this.cot = lines[0].split(" ").length;
        this.matrix = new Double[this.getHang()][this.getCot()];
        for (int i = 0; i < lines.length; i++) {
            String oneLine[] = lines[i].split(" ");
            for (int j = 0; j < oneLine.length; j++) {
                this.matrix[i][j] = Double.valueOf(oneLine[j]);
            }
        }
    }

    public int getHang() {
        return hang;
    }

    public void setHang(int hang) {
        this.hang = hang;
    }

    public int getCot() {
        return cot;
    }

    public void setCot(int cot) {
        this.cot = cot;
    }

    public Double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix nhan(Matrix matrix) {
        Matrix matrixResult = new Matrix(this.hang, matrix.cot);
        for(int i = 0; i < this.hang; i++) {
            for(int j = 0; j < matrix.cot; j++) {
                matrixResult.matrix[i][j] = 0.0;
                for(int k = 0; k < matrix.hang; k++) {
                    matrixResult.matrix[i][j] += this.getMatrix()[i][k] * matrix.getMatrix()[k][j];
                }
            }
        }
        return matrixResult;
    }

    public Matrix maTranChuyenVi() {
        Matrix matrixResult = new Matrix(this.hang, this.cot);
        for (int i = 0; i < this.hang; i++) {
            for (int j = 0; j < this.cot; j++) {
                matrixResult.matrix[i][j] = this.matrix[j][i];
            }
        }
        return matrixResult;
    }

    public Matrix createMaTranT() {
        Matrix matrixResult = new Matrix(this.hang, this.cot);
        for (int i = 0; i < this.hang; i++) {
            for (int j = 0; j < this.cot; j++) {
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
        for (int i = 0; i < this.hang; i++) {
            for (int j = 0; j < this.cot; j++) {
                if (String.valueOf(this.matrix[i][j].intValue()).toCharArray().length > maxLen) {
                    maxLen = String.valueOf(this.matrix[i][j].intValue()).toCharArray().length;
                }
            }
        }
        for (int i = 0; i < this.hang; i++) {
            for (int j = 0; j < this.cot; j++) {
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
        for (int i = 0; i < this.hang; i++) {
            for (int j = 0; j < this.cot; j++) {
                this.matrix[i][j] = Double.valueOf(Math.round(this.matrix[i][j]));
            }
        }
    }

    public Matrix bienDoiDCT() {
        Matrix maTranT = this.createMaTranT();
        Matrix maTranCV = maTranT.maTranChuyenVi();
        Matrix matrixDCT = maTranT.nhan(this).nhan(maTranCV);
        matrixDCT.round();
        return matrixDCT;
    }
}

