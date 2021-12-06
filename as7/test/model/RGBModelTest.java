package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods in RGBModel.
 */
public class RGBModelTest {

  private ImageProcessingModel rgbRedOne;
  private ImageProcessingModel rgbGreenOne;
  private ImageProcessingModel rgbBlueOne;

  @Before
  public void setUp() {
    int [][][] dataOne = {
            {
                {1, 2, 3, 255},
                {4, 5, 6, 255}
            },
            {
                {7,8,9, 255},
                {10,11,12, 255}
            }
    };
    rgbRedOne = new RGBModel(ImageProcessingModel.RedGreenBlue.R,  dataOne);
    rgbGreenOne = new RGBModel(ImageProcessingModel.RedGreenBlue.G,  dataOne);
    rgbBlueOne = new RGBModel(ImageProcessingModel.RedGreenBlue.B,  dataOne);

  }

  @Test
  public void testTransformRed() {
    rgbRedOne.transform();
    assertEquals(1, rgbRedOne.getData()[0][0][1]);
    assertEquals(4,rgbRedOne.getData()[0][1][2]);
    assertEquals(10, rgbRedOne.getData()[1][1][1]);
  }

  @Test
  public void testTransformGreen() {
    rgbGreenOne.transform();
    assertEquals(2, rgbGreenOne.getData()[0][0][0]);
    assertEquals(2, rgbGreenOne.getData()[0][0][1]);
    assertEquals(2, rgbGreenOne.getData()[0][0][2]);
    assertEquals(8, rgbGreenOne.getData()[1][0][2]);
  }

  @Test
  public void testTransformBlue() {
    rgbBlueOne.transform();
    assertEquals(9, rgbBlueOne.getData()[1][0][1]);
    assertEquals(3, rgbBlueOne.getData()[0][0][2]);
    assertEquals(3, rgbBlueOne.getData()[0][0][0]);

  }

  @Test
  public void testHistograms() {
    assertEquals(4, rgbRedOne.getHistograms()[3][255]);
    assertEquals(1, rgbBlueOne.getHistograms()[0][4]);
  }

}