package Task1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void testDistance(){
    Point point1 = new Point(0, 0);
    Point point2 = new Point( 4, 4);
    double distance = point1.distance(point2);
    Assert.assertEquals(distance, 5.656854249492381);
  }

  @Test
  public void testDistanceNotEqual(){
    Point point1 = new Point(0, 0);
    Point point2 = new Point( 4, 4);
    double distance = point1.distance(point2);
    Assert.assertNotEquals(distance, 5.656854249492380);
  }

  @Test
  public void testDistanceFailed(){
    Point point1 = new Point(0, 0);
    Point point2 = new Point( 4, 5);
    double distance = point1.distance(point2);
    Assert.assertEquals(distance, 6);
  }
}

