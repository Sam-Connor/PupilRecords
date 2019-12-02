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
	static String fileLocation = "C:\\Users\\samc5\\git\\pupilRecords\\iManage_EngineeringTask\\testData.csv";
	
	
	public static void main(String[] args) {
		
		pupils = new ArrayList<Pupil>();
		populatePupilList();
		printPupilList();
		
		System.out.println();
		displayPupilsByAge();
		
		System.out.println();
		displayPupilsByRawMark();
		
		displayAverageMark();
		
		System.out.println();
		//addPupil();
		//printPupilList();

	}

	public static void readFile() throws IOException, ParseException
	{
		//Creates a reader class directed to a location to read in a file
		Reader in = new FileReader(fileLocation);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
		
		//Assigns a format for the dateOfBirth to be stored in.
		DateFormat df = new SimpleDateFormat(datePattern);
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		
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
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
		
	    
		
		for (int i = 0; i < pupils.size(); i++) {
			Pupil record = pupils.get(i);
			String dateOfBirth = new SimpleDateFormat(datePattern).format(record.getDateOfBirth());
		    System.out.printf("%-20s %-20s %-5s", record.getName(), dateOfBirth, record.getRawMark());
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
		try {
			 dateOfBirth = new SimpleDateFormat(datePattern).parse(inputDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Please enter the pupil's raw mark (one decimal place)");
		Double rawMark = input.nextDouble();
		
		input.close();
		
		Pupil pupilToAdd = new Pupil(name, dateOfBirth, rawMark);
		pupils.add(pupilToAdd);
		
		
		try {
			FileWriter fw = new FileWriter(fileLocation,true);
			PrintWriter pw = new PrintWriter(fw);
			String record = name + "," + inputDate + "," + rawMark;
			pw.println(record);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}



