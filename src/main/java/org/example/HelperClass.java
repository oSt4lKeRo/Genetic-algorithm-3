package org.example;

import java.util.Random;

public class HelperClass {

	public static int[][] generateMas(int param){

		if(param == 1) {
			int n = 8;
			int T1 = 10;
			int T2 = 20;
			int diff = T2 - T1;

			int[][] mas = new int[n][n];
			Random random = new Random();

			for (int i = 0; i < mas.length; i++) {
				for (int j = i; j < mas[i].length; j++) {
					int value = random.nextInt(diff + 1);
					value += T1;
					mas[i][j] = value;
					mas[j][i] = value;
				}
			}
			printMas(mas);
			return mas;
		} else{
			int[][] mas = {{0, 13, 11, 19, 17, 12, 15}, {13, 0, 11, 18, 12, 10, 20}, {11, 11, 0, 15, 17, 11, 16}, {19, 18, 15, 0, 18, 19, 20}, {17, 12, 17, 18, 0, 20, 10}, {12, 10, 11, 19, 20, 0, 11}, {15, 29, 16, 20, 10, 11, 0}};
			printMas(mas);
			return mas;
		}
	}

	public static int[][] generateIndivid(int count, int firstElem, int size){
		int[][] individMas = new int[count][size];
		for(int i = 0; i < count; i++){
			int[] individ = generIndivid(firstElem, size);
			individMas[i] = individ;
		}
		printMas(individMas);
		return individMas;
	}

	public static int[] generIndivid(int firstElem, int size){

		int[] individMas = new int[size+1];

		for(int i = 0; i < individMas.length; i++){
			individMas[i] = size;
		}

		individMas[0] = firstElem;
		Random random = new Random();

		for(int i = 1; i < individMas.length; i++){

			if(i == individMas.length-1){
				individMas[i] = firstElem;
				break;
			}

			int elem = 0;
			while (true){
				elem = random.nextInt(size);
				if(masContains(individMas, elem)){
					break;
				}
			}
			individMas[i] = elem;
		}
		return individMas;
	}





	public static boolean masContains(int[] mas, int elem){
		for(int i : mas){
			if(elem == i){
				return false;
			}
		}
		return true;
	}

	public static int searchMax(int[] mas){
		int max = mas[0];
		for(int i : mas){
			if(i > max) {
				max = i;
			}
		}
		return max;
	}

	public static int searchMin(int[] mas){
		int min = mas[0];
		for(int i : mas){
			if(i < min){
				min = i;
			}
		}
		return min;
	}


	public static void printMas(int[][] mas){
		for(int i = 0; i < mas.length; i++){
			for(int j = 0; j < mas[i].length; j++){
				System.out.print(mas[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}


	public static void printMas(int[] mas){
		for(int i = 0; i < mas.length; i++){
			System.out.print(mas[i] + " ");
		}
		System.out.println();
	}

	public static void printResult(int[] bestElem, int[][] mas){
		for(int i = 0; i < mas.length; i++){
			for(int j : mas[i]){
				System.out.print(j + " ");
			}
			System.out.println("=> " + bestElem[i]);
		}
	}


}
