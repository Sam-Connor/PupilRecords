package iManage_EngineeringTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Pupil {

	private String name;
	private Date dateOfBirth;
	private Double rawMark;
	
	public Pupil (String name, Date dateOfBirth, Double rawMark )
	{
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.rawMark = rawMark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		
		return dateOfBirth;
	}
	
	public String getFormatedDateOfBirth()
	{
	
	String datePattern = "dd/MM/yyyy";
	DateFormat df = new SimpleDateFormat(datePattern);
	
	return df.format(dateOfBirth);
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Double getRawMark() {
		return rawMark;
	}

	public void setRawMark(Double rawMark) {
		this.rawMark = rawMark;
	}
	
	
	public static Comparator<Pupil> pupilAgeComparator = new Comparator<Pupil>()
	{
		public int compare(Pupil pupil1, Pupil pupil2)
		{
			return pupil1.getDateOfBirth().compareTo(pupil2.getDateOfBirth());
		}
		
	};
	
	public static Comparator<Pupil> pupilRawMarkComparator = new Comparator<Pupil>()
	{
		public int compare(Pupil pupil1, Pupil pupil2)
		{
			return pupil2.getRawMark().compareTo(pupil1.getRawMark());
		}
		
	};
	
}
