/**
 * Station.java
 * @author August Cho
 * @version 2024-02-06
 */

import java.util.ArrayList;

public class Station
{
	private ArrayList<Person> persons;	
	private ArrayList<Car> cars;
	private int stationNumber;

	public Station(int stationNumber)
	{
		persons = new ArrayList<Person>();
		cars = new ArrayList<Car>();
		this.stationNumber = stationNumber;
	}

	public void boardPassengers()
	{
		for (int n = persons.size() - 1; n > -1; n--)
		{
			Person person = persons.get(n);
			if (person.getDestination() > stationNumber)
			{
				int carIndex = -1;
				// Checks for cars going same direction
				for (int i = 0; i < cars.size(); i++)
				{
					Car car = cars.get(i);
					if (car.getDestination() > stationNumber)
					{
						carIndex = i;
					}
				}
				// Passenger boards car
				if (carIndex != -1)
				{
					cars.get(carIndex).addPerson(person);
					persons.remove(n);
				}
			}
			else if (person.getDestination() < stationNumber)
			{
				int carIndex = -1;
				// Checks for cars going same direction
				for (int i = 0; i < cars.size(); i++)
				{
					Car car = cars.get(i);
					if (car.getDestination() < stationNumber)
					{
						carIndex = i;
					}
				}
				// Passenger boards car
				if (carIndex != -1)
				{
					cars.get(carIndex).addPerson(person);
					persons.remove(n);
				}
			}
		}
	}
	public void spawnPerson(int destination)
	{
		persons.add(new Person(destination));
	}
	public void addPerson(Person person)
	{
		persons.add(person);
	}
	public void removePerson(int index)
	{
		persons.remove(index);
	}
	public ArrayList<Person> getPersons()
	{
		return persons;
	}

	public void spawnCar(int destination)
	{
		cars.add(new Car(stationNumber, destination));
	}
	public void addCar(Car car)
	{
		cars.add(car);
	}	
	public void removeCar(int index)
	{
		cars.remove(index);
	}
	public ArrayList<Car> getCars()
	{
		return cars;
	}

	public int getStationNumber()
	{
		return stationNumber;
	}
}
