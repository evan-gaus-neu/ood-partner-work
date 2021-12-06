package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the data and methods in the IntensityModel.
 */
public class IntensityModelTest {
  private int[][][] dataOne;
  private ImageProcessingModel intensityModel;

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
            },
            {
                {23, 49, 199, 255},
                {200, 1, 9, 255}
            }
    };
    intensityModel = new IntensityModel(dataOne, 255);
  }

  @Test
  public void testIntensityTransform() {
    intensityModel.transform();
    int average10 = (int) (((float) 7 + (float) 209 + (float) 90) / 3.0);
    assertEquals(average10, intensityModel.getData()[1][0][0]);
    assertEquals(average10, intensityModel.getData()[1][0][1]);
    assertEquals(average10, intensityModel.getData()[1][0][2]);
    assertEquals(70, intensityModel.getData()[2][1][0]);
    assertEquals(70, intensityModel.getData()[2][1][1]);
    assertEquals(70, intensityModel.getData()[2][1][2]);


  }

  @Test
  public void testHistograms() {
    assertEquals(6, intensityModel.getHistograms()[3][255]);
    assertEquals(1, intensityModel.getHistograms()[0][100]);
  }


}