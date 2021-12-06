package model;

/**
 * Provides an abstract class which represents the general implementation that all models use.
 */
public abstract class AbstractImageProcessingModel implements ImageProcessingModel {

  protected int[][][] data;
  protected int maxValue;

  /**
   * A constructor that assigns the image's pixel int data to the field.
   * @param data the 3d int array that represents the image's pixel data.
   */
  public AbstractImageProcessingModel(int[][][] data) {
    this.data = data;
    this.maxValue = 255;
  }

  /**
   * A constructor that assigns the image's pixel int data and the max value pixel to the fields.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public AbstractImageProcessingModel(int[][][] data, int maxValue) {
    this.data = data;
    this.maxValue = maxValue;
  }

  /**
   * The transformation function that modifies the data of the image.
   */
  @Override
  abstract public void transform();

  /**
   * Grabs the int 3d array that represents the image's pixel data.
   * @return the int 3d array of the image.
   */
  @Override
  public int[][][] getData() {
    return this.data;
  }

  /**
   * Grabs the max value that represents a pixel's possible max value.
   * @return the int representing the pixel's possible max value.
   */
  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public int[][] getHistograms() {
    int[][] hist = new int[4][256];
    for (int i = 0; i < this.data.length; i++) {
      for (int j = 0; j < this.data[i].length; j++) {
        for (int k = 0; k < this.data[i][j].length; k++) {
          hist[k][this.data[i][j][k]] += 1;
        }
      }
    }
    return hist;
  }
}
