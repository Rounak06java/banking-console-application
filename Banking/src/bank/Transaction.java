package bank;

public class Transaction {
	private Account Sender;
	private Account Receiver;
	private double transferredAmount;
	public Transaction(Account sender, Account receiver, double transferredAmount) {
		
		Sender = sender;
		Receiver = receiver;
		this.transferredAmount = transferredAmount;
	}
	public Account getSender() {
		return Sender;
	}
	public void setSender(Account sender) {
		Sender = sender;
	}
	public Account getReceiver() {
		return Receiver;
	}
	public void setReceiver(Account receiver) {
		Receiver = receiver;
	}
	public double getTransferredAmount() {
		return transferredAmount;
	}
	public void setTransferredAmount(double transferredAmount) {
		this.transferredAmount = transferredAmount;
	}
	

}
