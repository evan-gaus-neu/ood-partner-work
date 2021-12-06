package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data of the SepiaModel.
 */
public class SepiaModelTest {
  private AbstractColorTransformation sepiaModel1;
  private AbstractColorTransformation sepiaModel2;

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
    sepiaModel2 = new SepiaModel(dataOne);
    sepiaModel1 = new SepiaModel(dataOne, 255);
  }

  @Test
  public void testSepiaModel() {
    sepiaModel2.transform();
    assertEquals(66, sepiaModel2.getData()[0][1][1]);
    sepiaModel1.transform();
    assertEquals(66, sepiaModel2.getData()[0][1][1]);
    assertEquals(89, (sepiaModel2.convertRGB(sepiaModel2.getData()[0][1], 0)));
  }

  @Test
  public void testHistograms() {
    assertEquals(4, sepiaModel2.getHistograms()[3][255]);
    assertEquals(1, sepiaModel1.getHistograms()[0][100]);
  }

}