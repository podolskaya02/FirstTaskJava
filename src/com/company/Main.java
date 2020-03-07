package com.company;
import java.io.IOException;

class Main {

	public static void main(String[] args) {
	}

	static String getOperation(String example, int digitsBefore, int digitsAfter, int rounding) {
		String[] parts = example.split(" ");  // перевод строки в числа
		String firstNumberBefore = parts[0].split(",")[0]; // цифры первого числа до запятой
		String firstNumberAfter = parts[0].split(",")[1]; // цифры первого числа после запятой
		String secondNumberBefore = parts[2].split(",")[0]; // цифры второго числа до запятой
		String secondNumberAfter = parts[2].split(",")[1]; // цифры второго числа до запятой
		String operation = parts[1]; // операция
		int remainder = firstNumberAfter.length(); // количество цифр после запятой первого числа
		int remainder2 = secondNumberAfter.length(); // количество цифр после запятой второго числа
		int firstNumber = (int) (Integer.parseInt(String.valueOf(firstNumberBefore)) * Math.pow(10.0, remainder) + Integer.parseInt(firstNumberAfter)); // делаем целое число
		int secondNumber = (int) (Integer.parseInt(String.valueOf(secondNumberBefore)) * Math.pow(10.0, remainder2) + Integer.parseInt(secondNumberAfter)); // делаем целое число
		String res = ""; // конечное значение
		switch (operation) { // выбор операции
			case "+":
				res = getPlus(firstNumber, secondNumber, digitsBefore, remainder, remainder2, digitsAfter).toString();
				break;
			case "-":
				res = getMinus(firstNumber, secondNumber, digitsBefore, remainder, remainder2, digitsAfter).toString();
				break;
			case "*":
				res = getMultiplay(firstNumber, secondNumber, digitsBefore, digitsAfter).toString();
				break;
			case "/":
				res = getDivide(firstNumber, secondNumber, remainder, remainder2);
				break;
			default:
				System.out.print("Некорректный ввод");
		}
		String finalBefore = res.split(",")[0]; // значение в результате до запятой
		String finalAfter = res.split(",")[1]; // значение в результате после запятой
		if (finalBefore.length() != digitsBefore || finalAfter.length() != rounding) // проверка на количество чисел до и после
				res = getRounding(finalBefore, finalAfter, digitsBefore, rounding); // округляем число
		return res; // вывод результата
	}

	private static StringBuffer getPlus(int firstNumber, int secondNumber, int digitsBefore, int remainder, int remainder2, int digitsAfter) {
		int res;
		StringBuffer sb;
		// удобные для вычисления числа
		if (remainder > remainder2) {
			secondNumber = convertNumber(firstNumber, secondNumber, remainder, remainder2);
		}
		if (remainder < remainder2) {
			firstNumber = convertNumber(firstNumber, secondNumber, remainder, remainder2);
		}
		res = firstNumber + secondNumber; // сложение
		sb = new StringBuffer(Integer.toString(res));
		sb = getZeros(sb, digitsBefore, digitsAfter); // добавление нужного количества нулей
		sb = sb.insert(digitsBefore, ","); // постановка запятой
		return sb;
	}

	private static StringBuffer getMinus(int firstNumber, int secondNumber, int digitsBefore, int remainder, int remainder2, int digitsAfter) {
		int res;
		StringBuffer sb;
		if (remainder > remainder2) { //домножение чисел для удобства вычислений
			secondNumber = convertNumber(firstNumber, secondNumber, remainder, remainder2);
		}
		if (remainder < remainder2) {
			firstNumber = convertNumber(firstNumber, secondNumber, remainder, remainder2);
		}
			res = firstNumber - secondNumber; // вычитание
			sb = new StringBuffer(Integer.toString(res));
		    sb = getZeros(sb, digitsBefore, digitsAfter); // добавление нужного количества нулей
			sb = sb.insert(digitsBefore, ","); // постановка запятой
		return sb;
	}

	private static StringBuffer getMultiplay(int firstNumber, int secondNubmer, int digitsBefore, int digitsAfter) {
		int res, digit, ten = 10;
		//умножение столбиком
		digit = secondNubmer % 10;
		secondNubmer = secondNubmer / 10;
		res = firstNumber * digit;
		while (secondNubmer != 0) {
			digit = secondNubmer % 10;
			secondNubmer = secondNubmer / 10;
			if (digit != 0) {
				res = firstNumber * digit * ten + res;
				ten = ten * 10;
			}
			if (digit == 0) ten = ten * 10;
		}
		// постановка запятой
		StringBuffer sb = new StringBuffer(Integer.toString(res));
		sb = getZeros(sb, digitsBefore, digitsAfter); // добавление нужного количества нулей
		sb = sb.insert(digitsBefore, ","); // постановка запятой
		return sb;
	}

	private static String getDivide(int firstNumber, int secondNumber, int remainder, int remainder2) {
		int before, after, num1, count = 0;
		StringBuilder answer = new StringBuilder();
		if (remainder > remainder2) { //домножение чисел для удобства вычислений
			secondNumber = convertNumber(firstNumber, secondNumber, remainder, remainder2);
		}
		if (remainder < remainder2) {
			firstNumber = convertNumber(firstNumber, secondNumber, remainder, remainder2);
		}
		before = firstNumber / secondNumber; // целая часть
		// нахождение остатка от числа
		after = firstNumber % secondNumber;
		num1 = after * 10;
		while (num1 != 0) {
			while (num1 - secondNumber * count >= secondNumber) {
				count = count + 1;
			}
			num1 = (num1 - secondNumber * count ) * 10;
			answer.append(count); // остаток
			count = 0;
		}
		return before + "," + answer;
	}
	private static int convertNumber(int firstNumber, int secondNumber, int remainder, int remainder2) { //домножение чисел для удобства вычислений
		if (remainder < remainder2) {
			firstNumber = (int) (firstNumber * Math.pow(10.0, (remainder2 - remainder)));
			return firstNumber;
		}
		else {
			secondNumber = (int) (secondNumber * Math.pow(10.0, (remainder - remainder2)));
			return secondNumber;
		}
	}
	private static  StringBuffer getZeros(StringBuffer sb, int digitsBefore, int digitsAfter) { //добавление нужного количества нулей
		while (sb.length() < digitsBefore + digitsAfter) {
			sb = sb.insert(0, "0");
		}
		return sb;
	}
	private static String getRounding(String finalBefore, String finalAfter, int digitsBefore, int rounding) { // округление
		StringBuilder roundingAfter = new StringBuilder(finalAfter); // конечный результат
		int count = finalAfter.length(); // количество чисел после запятой в нашем числе
		int actualAfter = Integer.parseInt(finalAfter); // значение после запятой
		while (count != rounding) { // пока количество чисел после запятой не станет равно указанному
			 if (actualAfter % 10  < 5) {actualAfter = actualAfter / 10;
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

