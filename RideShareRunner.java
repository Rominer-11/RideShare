/**
 * RSGui.java GUI for RideShare project
 * @author August Cho
 * @version 2024-02-16
 */

import java.util.Scanner;
import java.util.ArrayList;

public class RideShareRunner
{
	private static int miles;
	private static int passengerMiles;

	public static void draw(Station[] stations)
	{
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
		System.out.println("RideShare -- Made by August Cho for APCS");
		System.out.println("----------------------------------------");
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
					System.out.print("_");
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
						System.out.print("_");
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
		System.out.println("----------------------------------------");
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
						passengerMiles += car.getPersons().size();
					}
					else if (car.getDestination() < station.getStationNumber())
					{
						car.setReadyToMove(false);
						stations[i - 1].addCar(car);
						station.removeCar(n);
						miles++;
						passengerMiles += car.getPersons().size();
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
		System.out.println("Welcome to RideShare. If you would like to randomly populate each station, type \"random\". To continue with an empty road, hit <ENTER>.");
		String random = in.nextLine();
		for (int i = 0; i < stations.length; i++)
		{
			stations[i] = new Station(i);
			if (random.equalsIgnoreCase("random"))
			{
				int multiplier = (int) (Math.random() * 3);
				for (int n = 0; n < multiplier; n++)
				{
					int dest = (int) (Math.random() * 32);
					while (dest == i)
					{
						//makes sure no people spawn at their destination
						dest = (int) (Math.random() * 32);
					}
					stations[i].spawnPerson(dest);
				}
				multiplier = (int) (Math.random() * 3);
				for (int n = 0; n < multiplier; n++)
				{
					stations[i].spawnCar((int) (Math.random() * 32));
				}
			}
		}
		
		int cycle = 1;

		while (true)
		{
			String input = "placeholder";
			while (!input.equals(""))
			{
				draw(stations);
				System.out.println("Miles driven: " + miles);
				System.out.println("Passenger miles: " + passengerMiles);
				System.out.println("Press <ENTER> to cycle a round. <CTRL> + <C> will break out of the program. To spawn a car, type C. To spawn a person, type P.");
				if (cycle == 1)
				{
					System.out.println("On the next round, passengers will be loaded and unloaded.");
				}
				if (cycle == 2)
				{
					System.out.println("On the next round, cars will move.");
				}
				System.out.println("> ");

				input = in.nextLine();

				if (input.equalsIgnoreCase("c"))
				{
					System.out.println("Location (station number): ");
					int loc = in.nextInt();
					System.out.println("Destination (station number): ");
					int dest = in.nextInt();
					stations[loc].spawnCar(dest);
					input = "this value is here to repeat command line";
					in.nextLine();
				}
				else if (input.equalsIgnoreCase("p"))
				{
					System.out.println("Location (station number): ");
					int loc = in.nextInt();
					System.out.println("Destination (station number): ");
					int dest = in.nextInt();
					stations[loc].spawnPerson(dest);
					input = "this value is here to repeat command line";
					in.nextLine();
				}
			}

			if (cycle == 1)
			{
				//load and unload
				for (Station station : stations)
				{
					station.unloadPassengers();
					station.checkCars();
					station.boardPassengers();
				}
				cycle = 2;
			}
			else if (cycle == 2)
			{
				//move the cars
				moveCars(stations);
				cycle = 1;
			}
			System.out.println("Cycling next round...");
		}
	}
}
