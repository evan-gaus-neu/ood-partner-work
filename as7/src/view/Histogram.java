package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * Represents a Histogram object.
 */
public class Histogram extends JPanel {

  private int[][] coordinates;

  /**
   * Constructs a Histogram object.
   *
   * @param coordinates where the histogram should be.
   */
  public Histogram(int[][] coordinates) {
    this.coordinates = coordinates;
  }

  public void changeCoordinates(int[][] coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g1 = (Graphics2D) g;
    g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    double width = 1000;
    double height = 200;
    double x = width / (coordinates[0].length - 1);
    double scale = height / getMax();

    for (int i = 0; i < coordinates[0].length - 1; i++) {
      g1.setPaint(Color.RED);
      double x1 = i * x;
      double y1 = height - scale * coordinates[0][i];
      double x2 = (i + 1) * x;
      double y2 = height - scale * coordinates[0][i + 1];
      g1.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

      g1.setPaint(Color.GREEN);
      x1 = i * x;
      y1 = height - scale * coordinates[1][i];
      x2 = (i + 1) * x;
      y2 = height - scale * coordinates[1][i + 1];
      g1.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

      g1.setPaint(Color.BLUE);
      x1 = i * x;
      y1 = height - scale * coordinates[2][i];
      x2 = (i + 1) * x;
      y2 = height - scale * coordinates[2][i + 1];
      g1.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

      g1.setPaint(Color.DARK_GRAY);
      x1 = i * x;
      y1 = height - scale * coordinates[3][i];
      x2 = (i + 1) * x;
      y2 = height - scale * coordinates[3][i + 1];
      g1.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

  }

  private int getMax() {
    int max = -1;
    if (coordinates.length > 2) {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < coordinates[i].length; j++) {
          if (coordinates[i][j] > max) {
            max = coordinates[i][j];
          }
        }
      }
    }
    return max;
  }
}
