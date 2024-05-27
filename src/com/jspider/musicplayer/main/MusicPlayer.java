package com.jspider.musicplayer.main;

import java.util.Scanner;

import com.jspider.musicplayer.operations.SongOperations;

public class MusicPlayer 
{
	private static boolean loop = true;
	private static int choice;
	private static Scanner scanner = new Scanner(System.in);
	private static SongOperations operations = new SongOperations();

	public static void main(String[] args) {
		musicPlayer();
	}

	public static void musicPlayer() {

		while (loop) {
			System.out.println("-----------START-----------");
			System.out.println("1. Add / Remove song\n" + "2. Play Song\n" + "3. Edit Song\n" + "4. Exit\n");
			choice = scanner.nextInt();

			//
			switch (choice) {
			case 1:
				System.out.println("1. Add a song\n" + "2. Remove a song\n" + "3. Go back");

				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					operations.addSongs();
					break;
				case 2:
					operations.displaySongs();
					break;
				case 3:
					operations.removeSong();
					break;
				}
				break;

			//
			case 2:
				System.out.println("1. Play all songs\n" + "2. choose Song\n" + "3. shuffle\n" + "4. exit\n");

				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					operations.addSongs();
					break;
				case 2:
					operations.displaySongs();
					break;
				case 3:
					operations.removeSong();
					break;
				case 4:
					break;
				}
				break;

			//
			case 3:
				// Edit Song
				// operations.editSong();
				break;

			case 4:
				// Exit
				break;

			}
		}
	}

}