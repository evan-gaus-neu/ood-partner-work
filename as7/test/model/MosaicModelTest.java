package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and data of the BrightenDarkenModel.
 */
public class MosaicModelTest {
  private ImageProcessingModel m1;
  private ImageProcessingModel m2;

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
    m1 = new MosaicModel(dataOne, 1, 255);
    m2 = new MosaicModel(dataOne, 2, 255);
  }

  @Test
  public void testMosaicWithOne() {
    m1.transform();
    assertEquals(1, getNumColors(m1.getData()));
  }

  // There is a 1 in 4 chance this fails, because if the two
  // chosen seeds are the same it creates one color
  @Test
  public void testMosaicWithTwo() {
    m2.transform();
    assertEquals(2, getNumColors(m2.getData()));
  }

  @Test
  public void testMosaicOneTwice() {
    m1.transform();
    m1.transform();
    assertEquals(1, getNumColors(m1.getData()));
  }

  @Test
  public void testMosaicOneThenTwo() {
    m1.transform();
    m2.transform();
    assertEquals(1, getNumColors(m1.getData()));
  }

  @Test
  public void testMosaicTwoThenOne() {
    m2.transform();
    m1.transform();
    assertEquals(1, getNumColors(m1.getData()));
  }

  @Test
  public void testHistogramsM1() {
    m1.transform();
    assertEquals(4, m1.getHistograms()[3][255]);
  }

  @Test
  public void testHistogramsM2() {
    m2.transform();
    assertEquals(4, m1.getHistograms()[3][255]);
  }

  // Helper to get the number of different colors in an int array
  private int getNumColors(int[][][] data) {

    ArrayList<String> tempList = new ArrayList<>();

    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        // Create a string of the color
        String colorStr = data[i][j][0] + "," + data[i][j][1] + "," + data[i][j][2];
        // If it isn't in the list, add it to the list
        if (!tempList.contains(colorStr)) {
          tempList.add(colorStr);
        }

      }
    }

    // Return the size of the unique colors list
    return tempList.size();

  }

}