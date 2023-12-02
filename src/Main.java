import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        double priceProduct;
        double sum;
        int numberPeople = 0;
        while(numberPeople <= 1) {
            System.out.println("На скольких человек необходимо разделить счёт? Значение должно быть больше 1");
            if (scanner.hasNextInt()) {
                numberPeople = scanner.nextInt();
                if (numberPeople <= 1) {
                    System.out.println("Приятнее кушать в компании, повтори ввод.");
                }
            } else {
                System.out.println("Некорректное значение, попробуй еще раз.");
                scanner.next();
            }
        }

    }
}
    /**
    class public Calculate(Scanner scanner, ) {
        double ;
        System.out.println("");

        void calculator() {


        }
     */





