package com.company;

class Example {
    private static int digitsBefore; // количество чисел до запятой в ответе
    private static int digitsAfter; // количество чисел после запятой в ответе
    private static int rounding; // сколько чисел после запятой должно быть в ответе (для округления)
    private static int remainder; // количество чисел после запятой у первого числа
    private static int remainder2; // количество чисел после запятой у второго числа
    private static int firstNumber; // первое число, домноженное до целого
    private static int secondNumber; // второе число, домноженное до целого
    private static String operation;
    private static StringBuffer sb;

    static String doSome(String allExample, int digitsBefore, int digitsAfter, int rounding) {
        Example.digitsBefore = digitsBefore;
        Example.digitsAfter = digitsAfter;
        Example.rounding = rounding;
        // разбираем числа, чтобы сделать их целыми
        String[] parts = allExample.split(" ");
        String firstNumberBefore = parts[0].split(",")[0];
        String firstNumberAfter = parts[0].split(",")[1];
        String secondNumberBefore = parts[2].split(",")[0];
        String secondNumberAfter = parts[2].split(",")[1];
        Example.operation = parts[1];
        int remainder = firstNumberAfter.length(); // количество цифр после запятой первого числа
        int remainder2 = secondNumberAfter.length(); // количество цифр после запятой второго числа
        Example.remainder = remainder;
        Example.remainder2 = remainder2;
        firstNumber = (int) (Integer.parseInt(String.valueOf(firstNumberBefore)) * Math.pow(10.0, remainder) + Integer.parseInt(firstNumberAfter));
        secondNumber = (int) (Integer.parseInt(String.valueOf(secondNumberBefore)) * Math.pow(10.0, remainder2) + Integer.parseInt(secondNumberAfter));
        getPlus();
        getMinus();
        getMultiplay();
        getDivide();
        return getAnswer();
    }

     private static void getPlus() {
        if (operation.equals("+")) {
            // удобные для вычисления числа
            if (Example.remainder > Example.remainder2) {
                convertNumber();
            }
            if (Example.remainder < Example.remainder2) {
                convertNumber();
            }
            // сложение
            sb = new StringBuffer(Integer.toString(firstNumber + secondNumber));
            getZeros();
            sb = sb.insert(digitsBefore, ","); // постановка запятой
        }
    }
    private static void getMinus() {
        if (operation.equals("-")) {
            if (remainder != remainder2) { //домножение чисел для удобства вычислений
                convertNumber();
            }
            // вычитание
            sb = new StringBuffer(Integer.toString( firstNumber - secondNumber));
            getZeros(); // добавление нужного количества нулей
            sb.insert(digitsBefore, ","); // постановка запятой
        }
    }
    private static void getMultiplay() {
        if (operation.equals("*")) {
            int res, digit, ten = 10;
            //умножение столбиком
            digit = secondNumber % 10;
            secondNumber = secondNumber / 10;
            res = firstNumber * digit;
            while (secondNumber!= 0) {
                digit = secondNumber % 10;
                secondNumber = secondNumber / 10;
                if (digit != 0) {
                    res = firstNumber * digit * ten + res;
                    ten = ten * 10;
                }
                if (digit == 0) ten = ten * 10;
            }
            // постановка запятой
            sb = new StringBuffer(Integer.toString(res));
            getZeros(); // добавление нужного количества нулей
            sb.insert(digitsBefore, ","); // постановка запятой
        }
    }
    private static void getDivide() {
        if (Example.operation.equals("/")) {
            int before, after, num1, count = 0;
            StringBuilder answer = new StringBuilder();
            if (remainder != remainder2) { //домножение чисел для удобства вычислений
                convertNumber();
            }
            before = firstNumber / secondNumber; // целая часть
            after = firstNumber % secondNumber;  // нахождение остатка от числа
            num1 = after * 10;
            while (num1 != 0) {
                while (num1 - secondNumber * count >= secondNumber) {
                    count = count + 1;
                }
                num1 = (num1 - secondNumber * count) * 10;
                answer.append(count); // остаток
                count = 0;
            }
            sb = new StringBuffer(Integer.toString(Integer.parseInt(before + String.valueOf(answer))));
            getZeros();
            sb.insert(digitsBefore, ","); // постановка запятой
        }
    }
    private static void  getZeros() { //добавление нужного количества нулей
        while (sb.length() < digitsBefore + digitsAfter) {
            sb = sb.insert(0, "0");
        }
    }
    private static void convertNumber() { //домножение чисел для удобства вычислений
        if (remainder < remainder2)  {
            firstNumber = (int) (firstNumber * Math.pow(10.0, (remainder2 - remainder)));
        }
        else {
            secondNumber = (int) (secondNumber * Math.pow(10.0, (remainder - remainder2)));
        }
    }
     private static String getAnswer() {
        String finalBefore = sb.toString().split(",")[0]; // значение в результате до запятой
        String finalAfter = sb.toString().split(",")[1]; // значение в результате после запятой
        if (finalBefore.length() != digitsBefore || finalAfter.length() != rounding) {// проверка на количество чисел до и после
           return getRounding(); // округляем число;
        } else return  finalBefore + "," + finalAfter;
     }
    private static String getRounding() { // округление
        String finalBefore = sb.toString().split(",")[0]; // значение в результате до запятой
        String finalAfter = sb.toString().split(",")[1]; // значение в результате после запятой
        StringBuilder roundingAfter = new StringBuilder(finalAfter); // конечный результат
        int count = finalAfter.length(); // количество чисел после запятой в нашем числе
        int actualAfter = Integer.parseInt(finalAfter); // значение после запятой
        while (count != rounding) { // пока количество чисел после запятой не станет равно указанному
            if (actualAfter % 10  < 5) {
                actualAfter = actualAfter / 10;
                roundingAfter.deleteCharAt(count - 1); // если цифра < 5 -> удаляем
                count = count - 1;} // уменьшаем значение количества чисел после запятой
            else { actualAfter = actualAfter / 10 + 1; // если цифра > 5 -> прибавляем единицу к следующей
                roundingAfter.deleteCharAt(count - 1); // удаляем последнюю цифру
                roundingAfter.deleteCharAt(count - 2); // удаляем еще одну
                roundingAfter.insert(count - 2, actualAfter % 10); // на ее место ставим округленное значение
                count = count - 1;} // уменьшаем значение количества чисел после запятой
        }
        return finalBefore + "," + roundingAfter;
    }
}

