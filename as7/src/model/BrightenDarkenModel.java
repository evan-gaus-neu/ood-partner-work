package model;

/**
 * A model which represents the ability to both brighten and darken an image.
 */
public class BrightenDarkenModel extends AbstractImageProcessingModel {
  private int factor;
  private int minValue;

  /**
   * Creates an instance of the brighten darken model which includes the need of a brighten factor.
   * @param data the 3d int array that represents the image's pixel data.
   * @param factor an int representing how much to brighten/darken
   */
  public BrightenDarkenModel(int[][][] data, int factor) {
    super(data);
    this.factor = factor;
    this.minValue = 0;
  }

  /**
   * Creates an instance of the brighten darken model which includes the need of a brighten factor.
   * @param data the 3d int array that represents the image's pixel data.
   * @param factor an int representing how much to brighten/darken
   * @param maxValue which represents the max value a pixel can be.
   */
  public BrightenDarkenModel(int[][][] data, int factor, int maxValue) {
    super(data, maxValue);
    this.factor = factor;
    this.minValue = 0;
  }

  /**
   * Brightens/Darkens the image.
   * If the image goes over the max or under the min, the pixel gets capped at the max/min.
   */
  @Override
  public void transform() {
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        for (int k = 0; k < 3; k++) {
          data[i][j][k] = data[i][j][k] + this.factor;
          if (data[i][j][k] > this.maxValue) {
            data[i][j][k] = this.maxValue;
          } else if (data[i][j][k] < this.minValue) {
            data[i][j][k] = this.minValue;
          }
        }
      }
    }
  }
}
