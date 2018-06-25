package com.cities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayGame {

	private int currentPlayer;
	private String lastChar;
	private String[] names;
	private List<String> possibleCities;
	private List<String> usedCities;
	private long startTime = 0;
	private long finishTime = 0;
	private long duration = 30000;

	public PlayGame(String[] names, List<String> possibleCities) {
		this.possibleCities = possibleCities;
		this.names = names; 
		this.usedCities = new ArrayList<String>();
	}
	
	/**
	 * Метод для запуска игры, выбор случайного города, случайного игрока.
	 */
	public void play() {
		System.out.println("Игра началась!!!");
		String initCity = possibleCities.get((int) (Math.random() * possibleCities.size()));
		System.out.println(initCity);
		setLastChar(initCity);
		currentPlayer = (int) (Math.random() * names.length);
		inputCity();

	}

	private void inputCity() {
		timer();
		System.out.println("Игрок " + names[currentPlayer] + " назовите город на букву " + lastChar.toUpperCase());
		Scanner scan = new Scanner(System.in);
		String nameCity = scan.nextLine();
		timer();
		if (usedCities.contains(nameCity)) {
			System.out.println("Такой город уже назван.");
			inputCity();
		}
		if (!equalsLastAndFirstChar(nameCity)) {
			System.out.println("Не правильно.");
			inputCity();
		}
		if (!possibleCities.contains(nameCity)) {
			System.out.println("Введен не существующий город.");
			inputCity();
		}
		System.out.println("Продолжаем дальше.");
		nextPlayer();
		setLastChar(nameCity);
		usedCities.add(nameCity);
		inputCity(); 
	}
	
	private void nextPlayer() {
		if (currentPlayer == (names.length-1)) {
			currentPlayer = 0;
		} else {
			currentPlayer++;
		}
		if (names[currentPlayer]==null) nextPlayer();
		startTime = 0;
	}
	
	/**
	 * Метод отсчитывает определенное колличество времени, в которое игрок должен дать ответ.
	 */
	public void timer() {
		if (startTime == 0) {
			startTime = System.currentTimeMillis();
			finishTime = startTime + duration;
			return;
		}
		if (finishTime <= System.currentTimeMillis()) {
			System.out.println("Время истекло. Вы проиграли.");
			removePlayer();
		}
	}
	
	private void removePlayer() {
		names[currentPlayer] = null;
		checkEndGame();
		nextPlayer();
		inputCity();
	}

	private void checkEndGame() {
		List<String> restPlayers = new ArrayList<>();
		for(String name : names) {
			if(name != null)
				restPlayers.add(name);
		}
		if(restPlayers.size() == 1) {
			System.out.println("Игрок " + restPlayers.get(0) + " Вы ПРОИГРАЛИ!!!");
			System.exit(0);
		}
	}
	
	private void setLastChar(String city) {
		int endIndex = city.length();
		lastChar = city.substring(endIndex-1, endIndex);
		if (lastChar.equals("ы") || lastChar.equals("ь")) {
			setLastChar(city.substring(0, city.length() - 1));
		}
		if (lastChar.equals("ё")) {
			lastChar = "е";
		}
		if (lastChar.equals("й")) {
			lastChar = "и";
		}
	}

	private boolean equalsLastAndFirstChar(String city) {
		if (city.toLowerCase().startsWith(lastChar)) {
			return true;
		}
		return false;
	}
}
