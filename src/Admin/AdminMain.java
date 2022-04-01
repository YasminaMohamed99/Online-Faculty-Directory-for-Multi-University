package Admin;

import java.sql.*;
import java.util.*;

public class AdminMain {

	public static void main(String[] args) throws SQLException {
		int choice;
		Admin admin = new Admin();
		Scanner intScan = new Scanner(System.in);
				
		Boolean isFound = admin.login();
		
		if(isFound) {
			while(true){
				System.out.println();
				System.out.println("To View Faculty Press 1");
				System.out.println("To Add Faculty Press 2");
				System.out.println("To Update Faculty Name Press 3");
				System.out.println("To Update Faculty Area of Experties Press 4");
				System.out.println("To Delete Faculty Press 5");
				System.out.println("To Exit Press 0");
				choice = intScan.nextInt();
				System.out.println();
				
				if(choice == 1){
					admin.ViewFaculty();
				} else if(choice == 2){
					admin.AddFaculty();
				} else if(choice == 3){
					admin.UpdateFacultyName();
				} else if(choice == 4){
					admin.UpdateFacultyArea();		
				} else if(choice == 5){
					admin.DeleteFaculty();
				} else if(choice == 0)
					break;
				else{
					System.out.println("The Choice is Erorr please enter the correct choice!");
					continue;
				}
					
			}
		} else {
			System.out.println("UserName or Password is incorrect!");
		}
	}

}
