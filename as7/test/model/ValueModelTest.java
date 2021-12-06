package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data of the ValueModel.
 */
public class ValueModelTest {
  private int[][][] dataOne;
  private ImageProcessingModel valueModel;

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
            },
            {
                {23,49,199, 255},
                {200,1,9, 255}
            }
    };
    valueModel = new ValueModel(dataOne, 255);
  }

  @Test
  public void testValueTransform() {
    valueModel.transform();
    assertEquals(200, valueModel.getData()[2][1][0]);
    assertEquals(200, valueModel.getData()[2][1][1]);
    assertEquals(200, valueModel.getData()[2][1][2]);
  }

  @Test
  public void testHistograms() {
    assertEquals(6, valueModel.getHistograms()[3][255]);
    assertEquals(1, valueModel.getHistograms()[0][100]);
  }

}