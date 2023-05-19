package org.example;

import java.util.ArrayList;
import java.util.Random;

import static org.example.HelperClass.*;

public class Functional {

	static class ReturnValue{
		int[][] taskMas;
		int bestElem;

		ReturnValue(int[][] taskMas, int bestElem){
			this.taskMas = taskMas;
			this.bestElem = bestElem;
		}
	}

	public static int[] greedyAlgorithm(int[][] taskMas, int firstElem){
		int[] distribute = new int[taskMas.length];

		int nowElem =  firstElem;
		ArrayList<Integer> banList = new ArrayList<>();
		banList.add(nowElem);

		for(int i = 0; i < taskMas.length; i++){

			if(i == taskMas.length-1){
				distribute[i] = taskMas[nowElem][firstElem];
				break;
			}

			int min = searchMax(taskMas[nowElem]);
			int indexMin = 0;
			for(int j = 0; j < taskMas.length; j++){
				if(taskMas[nowElem][j] < min && !banList.contains(j) && j != nowElem){
					min = taskMas[nowElem][j];
					indexMin = j;
				}
			}
			banList.add(indexMin);
			nowElem = indexMin;
			distribute[i]= min;
		}

//		printMas(distribute);
		return distribute;
	}

	public static int countSum(int[][] taskMas, int[] individ){
		int currentIndex = individ[0];
		int sum = 0;
		for(int i = 0; i < individ.length-1; i++){
			sum += taskMas[currentIndex][individ[i+1]];
			System.out.print(taskMas[currentIndex][individ[i+1]] + " ");
			currentIndex = individ[i+1];
		}
		System.out.println("= " + sum);
		return sum;
	}
	public static int countSum_lite(int[] individ){
		int sum = 0;
		for(int i : individ){
			sum+= i;
			System.out.print(i + " ");
		}
		System.out.println("= " + sum);
		return sum;
	}

	public static int[] crossover(int[] firstMas, int[] secondMas){
		Random random = new Random();
		int border = random.nextInt(firstMas.length-2)+1;
		System.out.println("Индекс разделения: " + border);

		int[] newIndivid = new int[firstMas.length];
		ArrayList<Integer> arr = new ArrayList<>();

		for(int i = 0; i < border; i++){
			arr.add(firstMas[i]);
		}
		for(int i = 0; i < secondMas.length; i++){
			if(!arr.contains(secondMas[i])){
				arr.add(secondMas[i]);
			}
		}
		for(int i = 0; i < arr.size(); i++){
			newIndivid[i] = arr.get(i);
		}
		newIndivid[newIndivid.length-1] = newIndivid[0];
		printMas(newIndivid);
		return newIndivid;
	}


	public static int[] mutation(int[] individ){

		for(int i : individ){
			System.out.print(i + " ");
		}

		Random random = new Random();
		int firstElem = random.nextInt(individ.length-2)+1;
		int secondElem = 0;
		System.out.print("[" + firstElem + "][" + secondElem + "] => ");
		while (true) {
			secondElem = random.nextInt(individ.length - 2) + 1;
			if (secondElem != firstElem) {
				break;
			}
		}
		int buf = individ[firstElem];
		individ[firstElem] = individ[secondElem];
		individ[secondElem] = buf;

		for(int i : individ){
			System.out.print(i + " ");
		}
		System.out.println();
		return individ;
	}


	public static ReturnValue start(int[][] taskMas, int[][] individMas, int firstElem, int countIndivid){

		int[] result = new int[individMas.length];
		int[][] resultMas = new int[individMas.length][individMas[0].length];

		for(int k = 0; k < individMas.length; k++) {
			Random random = new Random();
			int secondMasIndex = 0;
			while (true) {
				secondMasIndex = random.nextInt(individMas.length);
				if (k != secondMasIndex) {
					break;
				}
			}
			int[] firstMas = individMas[k];
			int[] secondMas = individMas[secondMasIndex];

			System.out.print("[O" + k +"] = ");
			printMas(firstMas);
			System.out.print("[O" + secondMasIndex +"] = ");
			printMas(secondMas);

///////////////////////////////////////////////////////Кроссовер
			System.out.println("Кроссовер");
			if(random.nextInt(100) < 100){
				firstMas = crossover(firstMas, secondMas);
			}
			if(random.nextInt(100) < 100){
				secondMas = crossover(secondMas, firstMas);
			}

///////////////////////////////////////////////////////Мутации
			System.out.println("\nМутация");
//			countSum(taskMas, firstMas);
//			countSum(taskMas, secondMas);
			if(random.nextInt(100) < 100){
				firstMas = mutation(firstMas);
			}
			if(random.nextInt(100) < 100){
				secondMas = mutation(secondMas);
			}
//			countSum(taskMas, firstMas);
//			countSum(taskMas, secondMas);

///////////////////////////////////////////////////////Итоги
			System.out.println("\nИтоги региона");
			int[] intermediateResults = new int[3];
			intermediateResults[0] = countSum(taskMas, individMas[k]);
			intermediateResults[1] = countSum(taskMas, firstMas);
			intermediateResults[2] = countSum(taskMas, secondMas);
			result[k] = searchMin(intermediateResults);
			int indexMas = 0;
			for(int i = 0; i < intermediateResults.length; i++){
				if(intermediateResults[i] == result[k]){
					indexMas = i;
					break;
				}
			}
			switch (indexMas){
				case 0:
					resultMas[k] = individMas[k];
					break;
				case 1:
					resultMas[k] = firstMas;
					break;
				case 2:
					resultMas[k] = secondMas;
					break;
			}
			System.out.println("\n");
		}

		System.out.println("\nОбщие итоги");
		printResult(result, resultMas);
		int bestElem = searchMin(result);
		System.out.println("Лучший элемент: " + bestElem);

		individMas = resultMas;
		ReturnValue returnValue = new ReturnValue(individMas, bestElem);
		return returnValue;
	}

}
