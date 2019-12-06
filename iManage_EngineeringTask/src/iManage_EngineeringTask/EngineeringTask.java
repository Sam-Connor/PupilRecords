package iManage_EngineeringTask;

import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.csv.*;

public class EngineeringTask {
	
	static ArrayList<Pupil> pupils;
	static String datePattern = "dd/MM/yyyy";
	static String fileLocation = "C:\\Users\\Sam\\git\\PupilRecords\\iManage_EngineeringTask\\testData.csv";
	
	
	public static void main(String[] args)
	{
		pupils = new ArrayList<Pupil>();
		populatePupilList();
		
		menuSelector();
	}

	public static void readFile() throws IOException, ParseException
	{
		//Creates a reader class directed to a location to read in a file
		Reader in = new FileReader(fileLocation);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
		
		//Assigns a format for the dateOfBirth to be stored in.
		DateFormat df = new SimpleDateFormat(datePattern);
		
		//iterates over all the records and parses the data.
		for (CSVRecord record : records)
		{
			String name = record.get("Name");
			String dateOfBirth = record.get("Date Of Birth");
			String rawMark = record.get("Raw Mark");
			
			Date date = df.parse(dateOfBirth);
			Double mark = Double.parseDouble(rawMark);
			
			Pupil p = new Pupil(name, date, mark);
			pupils.add(p);
			
		}
	}
	
	public static void populatePupilList()
	{
		try
		{
			readFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void displayPupilsByAge()
	{
		Collections.sort(pupils, Pupil.pupilAgeComparator);
		printPupilList();
	}
	
	public static void displayPupilsByRawMark()
	{
		Collections.sort(pupils, Pupil.pupilRawMarkComparator);
		printPupilList();
	}
	
	public static void printPupilList()
	{
		System.out.printf("%-20s %-20s %-5s", "Name", "Date Of Birth", "Mark");
		System.out.println();
		
		for (Pupil pupil : pupils)
		{
			String dateOfBirth = new SimpleDateFormat(datePattern).format(pupil.getDateOfBirth());
		    System.out.printf("%-20s %-20s %-5s", pupil.getName(), dateOfBirth, pupil.getRawMark());
		    System.out.println();
		}		
	}

	public static void displayAverageMark()
	{
		double markTotal = 0;
		for (Pupil pupil : pupils)
		{
			markTotal += pupil.getRawMark();
		}
		
		double averageMark = markTotal / pupils.size();
		System.out.printf("The average mark of the pupils is: %.1f"  , averageMark );
		System.out.println();
	}

	public static void addPupil() 
	{
		Scanner input = new Scanner(System.in);
		System.out.println("--Adding a new pupil--");
		
		System.out.println("Please enter the pupil's name");
		String name = input.next();
		
		System.out.println("Please enter the pupil's date of birth (dd/mm/yyyy)");
		
		String inputDate = input.next();
		Date dateOfBirth = null;
		try 
		{
			 dateOfBirth = new SimpleDateFormat(datePattern).parse(inputDate);
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Please enter the pupil's raw mark (one decimal place)");
		Double rawMark = input.nextDouble();
		
		Pupil pupilToAdd = new Pupil(name, dateOfBirth, rawMark);
		pupils.add(pupilToAdd);
	
		
		try 
		{
			FileWriter fw = new FileWriter(fileLocation,true);
			PrintWriter pw = new PrintWriter(fw);
			String record = name + "," + inputDate + "," + rawMark;
			pw.println(record);
			pw.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
	}

	public static void displayMenu()
	{
		System.out.println("-----------Menu Options-----------");
		System.out.println("1. Display All Pupils By Age - Oldest First");
		System.out.println("2. Display All Pupils By Raw Mark - Highest First");
		System.out.println("3. Display Average Raw Mark of All Pupils");
		System.out.println("4. Add New Pupil");
	}
	
	public static void menuSelector()
	{
		
		Scanner menuInput = new Scanner(System.in);
		boolean active = true;
		
		while(active)
		{
		displayMenu();
		System.out.println("Please select a menu choice: (1-5)");
		int choice = menuInput.nextInt();
		
		switch(choice)
		{
		case 1: displayPupilsByAge();
		break;
		case 2: displayPupilsByRawMark();
		break;
		case 3: displayAverageMark();
		break;
		case 4: addPupil();
		break;
		default: System.out.println("You have entered an invalid choice, try again.");
		menuSelector();
		break;
		}
		
		System.out.println("Do you want to use another menu option? (Y/N)");
		String restartChoice = menuInput.next();
		System.out.println(restartChoice);
		
		if(restartChoice.equalsIgnoreCase("y"))
		{
			active = true;
		}
		
		else if(restartChoice.equalsIgnoreCase("n"))
		{
			active = false;
		}
		
		else
		{
			System.out.println("Error - Invalid Choice");
			active = false;
			break;
		}
		}
		menuInput.close();
		
	}
}



