/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package patientmanagement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*; 

/**
 *
 * @author Venkatesh Prasad
 */
public class PatientManagement {
	int patient_id;
    String first_name;
    String last_name;
    int age;
    String email;
    String purpose;
    int status;
    String blood_group;
    String address;
    String mobile_number;
    String state;
    String city;
    Timestamp admit_time;
    Timestamp discharge_time;

    PatientManagement() {
        this.discharge_time = null;
    }
	Scanner sc=new Scanner(System.in);
	public void setFirstName() {
	     String s;
	     System.out.print("\nEnter First Name:");
             s=sc.nextLine();
	     this.first_name = s;
		

    }

    public void setLastName() {
	String s;
	System.out.print("\nEnter Last Name:");
        s=sc.nextLine();
        this.last_name = s;

    }

    public void setAge() {
	Matcher matcher;
	String age;
	int iage;
	do{
	System.out.print("\nEnter Age:");
        age=sc.nextLine();
	String regex = "[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(age);  
	 }while(! matcher.matches());
	iage=Integer.parseInt(age);
        this.age = iage;

    }

    public void setEmail() {
		String s;
 		Matcher matcher;
	do{
		System.out.print("\nEnter Email:");
        s=sc.nextLine();
	String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(s);  
         }while(! matcher.matches());  

        this.email = s;

    }

    public void setBloodGroup() {
	Matcher matcher;
	String s;
	do{
	System.out.print("\nEnter Blood Group (eg: O+,O-,AB+):");
        s=sc.nextLine();
	String regex = "(A|B|AB|O)[+-]";
        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(s);  
         }while(! matcher.matches());  
        this.blood_group = s;

    }

    public void setPurpose() {
		String s;
		System.out.print("\nEnter Purpose:");
        s=sc.nextLine();
        this.purpose = s;

    }

    public void setStatus(int s) {
		
        
        this.status = s;

    }

    public void setAdmitTime() {
        this.admit_time = new Timestamp(System.currentTimeMillis());

    }
	
	  public void setDischargeTime() {
        this.discharge_time = new Timestamp(System.currentTimeMillis());

    }

    public void setMobileNumber() {
	String s;
	Matcher matcher;
	do{
	System.out.print("\nEnter Mobile Number:");
        s=sc.nextLine();
	String regex = "\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(s);  
	}while(! matcher.matches());
	 this.mobile_number = s;
    }

    public void setAddress() 
	{
		String s;
		System.out.print("\nEnter Address:");
        s=sc.nextLine();
        this.address = s;

    }

    public void setState() {
		String s;
		System.out.print("\nEnter State:");
        s=sc.nextLine();
        this.state = s;

    }

    public void setCity() {
		String s;
		System.out.print("\nEnter City:");
        s=sc.nextLine();
        this.city = s;

    }

    public void setPatientdata() {
		setFirstName();
		setLastName();
		setEmail();
		setBloodGroup();
		setAge();
		setPurpose();
		setMobileNumber();
		setAdmitTime();
		setAddress();
		setState();
		setCity();
		setStatus(1);

    }
	
	
	public void setPatient(ResultSet rs) {
				try
				{
				 this.patient_id=rs.getInt("patient_id");
				 this.first_name=rs.getString("first_name");
				 this.last_name=rs.getString("last_name");
				 this.state=rs.getString("state");
				 this.city=rs.getString("city");
				 this.address=rs.getString("address");
				 this.mobile_number=rs.getString("mobile_number");
				 this.age=rs.getInt("age");
				 this.blood_group=rs.getString("blood_group");
				 this.purpose=rs.getString("purpose");
				 this.status=rs.getInt("status");
				 this.email=rs.getString("email");
				 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				Date parsedDate = dateFormat.parse(rs.getString("admit_time"));
				this.admit_time = new java.sql.Timestamp(parsedDate.getTime());
				}
				catch(Exception ex){
				 Logger.getLogger(PatientManagement.class.getName()).log(Level.SEVERE, null, ex);
				}

    }

    public void displayAllPatientData(int patient_option) {

        try {
			String str;
			str="";
            Statement stmt = null;
            Connection c = Connect_db.getConnection();
            stmt = c.createStatement();
			if(patient_option<3)
			{
			str="SELECT * FROM patient  where status = " +((patient_option==1)?1:0)+" order by patient_id";
			//System.out.println(str);
			}
			else{
			str="SELECT * FROM patient order by patient_id ";
			}
		    ResultSet rs = stmt.executeQuery(str);
			PatientManagement p =new PatientManagement();
			p.displayHeader();
            while (rs.next()) {
               
               p.setPatient(rs);
				p.display();
                
                
            }
			
            
          

        }
    
    catch (SQLException ex) {
            Logger.getLogger(PatientManagement.class.getName()).log(Level.SEVERE, null, ex);
    }

}



public void getDataAndForUpdate(int id)
{
	
	
	try{
			Statement stmt = null;
            Connection c = Connect_db.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient where patient_id="+id);
			 while (rs.next()) {
				 
				 this.patient_id=rs.getInt("patient_id");
				 this.first_name=rs.getString("first_name");
				 this.last_name=rs.getString("last_name");
				 this.state=rs.getString("state");
				 this.city=rs.getString("city");
				 this.address=rs.getString("address");
				 this.mobile_number=rs.getString("mobile_number");
				 this.age=rs.getInt("age");
				 this.blood_group=rs.getString("blood_group");
				 this.purpose=rs.getString("purpose");
				 this.status=rs.getInt("status");
				 this.email=rs.getString("email");
				 //
				 
				 
			 }
	}catch(SQLException ex)
	{
		Logger.getLogger(PatientManagement.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	
}
public void updateCase()
{
	String opt;
	int option;
	do{
	//	Scanner sc=new Scanner(System.in);
	System.out.println("Enter option to be edited:");
	System.out.println("1. first Name");
	System.out.println("2. Last Name");
	System.out.println("3. Age");
	System.out.println("4. Blood Group");
	System.out.println("5. Mobile number");
	System.out.println("6. Email");
	System.out.println("7. Address");
	System.out.println("8. City");
	System.out.println("9. State");
	System.out.println("10.Purpose");
	System.out.println("11. All data");
	opt=sc.nextLine();
	 option=Integer.parseInt(opt);
	switch(option)
	{
		case 1:
		setFirstName();
		break;
		
		case 2:
		setLastName();
		break;
		
		case 3:
		setAge();
		break;
		
		case 4:
		setBloodGroup();
		break;
		
		case 5:
		setMobileNumber();
		break;
		
		case 6:
		setEmail();
		break;
		
		case 7:
		setAddress();
		break;
		
		case 8:
		setCity();
		break;
		
		case 9:
		setState();
		break;
		
		case 10:
		setPurpose();
		break;
		
		case 11:
		setPatientdata();
		break;
	}
	}while(option<12 && option>0);
	
}



public void displayHeader()
{
	System.out.format("\n%5s%15s%15s%14s%14s%20s%20s%25s%14s%14s%14s%20s\n", "Id", "First name", "Last Name","Age","Blood group","Mobile Number","Email","Address","State","City","Purpose","Admit Time");
}




public void display()
{
	
	System.out.format("\n\n%5d%15s%15s%14s%14s%20s%20s%25s%14s%14s%14s%20s", this.patient_id, this.first_name,this.last_name ,this.age,this.blood_group,this.mobile_number,this.email,this.address,this.state,this.city,this.purpose,this.admit_time);
	
	System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ");
}

public void sortData(int patient_option)
{
	String[] strAr1=new String[] {"first_name", "last_name", "age","blood_group","mobile_number","email","address","city","state","purpose","patient_id","admit_time","exit"};
	String order="asc";
	String key="id";
	String opt;
	ResultSet rs;
	do{
		
	System.out.println("Enter key");
	System.out.println("1. first Name");
	System.out.println("2. Last Name");
	System.out.println("3. Age");
	System.out.println("4. Blood Group");
	System.out.println("5. Mobile number");
	System.out.println("6. Email");
	System.out.println("7. Address");
	System.out.println("8. City");
	System.out.println("9. State");
	System.out.println("10.Purpose");
	System.out.println("11. Id");
	System.out.println("12. Admit Time");
	System.out.println("13. Exit");
	
	opt=sc.nextLine();
	//opt=(Integer.parseInt(opt)-1);
	if(Integer.parseInt(opt) >= 13)
	{
		
		break;
	}
	
	try{	
	key=strAr1[Integer.parseInt(opt)-1];
	}
	catch(Exception ex)
	{
		System.out.println("Wrong Selection");
	}
	
	System.out.println("Enter Order:");
	System.out.println("1.Ascending");
	System.out.println("2.Descending");
	order=sc.nextLine();
	
	if(Integer.parseInt(order)==1)
	{
		order="asc";
	}
	else if(Integer.parseInt(order)==2)
	{
		order="desc";
	}
	else{
		System.out.println("Incorrect order");
		break;
	}
	String SQLSearch;
	if(patient_option<3){
	 SQLSearch = "SELECT * FROM patient  where status = " +((patient_option==1)?1:0)+" order by "+key +" " +order;
	}
	else{
	 SQLSearch = "SELECT * FROM patient order by "+key +" " +order;
	}
	try{
	Connection dbcon = Connect_db.getConnection();
            PreparedStatement prepareStatement = dbcon.prepareStatement(SQLSearch);
				
			
			//System.out.print(prepareStatement);
			 rs =prepareStatement.executeQuery();
			// PatientManagement p =new PatientManagement();
			 displayHeader();
				while(rs.next())
				{
				//System.out.println(rs.getString("patient_id"));
				setPatient(rs);
				
				display();
				
				}
			 
			}
			catch(SQLException ex)
			{
				System.out.print(ex.getMessage());
			}
			
			
			
	
	
	}while(Integer.parseInt(opt)>0 &&Integer.parseInt(opt)<11);
	
}



public void updateData(int id) {
		int check=0;
		updateCase();
        int flag=0;
        String SQLupdate = "UPDATE patient set first_name =? , last_name =?, email=?, age = ?, blood_group = ? , purpose = ?, status = ?, address = ?, mobile_number =? , state = ?, city = ?  WHERE patient_id =" +id ;

        

        try{Connection dbcon = Connect_db.getConnection();
            PreparedStatement prepareStatement = dbcon.prepareStatement(SQLupdate, Statement.RETURN_GENERATED_KEYS); 
            prepareStatement.setString(1, this.first_name);
            prepareStatement.setString(2, this.last_name);
            prepareStatement.setString(3, this.email);
            prepareStatement.setInt(4, this.age);
            prepareStatement.setString(5, this.blood_group);
            prepareStatement.setString(6, this.purpose);
            prepareStatement.setInt(7, this.status);
            prepareStatement.setString(8, this.address);
            prepareStatement.setString(9, this.mobile_number);
            prepareStatement.setString(10, this.state);
            prepareStatement.setString(11, this.city);
            

           check= prepareStatement.executeUpdate();
		   if(check>0)
		   {
			System.out.println("Update Successful");
		   }
		   else{
			System.out.println("Update Failed"); 
		   }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        
       

      
    }
    
    public void insertObject() {
        String SQLinsert = "INSERT INTO patient (first_name, last_name, email, age, blood_group, purpose, status, address, mobile_number, state, city, admit_time)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	int check=0;
        long id = 0;

        try {Connection dbcon = Connect_db.getConnection();
            PreparedStatement prepareStatement = dbcon.prepareStatement(SQLinsert);
            prepareStatement.setString(1, this.first_name);
            prepareStatement.setString(2, this.last_name);
            prepareStatement.setString(3, this.email);
            prepareStatement.setInt(4, this.age);
            prepareStatement.setString(5, this.blood_group);
            prepareStatement.setString(6, this.purpose);
            prepareStatement.setInt(7, this.status);
            prepareStatement.setString(8, this.address);
            prepareStatement.setString(9, this.mobile_number);
            prepareStatement.setString(10, this.state);
            prepareStatement.setString(11, this.city);
            prepareStatement.setObject(12, this.admit_time);

             check=prepareStatement.executeUpdate();

          
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
		if(check==0)
		{
			System.out.println("Patient Insertion Failed");
		}
		else{
		System.out.println("Patient Insertion Successful");
        }
    }
	
	
	public void searchData(int patient_option)
	{
			
	String[] strAr1=new String[] {"first_name", "last_name", "age","blood_group","mobile_number","email","address","city","state","purpose","patient_id","Exit"};
	String type,search_key;
	String key;
	String opt;
	ResultSet rs;
	do{
		
	System.out.println("Enter key");
	System.out.println("1. first Name");
	System.out.println("2. Last Name");
	System.out.println("3. Age");
	System.out.println("4. Blood Group");
	System.out.println("5. Mobile number");
	System.out.println("6. Email");
	System.out.println("7. Address");
	System.out.println("8. City");
	System.out.println("9. State");
	System.out.println("10.Purpose");
	System.out.println("11. Id");
	System.out.println("12. Exit");
	opt=sc.nextLine();
	//opt=(Integer.parseInt(opt)-1);
	
	if(Integer.parseInt(opt) >= 12)
	{
		
		break;
	}
	key=strAr1[Integer.parseInt(opt)-1];
	try{	
	key=strAr1[Integer.parseInt(opt)-1];
	
	}
	catch(Exception ex)
	{
		
		System.out.println("Wrong Selection");
		break;
		
	}
	
	System.out.println("Enter Type of search:");
	System.out.println("1. Start with");
	System.out.println("2. End With");
	System.out.println("3. Contains");
	System.out.println("4. Equals");
	System.out.println("5. Exit");
	type=sc.nextLine();
	
	if(Integer.parseInt(type)>=5)
	{
		break;
	}
	
	System.out.print("Enter key to search:");
	search_key=sc.nextLine();
	
	switch(Integer.parseInt(type))
	{
		
		case 1:
		search_key=search_key+"%";
		break;
		
		case 2:
		search_key="%"+search_key;
		break;
		
		case 3:
		search_key="%"+search_key+"%";
		break;
		
		case 4:
		search_key=search_key;
		break;
	}
	if(Integer.parseInt(type)>4)
	{
		break;
	}
	String SQLSearch;
	if(patient_option<3)
	{
		SQLSearch = "select * from patient where  status = " +((patient_option==1)?1:0)+ " and CAST(" +key+" as TEXT)  LIKE ?" ;
	}
	else
	{
	 SQLSearch = "select * from patient where CAST(" +key+ " as TEXT) LIKE ?" ;
	}
	//System.out.print(SQLSearch);
	try{
	Connection dbcon = Connect_db.getConnection();
            PreparedStatement prepareStatement = dbcon.prepareStatement(SQLSearch);
				
		prepareStatement.setString(1,search_key);	
			System.out.print(prepareStatement);
			 rs =prepareStatement.executeQuery();
				displayHeader();
				while(rs.next())
				{
					setPatient(rs);
					display();
				
				}
			 
			}
			catch(SQLException ex)
			{
				System.out.print(ex.getMessage());
			}
			
			
		
		
	}while(Integer.parseInt(opt)<12);
	}
	
	public void dischargePatient(int id)
	{
		int check=0;
		PatientManagement patient_object=new PatientManagement();
		patient_object.setDischargeTime();
		
		String SQLupdate = "UPDATE patient set discharge_time=?,status=0 where patient_id="+id;
		 try{Connection dbcon = Connect_db.getConnection();
            PreparedStatement prepareStatement = dbcon.prepareStatement(SQLupdate); 
			prepareStatement.setObject(1,patient_object.discharge_time);

           check= prepareStatement.executeUpdate();
		   if(check>0){
			System.out.println("Patient discharged successfully");
		   }
		   else{
			   System.out.println("Patient discharged failed");
		   }
        } catch (SQLException ex) {
	    System.out.println("Patient discharge failed");
           // System.out.print(ex.getMessage());
        }
	}
		
		
		
	public void deletePatient(int id)
	{
		
		
		String SQLupdate = " DELETE FROM patient WHERE patient_id="+id;
		 try{Connection dbcon = Connect_db.getConnection();
            PreparedStatement prepareStatement = dbcon.prepareStatement(SQLupdate); 
          

            prepareStatement.executeUpdate();
			System.out.println("Patient details deleted Successfuly");
          
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
		
	}
	
	public  static Login checkLogin(Login log)
	{
		int flag=0;
		boolean login;
		login=log.logger();
		while(!login)
		{	
			
			if(!login)
			{
				System.out.println("Incorrect Credentials");
			}
			flag++;
			login=log.logger();
			
			if(flag==2)
			{
				System.out.println("Try limit Exceeded");
				System.out.println("Exiting..");
				System.exit(0);
				
			}
		
			
			
		}
		return log;
	}
	
	
	static void main_function()
		{
		Login log=new Login();
		log=checkLogin(log);
		//System.out.println(log.password);
		   
        	PatientManagement patient_object=new PatientManagement();
		int patient_option;
		int option;
		Scanner scan=new Scanner(System.in);
		
        	System.out.println("\t\t\t\t Patient Management System");
	
		String user=log.user_role;
		do
		{
			System.out.println("User_name :" + log.user_name +"( "+user+ " )");
			System.out.println("Enter operation  ");
			System.out.println("1. Add New Patient  ");
			System.out.println("2. Display All Patient");
			System.out.println("3. Update patient information ");
			System.out.println("4. Sort ");
			System.out.println("5. Search: ");
			System.out.println("6. Discharge Patient");
			System.out.println("7. Delete Patient data");
			System.out.println("8. Create New user");
			System.out.println("9. Update User");
			System.out.println("10.Display User");
			System.out.println("11.Delete User");
			System.out.println("12.Log out");
			System.out.println("13.Exit");
			
			option=scan.nextInt();
			
			
			
			switch(option)
			{
				case 1:
				patient_object.setPatientdata();
				patient_object.insertObject();
				break;
				
				case 2:
				System.out.println("1. Active Patients ");
				System.out.println("2. Discharged Patients ");
				System.out.println("3. All Patients ");
				patient_option=scan.nextInt();
				patient_object.displayAllPatientData(patient_option);
				break;
				
				case 3:
				int id;
				System.out.println("Enter id to be Updated");
				id=scan.nextInt();
				patient_object.getDataAndForUpdate(id);
				patient_object.updateData(id);
				break;
				
				case 4:
				System.out.println("1. Active Patients ");
				System.out.println("2. Discharged Patients ");
				System.out.println("3. All Patients ");
				patient_option=scan.nextInt();
				patient_object.sortData(patient_option);
				break;
				
				case 5:
				//int id;
				System.out.println("1. Active Patients ");
				System.out.println("2. Discharged Patients ");
				System.out.println("3. All Patients ");
				patient_option=scan.nextInt();
				patient_object.searchData(patient_option);
				break;
				
				case 6:
				//int id;
				System.out.println("Enter id to be Discharged");
				id=scan.nextInt();
				patient_object.dischargePatient(id);
				break;
				
				case 7:
				//int id;
				System.out.println("Enter id to be deleted");
				id=scan.nextInt();
				patient_object.deletePatient(id);
				break;
				
				case 8:
				//Login log=new Login();
				
				//System.out.println((user).getClass().getSimpleName());
				if(user.compareTo("Admin")==0)
				{
				log.createUser();
				}
				else{
					System.out.println("Access rights denied");
				}
				break;
				
				case 9:
				if(user.compareTo("Admin")==0)
				{
				System.out.println("Enter id to be Updated");
				id=scan.nextInt();
				log.updateData(id);
				}
				else{
					System.out.println("Access rights denied");
				}
				break;
				
				case 10:
				Login display=new Login();
				display.displayHeader();
				display.displayData();
				break;
				
				case 11:
				if(user.compareTo("Admin")==0)
				{
				Login del =new Login();
				System.out.println("Enter id to be Delete");
				id=scan.nextInt();
				del.deleteData(id);
				}
				else{
					System.out.println("Access rights denied");
				}
				break;
				
				case 12:
				log.user_name="";
				log.email="";
				log.password="";
				log.user_role="";
				System.out.println("Logged out");
				main_function();
				break;
				
				case 13:
				System.exit(0);
				break;
				
				
				
			}
			
			
			
		}while(option<13 && option>0);
		}
	
	
	
	
				//Main Class
    public static void main(String[] args) {
		
			main_function();
		
		
	
       
    }






}
