package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data of the BrightenDarkenModel.
 */
public class BrightenDarkenModelTest {
  private ImageProcessingModel brighten50;
  private ImageProcessingModel darken10;

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
    brighten50 = new BrightenDarkenModel(dataOne, 50, 255);
    darken10 = new BrightenDarkenModel(dataOne, -10, 255);
  }

  @Test
  public void testBrightenMaxValue() {
    brighten50.transform();
    assertEquals(255, brighten50.getData()[0][0][1]);
  }

  @Test
  public void testBrightenRegular() {
    brighten50.transform();
    assertEquals(170, brighten50.getData()[1][1][0]);
    assertEquals(207, brighten50.getData()[0][1][2]);
  }

  @Test
  public void testDarkenMinValue() {
    darken10.transform();
    assertEquals(0, darken10.getData()[0][1][0]);
  }

  @Test
  public void testDarkenRegular() {
    darken10.transform();
    assertEquals(199, darken10.getData()[1][0][1]);
  }

  @Test
  public void testBrightenThenDarkenSameData() {
    brighten50.transform();
    darken10.transform();
    assertEquals(245, darken10.getData()[0][0][1]);
    assertEquals(245, brighten50.getData()[0][0][1]);
    assertEquals(51, darken10.getData()[1][1][1]);
  }

  @Test
  public void testDarkenThenBrightenSameData() {
    darken10.transform();
    brighten50.transform();
    assertEquals(50, darken10.getData()[0][1][0]);
    assertEquals(50, brighten50.getData()[0][1][0]);
  }

  @Test
  public void testBrightenTwiceIncludingMaxValue() {
    brighten50.transform();
    brighten50.transform();
    assertEquals(156, brighten50.getData()[0][1][1]);
    assertEquals(255, brighten50.getData()[0][1][2]);
  }

  @Test
  public void testDarkenTwiceIncludingMinValue() {
    darken10.transform();
    darken10.transform();
    assertEquals(0, darken10.getData()[1][1][2]);
    assertEquals(80, darken10.getData()[0][0][0]);
  }

  @Test
  public void testHistograms() {
    assertEquals(4, brighten50.getHistograms()[3][255]);
    assertEquals(4, darken10.getHistograms()[3][255]);
  }


}