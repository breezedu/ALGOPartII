package wifeproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhysicianConnections {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		//1st of all, readin informations about each physician
		System.out.println("Readin physician informations.");
		
		
		//initial an arrayList of physicianList including informations about each physician;
		ArrayList<physician> physicianList = new ArrayList<physician>();
		Scanner readin = new Scanner(new File("net2011.txt"));
		
		String title = readin.nextLine();
		
		System.out.println("The titles:" +title);
		
		
		physician firstPhy = new physician(readin.nextInt(), readin.nextInt(), readin.nextInt(), readin.next());
		firstPhy.addOrg(firstPhy.getImsid());
		
		physicianList.add(firstPhy);
		
		while(readin.hasNext()){
			int year = readin.nextInt();
			int pid = readin.nextInt();
			int org = readin.nextInt();
			String imsid = readin.next();
		
			//this is the information of current readed in physician;
			physician currPhy = new physician(year, pid, org, imsid);
			
			//get the last physician from the arrayList;
			int index = physicianList.size();
			physician frontPhy = physicianList.get(index-1);
			
			
			//if current physician has the same physician id as the last one in the arrayList
			//just add the ims-orgnization-id into the org-list of that physician
			//else, we got a new physician, so add the ims-id to his/her org-list
			//then add this new physician to the physician arrayList;
			if(currPhy.getPid() == frontPhy.getPid()){
				frontPhy.addOrg(currPhy.getImsid());
				
			} else {
				
				currPhy.addOrg(currPhy.getImsid());
				physicianList.add(currPhy);
				
			}//end if-else conditions;
			
			
		}//end while() loop;
		
		
		readin.close();
		
		
		
		//printout some basic informations about the first two physicians:
		int totalPhy = physicianList.size();
		System.out.println("There are " + physicianList.size() + " physicians.");
		
		printPhysician(firstPhy);
		printPhysician(physicianList.get(1));
		//so far everthing works well
		
		
		//2nd, calculate the connections of each physician
		System.out.println("Calculate the connections of each physician:");
		
		for(int i=0; i<totalPhy; i++){
		
			physician currPhy = physicianList.get(i);
			
			//compare currPhy's orgList with every orgList of other physician in the list
			for(int j=i+1; j<totalPhy; j++){
				
				physician tempPhy = physicianList.get(j);
				if( connectTwoPhy(currPhy, tempPhy)){
					
					currPhy.addConn(1);
					tempPhy.addConn(1);
					
				}//end if condition;
				
			}//end inner for j<totalPhy loop;
		
		}//end outer for i<totalPhy loop;
		
		
		for(int i=0; i<totalPhy; i++){
			
			printPhysician(physicianList.get(i));
		}
		
		
	}//end of main();
	

	/**********
	 * To check if two physicians have any common orginations;
	 * @param currPhy
	 * @param tempPhy
	 * @return
	 */
	private static boolean connectTwoPhy(physician PhyA, physician PhyB) {
		// TODO Auto-generated method stub
		
		ArrayList<String> A = PhyA.getOrgList();
		ArrayList<String> B = PhyB.getOrgList();
		
		for(int i=0; i<A.size(); i++){
			
			for(int j=0; j<B.size(); j++){
				
				if(A.get(i).equals(B.get(j))){
					return true;
				}
			}
		}
		
		return false;
	}//end connectTwoPhy() method;

	/********
	 * Printout the basic information about each physician;
	 * @param firstPhy
	 */
	private static void printPhysician(physician physi) {
		// TODO printout physician information
		int year = physi.getYear();
		int pid = physi.getPid();
		int orgn = physi.getOrg_n();
		int connections = physi.getConn();
		
		ArrayList<String> orgList = physi.getOrgList();
		
		
		int row = orgList.size();
		
		for(int i=0; i<row; i++){
			
			System.out.println(year +"   " + pid +"   " + orgn +"   " + orgList.get(i) + "   " + connections);
		}
		
		
		
	}//end of printPhysician() method;
	
} //end of everything in PhysicianConnections class;



class physician{
	
	private int year;
	private int pid;
	private int org_n;
	private String imsid;
	private ArrayList<String> imsidList = new ArrayList<String>();
	
	private int connections;
	
	public physician(int year, int pid, int org_n, String imsid){
		
		super();
		this.year = year;
		this.pid = pid;
		this.org_n = org_n;	
		this.imsid = imsid;
		
	}	
	
	public int getYear(){
		return year;
		
	}
	
	public int getPid(){
		return pid;
	}
	
	public int getOrg_n(){
		return org_n;
	}
	
	public String getImsid(){
		return imsid;
	}
	
	public ArrayList<String> getOrgList(){
		return imsidList;
	}
	
	public ArrayList<String> addOrg(String m){
		imsidList.add(m);
		
		return imsidList;
	}
	
	public int addConn(int connect){
		connections += connect;
		
		return connections;
	}
	
	public int getConn(){
		return connections;
	}
}//end physicians class;

//end of everything in PhysicianConnections class;
