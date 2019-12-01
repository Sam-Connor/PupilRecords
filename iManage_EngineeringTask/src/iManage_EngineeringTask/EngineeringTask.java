package iManage_EngineeringTask;

import java.io.*;
import java.text.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.csv.*;

public class EngineeringTask {

	public static void main(String[] args) {
		
		ArrayList<Pupil> pupils = new ArrayList<Pupil>();
		populatePupilList(pupils);
		

	}

	public static void readFile(ArrayList<Pupil> pupils) throws IOException, ParseException
	{
		//Creates a reader class directed to a location to read in a file
		String fileLocation = "C:\\Users\\samc5\\eclipse-workspace\\iManage_EngineeringTask\\testData.csv";
		Reader in = new FileReader(fileLocation);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
		
		//Assigns a format for the dateOfBirth to be stored in.
		String datePattern = "dd/MM/yyyy";
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
	
	public static void populatePupilList(ArrayList<Pupil> pupils)
	{
		try {
			readFile(pupils);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String datePattern = "dd/MM/yyyy";
		DateFormat df = new SimpleDateFormat(datePattern);
		
		for (Pupil pupil : pupils) {
			System.out.println(pupil.getName() + " " + df.format(pupil.getDateOfBirth()) + " " + pupil.getRawMark());
		}
	
	}
	
}



