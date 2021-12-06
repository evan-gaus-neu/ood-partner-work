package model;

/**
 * The model representing vertical flipping of an image data.
 */
public class VerticalModel extends AbstractFlippingModel {

  /**
   * Creates an instance of vertical flipping model.
   * @param data 3d int array of the image's pixels.
   */
  public VerticalModel(int[][][] data) {
    super(data);
  }

  /**
   * Creates an instance of vertical flipping model.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public VerticalModel(int[][][] data, int maxValue) {
    super(data, maxValue);
  }

  /**
   * Grabs the corresponding pixel, which is on the other vertical (height) side.
   * @param i height of the pixel.
   * @param j width of the pixel.
   * @param k color spot of the pixel.
   * @return the corresponding height flipped pixel for the k color.
   */
  @Override
  public int getFlippedPixel(int i, int j, int k) {
    return this.data[data.length - 1 - i][j][k];
  }
}
