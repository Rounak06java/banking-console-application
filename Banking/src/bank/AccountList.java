package bank;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountList {
	@Override
	public String toString() {
		return "AccountList [acclist=" + acclist + "]";
	}
	HashMap<Long, Account> acclist;
	AccountList()
	{
		this.acclist = new HashMap<Long, Account>();
	}
	
	void addAccount(String accHolderFirstName, String accHolderLastName, String branchName, double balance, long newaccid,
			String pass)
	{
		
		Account acc = new Account(accHolderFirstName, accHolderLastName, branchName, balance, newaccid, pass);
		
		
		acclist.put(newaccid, acc);
	}
	Account RetrieveAccount(long accid)
	{
		if(this.acclist.containsKey(accid))
			return this.acclist.get(accid);
		else
			return null;
	}
	void removeAccountfromAccID(long accid)
	{
		this.acclist.remove(acclist);
		
	}
	
	

}
