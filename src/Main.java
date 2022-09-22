import com.sun.jdi.connect.TransportTimeoutException;

import java.util.Scanner;

public class Main {
       public static void main(String[] args) {
           System.out.println("Введите выражение используя арабские или римские цифры не больше 10");
           Scanner fScanner = new Scanner(System.in);
           String input= fScanner.nextLine();

           System.out.println(calc(input));


       }
    public static String calc(String input) {
           String s = "*/-+"; // делаем строку, содержащию все операции, чтобы потом разделить на две части выражение
           String operator = ""; // находим в строке +-... для разделения двух частей
           String operator2 = ""; // для проверки на наличие дополнительных операторов
           String firstNum = ""; // первая цифра
           String secondNum = ""; // вторая цифра
           int a = 0; // переведенная из строки арабская цифра
           int b = 0; // переведенная из строки арабская цифра
           int result = 0;
           int countOperators = 0;
           String test = "";
           boolean error = false;
           boolean rome1 = false;
           boolean rome2 = false;
           boolean romeTrue = false;
           String[] rome = new String[] {"I", "II", "III", "IV", "V", "VI","VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL",
                "XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L",
                "LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX",
                "LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX",
                "LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX",
                "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC",
                "XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C",};
        // гребанные римляне )) не смог ничего лучше придумать (


               for (int i=0; i<input.length(); i++)
           {
               operator = input.substring(i,i+1); // находим математическую операцию +-*/
               if (s.contains(operator))
               {
                   firstNum = input.substring(0,i);
                   secondNum = input.substring(i+1);
                   break;
               }

           }

               if (!s.contains(operator)){  // проверка есть ли оператор в выражении, если нет то значит это не операция
                   try {
                       throw new Exception();

                   } catch (Exception e) {
                       error=true;
                       System.out.println("т.к. строка не является математической операцией");
                   }
               }

        for (int i=0; i<secondNum.length(); i++) // проверяем есть ли операторы во второй части и делаем исключение
        {
            operator2 = secondNum.substring(i,i+1);
            if (s.contains(operator2))
            {
                try {
                    throw new Exception();

                } catch (Exception e) {
                    error=true;
                    System.out.println("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор");
                }
                break;
            }

        }


            for (int i=0; i< rome.length; i++) //проверяем не являются ли наши вводные римскими цифрами и наши а и b присваиваем индексу
            {
                if (firstNum.equals(rome[i])) {
                    a = i + 1;
                    rome1=true;
                }
                if (secondNum.equals(rome[i])) {
                    b = i + 1;
                    rome2=true;
                }

            }
            if (rome1 && rome2){
                romeTrue=true;
            }

            if ((rome1 && !rome2 || !rome1 && rome2)&& !s.contains(operator2)){ //исключение если одна цифра римская а другая арабская
                try {
                    throw new Exception();

                } catch (Exception e) {
                    error=true;
                    System.out.println("т.к. используются одновременно разные системы счисления");
                }
            }


        if (!romeTrue && !error){    // если нет римских цифр, просто переводим строку в инт

                a = Integer.parseInt(firstNum);
                b = Integer.parseInt(secondNum);
            }



        if (a>10 || b>10){ //проверяем условия, что введенные числа меньше или =10
            try {
                throw new Exception();

            } catch (Exception e) {
                error=true;
                System.out.println("т.к. введенные числа больше 10");
            }
        }



        switch (operator) {                        // выбираем нужную математическую операцию, производим вычисление
            case "*":
                result=a*b;
                break;
            case "/":
                result=a/b;
                break;
            case "+":
                result=a+b;
                break;
            case "-":
                result=a-b;
                break;

        }
            if (!romeTrue){                 // если арабские то результат переводим в строку
                test = Integer.toString(result);
            }  if  (romeTrue && result<1){   // если римские проверяем не будет ли отрицательное число или 0 (исключение) и присваиваем результат строке из массива
            try {
                throw new Exception();
            } catch (Exception e) {
                error=true;
                System.out.println("т.к. в римской системе нет отрицательных чисел и нуля");
            }
        }
            if (romeTrue && result>=1){
                test = rome[result-1];
            }







            if (error) {  //выводим пустую строку в результат, если была ошибка, но у меня считает если цифры болше 10.
             test="";

            }


        return  test;

    }
}

       /* Немного потестировал, последние по приоритету выдают такую ошибку, хотя можно было бы и две
        1+2 = 3
        5*6 = 30
        8/2 = 4
        12*13 = т.к. введенные числа больше 10


        II*V = X
        VIII/III = II
        V-X  = т.к. в римской системе нет отрицательных чисел и нуля
        L-V = т.к. введенные числа больше 10

        V+5 = т.к. используются одновременно разные системы счисления
        XII-13 = т.к. используются одновременно разные системы счисления
        т.к. введенные числа больше 10

        123  = т.к. строка не является математической операцией
        asdfasdf = т.к. строка не является математической операцией
        1+2+3 = т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор

        X+V+I = т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор

        X-5+7 = т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор

        X-5+12 = т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор*/
