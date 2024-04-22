package miniPROJECT;

import java.util.*;

import java.lang.*;

public class InvestmentPortfolio {

	static Scanner scint = new Scanner(System.in);
	static Scanner scstr = new Scanner(System.in);

	public static void main(String[] args) {

		Signupin sp = new Signupin();
		int again;
		// TODO Auto-generated method stub
		try {
			System.out.printf("%100s %n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.printf("%100s %n", "WELCOME to our mini project. - INVESTMENT PORTFOLIO");
			System.out.printf("%100s %n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.printf("%100s %n", "Login or Register to view your investment portfolio");
			System.out.printf("%80s %n", " Creators :");
			System.out.printf("%110s %n", "UCE2022426-Abha Barge  UCE2022430-Mansi Bhale  UCE2022431-Bhumika Bhalkar");
			System.out.print(
					"-------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("-------------------------------------------");

			do {
				System.out.println("Available options are:");
				System.out.println("1. Login (Existent User)\n2. Register (New User)");
				System.out.println("Select a valid option (1/2)");
				int task = scint.nextInt();
				scstr.nextLine();
				if (task == 1) {
					System.out.print("Enter your username  :  ");
					String u = scstr.nextLine();
					System.out.print("Enter your password  :  ");
					String p = scstr.nextLine();
					int valid = sp.hf.accExists(u, p);
					switch (valid) {
					case 0:
						User user = sp.hf.get(u, p);
						user.validId();
						break;

					case 1:
						System.out.println("Wrong Password");
						break;

					case 2:
						System.out.println("Account does not exist. Please register.");
						break;
					}
				}

				else {
					try {
						System.out.printf("%20s %n", "Enter your username :");
						String u = scstr.next();

						System.out.printf("%20s %n", "Enter your password :");
						String p = scstr.next();

						sp.register(u, p);

					}

					catch (Exception e) {
						System.out.println("An error occurred!");
					}
				}

				System.out.println("Want to login/register as a different user? 1-Yes, 0-No");
				again = scint.nextInt();

			} while (again == 1);
			System.out.printf("%110s %n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.printf("%110s %n", "THANKYOU for going through our mini project. - INVESTMENT PORTFOLIO");
			System.out.printf("%110s %n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.printf("%80s %n", " Creators :");
			System.out.printf("%110s %n", "UCE2022426-Abha Barge  UCE2022430-Mansi Bhale  UCE2022431-Bhumika Bhalkar");
			System.out.print(
					"-------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("-------------------------------------------");
		} catch (Exception e) {
			System.out.println(" You've encountered an error! " + e.getMessage());
		}

	}

}

class Signupin {
	HashFun hf = new HashFun();

	void register(String u, String p) {
		boolean exist = hf.takenusernm(u);
		if (!exist) {
			User user = new User(u, p);
			hf.add(u, p);
			user.validId();
		}

		else {
			System.out.println("Username is taken. Please change your username.");
		}
	}

	Signupin() {

		Stock[] stockArr = Stockarr.stockArray;
		hf.add("jimhalpert", "123office");
		User u = hf.get("jimhalpert", "123office");
		
		u.inbuiltbuystk(stockArr[0], 4);
		u.inbuiltbuystk(stockArr[1], 3);
		u.inbuiltbuystk(stockArr[2], 2);

	}

	User get(String u, String p) {
		return hf.get(u, p);
	}

}

class User {
	Scanner scint = new Scanner(System.in);
	Scanner scstr = new Scanner(System.in);
	String username, password;
	LL myStocks = new LL();
	Stack1 pastTransactions = new Stack1();
	Queue1 wishList = new Queue1();
	GraphRecommend graph;
	double balance;
	User next;

	User(String u, String p) {
		username = u;
		password = p;
		balance = 50000;
	}

	void validId() {
		graph = new GraphRecommend();
		System.out.print(
				"-------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("-------------------------------------------");
		System.out.println("WELCOME! " + username);
		int rpt = 1;

		do {
			System.out.printf("%100s %n", "Username = " + username + "    Balance = " + balance);
			System.out.println("Your available options are: ");
			System.out.println("1. View my stocks\n2. View past transactions\n3. View my wishlist\n4. View all stocks.\n5. Search which data structures are implemented in this project.");
			System.out.println("6. Stocks as per increasing order of current price \n7. Recommended stocks to buy \n8. Logout");

			System.out.println("Enter a valid option (1/2/3/4/5/6/7/8)");
			int task = scint.nextInt();
			switch (task) {
			case 1:
				System.out.println("MY PORTFOLIO");
				this.myStocks.displayPortfolio();

				boolean choice; 
				
				do
				{
					System.out.printf("%110s %n","Enter NAME of stock whose information you wish to see: ");
					String name1 = scstr.nextLine();
					Stock temp = this.myStocks.head;
					while (temp != null)
					{	
						if (temp.name.equalsIgnoreCase(name1)) 
						{
							System.out.println("Stock exists.");
							this.StockInfo(temp);
							break;
						} else 
						{
							temp = temp.next;
						}
					}
					if(temp==null)
						System.out.println("Stock doesn't exist in your portfolio.");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					
					System.out.printf("%110s %n","View another stock? true-yes false-no");
					choice = scint.nextBoolean();
				}
				while(choice);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				break;

			case 2:
				Stack<String> tempstk = new Stack<>();
				while (!this.pastTransactions.isEmpty())
				{
					String val= this.pastTransactions.pop();
					System.out.println(val);
					tempstk.push(val);
				}
				while (!tempstk.isEmpty())
				{
					String transaction = tempstk.pop();
					this.pastTransactions.push(transaction);
				}
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				break;

			case 3:
				System.out.println("MY WISHLIST");
				if(this.wishList.isEmpty1())
					System.out.println("Your wishlist is empty! Add something you wish to buy! :)");
				else
				{
					this.wishList.displayWishList();
					boolean choice1; 
					
					do
					{
						System.out.printf("%110s %n","Enter NAME of stock whose information you wish to see: ");
						String name1 = scstr.nextLine();
						Stock temp = this.wishList.head;
						while (temp != null)
						{
							if (temp.name.toLowerCase().equals(name1.toLowerCase())) {
								this.StockInfo(temp);
								System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
								break;
							} else {
								temp = temp.next;
							}
						}
						if(temp==null)
							System.out.println("Invalid name of stock.");
						System.out.printf("%110s %n","View another stock? true-yes false-no");
						choice1 = scint.nextBoolean();
					}
					while(choice1);
					
				
				}	
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				break;

			case 4:
				for(int i=0; i<Stockarr.stockArray.length; i++)
				{
					System.out.print(i+1);
					Stock s = Stockarr.stockArray[i];
					s.displayinfo(s);
				}
				
				boolean c;
				do
				{
					System.out.printf("%110s %n","Enter the NUMBER of the company whose information you wish to see: ");
					int num = scint.nextInt();
					if(num>=0 && num<=Stockarr.stockArray.length)
						this.StockInfo(Stockarr.stockArray[num-1]);
					
					else
						System.out.println("Invalid number.");
			
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.printf("%110s %n","View another stock? true-yes false-no");
					c = scint.nextBoolean();
				}
				while(c);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				break;  

			case 5:
				DictionaryBST dictionary = new DictionaryBST();
				dictionary.addall();
		        boolean running = true;
		        try 
		        {
		        	Scanner scstr = new Scanner(System.in);
		        	Scanner scint = new Scanner(System.in);
		            while (running) 
		            {
		            	String keyword;
		                System.out.println("Menu:");
		                System.out.println("1. Search for a keyword");
		                System.out.println("2. Exit");
		                System.out.printf("%110s %n","Enter your choice: ");
		                int choi = scint.nextInt();
		                // Consume the newline character
		                
		                switch (choi) 
		                {
		                   
		                    case 1:
		                    	System.out.printf("%110s %n","Enter keyword to search: ");
		                        keyword = scstr.nextLine();
		                        DictionaryNode result = dictionary.searchRec(dictionary.root, keyword);
		                        if(result!=null)
		                        	System.out.println("Implementation: " + result.meaning);
		                        break;
		                   
		                    case 2:
		                        running = false;
		                        break;
		                        
		                    default:
		                        System.out.println("Invalid choice. Please enter a valid option.");
		                        break;
		                }
		            }
		            
		            System.out.println("Exiting the dictionary.");
		            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		            break;


		        }
		        catch(Exception e)
		        {
		        	System.out.println("An error occurred! ");
		        	break;
		        }

			case 6:
				int flag1=0;
				Stock[] stockval = Stockarr.stockArray.clone();
			    Stock[] stockv = this.pivotsort(stockval, 0, stockval.length-1);
			    for(int i=0; i<stockval.length; i++)
				{
					System.out.print(i+1);
					Stock s = stockv[i];
					s.displayinfo(s);
				}
			    boolean cho;
			    do
				{
					System.out.printf("%110s %n","Enter the NAME of the stock whose information you wish to see: ");
					String n = scstr.nextLine();
					for(Stock s : Stockarr.stockArray)
					{
						if(s.name.equalsIgnoreCase(n))
						{
							this.StockInfo(s);
							flag1 = 1;
							break;
						}
					}
					if (flag1 != 1) 
						System.out.println("Invalid company name!");
					
			
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.printf("%110s %n","View another stock? true-yes false-no");
					cho = scint.nextBoolean();
				}
				while(cho);
				
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

				break;  
				
			case 7:
				this.displayRec();
				break;

			case 8:
				System.out.printf("%110s %n","Are you sure you mant to Logout? 1-Yes LOGOUT   /   2-No, CANCEL");
				int val = scint.nextInt();
				if (val == 1)
				{
					rpt = 0;
					System.out.print("-------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("-------------------------------------------");
				}
			}
		} while (rpt == 1);

	}

	void displayRec() {
		LinkedList<Stock> ll = graph.getRec();
		int count = 1;
		System.out.println("RECOMMENDED STOCKS FOR BUYING :");
		for (Stock s : ll) {

			System.out.println(count + ". " + s.name);
			count++;
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		count = 1;
	}
	
	Stock[] pivotsort(Stock[] st, int start, int end)
	{
		if(start>end)
			return st;
		
		else
		{
			int i=0, j = st.length-1;
			int pivot = (start+end)/2;
			
			while(st[i].currPrice<st[pivot].currPrice)
				i++;
			
			while(st[j].currPrice>st[pivot].currPrice)
				j--;
			
			if(i<j)
				this.swap(st, i, j);
			
			else if(i<pivot)
				this.swap(st, pivot, i);
			
			else if(j>pivot)
				this.swap(st, pivot, j);
		
			this.pivotsort(st, i+1, end);
			this.pivotsort(st, start, j-1);
			
			
		}
		
		return st;
	}
	
	void swap(Stock[] st, int i, int j)
	{
		Stock temp = st[i];
		st[i] = st[j];
		st[j] = temp;
	}
	

	void buystk(Stock s, int num) {
		
		double amt = s.currPrice * num;
		if(amt<=this.balance)
		{
			System.out.println("Bought Stock " + s.name + " at current price Rs." + s.currPrice+" per stock");
			balance = balance - amt;
			s.quant += num;
			System.out.println("Total amount deducted = "+s.currPrice*num);
			if (!myStocks.contains(s))
				myStocks.create(s);
			
			pastTransactions.push("Bought " + num + " stocks of " + s.name);
		}
		else
			System.out.println("Insufficient Balance. Transaction is aborted.");
	}

	void sellstk(Stock s, int num) {
		String pl = ((s.currPrice - s.pastPrice) > 0) ? "PROFIT" : "LOSS";
		if (myStocks.contains(s)&& s.quant >= num)
		{
			balance += s.currPrice * num;
			if (num == s.quant)
				this.myStocks.delete(s);
			s.quant -= num;
			
			System.out.println("Sold Stock " + s.name + " at " + pl + "of Rs." + s.currPrice + " per stock");
			System.out.println("Total amount gained = "+s.currPrice*num);
			pastTransactions.push("Sold " + num + " stocks of " + s.name+" at a "+pl+" of Rs."+(s.currPrice-s.pastPrice));
		} 
		else
			System.out.println("You don't have those many stocks, sorry");

	}
	
	void inbuiltbuystk(Stock s, int num) 
	{
		balance = balance - (s.currPrice * num);
		s.quant += num;
		if (!myStocks.contains(s))
			myStocks.create(s);

		pastTransactions.push("Bought " + num + " stocks of " + s.name);
	}


	void StockInfo(Stock s) {
		int ch;
		System.out.println("Company's name :" + s.name);
		System.out.println("Current Price: " + s.currPrice);
		System.out.println("Past price: " + s.pastPrice);
		System.out.println("Do you want to sell/buy this company's stock?");
		
		System.out.printf("%110s %n","1.BUY stocks of this company ");
		System.out.printf("%110s %n","2.SELL stocks of this company");
		System.out.printf("%110s %n","3.Add in wishlist            ");
		System.out.printf("%110s %n","4.Remove from wishlist       ");
		System.out.printf("%110s %n","5.Close");

		System.out.println("Enter valid choice (1/2/3/4/5)");
		ch = scint.nextInt();
		if (ch == 2) {
			System.out.printf("%110s %n","Enter the no of stocks you want to sell: ");
			int ns = scint.nextInt();
			this.sellstk(s, ns);
			return;
		} else if (ch == 1) {
			System.out.printf("%110s %n","Enter the no of stocks you want to buy: ");
			int ns = scint.nextInt();
			this.buystk(s, ns);
			return;
		} else if (ch == 3) {
			this.wishList.add(s);
			System.out.println("Added in wishlist!");
		} else if (ch == 4) {
			Stock removed = this.wishList.remove();
			System.out.println("Wishlist updated!");
		}
	}

}

class HashFun {
	// Using Separate Chaining method

	User[] userArr;

	public HashFun() {
		userArr = new User[5];
	}

	public boolean takenusernm(String usernm) {
		boolean flag = false;
		int ind = this.hash(usernm);
		User u = userArr[ind];
		while (u != null) {
			if (u.username.equals(usernm)) {
				flag = true;
			}

			u = u.next;

		}

		return flag;
	}

	void add(String Username, String Password) {
		int ind = this.hash(Username);
		User newUser = new User(Username, Password);
		if (userArr[ind] == null)
			userArr[ind] = newUser;
		else {
			User ptr = userArr[ind];
			while (ptr.next != null)
				ptr = ptr.next;
			ptr.next = newUser;
		}

	}

	User get(String u, String p) {
		int ind = this.hash(u);
		User ptr = userArr[ind];
		while (ptr != null) {
			if (ptr.password.equals(p))
				return ptr;

			ptr = ptr.next;
		}

		return null;
	}

	int accExists(String Username, String Password) {
		int ind = this.hash(Username);
		User u = userArr[ind];
		while (u != null) {
			if (u.username.equals(Username)) {
				if (u.password.equals(Password))
					return 0; // 0 if account exists

				return 1; // 1 if password is wrong
			}

			u = u.next;

		}

		return 2; // 2 if account doesn't exist

	}

	int hash(String username) {
		int sum = 0;
		for (int i = 0; i < username.length(); i++) {
			sum = sum + (int) username.charAt(i);
		}

		return sum%userArr.length;
	}

}

class Stock {
	String name;
	LinkedList<Stock> neighbours;
	Stock next;
	double pastPrice, currPrice;
	int ind, quant = 0;

	Stock(String n, double cp, double pp, int ind) {
		name = n;
		currPrice = cp;
		pastPrice = pp;
		double profit = pastPrice - currPrice;
		neighbours = new LinkedList<>();
		this.ind = ind;
	}

	void displayinfo(Stock s) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Stock name :             " + s.name);
		System.out.println("Stock's current price :  Rs." + s.currPrice);
		System.out.println("Stock's past price :     Rs." + s.pastPrice);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}

class Stockarr {
	public static Stock[] stockArray = { 
			new Stock("Microns", 157.65, 150.0, 0),new Stock("Adani Enterprises", 2261.89, 2258.45, 1), new Stock("Aether", 868.8, 868.2, 2),
			new Stock("Allied Digital Services Ltd.", 156.09, 115.67, 3),new Stock("Allcargo Terminals Ltd.", 38.3, 39, 4), new Stock("Zomato", 103.7, 114, 5),
			new Stock("PowerGrid", 203.1, 260, 6), new Stock("Bajaj", 7449, 7474.52, 7), new Stock("ONGC", 186.2, 190.5, 8),
			new Stock("Apollo tires", 387.4, 381.3, 9), new Stock("Delta Corp", 134.7, 127.85, 10), new Stock("Borosil",394.85,386.40, 11),
			new Stock("CAMS", 2263.45, 2246.9, 12), new Stock("Galaxy Surfacta", 2821.15, 2729.75, 13), new Stock("Kingfa Science", 2300.05, 2389.20, 14),
			new Stock("IDFC", 113.2, 114.5, 15), new Stock("IndiaBulls housing", 165.8, 161.9, 16), new Stock("Invesco India N", 2139.7, 2134.82, 17),
			new Stock("Ishan Intl", 63.5, 57.65, 18), new Stock("Infibeam Avenue", 19.65, 19.10, 19), new Stock("Indian Terrain", 55.6, 55.25, 20)

	};
}

class GraphRecommend {
	Stock[] stockArr = Stockarr.stockArray;
	ArrayList<Integer> visited = new ArrayList<>(0);
	


	GraphRecommend() {
		
		for (int i = 0; i < stockArr.length; i++) // We use the existent stock Array
		{
			visited.add(0);
			for (int j = i + 1; j < stockArr.length; j++) {
				double thiprc = stockArr[i].pastPrice;
				double otherprc = stockArr[j].currPrice;
				double diff = Math.abs(thiprc - otherprc);
				if (diff <= 100) 
				{
					stockArr[i].neighbours.add(stockArr[j]);
					stockArr[j].neighbours.add(stockArr[i]);
				}
			}
		}
	}
	

	LinkedList<Stock> getRec() 
	{
		LinkedList<Stock> ret = new LinkedList<>();					//store recommended stocks (neighbours)
		Stock ptr = stockArr[0];
		Stock MyStockPtr = ptr;
		Queue<Stock> q1 = new LinkedList<>();
		q1.add(ptr);
		while(MyStockPtr!=null)
		{
			while (!q1.isEmpty()) 										//traverse using bfs
			{
				ptr = q1.remove();
				ret.add(ptr);
				int ind = ptr.ind;
				visited.set(ind, 1);
				for (Stock s : stockArr[ind].neighbours) 
				{
					if(visited.get(s.ind)==0)
					{
						q1.add(s);
						visited.set(s.ind, 1);
					}
				}
				
			}
			MyStockPtr = MyStockPtr.next;
		}
		return ret;
	}
}

class LL 
{ // my portfolio
	public Stock head;
	public Stock tail;
	int size;

	LL() {
		this.head = null;
		this.tail = null;
	}

	public boolean contains(Stock n) {
		Stock ptr = head;
		while (ptr != head) {
			if (ptr == n)
				return true;
		}
		return false;
	}

	public Stock getIndex(int index) {

		Stock node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;

	}

	public void create(Stock s) {
		Stock stock = new Stock(s.name, s.currPrice, s.pastPrice, s.ind);
		if (head == null) {
			head = stock;
			tail = stock;
			size += 1;
			return;
		}
		Stock temp = head;
		while (temp.next != null) 
		{
			temp = temp.next;
		}
		temp.next = stock;
		tail = stock;
		size += 1;
	}

	public boolean delete(Stock s) {

		boolean flag = false;
		Stock temp = head;

		while (temp != null) {
			if (s.name == temp.name) {
				if (temp == head) {
					if (head.next == null) {
						head = null;
					} else {
						head = head.next;
					}
				} else if (size > 1 && temp.next == null) {
					Stock secondLast = getIndex(size - 2);
					secondLast.next = null;
				} else {
					Stock temp1 = head;
					while (temp1.next != temp) {
						temp1 = temp1.next;
					}
					temp1.next = temp.next;
				}
				size--; // decreasing size of LL after deleting
				flag = true; // check for account number
				break; // breaking from the loop as node deleted
			}
			temp = temp.next;
		}
		if (!flag) {
			System.out.println("You haven't bought the stock of " + s.name);
			return flag;
		}
		return flag;
	}

	public void displayPortfolio() {
		if (head == null) {
			System.out.println("Your portfolio is empty!");
			return;
		}
		Stock stock = head;
		while (stock != null) {
			System.out.println("Company Name: " + stock.name);
			stock = stock.next;
		}
	}

}

class Node {

	String pastTrans;
	Node link;
}

class Stack1 {

	Node top;

	Stack1() {
		this.top = null;
	}

	public void push(String x) {

		Node temp = new Node();

		if (top == null) {
			top = new Node();
			top.pastTrans = x;
		}

		temp.pastTrans = x;

		temp.link = top;

		top = temp;
	}

	public String pop() {

		if (top == null) {
			System.out.print("\nPast Transaction stack is empty");
			return null;
		}

		String value = top.pastTrans;

		top = (top).link;

		return value;
	}

	public boolean isEmpty() {
		return top == null;
	}
}

class Queue1 extends LL {
	Stock head;
	Stock tail;
	Stock next;

	public void add(Stock s) {
		Stock temp = new Stock(s.name, s.currPrice, s.pastPrice, s.ind);

		if (head == null && tail == null) 
		{
			head = temp;
			tail = temp;
			return;
		}
		tail.next = temp;
		tail = temp;
	}

	public Stock remove() {
		if (head == null) {
			return null;
		}
		Stock s = head;
		head = head.next;
		return s;
	}

	public boolean isEmpty1() {
		if (head == null) {
			return true;
		}
		return false;
	}

	public void displayWishList() {
		ArrayList<Stock> arr = new ArrayList<>();
		while (!this.isEmpty1()) {
			Stock temp = this.remove();
			arr.add(temp);
			System.out.println(temp.name);
		}
		for (int i = 0; i < arr.size(); i++) {
			Stock s = arr.get(i); // Retrieve the element at index i
			if (i == 0) {
				head = s;
			}
			if (i == arr.size() - 1) {
				tail = s;
			}
			this.add(s);
		}
	}

	public boolean deleteFromWishlist(String name) {
		Stock temp = head;
		while (temp != null) {
			if (temp.name.toLowerCase().equals(name)) {
				boolean flag = delete(temp);
				return flag;
			}
		}
		return false;

	}

}

class DictionaryNode 
{
    String keyword;
    String meaning;
    DictionaryNode left;
    DictionaryNode right;

    public DictionaryNode(String keyword, String meaning) 
    {
        this.keyword = keyword;
        this.meaning = meaning;
        this.left = null;
        this.right = null;
    }
}

class DictionaryBST 
{
    DictionaryNode root;

    public DictionaryBST() 
    {
        root = null;
    }

    

    public String search(String keyword) 
    {
        DictionaryNode result = searchRec(root, keyword);
        if (result != null) 
        {
            return result.meaning;
        } 
        else 
        {
            return "Keyword not found in the dictionary.";
        }
    }

     DictionaryNode searchRec(DictionaryNode root, String keyword) 
    {
        if (root == null || root.keyword.equalsIgnoreCase(keyword)) 
        {
            return root;
        }

        if (keyword.compareTo(root.keyword) < 0) 
        {
            return searchRec(root.left, keyword);
        } 
        else 
        {
            return searchRec(root.right, keyword);
        }
    } 
    
    void insert(String key, String meaning)
    {
    	 DictionaryNode temp = new DictionaryNode(key, meaning);
    	 
    	 if (root == null) 
    	 {
             root = temp;
             temp=null;
         }
    	 else
    	 {
    		 
    		 DictionaryNode ptr=root;
    		 DictionaryNode parent = ptr;
    		 char ch='X';
    		 while(ptr!=null)
    		 {
    			 if (key.compareTo(ptr.keyword) < 0)
    			 {
    				 parent=ptr;
    				 ptr = ptr.left;
    				 ch = 'L';
    			 }
    			 
    			 else
    			 {
    				 parent = ptr;
    				 ptr = ptr.right;
    				 ch = 'R';
    			 }
    		 }
    		 
    		 if(ch=='L')
    			 parent.left = temp;
    		 
    		 else
    			 parent.right=temp;
    		 
    	 }
     }

    
    
    public void addall() 
    {
        this.insert("array", "use - storing all stocks, sorting stock values\n"
        		+ "significance - time complexity(quick sort)\n"
        		+ "best case is O(n log n) \n"
        		+ "worst case is O(n^2)\n");
        this.insert("stack","use - for past transaction\n"
        		+ "significance - time complexity\n"
        		+ "best case is O(1) \n"
        		+ "worst case is O(n)\n");
        this.insert("queue", "use - for wishlist\n"
        		+ "significance - time complexity\n"
        		+ "best case is O(1) \n"
        		+ "worst case is O(n)\n");
        this.insert("linkedlist","use - Stores the stocks bought by the users, in their profile."
        		+ "significance - time complexity of insertion and deletion\n"
        		+ "best case is O(1) \n"
        		+ "worst case is O(n)\n");
        this.insert("binary search tree", "use - searching the use of data structures and their significance in this project\n"
        		+ "significance - time complexity of searching\n"
        		+ "average case is O(log n) where log n is the height of the tree\n"
        		+ "worst case is O(n), when its a skew tree and behaves like a linked list\n");
        this.insert("graph", "use - recommendation system\n"
        		+ "significance - time complexity of traversal\n"
        		+ "every case is O(V+E) \n"
        		+ "V-vertices E-edges\n");
        this.insert("hash table", "use - storing all user profiles\n"
        		+ "significance - time complexity\n"
        		+ "best case is O(1) \n"
        		+ "for searching, insertion and deletion\n");
    }
}