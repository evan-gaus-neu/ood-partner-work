package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data of the LumaModel.
 */
public class LumaModelTest {
  private ImageProcessingModel lumaModel;

  @Before
  public void setUp() {
    int[][][]  data = {
            {
                {100, 243, 34, 255},
                {4, 56, 157, 255}
            },
            {
                {7,209,90, 255},
                {120,11,12, 255}
            },
            {
                {23,49,199, 255},
                {200,1,9, 255}
            }
    };
    lumaModel = new LumaModel(data, 255);
  }

  @Test
  public void testLumaTransform() {
    lumaModel.transform();
    int luma00 = (int) (0.2126 * 100 + 0.7152 * 243 + 0.0722 * 34);
    assertEquals(luma00, lumaModel.getData()[0][0][0]);
    assertEquals(luma00, lumaModel.getData()[0][0][1]);
    assertEquals(luma00, lumaModel.getData()[0][0][2]);


  }

  @Test
  public void testHistograms() {
    assertEquals(6, lumaModel.getHistograms()[3][255]);
    assertEquals(1, lumaModel.getHistograms()[0][100]);
  }

}