package model;

/**
 * A model representing the ability to blur an image through filtering.
 */
public class BlurModel extends AbstractBlurSharpenModel {

  /**
   * Defines a blur model given only a 3d int array. The matrix is a universal blur matrix.
   * @param data an int 3d array representing the image pixel data.
   */
  public BlurModel(int[][][] data) {
    super(data);
    this.matrix = new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
  }

  /**
   * Defines a blur model given only a 3d int array. The matrix is a universal blur matrix.
   * @param data an int 3d array representing the image pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public BlurModel(int[][][] data, int maxValue) {
    super(data, maxValue);
    this.matrix = new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
  }

  /**
   * Defines a blur model given only a 3d int array. The matrix allows for matrix configuration.
   * @param data an int 3d array representing the image pixel data.
   * @param matrix a 2d int array representing the matrix filtering.
   */
  public BlurModel(int[][][] data, double[][] matrix) {
    super(data, matrix);
    if (matrix.length % 2 != 1) {
      throw new IllegalArgumentException("Matrix must have odd dimensions");
    }
  }

  /**
   * Defines a blur model given only a 3d int array. The matrix allows for matrix configuration.
   * @param data an int 3d array representing the image pixel data.
   * @param maxValue which represents the max value a pixel can be.
   * @param matrix a 2d int array representing the matrix filtering.
   */
  public BlurModel(int[][][] data, int maxValue, double[][] matrix) {
    super(data, maxValue, matrix);
    if (matrix.length % 2 != 1) {
      throw new IllegalArgumentException("Matrix must have odd dimensions");
    }
  }
}
