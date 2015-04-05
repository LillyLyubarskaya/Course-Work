package network;

import java.awt.Point;
import java.util.ArrayList;
import operator.Operator;

public  class Tower {
	//max amount of connections
	private int bound=10;
	private Point coordinates;
	private ArrayList<Operator> operators=new ArrayList<>();
	private  volatile int connections=0;
	public Tower(Point coordinates){
		this.coordinates=coordinates;
	}
	public void deleteConnection(){
		connections--;
	}
	public void addOperator(Operator...operator){
		for(Operator o:operator){
			operators.add(o);
		}
	}
	public boolean containsOperator(Operator operator){
		return operators.contains(operator);
	}
	Point getCoordinates() {
		return coordinates;
	}
	void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}
	int getBound() {
		return bound;
	}
	void setBound(int bound) {
		this.bound = bound;
	}
	boolean addConnection(){
		if(connections<bound){
			connections++;
			return true;
		}
		return false;
	}
	

}
