/**
 * Car.java
 * @author August Cho
 * @version 2024-02-06
 */

import java.util.ArrayList;

public class Car
{
	private ArrayList<Person> persons;	
	private int initialStation;
	private int currentStation;
	private int destination;
	private boolean readyToMove;

	public Car(int initialStation, int destination)
	{
		persons = new ArrayList<Person>();
		this.initialStation = initialStation;
		this.currentStation = initialStation;
		this.destination = destination;
		this.readyToMove = true;
	}

	public void setReadyToMove(boolean b)
	{
		readyToMove = b;
	}
	public boolean getReadyToMove()
	{
		return readyToMove;
	}
	public void addPerson(Person person)
	{
		if (persons.size() < 4)
		{
			persons.add(person);
		}
	}
	public void removePerson(int index)
	{
		persons.remove(index);
	}
	public ArrayList<Person> getPersons()
	{
		return persons;
	}
	public int getStationNumber()
	{
		return currentStation;
	}
	public int getDestination()
	{
		return destination;
	}
}
