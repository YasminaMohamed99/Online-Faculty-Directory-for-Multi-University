package User;

import java.sql.SQLException;
import java.util.Scanner;

import Data.DataBase;

public class User {
	DataBase data = new DataBase();
	Scanner stringScan = new Scanner(System.in);

	public void listAllFaculty() throws SQLException{
		data.selectAll();
	}
	public void SearchByName() throws SQLException{
		System.out.print("Enter Faculty Name:");
		String facultyname = stringScan.nextLine();
		if(data.verifyFacultyName(facultyname)) {
			data.selectFacultyByName(facultyname);
		} else {
			System.out.println("Faculty is not found!\n");
		}
	}
	public void SearchByDepartment() throws SQLException{
		System.out.print("Enter Department Name:");
		String facultydepartment = stringScan.nextLine();
		data.selectFacultyByDeb(facultydepartment);
	}
	public void SearchByCourse() throws SQLException{
		System.out.print("Enter Course Name:");
		String facultycourse = stringScan.nextLine();
		data.selectFacultyBycourse(facultycourse);
	}
	public void SearchByAreaOfExparties() throws SQLException{
		System.out.print("Enter Faculty Area of Experties:");
		String facultyArea = stringScan.nextLine();
		data.selectFacultyByArea(facultyArea);
	}
	public void SearchByProfessionalInterst() throws SQLException{
		System.out.print("Enter Faculty Professional Interst:");
		String facultyProf = stringScan.nextLine();
		data.selectFacultyByProfissional(facultyProf);
	}

}
