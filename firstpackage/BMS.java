package firstpackage;

import java.util.ArrayList;
import java.util.Scanner;

public class BMS 
{
	ArrayList<Customers> CustomersList;
	ArrayList<Accounts> AccountsList;
	
	public BMS()
	{
		CustomersList = new ArrayList<Customers>();
		AccountsList = new ArrayList<Accounts>();
	}
	public int searchcustomers(String name)
	{
		int counter = -1;
		if(!CustomersList.isEmpty())
		{
			for	(int i=0;i<CustomersList.size();i++)
			{
				if(CustomersList.get(i).getName().equals(name))
				{
					counter = i;
					break;
				}
			}
		}
		return counter;
	}
	public int searchcustomers(Long accountnumber)
	{
		int counter = -1;
		if(!CustomersList.isEmpty())
		{
			for	(int i=0;i<CustomersList.size();i++)
			{
				if(CustomersList.get(i).Accountnumber.get(0).equals(accountnumber))
				{
					counter = i;
					break;
				}
				if(CustomersList.get(i).Accountnumber.size() > 1)
				{
					if(CustomersList.get(i).Accountnumber.get(1).equals(accountnumber))
					{
						counter = i;
						break;
					}
				}
			}
		}
		return counter;
	}
	public int searchaccounts(Long accountnumber)
	{
		int counter = -1;
		if(!AccountsList.isEmpty())
		{
			for	(int i=0;i<AccountsList.size();i++)
			{
				if(AccountsList.get(i).getAccountnumber().equals(accountnumber))
				{
					counter = i;
					break;
				}
			}
		}
		return counter;
	}
	public void openanaccount()
	{
		Scanner input = new Scanner(System.in);
		String name = new String(),Address = new String(),Accounttype;
		Long phone;
		double balance;
		System.out.print("Enter your name : ");
		name = input.nextLine();
		System.out.print("Enter your Address : ");
		Address = input.nextLine();
		System.out.print("Enter your phone : ");
		phone = input.nextLong();
		System.out.print("Enter your balance : ");
		balance = input.nextDouble();
		long accountnumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		int loginpin = (int) Math.floor(Math.random() * 9000) + 1000;
		input.nextLine();
		System.out.print("Enter checking for checking account and Saving for saving account : ");
		Accounttype = input.nextLine();
		
		
		int index = searchcustomers(name);
		if(index == -1)
		{
			if(Accounttype.toLowerCase().equals("checking"))
			{
				Accounts newaccount = new Checking_Accounts(accountnumber,balance);
				Customers newcustomer = new Customers(name,Address,phone,accountnumber);
				newcustomer.setLoginpin(loginpin);
				CustomersList.add(newcustomer);
				AccountsList.add(newaccount);
			}
			else if(Accounttype.toLowerCase().equals("saving"))
			{
				Accounts newaccount = new Saving_Accounts(accountnumber,balance);
				Customers newcustomer = new Customers(name,Address,phone,accountnumber);
				newcustomer.setLoginpin(loginpin);
				CustomersList.add(newcustomer);
				AccountsList.add(newaccount);
			}
			System.out.print("\nYou have successfully registered your account!!!\n");
			System.out.print("Please remember your assigned account number and pin for login!!!\nAccount Details are as follows : \n");
			System.out.print("\t\tName : "+name+"\tAddress : "+Address+"\tAccount Type : "+Accounttype+"\n");
			System.out.print("\t\tAccount Number : "+accountnumber+"\tLogin Pin : "+loginpin+"\tPhone : "+phone+"\tBalance : "+balance+"\n\n");
		}
		else if(index != -1)
		{
			if(CustomersList.get(index).checktwoaccounts() == true)
			{
				System.out.println("\nSir you have already two accounts registered!!!\n\n");
			}
			else if(CustomersList.get(index).checktwoaccounts() == false && Accounttype.toLowerCase().equals("checking"))
			{
				Accounts newaccount = new Checking_Accounts(accountnumber,balance);
				CustomersList.get(index).Accountnumber.add(accountnumber);
				AccountsList.add(newaccount);
				
			}
			else if(CustomersList.get(index).checktwoaccounts() == false && Accounttype.toLowerCase().equals("saving"))
			{
				Accounts newaccount = new Saving_Accounts(accountnumber,balance);
				CustomersList.get(index).Accountnumber.add(accountnumber);
				AccountsList.add(newaccount);	
			}
			System.out.print("\nYou have successfully registered your second account!!!\n");
			System.out.print("Please remember your assigned account number!!!\nAccount Details are as follows : \n");
			System.out.print("\t\tName : "+name+"\tAddress : "+Address+"\tAccount Type : "+Accounttype+"\n");
			System.out.print("\t\tAccount Number : "+accountnumber+"\tPhone : "+phone+"\tBalance : "+balance+"\n\n");
		}
		
	}
	public void closeanaccount()
	{
		Scanner input = new Scanner(System.in);
		Long accountnumber;
		System.out.print("Enter the account number : ");
		accountnumber = input.nextLong();
		
		int index2 = searchcustomers(accountnumber);
		if(index2 != -1)
		{
			if(CustomersList.get(index2).Accountnumber.size() > 1)
			{
				CustomersList.get(index2).Accountnumber.remove(accountnumber);
			}
			else if(CustomersList.get(index2).Accountnumber.size() == 1)
			{
				CustomersList.remove(index2);
			}
			int index = searchaccounts(accountnumber);
			AccountsList.remove(index);
			System.out.print("Account "+accountnumber+" Successfully removed\n\n");
		}
		else if(index2 == -1)
		{
			System.out.print("Account "+accountnumber+" Not present\n\n");
		}

	}
	public void doaccountoperations(Long accountnumber)
	{
		while(true)
		{
			Scanner input = new Scanner(System.in);
			System.out.print("\n1. Deposit an amount\n");
			System.out.print("2. Make a withdrawal\n");
			System.out.print("3. Check balance\n");
			System.out.print("4. Print the bank statement\n");
			System.out.print("5. Transfer amount to other account\n");
			System.out.print("6. Calculate zakat\n");
			System.out.print("7. Display all deductions\n");
			System.out.print("8. Exit\n\n");
			System.out.print("Enter the choice : ");
			int choice = input.nextInt();
			while(choice > 8 || choice < 1)
			{
				System.out.print("Enter the correct choice : ");
				choice = input.nextInt();
			}
			int index = searchaccounts(accountnumber);
			if(choice == 1)
			{
				System.out.print("Enter the deposit amount : ");
				double amount = input.nextDouble();
				AccountsList.get(index).makeDeposit(amount);
			}
			else if(choice == 2)
			{
				System.out.print("Enter the withdrawal amount : ");
				double amount = input.nextDouble();
				AccountsList.get(index).makeWithdrawal(amount);
			}
			else if(choice == 3)
			{
				int index2 = searchcustomers(accountnumber);
				System.out.print("\nName : "+CustomersList.get(index2).getName()+"\tBalance : "+AccountsList.get(index).checkBalance()+" Rs\n\n");
			}
			else if(choice == 4)
			{
				int index2 = searchcustomers(accountnumber);
				System.out.println("Customer Information : ");
				CustomersList.get(index2).display();
					
				
				System.out.println("Customer Account Information : ");
				
				AccountsList.get(index).display();
				
				System.out.println("Transaction History : ");
				
				for	(int i=0;i<AccountsList.get(index).Transactions.size();i++)
				{
					System.out.println("\t\t"+AccountsList.get(index).Transactions.get(i));
				}
				
			}
			else if(choice == 5)
			{
				System.out.print("\nEnter the second bank account number : ");
				Long accountnumber2 = input.nextLong();
				System.out.print("Enter the amount : ");
				double amount = input.nextDouble();
				int index2 = searchaccounts(accountnumber2);
				if(index2 != -1)
				{
					if(AccountsList.get(index).Balance > amount)
					{
						AccountsList.get(index).Balance -= amount;
						AccountsList.get(index2).Balance += amount;
					}
					else 
					{
						System.out.print("\nNot enough balance\n");
					}
				}
				else 
				{
					System.out.print("\nBank account you entered doesnot exist\n");
				}
			}
			else if(choice == 6)
			{
				if(AccountsList.get(index).getaccounttype().equals("saving"))
				{
					if(AccountsList.get(index).Balance >= 20000)
					{
						AccountsList.get(index).calculateZakat();
					}
					else 
					{
						System.out.print("\nBalance is less than 20000\n");
					}
				}
				else
				{
					System.out.print("\nZakat is only calculated for saving account\n\n");
				}
			}
			else if(choice == 7)
			{
				System.out.println("Deductions History : ");
				for(int j=0;j<AccountsList.get(index).Transactions.size();j++)
				{
					System.out.println("\t\t"+AccountsList.get(index).Transactions.get(j));
				}
			}
			else if(choice == 8)
			{
				break;
			}
		}
	}

	public void Menu()
	{
		while(true)
		{
			int choice;
			System.out.print("1. Open an account\n");
			System.out.print("2. Close an account\n");
			System.out.print("3. Login to an account\n");
			System.out.print("4. Perform Account Operations\n");
			System.out.print("5. Specify interest rate\n");
			System.out.print("6. Display all account details\n");
			System.out.print("7. Display all account details along with deductions\n");
			System.out.print("8. Exit\n");
			
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the choice : ");
			choice = input.nextInt();
			while(choice > 8 || choice < 1)
			{
				System.out.print("Enter the correct choice : ");
				choice = input.nextInt();
			
			}
			if(choice == 1)
			{
				openanaccount();
			}
			else if(choice == 2)
			{
				closeanaccount();
			}
			else if(choice == 3)
			{
				input.nextLine();
				String customername = "";
				System.out.print("Enter the Customer name : ");
				customername = input.nextLine();
				System.out.print("Enter the Pin : ");
				int pin = 0;
				pin = input.nextInt();
				int index = searchcustomers(customername);
				if(index == -1)
				{
					System.out.println("\nCustomer with this name isnot present\n\n");
				}
				else if(index != -1)
				{
					if(CustomersList.get(index).getLoginpin() == pin)
					{
						CustomersList.get(index).logincheck = true;
						System.out.println("\nYou have succesfully logged in now you can use different account operations for all your accounts\n");
					}
					else 
					{
						System.out.print("\nInvalid Pin\n");
					}
				}
			}
			else if(choice == 4)
			{
				boolean login = false;
				Long Accountnumberoperations = (long) 0;
				System.out.print("Enter the Account number : ");
				Accountnumberoperations = input.nextLong();
				int index = searchcustomers(Accountnumberoperations);
				if(index == -1)
				{
					System.out.println("\nCustomer with this account number isnot present\n\n");
				}
				else if(index != -1)
				{
					if(CustomersList.get(index).logincheck == true)
					{
						login = true;
					}
				}
				if(login)
				{
					doaccountoperations(Accountnumberoperations);
				}
				else 
				{
					System.out.println("Please login to your account first\n\n");
				}
			}
			else if(choice == 5)
			{
				double interestrate;
				System.out.print("Enter the interestrate : ");
				interestrate = input.nextDouble();
				for	(int i=0;i<AccountsList.size();i++)
				{
					AccountsList.get(i).specifyinterestrate(interestrate);
					AccountsList.get(i).CalculateInterest();
				}
				System.out.print("\nInterest rate of "+interestrate+" % has been set for all accounts\n");
			}
			else if(choice == 6)
			{
				
				for(int i=0;i<CustomersList.size();i++)
				{
					System.out.println("Customer "+(i+1)+" Information : ");
					CustomersList.get(i).display();
					ArrayList<Long> accountnumbers = CustomersList.get(i).getaccountnumbers();
					System.out.println("Customer "+(i+1)+" Accounts Information : ");
					
					int index = searchaccounts(accountnumbers.get(0));
					
					if(index != -1)
					{
						AccountsList.get(index).display();
					}
					if(accountnumbers.size() > 1) 
					{
						int index2 = searchaccounts(accountnumbers.get(1));
						if(index2 != -1)
						{
							AccountsList.get(index2).display();
						}
					}
				}
			}
			else if(choice == 7)
			{
				for(int i=0;i<CustomersList.size();i++)
				{
					System.out.println("Customer "+(i+1)+" Information : ");
					CustomersList.get(i).display();
					ArrayList<Long> accountnumbers = CustomersList.get(i).getaccountnumbers();
					System.out.println("Customer "+(i+1)+" Account 1 Information : ");
					
					int index = searchaccounts(accountnumbers.get(0));
					
					if(index != -1)
					{
						AccountsList.get(index).display();
						System.out.println("Deductions History : ");
						for(int j=0;j<AccountsList.get(index).Transactions.size();j++)
						{
							System.out.println("\t\t"+AccountsList.get(index).Transactions.get(j));
						}
					}
					if(accountnumbers.size() > 1) 
					{
						System.out.println("\nCustomer "+(i+1)+" Account 2 Information : ");
						int index2 = searchaccounts(accountnumbers.get(1));
						if(index2 != -1)
						{
							AccountsList.get(index2).display();
							System.out.println("Deductions History : ");
							for(int j=0;j<AccountsList.get(index2).Transactions.size();j++)
							{
								System.out.println("\t\t"+AccountsList.get(index2).Transactions.get(j));
							}
						}
					}
				}
			}
			else if(choice == 8)
			{
				System.out.print("GOOD BYE :)");
				break;
			}
		}
	}
}
