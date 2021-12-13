package model;

import java.util.InputMismatchException;

import data.ColorPixel;

/**
 * The class that acts as the modified Model for this design
 * and is based on the interface ImageProModel. It contains the newer image filters.
 */
public class IPModelV2 extends ImageProModelImpl implements IPMV2 {

  /**
   * Constructor that just calls the super, setting up the map of images.
   */
  public IPModelV2() {
    super();
  }

  // New added methods

  @Override
  public void blur(String name, String dest) throws IllegalArgumentException {
    Double[][] kernel = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
    if (images.containsKey(name)) {
      kernelHelper(name, dest, kernel);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void sharpen(String name, String dest) throws IllegalArgumentException {
    Double[][] kernel = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
    if (images.containsKey(name)) {
      kernelHelper(name, dest, kernel);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  private void kernelHelper(String name, String dest, Double[][] kernel) {
    ColorPixel[][] oldImage = images.get(name);
    ColorPixel [][] newImage = new ColorPixel[oldImage.length][oldImage[0].length];

    newImage = arraySetUp(newImage);

    String [] arr = {"Red", "Green", "Blue"};

    for (int i = 0; i < oldImage.length; i++) {
      for (int j = 0; j < oldImage[i].length; j++) {
        for (String str : arr) {
          int val = computeVal(oldImage, kernel, i, j, str);
          if (str.equals("Red")) {
            newImage[i][j].setR(val);
          }
          else if (str.equals("Green")) {
            newImage[i][j].setG(val);
          }
          else {
            newImage[i][j].setB(val);
          }
        }
      }
    }
    images.put(dest, newImage);
  }

  private int computeVal(ColorPixel [][] givenImage, Double [][] kernel, int x, int y, String str) {
    double tempVal = 0.0;
    int xCor = 0;
    int yCor = 0;
    for (int i = x - kernel.length / 2; i < kernel.length + (x - kernel.length / 2); i++) {
      for (int j = y - kernel.length / 2; j < kernel[0].length + (y - kernel.length / 2); j++) {
        if (!(i < 0 || i >= givenImage.length || j < 0 || j >= givenImage[0].length)) {
          if (str.equals("Red")) {
            tempVal += kernel[xCor][yCor] * givenImage[i][j].getR();
          }
          else if (str.equals("Green")) {
            tempVal += kernel[xCor][yCor] * givenImage[i][j].getG();
          }
          else {
            tempVal += kernel[xCor][yCor] * givenImage[i][j].getB();
          }
        }
        yCor++;
      }
      yCor = 0;
      xCor++;
    }
    int finalVal = (int) Math.round(tempVal);
    return finalVal;
  }

  private ColorPixel [][] arraySetUp(ColorPixel [][] newImage) {
    for (int i = 0; i < newImage.length; i++) {
      for (int j = 0; j < newImage[i].length; j++) {
        newImage[i][j] = new ColorPixel(0,0,0);
      }
    }
    return newImage;
  }

  @Override
  public void greyscale(String name, String dest) throws IllegalArgumentException {
    Double[][] kernel = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    if (images.containsKey(name)) {
      filterHelper(name, dest, kernel);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }

  }

  @Override
  public void sepia(String name, String dest) throws IllegalArgumentException {
    Double[][] kernel = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
    if (images.containsKey(name)) {
      filterHelper(name, dest, kernel);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  private void filterHelper(String name, String dest, Double[][] kernel) {
    ColorPixel[][] oldImage = images.get(name);
    ColorPixel [][] newImage = new ColorPixel[oldImage.length][oldImage[0].length];

    newImage = arraySetUp(newImage);

    String [] arr = {"Red", "Green", "Blue"};

    for (int i = 0; i < oldImage.length; i++) {
      for (int j = 0; j < oldImage[i].length; j++) {
        for (String str : arr) {
          if (str.equals("Red")) {
            Double[] singleKernel = kernel[0];
            int val = computeFilterVal(oldImage, singleKernel, i, j);
            newImage[i][j].setR(val);
          }
          else if (str.equals("Green")) {
            Double[] singleKernel = kernel[1];
            int val = computeFilterVal(oldImage, singleKernel, i, j);
            newImage[i][j].setG(val);
          }
          else {
            Double[] singleKernel = kernel[2];
            int val = computeFilterVal(oldImage, singleKernel, i, j);
            newImage[i][j].setB(val);
          }
        }
      }
    }
    images.put(dest, newImage);
  }

  private int computeFilterVal(ColorPixel [][] givenImage, Double [] kernel, int x, int y) {
    double tempVal = 0.0;
    for (int i = 0; i < kernel.length; i++) {
      if (i == 0) {
        tempVal += kernel[i] * givenImage[x][y].getR();
      }
      else if (i == 1) {
        tempVal += kernel[i] * givenImage[x][y].getG();
      }
      else {
        tempVal += kernel[i] * givenImage[x][y].getB();
      }
    }

    int finalVal = (int) Math.round(tempVal);
    return finalVal;
  }


  @Override
  public void mask(String filter, String name, String mask, String dest, int increment) throws IllegalArgumentException {
    if (images.containsKey(name) && images.containsKey(mask)) {

      // Switch for the filter (BIG)
      switch (filter) {
        case "red-component":
          redComponent(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "green-component":
          greenComponent(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "blue-component":
          blueComponent(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "value-component":
          valueComponent(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "intensity-component":
          intensityComponent(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "luma-component":
          lumaComponent(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "horizontal-flip":
          horFlip(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "vertical-flip":
          vertFlip(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "brighten":
          brighten(increment, name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "blur":
          blur(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "sharpen":
          sharpen(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "greyscale":
          greyscale(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        case "sepia":
          sepia(name, "EvanVinnieDoNotUseThisAsYourName");
          break;
        default: // Unrecognized command
          throw new IllegalArgumentException("Filter command not recognized");
      }

      // Our 3 images
      ColorPixel[][] oldImage = images.get(name);
      ColorPixel[][] newImage = images.get("EvanVinnieDoNotUseThisAsYourName");
      ColorPixel[][] maskImage = images.get(mask);
      // Set up the return image
      ColorPixel[][] returnImage = new ColorPixel[oldImage.length][oldImage[0].length];

      returnImage = arraySetUp(returnImage);

      // Loop through the mask
      for (int i = 0; i < maskImage.length; i++) {
        for (int j = 0; j < maskImage[i].length; j++) {

          ColorPixel maskP = maskImage[i][j];
          ColorPixel origP = oldImage[i][j];
          ColorPixel newP = newImage[i][j];

          // If the mask is black, set the filtered
          if (maskP.getR() == 0 && maskP.getG() == 0 && maskP.getB() == 0) {
            returnImage[i][j].setR(newP.getR());
            returnImage[i][j].setG(newP.getG());
            returnImage[i][j].setB(newP.getB());
          }
          else {
            returnImage[i][j].setR(origP.getR());
            returnImage[i][j].setG(origP.getG());
            returnImage[i][j].setB(origP.getB());
          }
        }
      }
      // If the pixel is black
      //      Take it from the temp (affected image)
      // If the pixel is not black
      //      Take it from the old image (original image)

      // Put it in destination
      images.put(dest, returnImage);
    }
    else {
      throw new IllegalArgumentException("Given name or mask didn't correspond to an image");
    }
  }


}
