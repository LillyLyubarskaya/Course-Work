package consumer;
import java.util.ArrayList;

import network.Network;
import operator.Operator;
public class Account {
	// 0-offline 1-free 2-busy
	private volatile int state=0;
	private String number;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	private ArrayList<String> sms=new ArrayList<String>();
	private Consumer owner;
	private Operator operator;
	private int balance;
	public Account(Operator operator,int balance,String number){
		setNumber(number);
		setOperator(operator);
		setBalance(balance);
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void changeBalane(int delta){
		balance+=delta;
	}
	public Consumer getOwner() {
		return owner;
	}
	public void setOwner(Consumer owner) {
		this.owner = owner;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public void connect(){
		if(Network.registerInNetwork(this)){
			System.out.println(" >> YOU'RE ONLINE "+this.getNumber());
			state=1;
		}
		else{
			 System.out.println(" >> CANNOT FIND NETWORK "+this.getNumber());
		}
	}
	public synchronized void  acceptSMS(String sms){
		this.sms.add(sms);
	}
	public void communicate(Account account,Object context){
		if(balance>0 && state==1){
			Network.commutateWith(this, account,context);
		}else{
			System.out.println(" >> NOT ENOUGH MONEY ON BALANCE OR IS BUSY ACCOUNT "+this.getNumber());
		}
	}
	public boolean isFree(){
		if(state==1)
			return true;
		return false;		
	}
	public void setBusy(){
		state=2;
	}
	public void setFree(){
		state=1;
	}
}
