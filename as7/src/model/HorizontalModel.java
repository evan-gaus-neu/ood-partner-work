package model;

/**
 * The model representing horizontal flipping of an image data.
 */
public class HorizontalModel extends AbstractFlippingModel {

  /**
   * Creates an instance of horizontal flipping model.
   * @param data 3d int array of the image's pixels.
   */
  public HorizontalModel(int[][][] data) {
    super(data);
  }

  /**
   * Creates an instance of horizontal flipping model.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public HorizontalModel(int[][][] data, int maxValue) {
    super(data, maxValue);
  }

  /**
   * Grabs the corresponding pixel, which is on the other horizontal (width) side.
   * @param i height of the pixel.
   * @param j width of the pixel.
   * @param k color spot of the pixel.
   * @return the corresponding width flipped pixel for the k color.
   */
  @Override
  public int getFlippedPixel(int i, int j, int k) {
    return this.data[i][data[i].length - 1 - j][k];
  }

}
