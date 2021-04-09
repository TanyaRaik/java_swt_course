package ru.stqa.pft.samdbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    greeting("Mike");
    greeting("Spike");
    greeting("Nike");

    double len = 5;
    System.out.println("Площадь квадрата со стороной " + len + " =" + area(len));
    double a = 5;
    double b = 4;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " =" + area(a, b));
  }

  public static void greeting(String name){
    System.out.println("Hello, "  + name + '!');
  }

  public static double area(double val){
    return val * val;
  }

  public static double area(double a, double b){
    return a * b;
  }
}