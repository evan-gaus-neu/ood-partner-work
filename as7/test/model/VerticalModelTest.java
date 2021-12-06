package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data of the VerticalModel.
 */
public class VerticalModelTest {
  private int[][][] dataOne;
  private ImageProcessingModel verticalFlip;

  @Before
  public void setUp() {
    int[][][]  dataOne = {
            {
                {100, 243, 34, 255},
                {4, 56, 157, 255}
            },
            {
                {7,209,90, 255},
                {120,11,12, 255}
            }
    };
    verticalFlip = new VerticalModel(dataOne, 255);
  }

  @Test
  public void testVerticalFlipping() {
    verticalFlip.transform();
    assertEquals(100, verticalFlip.getData()[1][0][0]);
    assertEquals(120, verticalFlip.getData()[0][1][0]);
    assertEquals(157, verticalFlip.getData()[1][1][2]);
  }

  @Test
  public void testHistograms() {
    assertEquals(4, verticalFlip.getHistograms()[3][255]);
    assertEquals(1, verticalFlip.getHistograms()[0][100]);
  }

}