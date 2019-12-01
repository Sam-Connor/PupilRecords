package iManage_EngineeringTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	/*
	String datePattern = "dd/MM/yyyy";
	DateFormat df = new SimpleDateFormat(datePattern);
	
	Use this to format date when displaying
	Not on accessor
	*/

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Double getRawMark() {
		return rawMark;
	}

	public void setRawMark(Double rawMark) {
		this.rawMark = rawMark;
	}
	
	
	
	
}
