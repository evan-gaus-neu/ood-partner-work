package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data in the HorizontalModel.
 */
public class HorizontalModelTest {
  private ImageProcessingModel horizontalModel1;

  @Before
  public void setUp() {
    int[][][] dataOne = {
            {
                {100, 243, 34, 255},
                {4, 56, 157, 255}
            },
            {
                {7, 209, 90, 255},
                {120, 11, 12, 255}
            }
    };
    horizontalModel1 = new HorizontalModel(dataOne, 255);
  }

  @Test
  public void testFlipping() {
    horizontalModel1.transform();
    assertEquals(7, horizontalModel1.getData()[1][1][0]);
    assertEquals(120, horizontalModel1.getData()[1][0][0]);
    assertEquals(11, horizontalModel1.getData()[1][0][1]);
    assertEquals(34, horizontalModel1.getData()[0][1][2]);
  }

  @Test
  public void testHistograms() {
    assertEquals(4, horizontalModel1.getHistograms()[3][255]);
    assertEquals(1,horizontalModel1.getHistograms()[0][100]);
  }

}