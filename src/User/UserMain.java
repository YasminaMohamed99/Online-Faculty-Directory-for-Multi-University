package User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserMain {

	public static void main(String[] args) throws SQLException {
		int choice;
		User user = new User();
		Scanner intScan = new Scanner(System.in);
		
		while(true){
			System.out.println("List All Faculty Press 1");
			System.out.println("To Search Press 2");
			System.out.println("To Exit Press 0");
			choice = intScan.nextInt();
			System.out.println();
			
			if(choice == 1){
				user.listAllFaculty();
			}else if(choice ==2){
				while(true){
					System.out.println("Searsh By Name Press 1");
					System.out.println("Searsh By Department Press 2");
					System.out.println("Searsh By Course Press 3");
					System.out.println("Searsh By Area of Experties Press 4");
					System.out.println("Searsh By Professional Interst Press 5");
					System.out.println("To Exit Press 0");
					int c = intScan.nextInt();
					System.out.println();
					if(c==1){
						user.SearchByName();
					}else if(c==2){
						user.SearchByDepartment();
					}else if(c==3){
						user.SearchByCourse();
					}else if(c==4){
						user.SearchByAreaOfExparties();
					}else if(c==5){
						user.SearchByProfessionalInterst();
					}else if(c==0)
						break;
					else{
						System.out.println("The Choice is incorrect please enter the correct choice!");
						continue;
					}
				}
			}else if(choice == 0)
				break;
			else{
				System.out.println("The Choice is incorrect please enter the correct choice!");
				continue;
				}
		}
	}
}
