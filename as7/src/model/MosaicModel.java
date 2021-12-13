package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A model which represents the ability to create a mosaic of an.
 */
public class MosaicModel extends AbstractImageProcessingModel {
  private int numSeeds;
  private int minValue;
  private Map<String, ArrayList<Position>> seedMap;
  private ArrayList<Position> seedPositions;

  /**
   * Creates an instance of the mosaic model which includes the need of a given number of seeds.
   * If the number of seeds is less than 1, it defaults to 1 seed.
   * @param data the 3d int array that represents the image's pixel data.
   * @param numSeeds an int representing the number of seeds to create
   */
  public MosaicModel(int[][][] data, int numSeeds) {
    super(data);
    if (numSeeds < 1) {
      numSeeds = 1;
    }
    this.numSeeds = numSeeds;
    this.minValue = 0;
    this.seedMap = new HashMap<String, ArrayList<Position>>();
    this.seedPositions = new ArrayList<>();
  }

  /**
   * Creates an instance of the mosaic model which includes the need of a given number of seeds.
   * If the number of seeds is less than 1, it defaults to 1 seed.
   * @param data the 3d int array that represents the image's pixel data.
   * @param numSeeds an int representing the number of seeds to create
   * @param maxValue which represents the max value a pixel can be.
   */
  public MosaicModel(int[][][] data, int numSeeds, int maxValue) {
    super(data, maxValue);
    if (numSeeds < 1) {
      numSeeds = 1;
    }
    this.numSeeds = numSeeds;
    this.minValue = 0;
    this.seedMap = new HashMap<String, ArrayList<Position>>();
    this.seedPositions = new ArrayList<>();
  }

  /**
   * Create a mosaic of the image.
   */
  @Override
  public void transform() {

    // ===== This is where we actually do the mosaic =====

    // Data for this method
    Random random = new Random();

    // Randomly generate the seeds (number is based on numSeeds)
    // Loop through and add them to a map where the key is the pixel
    // position "x,y" and it stores an empty array list of pixel positions
    // Generate numSeeds number of seeds
    for (int i = 0; i < numSeeds; i++) {
      // Get a random x and y position within the image
      int xPos = random.nextInt(data.length);
      int yPos = random.nextInt(data[0].length);
      // Create the key to store them
      String key1 = xPos + "," + yPos;
      // Add the seed to the seed map, and the list of seed positions
      seedMap.put(key1, new ArrayList<>());
      seedPositions.add(new Position(xPos, yPos));
    }


    // Loop through every pixel in the image
    // For this pixel, find the nearest neighbor (helper)
    // add the pixel to the list for its the nearest seed
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        // Create the current position
        Position currentPosition = new Position(i, j);

        // get the nearest seed to I, J (current pixel)
        Position nearestSeed = getNearestNeighbor(currentPosition);

        // Get the key based on that seed
        String key2 = nearestSeed.xPos + "," + nearestSeed.yPos;

        // Add the current position to the list for the nearestSeed
        ArrayList<Position> tempList = seedMap.get(key2);
        tempList.add(currentPosition);
        seedMap.put(key2, tempList);
      }
    }

    // Loop through all the seeds
    // For each seed, loop through the list of pixels (its cluster)
    // Find the AVG color
    // Set the pixels to the average color
    for (String key3: seedMap.keySet()) {
      // Loop through the positions for this seed
      double totalR = 0;
      double totalG = 0;
      double totalB = 0;
      int count = 0;

      for (Position position: seedMap.get(key3)) {
        // Add the RGB values
        totalR += data[position.xPos][position.yPos][0];
        totalG += data[position.xPos][position.yPos][1];
        totalB += data[position.xPos][position.yPos][2];
        // Add to the count
        count++;
      }

      // Find average for this seed
      totalR = totalR / count;
      totalG = totalG / count;
      totalB = totalB / count;

      // Get the ints
      int avgR = (int) Math.round(totalR);
      int avgG = (int) Math.round(totalG);
      int avgB = (int) Math.round(totalB);

      // Set the positions
      for (Position position: seedMap.get(key3)) {
        // Set the RGB values
        data[position.xPos][position.yPos][0] = avgR;
        data[position.xPos][position.yPos][1] = avgG;
        data[position.xPos][position.yPos][2] = avgB;
      }
    }

  }


  private Position getNearestNeighbor(Position currentPosition) {
    Position temp = new Position(-100, -100);
    int shortestDistance = 100000;
    for (Position cur : seedPositions) {
      int tempDist = Math.abs(currentPosition.xPos - cur.xPos) +
              Math.abs(currentPosition.yPos - cur.yPos);
      if (tempDist < shortestDistance) {
        shortestDistance = tempDist;
        temp.xPos = cur.xPos;
        temp.yPos = cur.yPos;
      }
    }
    return temp;
    // Use seed positions as a list of the positions of the seeds
  }


  /**
   * Class to represent an x and y position.
   */
  public class Position {
    /**
     * The x position.
     */
    public int xPos;
    /**
     * The y position.
     */
    public int yPos;

    /**
     * Constructor to create a position.
     * @param xPos the x position
     * @param yPos the y position
     */
    public Position(int xPos, int yPos) {
      this.xPos = xPos;
      this.yPos = yPos;
    }
  }

}
