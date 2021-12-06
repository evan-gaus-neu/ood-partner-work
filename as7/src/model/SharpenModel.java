package model;

/**
 * A model representing the ability to sharpen an image through filtering.
 */
public class SharpenModel extends AbstractBlurSharpenModel {

  /**
   * Defines a sharp model given only a 3d int array. The matrix is a universal sharpen matrix.
   * @param data an int 3d array representing the image pixel data.
   */
  public SharpenModel(int[][][] data) {
    super(data);
    this.matrix = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
  }

  /**
   * Defines a sharp model given only a 3d int array. The matrix is a universal sharpen matrix.
   * @param data an int 3d array representing the image pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public SharpenModel(int[][][] data, int maxValue) {
    super(data, maxValue);
    this.matrix = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
  }

  /**
   * Defines a sharp model given only a 3d int array. The matrix is a universal sharpen matrix.
   * @param data an int 3d array representing the image pixel data.
   * @param matrix a 2d int array representing the matrix filtering.
   */
  public SharpenModel(int[][][] data, double[][] matrix) {
    super(data, matrix);
    if (matrix.length % 2 != 1) {
      throw new IllegalArgumentException("Matrix must have odd dimensions");
    }
  }

  /**
   * Defines a sharp model given only a 3d int array. The matrix is a universal sharpen matrix.
   * @param data an int 3d array representing the image pixel data.
   * @param maxValue which represents the max value a pixel can be.
   * @param matrix a 2d int array representing the matrix filtering.
   */
  public SharpenModel(int[][][] data, int maxValue, double[][] matrix) {
    super(data, maxValue, matrix);
    if (matrix.length % 2 != 1) {
      throw new IllegalArgumentException("Matrix must have odd dimensions");
    }
  }
}
