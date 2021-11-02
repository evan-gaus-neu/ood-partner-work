package model;

/**
 * This interface represents operations that an image
 * processing program would complete.
 */
public interface ImageProModel {

  /**
   * Loads a ppm image from the given path into the model.
   * @param path the path of the image
   */
  void loadImage(String path);

  /**
   * Saves the image represented by this model to the given path.
   * @param path the path of the newly saved image
   */
  void saveImage(String path);

  /**
   * Creates a visualization of the red in the
   * image represented by this model.
   * @return the model representing the new image
   */
  ImageProModel redComponent();

  /**
   * Creates a visualization of the green in the
   * image represented by this model.
   * @return the model representing the new image
   */
  ImageProModel greenComponent();

  /**
   * Creates a visualization of the blue in the
   * image represented by this model.
   * @return the model representing the new image
   */
  ImageProModel blueComponent();

  /**
   * Creates a visualization of the value in the
   * image represented by this model.
   * @return the model representing the new image
   */
  ImageProModel valueComponent();

  /**
   * Creates a visualization of the intensity in the
   * image represented by this model.
   * @return the model representing the new image
   */
  ImageProModel intensityComponent();

  /**
   * Creates a visualization of the luma of the
   * image represented by this model.
   * @return the model representing the new image
   */
  ImageProModel lumaComponent();

  /**
   * Creates a new image that is a horizontal flip
   * of this model.
   * @return the model representing the new image
   */
  ImageProModel horFlip();

  /**
   * Creates a new image that is a vertical flip
   * of this model.
   * @return the model representing the new image
   */
  ImageProModel vertFlip();

  /**
   * Creates a new image that is a brightened or
   * darkened version of this model, altered by
   * the increment given.
   *
   * The suggested range for increment is -255 to 255,
   * because anything lower or higher than this range
   * will have the same effect as -255 or 255.
   * Key note positive values will brighten and negative values
   * will darken.
   * @param increment the increment to change the brightness
   * @return the model representing the new image
   */
  ImageProModel brighten(int increment);

}
