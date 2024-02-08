/**
 * RSGui.java GUI for RideShare project
 * @author August Cho
 * @version 2024-02-06
 */

import java.util.Scanner;
import java.util.ArrayList;

public class RideShareRunner
{
	public static void draw(Station[] stations)
	{
		for (Station station : stations)
		{
			System.out.print("Station " + station.getStationNumber() + ": ");
			for (Person person : station.getPersons())
			{
				System.out.print(person.getDestination());
			}
			System.out.print(" | ");
			for (Car car : station.getCars())
			{
				System.out.print("[");
				for (Person person : car.getPersons())
				{
					System.out.print(person.getDestination());
				}
				for (int i = 0; i < (3 - car.getPersons().size()); i++)
				{
					System.out.print("_");
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
					}
					else if (car.getDestination() < station.getStationNumber())
					{
						car.setReadyToMove(false);
						stations[i - 1].addCar(car);
						station.removeCar(n);
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
		Scanner in = new Scanner(System.in);
		
		Station[] stations = new Station[3];
		for (int i = 0; i < stations.length; i++)
		{
			stations[i] = new Station(i);
		}

		stations[0].spawnPerson(2);
		stations[1].spawnPerson(0);
		stations[2].spawnPerson(1);

		stations[0].spawnCar(2);
		stations[2].spawnCar(1);
		stations[2].spawnCar(0);

		while (true)
		{
			draw(stations);
			
			for (Station station : stations)
			{
				station.boardPassengers();
				
			}
			in.nextLine();
			draw(stations);
			moveCars(stations);	
			in.nextLine();
		}
	}
}
