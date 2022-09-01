//Shruti Patil
//2940
//C2 Batch

import java.util.*;
import java.util.Scanner;
import java.lang.String;

class node {
	//constructor
	node left , right;
	String name;
	String num;
	String mail;
	
	public node(String name , String num, String mail) {               //empty constructor
		left = right = null;
		this.name = name;
		this.num = num;
		this.mail = mail;
	}
	void display() {
		//defining the function to display the details in the phone directory 
		//The details include the name of the person and his/her phone number
		System.out.println("Person's Name : " + this.name + "\tPhone Number : " + this.num + "\tEmail Address : " + this.mail );
	}
}
class directory {
	Scanner sc = new Scanner(System.in);
	node root , ptr;                    //constructor
	String name;
	String num;
	String mail;
	
	public directory() {
		root = null;
	}
	void build() {
		//defining the function to build a phone directory 
		//Using the binary search tree
		int c;
		do {
			//accepting the details from the user to store in the phone directory
			System.out.println("Personal details...");
			System.out.println("Enter the name of the person : ");
			name = sc.nextLine();
			System.out.println("Enter the phone number of the person : ");
			num = sc.nextLine();
			System.out.println("Enter the email address of the person : ");
			mail = sc.nextLine();
			
		    //storing the details in the temporary node
			node temp = new node(name , num , mail);
			
			if (root==null) {
				//if the root is empty
				root = temp;
			}
			else {
				ptr = root;
				while (ptr!=null) {
					//if the root is not empty
					if (ptr.name.compareTo(temp.name)>0) {
						//if the ptr  node is to the left of the temp node
						if (ptr.left == null) {
							//if the left node of the ptr is empty
							ptr.left = temp;
							break;
						}
						else {
							//if the left node of the ptr is not empty
							ptr = ptr.left;
						}
					}
					else {
						//if the ptr node is to the right of the temp node
						if (ptr.right == null) {
							//if the right node of the ptr is empty
							ptr.right = temp;
							break;
						}
						else {
							//if the right node of the ptr is not empty
							ptr = ptr.right;
						}
					}
				}
			}
			System.out.println("\nDo you want to add more entries ? (0/1) : ");
			c = sc.nextInt();
			sc.nextLine();
		}while (c!=0);	
	}
	//defining a function to display the Phone directory
	public void inorder() {
		inorder(this.root);
	}
	private void inorder(node node) {
		if (node!=null) {
			inorder(node.left);
			node.display();
			inorder(node.right);
		}
	}
	void search() {
		//defining the function to search the entries from the phone directory
		System.out.println("Enter the name of the person whose phone number is to be searched : ");
		String key = sc.nextLine();
		ptr = root;
		while (ptr!=null) {
			if (key.equals(ptr.name)) {
				//if the name to be searched and the name in the ptr node are same 
				System.out.println("The information of " + key + " is present....");
				System.out.println("Name : " + key + "\tPhone Number : " + ptr.num + "\tEmail Address : " + ptr.mail);
				break;
			}
			else if (key.compareTo(ptr.name)<0) {
				//if the name to be searched is to the left of the ptr node
				ptr = ptr.left;
			}
			else {
				//if the name to be searched is to the right of the ptr node
				ptr = ptr.right;
			}
		}
	}
	void update() {
		//defining the function to update the number of the person in the directory
		System.out.println("Enter the name of the person whose phone number is to be updated : ");
		String key = sc.nextLine();
		ptr = root;
		while (ptr!=null) {
			if (key.equals(ptr.name)) {
				//if the name to be updated and the name in the ptr node are same
				System.out.println("The information of " + key + " is present....");
				System.out.println("Enter the new phone number of the " + key + " : ");
				ptr.num = sc.nextLine();
				System.out.println("Enter the new email address : ");
				ptr.mail = sc.nextLine();
				System.out.println("Name : " + key + "\tPhone number : " + ptr.num + "\tEmail Address : " + ptr.mail);
				System.out.println("The phone number of " + key + " is updated....");
				return;
			}
			else if (key.compareTo(ptr.name)<0) {
				//if the name to be updated is to the left of the ptr node
				ptr = ptr.left;
			}
			else {
				//if the name to be updated is to the right of the ptr node
				ptr = ptr.right;
			}
		}
		System.out.println("The details of " + key + " is not present in the directory...");
	}

    public void remove(){
    	//defining the function to delete any entry from the phone directory
        System.out.println("\nEnter the name of the person whose details are to be deleted from the directory : ");
        String key = sc.nextLine();
        this.root = remove(this.root, key);
        System.out.println("The word " + key + " is deleted from the directory....");
    }

    private node remove(node root, String key)
    {
        //if tree is empty
        if (root == null)
            return root;
        
        if (key.compareTo(root.name) < 0)
            root.left = remove(root.left, key);
        else if (key.compareTo(root.name) > 0)
            root.right = remove(root.right, key);
 
        
        else {
            //for node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
 
            //for node having two children left as well as right
            node temp = getSuccessor(root.right);
            root.name = temp.name;
            root.num = temp.num;
            root.mail = temp.mail;
 
            // Delete the inorder successor
            root.right = remove(root.right, root.name);
        }
         return root;
    }
 
    private node getSuccessor(node node) {
    	//defining the function to get the successor of the node 
        String name = node.name;
        String num = node.num;

        while (node.left != null)
        {
            name = node.left.name;
            num = node.left.num;
            node = node.left;
        }
        return new node(name, num , mail);
    }

}
public class PhDirectory {

	public static void main(String[] args) {
		directory d = new directory();
		Scanner sc = new Scanner(System.in);
		int ch ;
		do {
			System.out.println("\n*****Phone Directory*****");
			System.out.println("****Operations*****");
			System.out.println("\n1.Creating the Phone directory.\n2.Displaying the Phone directory."
					+ "\n3.Searching the entry from the Phone directory\n4.Updating the details of any entry from the Phone directory"
					+ ".\n5.Deleting a entry from the phone directory.\n6.Quit");
			System.out.print("\nEnter your choice : ");
			ch = sc.nextInt();
			switch(ch) { 
			   case 1 :
				   d.build();
				   break;
			   case 2 :
				   System.out.println("____Phone Directory____");
				   d.inorder();
				   break;
			   case 3 :
				   d.search();
				   break;
			   case 4 :
				   d.update();
				   break;
			   case 5 :
				   d.remove();
				   break;
			   case 6 :
				   System.out.print("Thank you for using the Phone Directory services....Do use again...");
				   break;
			   default :
				   System.out.println("Invalid Choice.....");
			}
		}while (ch!=0);
		sc.close();
	}

}

/********************
Output :

*****Phone Directory*****
****Operations*****

1.Creating the Phone directory.
2.Displaying the Phone directory.
3.Searching the entry from the Phone directory
4.Updating the details of any entry from the Phone directory.
5.Deleting a entry from the phone directory.
6.Quit

Enter your choice : 1
Personal details...
Enter the name of the person : 
juhi rajwade
Enter the phone number of the person : 
9867452011
Enter the email address of the person : 
jkr@gmail.com

Do you want to add more entries ? (0/1) : 
1
Personal details...
Enter the name of the person : 
shruti patil
Enter the phone number of the person : 
9623457811
Enter the email address of the person : 
srp2@gmail.com

Do you want to add more entries ? (0/1) : 
1
Personal details...
Enter the name of the person : 
meera deshmukh
Enter the phone number of the person : 
9999996645
Enter the email address of the person : 
md@gmail.com

Do you want to add more entries ? (0/1) : 
1
Personal details...
Enter the name of the person : 
diana penty
Enter the phone number of the person : 
8886671000
Enter the email address of the person : 
dp3@gmail.com

Do you want to add more entries ? (0/1) : 
1
Personal details...
Enter the name of the person : 
bella mehta
Enter the phone number of the person : 
8888777542
Enter the email address of the person : 
bellam@gmail.com

Do you want to add more entries ? (0/1) : 
1
Personal details...
Enter the name of the person : 
ishika pandey
Enter the phone number of the person : 
9257873221
Enter the email address of the person : 
ishk56@gmail.com

Do you want to add more entries ? (0/1) : 
0

*****Phone Directory*****
****Operations*****

1.Creating the Phone directory.
2.Displaying the Phone directory.
3.Searching the entry from the Phone directory
4.Updating the details of any entry from the Phone directory.
5.Deleting a entry from the phone directory.
6.Quit

Enter your choice : 2
____Phone Directory____
Person's Name : bella mehta	Phone Number : 8888777542	Email Address : bellam@gmail.com
Person's Name : diana penty	Phone Number : 8886671000	Email Address : dp3@gmail.com
Person's Name : ishika pandey	Phone Number : 9257873221	Email Address : ishk56@gmail.com
Person's Name : juhi rajwade	Phone Number : 9867452011	Email Address : jkr@gmail.com
Person's Name : meera deshmukh	Phone Number : 9999996645	Email Address : md@gmail.com
Person's Name : shruti patil	Phone Number : 9623457811	Email Address : srp2@gmail.com

*****Phone Directory*****
****Operations*****

1.Creating the Phone directory.
2.Displaying the Phone directory.
3.Searching the entry from the Phone directory
4.Updating the details of any entry from the Phone directory.
5.Deleting a entry from the phone directory.
6.Quit

Enter your choice : 3
Enter the name of the person whose phone number is to be searched : 
bella mehta 
Person's Name : bella mehta	Phone Number : 8888777542	Email Address : bellam@gmail.com
*****Phone Directory*****
****Operations*****

1.Creating the Phone directory.
2.Displaying the Phone directory.
3.Searching the entry from the Phone directory
4.Updating the details of any entry from the Phone directory.
5.Deleting a entry from the phone directory.
6.Quit

Enter your choice : 4
Enter the name of the person whose phone number is to be updated : 
shruti patil
The information of shruti patil is present....
Enter the new phone number of the shruti patil : 
8989898556
Enter the new email address : 
srp6@gmail.com
Name : shruti patil	Phone number : 8989898556	Email Address : srp6@gmail.com
The phone number of shruti patil is updated....

*****Phone Directory*****
****Operations*****

1.Creating the Phone directory.
2.Displaying the Phone directory.
3.Searching the entry from the Phone directory
4.Updating the details of any entry from the Phone directory.
5.Deleting a entry from the phone directory.
6.Quit

Enter your choice : 5

Enter the name of the person whose details are to be deleted from the directory : 
meera deshmukh
The word meera deshmukh is deleted from the directory....

*****Phone Directory*****
****Operations*****

1.Creating the Phone directory.
2.Displaying the Phone directory.
3.Searching the entry from the Phone directory
4.Updating the details of any entry from the Phone directory.
5.Deleting a entry from the phone directory.
6.Quit

Enter your choice : 5

Enter the name of the person whose details are to be deleted from the directory : 
shruti patil
The word shruti patil is deleted from the directory....

*****Phone Directory*****
****Operations*****

1.Creating the Phone directory.
2.Displaying the Phone directory.
3.Searching the entry from the Phone directory
4.Updating the details of any entry from the Phone directory.
5.Deleting a entry from the phone directory.
6.Quit

Enter your choice : 5

Enter the name of the person whose details are to be deleted from the directory : 
diana penty
The word diana penty is deleted from the directory....

*****Phone Directory*****
****Operations*****

1.Creating the Phone directory.
2.Displaying the Phone directory.
3.Searching the entry from the Phone directory
4.Updating the details of any entry from the Phone directory.
5.Deleting a entry from the phone directory.
6.Quit

Enter your choice : 6
Thank you for using the Phone Directory services....Do use again...

**************************/