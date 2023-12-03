import java.util.Scanner;
import java.util.Locale;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        int numberPeople = 0;
        while(numberPeople <= 1){
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
        scanner.nextLine();
        Calculate.calculator(scanner, numberPeople); //вызываем метод калькулятор с параметрами сканера и колл. человек
        scanner.close();
    }
}

class Calculate{
    public static void calculator(Scanner scanner, int numberPeople){
        StringBuilder products = new StringBuilder(); //пробовал динамический список, пробовал массив, но не получалось дальше, нашел стрингбилдер, правильно ли вообще его использовать?
        StringBuilder priceProduct = new StringBuilder();
        double totalSum = 0.0;
        String input = ""; //переменная для запроса ввода

        while (!input.equalsIgnoreCase("завершить")){
            System.out.println("Введите название товара (или 'завершить' для завершения): ");
            input = scanner.nextLine(); //запрос на ввод товаров

            if(!input.equalsIgnoreCase("завершить")){
                products.append(input).append("\n");
                boolean validPrice = false;
                while (!validPrice) {
                    System.out.print("Введите стоимость товара с копейками (например, 10.45 или 11.40) : ");
                    String priceInput = scanner.nextLine();
                    try {
                        double productPrice = Double.parseDouble(priceInput);
                        if (productPrice > 0) {
                            totalSum += productPrice; //добавляем стоимость товара к общей сумме
                            priceProduct.append(priceInput).append("\n"); //добавляем стоимость товара в список
                            System.out.println("Товар успешно добавлен!\n");
                            validPrice = true;
                        } else {
                            System.out.println("Некорректное значение! Стоимость должна быть больше 0.");
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Некорректный формат стоимости. Попробуйте снова.");
                    }
                }
            }
        }
        //выводим список добавленных товаров и их стоимость
        Formatter.formatPriceProduct(products.toString().split("\n"), priceProduct.toString().split("\n"));
        System.out.println("Общая сумма товаров: " + totalSum + Formatter.format(totalSum)); //выводим общую сумму товаров

        Formatter.format(totalSum, numberPeople); //рассчитываем и выводим сумму для каждого человека
    }
}

class Formatter{
    public static String format(double amount) { //функция определения падежа
        int lastNumber = (int) amount;
        String currency;
        if (lastNumber == 1) {
            currency = " рубль";
        } else if (lastNumber % 10 >= 2 && lastNumber % 10 <= 4 && (lastNumber % 100 < 10 || lastNumber % 100 >= 20)) {
            currency = " рубля";
        } else {
            currency = " рублей";
        }
        return currency;
    }

    public static void formatPriceProduct(String[] productsList, String[] priceProductsList) { //функция вывода стоимости каждого товара с валютой
        System.out.println("Добавленные товары:");
        for (int i = 0; i < productsList.length; i++) {
            System.out.println(productsList[i] + " - " + priceProductsList[i] + format(Double.parseDouble(priceProductsList[i])));
        }
    }

    public static void format(double totalSum, int numberPeople){

        double forOnePerson = totalSum / numberPeople;

        // Выводим сумму для каждого человека с учетом падежа слова "рубль"
        System.out.printf("Каждый должен заплатить: %.2f%s\n", forOnePerson, format(forOnePerson));
    }
}
