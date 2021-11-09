package model;

/**
 * This interface represents updated operations that
 * a sample image processor would offer.
 */
public interface IPMV2 extends ImageProModel {

  /**
   * Blurs the given image using Gaussian blur.
   * @param name the name of the image to be blurred.
   * @param dest the name of the blurred image.
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void blur(String name, String dest) throws IllegalArgumentException;

  /**
   * Sharpens an image, creating a new image with sharper boundaries between regions.
   * @param name the name of the image to be sharpened
   * @param dest the name of the sharper image
   * @throws IllegalArgumentException if the name of the images to be copied
   *                                  doesn't exist in the map of images
   */
  void sharpen(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a greyscale version of the given image using a filter.
   * @param name the name of the image to be greyscaled
   * @param dest the name of the greyscale image
   * @throws IllegalArgumentException if the name of the images to be copied
   *                                  doesn't exist in the map of images
   */
  void greyscale(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a sepia version of a given image using a filter.
   * @param name the name of the image to be manipulated
   * @param dest the name of the sepia image
   * @throws IllegalArgumentException if the name of the images to be copied
   *                                  doesn't exist in the map of images
   */
  void sepia(String name, String dest) throws IllegalArgumentException;

}
