package com.jspider.musicplayer.operations;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class SongOperations 
{
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static Properties properties;
	private static ResultSet resultSet;
	private static FileReader fileReader;
	private static int result;
	private static String filePath = "A:\\J2EE\\multiplayer\\resources\\db_info.properties";
	private static Scanner scanner = new Scanner(System.in);
	private static int id;
	private static String name;
	private static String singer;
	private static int duration;
	private static String movie;
	private static String lyricist;
	private static String composer;

	public void openConnecton() {
		try {
			fileReader = new FileReader(filePath);
			properties = new Properties();
			properties.load(fileReader);
			Class.forName(properties.getProperty("driverPath"));
			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSongs() {
		openConnecton();
		System.out.println("How many songs you want to add?");
		int choice = scanner.nextInt();
		try {
			for (int i = 0; i <= choice; i++) {
				preparedStatement = connection.prepareStatement(properties.getProperty("insert"));

				System.out.println("Enter the song id : ");
				id = scanner.nextInt();
				preparedStatement.setInt(1, id);

				System.out.println("Enter the song name : ");
				name = scanner.next();
				preparedStatement.setString(2, name);

				System.out.println("Enter the song singer : ");
				singer = scanner.next();
				preparedStatement.setString(3, singer);

				System.out.println("Enter the song duration : ");
				duration = scanner.nextInt();
				preparedStatement.setInt(4, duration);

				System.out.println("Enter the song movie/album : ");
				movie = scanner.next();
				preparedStatement.setString(5, movie);

				System.out.println("Enter the song lyricist : ");
				lyricist = scanner.next();
				preparedStatement.setString(6, lyricist);

				System.out.println("Enter the song composer : ");
				composer = scanner.next();
				preparedStatement.setString(7, composer);

				result = preparedStatement.executeUpdate();
				if (result != 0) {
					System.out.println(result + " row(s) affected");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}

	public void displaySongs() {
		openConnecton();
		try {
			preparedStatement = connection.prepareStatement(properties.getProperty("display"));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + " . " + resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection();

	}

	public void removeSong() {
		openConnecton();
		System.out.println("select a song to remove");
		displaySongs();
		try {
			preparedStatement = connection.prepareStatement(properties.getProperty("delete"));
			System.out.println("Enter the song id : ");
			int choice = scanner.nextInt();
			preparedStatement.setInt(1, choice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
