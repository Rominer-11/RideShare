/**
 * RSGui.java GUI for RideShare project
 * @author August Cho
 * @version 2024-02-06
 */

import java.util.Scanner;
import java.util.ArrayList;

public class RideShareRunner
{
	private static int miles;

	public static void draw(Station[] stations)
	{
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
		for (Station station : stations)
		{
			for (int i = 0; i < String.valueOf(stations.length).length() - String.valueOf(station.getStationNumber()).length() + 1; i++)
			{
				System.out.print(" ");
			}
			System.out.print("S" + station.getStationNumber() + ": ");
			for (Person person : station.getPersons())
			{
				for (int i = 0; i < String.valueOf(stations.length).length() - String.valueOf(person.getDestination()).length() + 1; i++)
				{
					System.out.print("-");
				}
				System.out.print(person.getDestination());
			}
			for (Car car : station.getCars())
			{
				System.out.print("   [");
				for (Person person : car.getPersons())
				{
					for (int i = 0; i < String.valueOf(stations.length).length() - String.valueOf(person.getDestination()).length() + 1; i++)
					{
						System.out.print("-");
					}
					System.out.print(person.getDestination());
				}
				for (int i = 0; i < (3 - (car.getPersons().size())); i++)
				{
					System.out.print("___");
				}
				System.out.print("]");
				System.out.print("(" + car.getDestination() + ")");
			}
			System.out.print("\n");
		}
	}
	public static void moveCars(Station[] stations)
	{
		for (int i = 0; i < stations.length; i++)
		{
			//move cars
			Station station = stations[i];
			ArrayList<Car> cars = station.getCars();
			for (int n = cars.size() - 1; n > -1; n--)
			{
				Car car = cars.get(n);
				if (car.getReadyToMove() == true)
				{
					if (car.getDestination() > station.getStationNumber())
					{
						car.setReadyToMove(false);
						stations[i + 1].addCar(car);
						station.removeCar(n);
						miles++;
					}
					else if (car.getDestination() < station.getStationNumber())
					{
						car.setReadyToMove(false);
						stations[i - 1].addCar(car);
						station.removeCar(n);
						miles++;
					}
				}
			}
		}
		for (int i = 0; i < stations.length; i++)
		{
			//reset cars
			Station station = stations[i];
			for (Car car : station.getCars())
			{
				car.setReadyToMove(true);
			}
		}
	}
	
	public static void main(String[] args)
	{
		miles = 0;
		Scanner in = new Scanner(System.in);
		
		Station[] stations = new Station[32];
		for (int i = 0; i < stations.length; i++)
		{
			stations[i] = new Station(i);

			int multiplier = (int) (Math.random() * 2);
			for (int n = 0; n < multiplier; n++)
			{
				stations[i].spawnPerson((int) (Math.random() * 32));
			}
			multiplier = (int) (Math.random() * 2);
			for (int n = 0; n < multiplier; n++)
			{
				stations[i].spawnCar((int) (Math.random() * 32));
			}

		}
/*
		stations[0].spawnPerson(30);
		stations[0].spawnPerson(30);
		stations[0].spawnPerson(25);
		stations[0].spawnPerson(22);
		stations[0].spawnCar(31);
*/
		while (true)
		{
			draw(stations);
			System.out.println("Miles driven: " + miles);
			
			for (Station station : stations)
			{
				station.unloadPassengers();
				station.checkCars();
				station.boardPassengers();
			}
			in.nextLine();
			draw(stations);
			System.out.println("Miles driven: " + miles);
			moveCars(stations);	
			in.nextLine();
		}
	}
}
