package Task1;

public class Distance {
  public static void main(String args[]) {
    Point points = new Point(10, 20, 3, 50);

    //double distance = distance(point1, point2);

    double distance = points.getDistance();
    System.out.println("Расстояние между точками =" + distance);
  }

  //public static double distance(Point point1, Point point2) {
  //  return Math.sqrt((point2.x - point1.x) * (point2.x - point1.x) + (point2.y - point1.y) * (point2.y - point1.y));
  //}
}
