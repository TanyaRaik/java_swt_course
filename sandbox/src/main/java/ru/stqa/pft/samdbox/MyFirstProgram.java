package ru.stqa.pft.samdbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    greeting("Mike");
    greeting("Spike");
    greeting("Nike");

    Square s = new Square();
    s.l = 5;
    System.out.println("Площадь квадрата со стороной " + s.l + " =" + area(s));

    Rectangle r = new Rectangle();
    r.a = 5;
    r.b = 4;
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " =" + area(r));
  }

  public static void greeting(String name){
    System.out.println("Hello, "  + name + '!');
  }

  public static double area(Square s){
    return s.l * s.l;
  }

  public static double area(Rectangle r){
    return r.a * r.b;
  }
}