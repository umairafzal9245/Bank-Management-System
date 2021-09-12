package firstpackage;
import java.util.ArrayList;
import java.util.Date; 


public class Accounts 
{
	protected Long Accountnumber;
	protected double Balance;
	private String datecreated;
	public ArrayList<String> Transactions = new ArrayList<String>();
	
	public Accounts()
	{
		Accountnumber = (long) 0;
		Balance = 0;
		datecreated = "";
	}
	public Accounts(Long Accountnumber,double Balance)
	{
		this.Accountnumber = Accountnumber;
		this.Balance = Balance;
		this.datecreated = java.time.LocalDate.now().toString();
	}
	
	public void display(){}
	public void CalculateInterest(){}
	public void specifyinterestrate(double interestrate){}
	public boolean makeDeposit(double amount)
	{ 
		return true;
	}
	public boolean makeWithdrawal(double amount){return true;}
	public void calculateZakat(){}
	
	public double checkBalance()
	{ 
		return Balance;
	}
	public boolean transferAmount()
	{
		return true;
	}
	
	public void printStatement()
	{
		
	}
	
	public String getaccounttype(){
		return "";
	}
	public Long getAccountnumber() {
		return Accountnumber;
	}
	public void setAccountnumber(Long accountnumber) {
		Accountnumber = accountnumber;
	}
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	public String getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}
}
