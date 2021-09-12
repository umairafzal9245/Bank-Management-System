package firstpackage;
import java.util.ArrayList;

public class Customers 
{
	private String Name;
	private String Address;
	private Long Phone;
	public ArrayList<Long> Accountnumber = new ArrayList<Long>();
	private int loginpin;
	public boolean logincheck;
	
	public Customers()
	{
		Name = "";
		Address = "";
		Phone = (long) 0;
		setLoginpin(0);
		logincheck = false;
	}
	public Customers(String Name,String Address,Long Phone,Long Accountnumber)
	{
		this.Name = Name;
		this.Address = Address;
		this.Phone = Phone;
		this.setLoginpin(0);
		this.logincheck = false;
		this.Accountnumber.add(Accountnumber);
	}
	public boolean checktwoaccounts()
	{
		if(Accountnumber.size() >= 2)
			return true;
		
		return false;
	}
	public void display()
	{
		System.out.print("\t\tName : "+Name+"\tAddress : "+Address+"\tPhone : "+Phone+"\tPin : "+loginpin+"\n\n");
	}
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Long getPhone() {
		return Phone;
	}

	public void setPhone(Long phone) {
		Phone = phone;
	}
	public ArrayList<Long> getaccountnumbers()
	{
		return Accountnumber;
	}
	public int getLoginpin() {
		return loginpin;
	}
	public void setLoginpin(int loginpin) {
		this.loginpin = loginpin;
	}	
}
