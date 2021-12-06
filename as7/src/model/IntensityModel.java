package model;

/**
 * A greyscale model which assigns the spots colors to the average of the original color values.
 */
public class IntensityModel extends AbstractRGBOnlyModel {

  /**
   * Creates an instance of the average color values model.
   * @param data the 3d int array that represents the image's pixel data.
   */
  public IntensityModel(int[][][] data) {
    super(data);
  }

  /**
   * Creates an instance of the average color values model.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public IntensityModel(int[][][] data, int maxValue) {
    super(data, maxValue);
  }

  /**
   * Calculates the average of the spots RGB values.
   * @param rgb the int array of size 3 which represents the rgb values at a spot.
   * @return the average as a truncated int of the spots RGB values.
   */
  @Override
  public int convertRGB(int[] rgb, int k) {
    return (int) (((float) rgb[0] + (float) rgb[1] + (float) rgb[2]) / 3.0);
  }
}
