package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data of BlurModel.
 */
public class BlurModelTest {
  private AbstractBlurSharpenModel blurModel1;
  private AbstractBlurSharpenModel blurModel2;

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
    blurModel2 = new BlurModel(dataOne);
    blurModel1 = new BlurModel(dataOne, 255);
  }

  @Test
  public void testBlurModel() {
    assertEquals(33, blurModel2.getFiltered(0,0,0));
    blurModel2.transform();
    assertEquals(33, blurModel2.getData()[0][0][0]);
    blurModel1.transform();
    assertEquals(33, blurModel2.getData()[0][0][0]);
  }

  @Test
  public void testHistograms() {
    assertEquals(4, blurModel2.getHistograms()[3][255]);
  }

}