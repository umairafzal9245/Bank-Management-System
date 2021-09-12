package firstpackage;
import java.text.SimpleDateFormat;  
import java.util.Date;  
public class Saving_Accounts extends Accounts
{
	private double interestrate;
	private double interestamount;
	public String accountype = "saving";
	public double zakat;
	public Saving_Accounts(Long Accountnumber,double Balance) 
	{
		super(Accountnumber,Balance);
		this.interestrate = 0;
		this.interestamount = 0;
		this.zakat = 0;
	}
	@Override
	public void display()
	{
		System.out.print("\t\tAccount number : "+super.getAccountnumber()+"\tBalance : "+super.getBalance()+" Rs\tDate Created : "+super.getDatecreated()+"\n");
		System.out.print("\t\tInterest rate : "+interestrate+" %\t\tAccount Type : Saving\t\tInterest Amount : "+interestamount+" Rs\t\tZakat : "+zakat+" Rs\n\n");
	}
	@Override
	public void specifyinterestrate(double interestrate)
	{
		this.interestrate = interestrate;
	}
	@Override
	public void CalculateInterest()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    String dateandtime = formatter.format(date).toString();
		if(interestrate > 0)
		{
			interestamount = (Balance*interestrate)/100;
			Balance -= interestamount;
			System.out.print("\nInterest amount : "+interestamount+" Rs withdrawal from your account "+Accountnumber+"\n\n");
			Transactions.add("Interest calculated and deducted "+interestamount+" Rs | Date : "+dateandtime);
		}
		else 
		{
			System.out.print("\nInterest rate should be greater than 0 to calculate interest\n");
		}
	}
	@Override
	public boolean makeDeposit(double amount)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    String dateandtime = formatter.format(date).toString();
	    
		Balance = Balance + amount;
		System.out.print("\nBalance "+Balance+" Rs has been succesfully deposited in your saving account\n\n");
		Transactions.add("Deposited "+amount+" Rs | Date : "+dateandtime);
		CalculateInterest();
		return true;
	}
	@Override
	public boolean makeWithdrawal(double amount)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    String dateandtime = formatter.format(date).toString();
		if(amount < Balance)
		{
			Balance = Balance - amount;
			System.out.print("\nAmount "+amount+" Rs has been withdrawal from your account\n");
			Transactions.add("Withdrawal "+amount+" Rs | Date : "+dateandtime);
		}
		else 
		{
			System.out.print("\nNot enough balance\n");
		}
		return true;
	}
	@Override
	public String getaccounttype()
	{
		return accountype;
	}
	@Override
	public void calculateZakat()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    String dateandtime = formatter.format(date).toString();
		zakat = (Balance*2.5)/100;
		Balance -= zakat;
		System.out.print("\nZakat amount : "+zakat+" Rs withdrawal from your account\n\n");
		Transactions.add("Zakat calculated and deducted "+zakat+" Rs | Date : "+dateandtime);
	}
	public double getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}
	public double getInterestamount() {
		return interestamount;
	}
	public void setInterestamount(double interestamount) {
		this.interestamount = interestamount;
	}
	
}
