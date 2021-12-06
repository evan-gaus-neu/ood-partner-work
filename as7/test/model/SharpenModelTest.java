package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data of SharpenModel.
 */
public class SharpenModelTest {

  private AbstractBlurSharpenModel sharpenModel1;
  private AbstractBlurSharpenModel sharpenModel2;

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
    sharpenModel2 = new SharpenModel(dataOne);
    sharpenModel1 = new SharpenModel(dataOne, 255);
  }

  @Test
  public void testSharpenModel() {
    assertEquals(132, sharpenModel2.getFiltered(0, 0, 0));
    sharpenModel2.transform();
    assertEquals(132, sharpenModel2.getData()[0][0][0]);
    sharpenModel1.transform();
    assertEquals(132, sharpenModel2.getData()[0][0][0]);
  }

  @Test
  public void testHistograms() {
    assertEquals(4, sharpenModel1.getHistograms()[3][255]);
    assertEquals(1, sharpenModel1.getHistograms()[0][100]);
  }

}