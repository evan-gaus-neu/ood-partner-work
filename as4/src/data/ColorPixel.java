package data;

/**
 * Class to represent a color, broken down into 3 values,
 * red, green, and blue. Could be extended to also represent
 * a color with an opacity value. Individual RGB values can
 * be got and set.
 * NOTE: The setters round the "set value" to be within the
 * range, with 0 being the lowest value and 255 being the
 * highest.
 */
public class ColorPixel {

  // Data
  private int r;
  private int g;
  private int b;

  // Constructor
  /**
   * Constructs a color object, with given Red, Green, and Blue values
   * (with 0 being the lowest possible value for an individual color,
   * and 255 being the highest).
   * @param r the value for red
   * @param g the value for green
   * @param b the value for blue
   * @throws IllegalArgumentException if any of the given color values are invalid
   */
  public ColorPixel(int r, int g, int b) throws IllegalArgumentException {

    // Check if the inputs are valid
    if (r < 0 || r > 255) {
      throw new IllegalArgumentException("Red value was invalid");
    }
    if (g < 0 || g > 255) {
      throw new IllegalArgumentException("Green value was invalid");
    }
    if (b < 0 || b > 255) {
      throw new IllegalArgumentException("Blue value was invalid");
    }
    // Set the data
    this.r = r;
    this.g = g;
    this.b = b;
  }

  // Methods

  /**
   * Gets the value of R for this color.
   * @return the int value R (from 0-255)
   */
  public int getR() {
    return r;
  }

  /**
   * Sets the value of R for this color, if the value is in the range 0-255.
   * If the value is less than 0, R is set to 0, and if the value is greater
   * than 255, R is set to 255.
   * @param r the new value of R for this color (0-255)
   */
  public void setR(int r) {
    if (r < 0) {
      this.r = 0;
    }
    else if (r > 255) {
      this.r = 255;
    }
    else {
      this.r = r;
    }
  }

  /**
   * Gets the value of G for this color.
   * @return the int value G (from 0-255)
   */
  public int getG() {
    return g;
  }

  /**
   * Sets the value of G for this color, if the value is in the range 0-255.
   * If the value is less than 0, G is set to 0, and if the value is greater
   * than 255, G is set to 255.
   * @param g the new value of R for this color (0-255)
   */
  public void setG(int g) {
    if (g < 0) {
      this.g = 0;
    }
    else if (g > 255) {
      this.g = 255;
    }
    else {
      this.g = g;
    }
  }

  /**
   * Gets the value of B for this color.
   * @return the int value B (from 0-255)
   */
  public int getB() {
    return b;
  }

  /**
   * Sets the value of B for this color, if the value is in the range 0-255.
   * If the value is less than 0, B is set to 0, and if the value is greater
   * than 255, B is set to 255.
   * @param b the new value of R for this color (0-255)
   */
  public void setB(int b) {
    if (b < 0) {
      this.b = 0;
    }
    else if (b > 255) {
      this.b = 255;
    }
    else {
      this.b = b;
    }
  }

}
