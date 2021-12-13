package model;

import data.ColorPixel;

/**
 * This interface represents operations that an image
 * processing program would complete.
 */
public interface ImageProModel {

  /**
   * Loads a ppm image from the given path into the model.
   * @param name the name of the image
   * @param image the image to load into the system
   */
  void loadImage(String name, ColorPixel[][] image);


  /**
   * Returns the image of the given name, so that it can be saved.
   * @param name the name of the image to be saved
   * @throws IllegalArgumentException if the name of the image to be saved
   *                                  doesn't exist in the map of images
   */
  ColorPixel[][] saveImage(String name) throws IllegalArgumentException;


  /**
   * Creates a visualization of the red in the
   * image represented by this model.
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void redComponent(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a visualization of the green in the
   * image represented by this model.
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void greenComponent(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a visualization of the blue in the
   * image represented by this model.
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void blueComponent(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a visualization of the value in the
   * image represented by this model.
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void valueComponent(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a visualization of the intensity in the
   * image represented by this model.
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void intensityComponent(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a visualization of the luma of the
   * image represented by this model.
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void lumaComponent(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a new image that is a horizontal flip
   * of this model.
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void horFlip(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a new image that is a vertical flip
   * of this model.
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void vertFlip(String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a new image that is a brightened or
   * darkened version of this model, altered by
   * the increment given.
   * The suggested range for increment is -255 to 255,
   * because anything lower or higher than this range
   * will have the same effect as -255 or 255.
   * Key note positive values will brighten and negative values
   * will darken.
   * @param increment the increment to change the brightness
   * @param name the name of the image to copy
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be copied
   *                                  doesn't exist in the map of images
   */
  void brighten(int increment, String name, String dest) throws IllegalArgumentException;

  /**
   * Creates a new downscaled version of the image, set to the new width and height.
   * @param newWidth the width of the new downsized image
   * @param newHeight the height of the new downsized image
   * @param name the name of the starter image
   * @param dest the name of the destination image
   * @throws IllegalArgumentException if the name of the image to be manipulated
   *                                  doesn't exist in the map of images
   */
  void resize(int newWidth, int newHeight, String name, String dest) throws IllegalArgumentException;

}
