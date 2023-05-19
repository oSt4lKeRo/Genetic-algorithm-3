package org.example;

import java.util.Random;

import static org.example.HelperClass.*;
import static org.example.Functional.*;
public class Main {
	public static void main(String[] args) {

		Random random = new Random();

		int[][] taskMas = generateMas(2);
		int firstElem = random.nextInt(taskMas.length); firstElem = 5;
		int countIndivid = 4;

		System.out.println("Начальный элемент: " + firstElem);

		int[][] individMas = generateIndivid(countIndivid, firstElem, taskMas.length);

		int bestElem = 5000;
		int repeatBest = 1;
		int iteratorCount = 0;
		int needIteratorCount = 10;

		while (repeatBest != needIteratorCount){
			System.out.println("\n\n\nНовая Эпока: Номер эпоки: " + iteratorCount + "; Лучший элемент: " + bestElem + "; Повторение луч.эл-та: " + (repeatBest+1) + ";");
			ReturnValue returnValue = start(taskMas, individMas, firstElem, countIndivid);
			if(returnValue.bestElem < bestElem){
				System.out.println("Новый лучший элемент: " + returnValue.bestElem);
				bestElem = returnValue.bestElem;
				repeatBest = 1;
			} else {
				repeatBest++;
			}
			individMas = returnValue.taskMas;
			iteratorCount++;
		}

		System.out.println("\nРабота жадного алгоритма: ");
		countSum_lite(greedyAlgorithm(taskMas, firstElem));

	}
}