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

	public void addPerson(int destination)
	{
		persons.add(new Person(destination));
	}
	public ArrayList<Person> getPersons()
	{
		return persons;
	}

	public void addCar(int destination)
	{
		cars.add(new Car(stationNumber, destination));
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
