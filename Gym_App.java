package Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class memberDetails {
	private int id;
	private String name;
	String status;
	LocalDate start_Date;
	LocalDate end_Date;
	private String plan_Type;
	public static int count = 0;

	public memberDetails(int id, String name, String status, String start_Date, String end_Date, String plan_Type) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.id = id;
		this.name = name;
		this.status = status;
		this.start_Date = LocalDate.parse(start_Date, formatter);
		this.end_Date = LocalDate.parse(end_Date, formatter);
		this.plan_Type = plan_Type;
		count++;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public LocalDate getStartDate() {
		return start_Date;
	}

	public LocalDate getEndDate() {
		return end_Date;
	}

	public String getPlanType() {
		return plan_Type;
	}
}

public class Gym_App {
	public static void main(String[] args) throws Exception {
		List<memberDetails> list = new ArrayList<memberDetails>();

		Scanner sc = new Scanner(System.in);
		System.out.println("How many Members do you want to Enter:");
		int num = sc.nextInt();

		for (int i = 0; i < num; i++) {
			System.out.println("Please enter member " + (i + 1) + " details:");
			int id = i+1;
			System.out.print("Enter Name: ");
			String name = sc.next();
			System.out.print("Enter Membership Status(New / Active/ Expired): ");
			String status = sc.next();
			System.out.print("Enter Begin Date(dd-mm-yyyy): ");
			String start_Date = sc.next();
			System.out.print("Enter End Date(dd-mm-yyyy): ");
			String end_Date = sc.next();
			System.out.print("Enter Membership Plan type(Monthly / Quaterly / Yearly) : ");
			String plan_Type = sc.next();

			list.add(new memberDetails(id, name, status, start_Date, end_Date, plan_Type));
		}

		// selecting the choice

		int ch;
		int discount = 0;
		int payAmount = 0;

		while (true) {
			System.out.println("\n **Gym Membership Application*");
			System.out.println(
					"\nSelect option\n1. Display all member details\n2. Search by membership number and  Choose membership plan\n3. Delete an Existing member\n4. Exit");
			System.out.println("Enter your choice");
			ch = sc.nextInt();
			switch (ch) {
			// display all members details
			case 1:
				System.out.println("Employee Details are:");
				System.out.println("ID\tName\t\tStatus\tStart Date\tEnd Date\tPlan Type: ");

				for (memberDetails s : list) {
					System.out.println(s.getId() + "\t" + s.getName() + "\t\t" + s.getStatus() + "\t" + s.getStartDate()
							+ "\t" + s.getEndDate() + "\t" + s.getPlanType());
				}
				break;

			// choose a selected member details and perform discount operation
			case 2:
				System.out.println("Enter the Member Id for display the Details: ");
				int id_check = sc.nextInt();
				System.out.println("Employee Details are:");
				System.out.println("ID\tName\t\tStatus\tStart Date\tEnd Date\tPlan Type: ");

				memberDetails found = null;

				for (memberDetails s : list) {
					if (s.getId() == id_check) {
						found = s;
						System.out.println(s.getId() + "\t" + s.getName() + "\t\t" + s.getStatus() + "\t"
								+ s.getStartDate() + "\t" + s.getEndDate() + "\t" + s.getPlanType());

					}
				}

				if (found == null) {
					System.out.println("Member not found");

				}

				LocalDate now = LocalDate.now();
				long days = ChronoUnit.DAYS.between(now, found.getEndDate());
				System.out.println(days);
				System.out.println(found.getStatus().toLowerCase());

				Boolean isRenewing = false;
				Boolean hasExpired = found.getStatus().toLowerCase().equals("expired") && days < -15;

				if (found.getStatus().toLowerCase().equals("active") && days <= 15) {
					isRenewing = true;
				}

				if (found.getStatus().toLowerCase().equals("expired") && days <= -15) {
					isRenewing = true;
					
				}

				System.out.println("choose membership plan:" + "\n1. Monthly 2000" + "\n2. Quarterly 6000"
						+ "\n3. Half Annual 10,000" + "\n4. Annual 20,000");

				int packInput = sc.nextInt();

				// Monthly pay
				if (packInput == 1) {
					Boolean isDiscounted = false;
					payAmount = 2000;
					discount = payAmount;

					if (isRenewing) {
						isDiscounted = true;
						discount -= 500;
					}
					if (isDiscounted == false) {
						System.out.println("No discount on monthly bases");
					} else {
						System.out.println("After Discount you have to pay :" + discount);
					}
				}
				// Quarterly pay
				else if (packInput == 2) {
					Boolean isDiscounted = false;
					payAmount = 6000;
					discount = payAmount;
					if (isRenewing) {
						isDiscounted = true;
						discount -= 1500;
					}
					if (isDiscounted == false) {
						discount -=1000; 
						System.out.println("discount on Quaterly " +discount);
					} else {
						System.out.println("After Discount you have to pay :" + discount);
					}

					// Half yearly pay
				} else if (packInput == 3) {
					Boolean isDiscounted = false;
					payAmount = 10000;
					discount = payAmount;

					if (isRenewing) {
						isDiscounted = true;
						discount -= 3000;

					}
					if (isDiscounted == false) {
						discount -= 2000;
						System.out.println("discount on half-yearly " +discount);
					}

					else {
						System.out.println("After Discount you have to pay :" + discount);
					}
					// Yearly pay
				} else if (packInput == 4) {
					Boolean isDiscounted = false;
					payAmount = 20000;
					discount = payAmount;
					if (isRenewing) {
						isDiscounted = true;
						discount -= 4000;
					}
					if (isDiscounted == false) {
						discount -= 3000;
						System.out.println("discount on yearly bases" +discount);
					}

					else {
						System.out.println("After Discount you have to pay :" + discount);
					}

				}
				System.out.println("Choose Option: \n1. For Buy.\n2. For cancel.");
				int buyOrCancel = sc.nextInt();
				if (buyOrCancel == 1) {
					found.status = "active";
					// Monthly
					if (packInput == 1) {
						found.end_Date = LocalDate.now().plusMonths(1);
					}
					// Quarterly
					if (packInput == 2) {
						found.status = "active";
						found.end_Date = LocalDate.now().plusMonths(4);

					} // half yearly
					if (packInput == 3) {
						found.status = "active";
						found.end_Date = LocalDate.now().plusMonths(6);

					}
					// yearly
					if (packInput == 4) {
						found.status = "active";
						found.end_Date = LocalDate.now().plusMonths(12);

					}
					System.out.println("Your total payment is :" +discount+".Your subscription will be active till :"  +found.end_Date);

				} else {
					System.out.println("You have opted to cancel Plan");
					payAmount = 0;
				}
				break;

			case 3:
				System.out.println("Enter an Id to Delete an existing member : ");
				int id_remove = sc.nextInt();
				for (memberDetails s : list) {
					if (s.getId() == id_remove) {
						list.remove(id_remove);
					}
				}
				break;

			case 4:
				System.out.println("Exit Successfully");
				System.exit(0);
				break;
			}

		}

	}
}