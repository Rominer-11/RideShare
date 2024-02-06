/**
 * RSGui.java GUI for RideShare project
 * @author August Cho
 * @version 2024-02-06
 */

public class RideShareRunner
{
	public static void main(String[] args)
	{
		Station[] stations = new Station[3];
		for (int i = 0; i < stations.length; i++)
		{
			stations[i] = new Station(i);
		}

		stations[0].addPerson(2);
		stations[1].addPerson(0);
		stations[2].addPerson(1);

		stations[0].addCar(2);

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
}
