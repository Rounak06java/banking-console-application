package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Scanner sc;
	static AccountList AL;
	static HashSet<Account> frozen;
	
	 Main()
	 {
		 sc= new Scanner(System.in);
		 AL = new AccountList();
		 frozen = new HashSet<Account>();
	 }
	 public long generateID()
	 {
		 long newaccid;
			long maxaccid=0;
			if(AL.acclist.size()==0)
				newaccid =0;
			else
			{
				 maxaccid = Long.MIN_VALUE;
				 for (Map.Entry mapElement : AL.acclist.entrySet()) {
			            long key = (Long)mapElement.getKey();
			            if(key>maxaccid)
						maxaccid= key;
				}
			}
			 newaccid = maxaccid + 1;
			 return newaccid;
	 }
	 public int InputInt()
	 {
		String a = sc.nextLine();
		if(checkNum(a))
			return Integer.parseInt(a);
		else
		{
			System.out.println("Please retry with correct format");
			return InputInt();
		}
	 }
	 public double InputDouble()
	 {
		 String a = sc.nextLine();
			if(checkDecimalNum(a))
				return Double.parseDouble(a);
			else
			{
				System.out.println("Please retry with correct format");
				return InputDouble();
			} 
	 }
	 public long InputLong()
	 {
		 String a = sc.nextLine();
		 if(checkNum(a))
				return Long.parseLong(a);
			else
			{
				System.out.println("Please retry with correct format");
				return InputLong();
			} 
	 }
	 public boolean checkNum(String s)
	 {
		 for (int i=0;i<s.length();i++)
		 {
			 if(s.charAt(i)<48 || s.charAt(i)>57)
				 return false;
		 }
		 return true;
	 }
	 public boolean checkDecimalNum(String s)
	 {
		 if(checkNum(s))
			 return true;
		 else
		 {
			 int countdot = 0;
			 for (int i=0;i<s.length();i++)
			 {
				 if(s.charAt(i)=='.')
				 {
					 countdot++;
				 }
				 if((s.charAt(i)<48 || s.charAt(i)>57) && s.charAt(i)!='.')
					 return false;
				 
			 }
			 if(countdot<=1)
				 return true;
			 else
				 return false;
		 }
	 }
	public void topLevelMenu()
	{
		System.out.println("Choose an Option");
		System.out.println("1. Create New Account");
		System.out.println("2. Login to Existing Account");
		System.out.println("3. Exit Application");
		System.out.println("Choose an option");
		int c = InputInt();
		switch(c)
		{
		case 1:
			createNewAccount();
			return;
			
		case 2:
			LoginToExistingAccount();
			return;
			
		case 3:
			return;
		default:
			System.out.println("Wrong Option is chosen. Please retry");
			topLevelMenu();
			return;
						
		}
		
	}
	

	
	public void LoginToExistingAccount() {
		System.out.println("Enter your Account ID");
		long accid = InputLong();
		
		Account a = AL.RetrieveAccount(accid);
		if(a==null)
		{
			System.out.println("Account Does Not Exist. Try Again");
			topLevelMenu();
			return;
			
		}
		if(frozen.contains(a))
		{
			System.out.println("The Account has been frozen. Please contact Administrator");
			topLevelMenu();
			return;
		}
		else
		{
			String pass;
			int i =0;
			do {
				System.out.println("Please enter password");
				pass = sc.nextLine();
				i++;
				if(i==5)
				{
					break;
					
				}
				if(!(pass.equals(a.getPass())))
						System.out.println("Wrong Password! Please Try Again");
			}while (!(pass.equals(a.getPass())));
			if(i==5)
			{
				System.out.println("You have exceeded the allowed number of attempts. So your account is freezed. Please contact the administrator.");
				frozen.add(a);
				topLevelMenu();
				return;
			}
			Account a1 = AL.RetrieveAccount(accid);
			postLoginAccount(a1);
			return;
			
		
		}
		
		
	}
	public void postLoginAccount(Account a) {
		System.out.println("Choose an option: ");
		System.out.println("1. View Account Details");
		System.out.println("2. Send Money");
		System.out.println("3. View All Transactions");
		System.out.println("4. Logout");
		System.out.println("5. Exit the Application");
		int c = InputInt();
		switch (c)
		{
		case 1:
			System.out.println("The account details are given below");
			System.out.println(a.toString());
			postLoginAccount(a);
			return;
		case 2:
			sendMoney(a);
			return;
		case 3:
			System.out.println("The details of all the transactions are");
			a.displayTransactions();
			postLoginAccount(a);
			return;
		case 4:
			topLevelMenu();
			return;
		case 5:
			return;
		default:
			System.out.println("Please enter the correct option");
			postLoginAccount(a);
			return;
			
			
		}
		
		
	}
	public void sendMoney(Account a) {
		System.out.println("Enter the Account ID of the Receiver. Press -1 to cancel");
		long accid = InputLong();
		if(accid ==-1)
			topLevelMenu();
		Account rec = AL.RetrieveAccount(accid);
		if(rec==null|| accid==a.getAccid())
		{
			System.out.println("Account does not exist. Please try again");
			sendMoney(a);
		}
		else
		{
			System.out.println("The following are the account details of receiver, please cross check");
			System.out.println(rec.toStringWithoutBal());
			accountVerificationofReceiver(a, rec);
		}
	}
	public void accountVerificationofReceiver(Account send, Account rec) {
		System.out.println("1. Yes, these are correct");
		System.out.println("2. No, I want to retry");
		int ch = InputInt();
		switch(ch)
		{
		case 1: 
			System.out.println("Enter the amount to be sent.");
			double amt = InputDouble();
			if(amt>send.getBalance())
			{
				System.out.println("Insufficient Balance. Try Again");
				sendMoney(send);
			}
			send.sendMoney(rec, amt);
			break;
		case 2:
			sendMoney(send);
			break;
		default:
			System.out.println("Wrong Option. Please try again");
			accountVerificationofReceiver(send, rec);
			break;
		
		}
		System.out.println("Amount transferred successfully");
		afterTransferMenu(send);
		return;
	
			

		
	}
	public void afterTransferMenu(Account a) {
		System.out.println("1. Transfer Amount Again");
		System.out.println("2. Go back to previous menu");
		System.out.println("3. Logout");
		System.out.println("4. Exit Application");
		System.out.println("Enter your choice:");
		int c = InputInt();
		switch(c)
		{
		case 1:
			sendMoney(a);
			return;
		case 2:
			postLoginAccount(a);
			return;
		case 3:
			topLevelMenu();
			return;
		case 4:
			return;
			default:
				System.out.println("Wrong Option. Try Again");
				afterTransferMenu(a);
				return;
		}
		
	}
	public void createNewAccount() {
		System.out.println("Please Enter your First Name:");
		String firstName = sc.nextLine();
		System.out.println("Please Enter your Last Name:");
		String lastName = sc.nextLine();
		System.out.println("Enter the Branch Name:");
		String branchName = sc.nextLine();
		System.out.println("How much amount would you like to deposit");
		double balance = InputDouble();
		long accid = generateID();
		String firpass,confirmPass;
		do {
		System.out.println("Enter your password");
		firpass= sc.nextLine();
		System.out.println("Reconfirm password");
		confirmPass = sc.nextLine();
		if(!(firpass.equals(confirmPass)))
		{
			System.out.println("Password didn't match! Retry"); 
		}
		}while(!(firpass.equals(confirmPass)));
		AL.addAccount(firstName, lastName, branchName, balance, accid, confirmPass);
		System.out.println("Your Accont is successfully created with the following Account ID: " + accid);
		displayOptionsAfterAccCreation();
		
		
		
		
	}
	public void displayOptionsAfterAccCreation()
	{
		System.out.println("Choose an option: ");
		System.out.println("1. Login to Your Account");
		System.out.println("2. Return back to the previous menu");
		System.out.println("3. Exit the System");
		int c = InputInt();
		switch(c)
		{
		case 1:
			LoginToExistingAccount();
			return;
		case 2:
			topLevelMenu();
			return;
		case 3:
			return;
		default:
			System.out.println("Wrong Option is chosen. Please retry");	
			displayOptionsAfterAccCreation();
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to ABC Bank");
		Main m = new Main();
		m.topLevelMenu();
		System.out.println("Thank you for using our services");

	}

}
