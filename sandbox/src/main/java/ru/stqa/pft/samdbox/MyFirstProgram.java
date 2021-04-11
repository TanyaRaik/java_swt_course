package ru.stqa.pft.samdbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    greeting("Mike");
    greeting("Spike");
    greeting("Nike");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " =" + s.area());

    Rectangle r = new Rectangle(5, 4);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " =" + r.area());
  }

  public static void greeting(String name){
    System.out.println("Hello, "  + name + '!');
  }

}