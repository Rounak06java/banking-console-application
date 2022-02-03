package bank;

import java.util.ArrayList;

public class Account {
	
	public String toString() {
		return "Account [First Name =" + AccHolderFirstName + ", Last Name =" + AccHolderLastName
				+ ", Branch Name =" + branchName + ", balance =" + balance + ", Account ID =" + accid + "]";
	}
	public String toStringWithoutBal() {
		return "Account [First Name =" + AccHolderFirstName + ", Last Name =" + AccHolderLastName
				+ ", Branch Name =" + branchName + ", Account ID =" + accid + "]";
	}
	private String AccHolderFirstName;
	private String AccHolderLastName;
	private String branchName;
	private double balance;
	private long accid;
	private String pass;
	private ArrayList<Transaction> tranlist;
	public Account(String accHolderFirstName, String accHolderLastName, String branchName, double balance, long accid,
			String pass) {
		
		AccHolderFirstName = accHolderFirstName;
		AccHolderLastName = accHolderLastName;
		this.branchName = branchName;
		this.balance = balance;
		this.accid = accid;
		this.pass = pass;
		tranlist = new ArrayList<Transaction>();
	}
	public String getAccHolderFirstName() {
		return AccHolderFirstName;
	}
	public void setAccHolderFirstName(String accHolderFirstName) {
		AccHolderFirstName = accHolderFirstName;
	}
	public String getAccHolderLastName() {
		return AccHolderLastName;
	}
	public void setAccHolderLastName(String accHolderLastName) {
		AccHolderLastName = accHolderLastName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getAccid() {
		return accid;
	}
	public void setAccid(long accid) {
		this.accid = accid;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void addTransaction(Transaction t)
	{
		this.tranlist.add(t);
	}
	public ArrayList<Transaction> getTransactions()
	{
		return this.tranlist;
	}
	public String getFullName()
	{
		return this.AccHolderFirstName + " " + this.AccHolderLastName;
	}
	public void sendMoney(Account a, double amt)
	{
		if(this.balance>amt)
		{
			this.setBalance(this.balance-amt);
			a.setBalance(a.getBalance()+amt);
			Transaction t = new Transaction(this, a, amt);
			tranlist.add(t);
			a.addTransaction(t);
		}
		
	}
	public void displayTransactions()
	{
		if(this.tranlist.size()==0)
			System.out.println("No transactions yet!");
		for (Transaction t: this.tranlist)
		{
			String sender = t.getSender()==this? "You": t.getSender().getFullName();
			String receiver = t.getReceiver()==this? "You": t.getReceiver().getFullName();
			double amt = t.getTransferredAmount();
			System.out.println(sender + " sent " + receiver + " " + amt + " amount");
		}
	}
	
	

}
