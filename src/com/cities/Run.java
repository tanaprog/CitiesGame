package com.cities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) {

		List<String> possibleCities = new ArrayList<String>();
		try {
			possibleCities = Files.readAllLines(Paths.get("resources/cities.txt"));
		} catch (IOException e) {
			System.out.println("Не удалось загрузть список городов. " + e);
		}
		
		System.out.println("Введите колличество игроков: ");
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt(); 
		
		String[] names = new String[number];
		for (int i = 0; i < number; i++) {
			System.out.println("Введите имя "+ (i+1) + "-го игрока: ");
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			names[i] = name;
		}
		
		PlayGame playGame = new PlayGame(names, possibleCities);
		
		playGame.play(); 
		}
		
	}
