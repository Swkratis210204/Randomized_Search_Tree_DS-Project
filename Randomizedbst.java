import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Randomizedbst implements TaxEvasionInterface, Comparable {

    static private class TreeNode {
        public LargeDepositor item;
        private TreeNode left;
        private TreeNode right;
        private int N;

        // Constructor
        public TreeNode(LargeDepositor item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.N=1;
        }
    
        // Getters
        public TreeNode getLeft() {
            return left;
        }
    
        public TreeNode getRight() {
            return right;
        }
    
        public  int getN() {
            return N;
        }
    
        // Setters
        public void setItem(LargeDepositor item) {
            this.item = item;
        }
    
        public void setLeft(TreeNode left) {
            this.left = left;
        }
    
        public void setRight(TreeNode right) {
            this.right = right;
        }
    
        public void setN(TreeNode h) {
            this.N = size(h);
        }
        

        public TreeNode rotR(TreeNode h){
            TreeNode x=h.getLeft();
            h.setLeft(x.getRight());
            x.setRight(h);
            return x;
        }
        public TreeNode rotL(TreeNode h){
            TreeNode x=h.getRight();
            h.setRight(x.getLeft());
            x.setLeft(h);
            return x;
        }
    
        // toString method
        @Override
        public String toString() {
            return "TreeNode{" +
                    "item=" + item +
                    ", left=" + left +
                    ", right=" + right +
                    ", N=" + N +
                    '}';
        }
    }
//END OF TREENODE------START OF RANDOMIZEDBST

    private TreeNode root;
    private int size;
    static Scanner sc=new Scanner(System.in);
//---------------------Inserts(Insert and InsertAtRoot)+ size---------------------------------------
    private TreeNode insertR(TreeNode h, LargeDepositor x) {
        if (h == null){
            return  new TreeNode(x);
        } 

         if(searchByAFMR(root,x.key())!=x && searchByAFMR(root,x.key())!=null){
            System.out.println("AFM allready existing");
            System.exit(0);
        }

        if (Math.random()*(h.getN()+1) < 1.0){
            return insertAsRoot(x,h);
        }
        if (x.key()< h.item.key()){
            h.setLeft(insertR(h.getLeft(), x));
        }
        else{
            h.setRight(insertR(h.getRight(), x));
        }
        h.setN(h);
        return h; 
    }

    public void insert(LargeDepositor x) { 
        size++;
        root = insertR(root, x);  
    }

    private TreeNode insertAsRoot(LargeDepositor item, TreeNode h) {
    if (h == null){return new TreeNode(item);} 
        if (item.key()< h.item.key()){
            h.setLeft(insertAsRoot(item,h.getLeft())); //αναδρομικά το x ρίζα του h.l
            h=h.rotR(h); } //δεξιά περιστροφή για να έρθει στη ρίζα
        else {
            h.setRight(insertAsRoot(item,h.getRight())); 
            h = h.rotL(h); 
        }
        return h; 
    } 
    private static int size(TreeNode node) {
        return (node == null) ? 0 : 1 + size(node.getLeft()) + size(node.getRight());
    }

//---------------------------updateSavings---------------------------------------
    @Override
    public void updateSavings(int AFM, double savings) {
        LargeDepositor temp=searchByAFM(AFM);
        if(temp!=null){
            temp.setSavings(savings);
        }
    }
//---------------------------searchByAFM---------------------------------------

    public LargeDepositor searchByAFMR(TreeNode h, int AFM) {
        if (h == null) {return null;}
        if (AFM== h.item.key()){ return h.item;}
        if (AFM< h.item.key()){ return searchByAFMR(h.getLeft(), AFM);}
        else { return searchByAFMR(h.getRight(),AFM);}
    }

    @Override
    public LargeDepositor searchByAFM(int AFM){
        if(searchByAFMR(root, AFM)==null){
            System.out.println("Did not find anyone with that AFM");
        }else{
            return searchByAFMR(root, AFM);
        }
        return searchByAFMR(root, AFM);
    }


//---------------------------Remove---------------------------------------
    @Override
    public void remove(int AFM) {
        size--;
        root = removeR(root, AFM);
    }
    private TreeNode removeR(TreeNode root, int AFM) {
        if(root==null){return null;}
        int w=root.item.key();
        if (AFM<w){
             root.setLeft( removeR(root.getLeft(), AFM));
        }
        if (AFM>w){
            root.setRight( removeR(root.getRight(), AFM));
       }
       if (AFM== w){
        root = joinLR(root.getLeft(),root.getRight());
        }
        return root;
    }


    private TreeNode joinLR(TreeNode left, TreeNode right) {
        if (right == null){ return left;}
            right = partR(right, 0); 
            right.setLeft(left);
            return right; 
    }

    private TreeNode partR(TreeNode h, int k) {
        int t = (h.getLeft() == null) ? 0 : h.getLeft().getN();
        if (t > k) {
            h.setLeft(partR(h.getLeft(), k)); 
            h=h.rotR(h); 
        }
        if (t < k) {
            h.setRight(partR(h.getRight(), k-t-1));
            h=h.rotL(h); 
        }
        return h;
    }

    public int getSize() {
        return size;
    }
//-------------------------printΤopLargeDepositors--------------------------------------------

    public void findTopLargeDepositorsUnder8000(TreeNode hT,MyList<LargeDepositor> list,int k) {
        if (hT != null){
            if (hT.item.getTaxedIncome() < 8000 && list.getSize() < k) list.insertAtBack(hT.item);

            findTopLargeDepositorsUnder8000(hT.left,list,k);
            findTopLargeDepositorsUnder8000(hT.right,list,k);
        }
    }

    public void findTopLargeDepositorsCompare(TreeNode hT,MyList<LargeDepositor> list,int kmore, int k) {
        if (hT != null){
            if (list.getSize() < k ) {
                if (!list.isInside(hT.item)) list.insertAtFront(hT.item);                            // vazw sthn arxh kapoia arxika kmore epipleon stoixeia wste na gemisei h lista
            } else{                                                     // (den ta vazw sto telos gia einai pio eykolo sthn arxh, tha ta valw ystera)
                Node<LargeDepositor> cur = list.getHead();

                LargeDepositor min = cur.getData();;
                int i = 1;
                while (i < kmore) {                                             // elegxw poio apo ta kmore stoixeia einai pithanotero na fygei(dhladh to mikrotero)
                    i++;
                    cur = cur.getNext();
                    if (Comparable.compareTo(cur.getData(), min) < 0) {
                        min = cur.getData();
                    };
                }
                
                if (Comparable.compareTo(hT.item, min) > 0 && !list.isInside(hT.item)) {                        // elegxw an min < tou twrinou stoixeio sto dentro wste na dw an prepei na to valw antaytou
                    try {
                        list.remove(min);
                        list.insertAtFront(hT.item);
                    } catch (Exception e) {
                        System.err.println("Didnt remove element");
                    };
                }
            }

            findTopLargeDepositorsCompare(hT.left,list,kmore,k);                            // anadromikes klhseis wste na elejxw ola ta stoixeia sto dentro
            findTopLargeDepositorsCompare(hT.right,list,kmore,k);
            
        }
    }

    @Override
    public void printΤopLargeDepositors(int k) throws Exception{
        MyList<LargeDepositor> list = new MyList<>();

        if (k > getSize()) {
            System.out.println("The RandomizedBST does not have that many Large Depositors."+ 
            "I will instead print you the top "+getSize()+" Large Depositors"+"\n");
            k = getSize();
        }

        findTopLargeDepositorsUnder8000(root,list,k);
        int OldListSize = list.getSize();
        if (list.getSize() < k) {
            findTopLargeDepositorsCompare(root,list,k-list.getSize(),k);

            for(int i = 0; i < k-OldListSize ; i++) list.insertAtBack(list.removeFromFront());  //allazw tis theseis twn dyo eidwn Depositors wste na exw prwta autous me <8000 kai ystera toys ypoloipoys               
        }
        sort(list);
        System.out.println(list);
    }

    private void sort(MyList<LargeDepositor> list) throws Exception {
        Node<LargeDepositor> cur=list.getHead();
        int count=0;
        for(int i=0;i<list.getSize();i++){
            if(cur.getData().getTaxedIncome()<8000){
                count++;
                cur=cur.getNext();
            }
            
        }

        Node<LargeDepositor> flag = cur;        //gives us the next element after the last large Depositor Under 8000

        cur=list.getHead();
        int j=0;
        while(j<count){
            Node<LargeDepositor> mindep=cur;
            double min=cur.getData().getTaxedIncome();

            Node<LargeDepositor> cur2=cur;
            for(int i=0;i<count-j;i++){
                if(cur2.getData().getTaxedIncome()<min){
                    min=cur2.getData().getTaxedIncome();
                    mindep=cur2;
                }
                cur2=cur2.getNext();
            }

            Node<LargeDepositor> temp=mindep;
             
            list.remove(mindep.getData());
            list.insertAtBack(temp.getData());
            
            cur=list.getHead();
            j++;
        }

        cur=flag;
        j=0;
        while(j<list.getSize()-count){
            Node<LargeDepositor> maxdep=cur;
            double max=cur.getData().getSavings()-cur.getData().getTaxedIncome();

            Node<LargeDepositor> cur2=cur;
            for(int i=0;i<(list.getSize()-count)-j;i++){
                if(cur2.getData().getSavings()-cur2.getData().getTaxedIncome()>max){
                    max=cur2.getData().getSavings()-cur2.getData().getTaxedIncome();
                    maxdep=cur2;
                }
                cur2=cur2.getNext();
            }

            Node<LargeDepositor> temp=maxdep;
             
            if (maxdep == flag) flag = flag.getNext();
            list.remove(maxdep.getData());
            list.insertAtBack(temp.getData());
            
            cur=flag;
            j++;
        }

    }
    
    //-------------------------Demos--------------------------------------------
    public LargeDepositor searchByLastNameR(TreeNode h,String last_name, MyList<LargeDepositor> names) {

        if (h == null) return null;

        searchByLastNameR(h.getLeft(), last_name, names);
        searchByLastNameR(h.getRight(), last_name, names);

        if (last_name.equalsIgnoreCase(h.item.getLastName())) names.insertAtBack(h.item);;

        return null;
    }

    @Override
    public MyList<LargeDepositor> searchByLastName(String last_name) {
        MyList<LargeDepositor> names = new MyList<>();
        searchByLastNameR(root, last_name, names);
        if (names.getSize() < 6) System.out.println(names);
        return names;
    }

    public double getMeanSavingsR(TreeNode h) {
        if (h == null) return 0;
        return getMeanSavingsR(h.getLeft()) + getMeanSavingsR(h.getRight()) + h.item.getSavings();

    }

    @Override
    public double getMeanSavings() {
        return getMeanSavingsR(root)/ size(root);
    }
    
    @Override
    public void load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line=reader.readLine();
            while (line!= null) {
                insert(StringToLargeDepositor(line));
                line=reader.readLine();
            }
            
        } catch (IOException e) {
            // Handle exceptions, such as file not found or permission issues
            e.printStackTrace();
        }
    }

    public LargeDepositor StringToLargeDepositor(String line) {
        String[] str=line.split(" ");
        return new LargeDepositor(Integer.parseInt(str[0]),str[1],str[2],Double.parseDouble(str[3]),Double.parseDouble(str[4]));
    }
    //---------------------------Print---------------------------------------
    private void printByAFMR(TreeNode h) {
        if(h==null){
            return;
        }
        printByAFMR(h.getLeft());
        System.out.println(h.item.toString());
        printByAFMR(h.getRight());
    }
    @Override
    public void printByAFM(){
        printByAFMR(root);
    }










//-------------------------------------MAIN--------------------------------------------------
    public static void main(String[] args) throws Exception{

        Randomizedbst random=new Randomizedbst();
        random.load("text.txt");

        menu();
        System.out.print("Choose (1-8): ");
        int choice=sc.nextInt();
        sc.nextLine();
        while(choice>0 && choice<9){
            switches(choice,random);
            menu();
            System.out.print("Choose(1-8) : ");
            choice=sc.nextInt();
            sc.nextLine();
        }
        System.out.println("Ending the program...");


    }
    private static void switches(int choice, Randomizedbst random) {
        switch (choice) {
            case 1:
                System.out.print("Give the first name of the Depositor you want to insert : \n");
                String firstname=sc.nextLine();
                System.out.print("Give the last name of the Depositor you want to insert : \n");
                String lastname=sc.nextLine();
                System.out.print("Give the AFM of the Depositor you want to insert : \n");
                int AFM=sc.nextInt();
                if(random.searchByAFM(AFM)!=null){
                    System.out.println("There is already a depositor with that AFM");
                    System.exit(0);
                }
                sc.nextLine();
                System.out.print("Give the savings of the Depositor you want to insert : \n");
                double savings=sc.nextDouble();
                sc.nextLine();
                System.out.print("Give the taxed income of the Depositor you want to insert : \n");
                double taxedincome=sc.nextDouble();
                sc.nextLine();
                random.insert(new LargeDepositor(AFM, firstname, lastname, savings, taxedincome));
                break;
           case 2:
                System.out.print("Give the AFM of the Depositor you want to change the savings : \n");
                AFM=sc.nextInt();
                sc.nextLine();
                if(random.searchByAFM(AFM)==null){
                    break;
                };
                System.out.print("Give the new savings : \n");
                savings=sc.nextDouble();
                sc.nextLine();
                random.updateSavings(AFM, savings);
                break;

            case 3:
                System.out.print("Give the AFM of the Depositor you want to search : \n");
                AFM=sc.nextInt();
                sc.nextLine();
                System.out.println("\nThe Depositor you searched is : \n");
                System.out.println(random.searchByAFM(AFM));
                break;

            case 4:
                System.out.print("Give the last name of the Depositor you want to search : \n");
                lastname=sc.nextLine().trim();
                System.out.println("\nThe Depositor/s you searched is/are : \n");
                random.searchByLastName(lastname);
                break;

            case 5:
                System.out.print("Give the AFM of the Depositor you want to remove : \n");
                AFM=sc.nextInt();
                sc.nextLine();
                if(random.searchByAFM(AFM)==null){
                    System.out.println("There is not an existing Depositor with that AFM");
                }
                random.remove(AFM);
                System.out.println("\nThe Depositor is removed\n");
                break;

            case 6:
                System.out.print("Give the mean Savings of all the Depositors are : \n");
                System.out.println(random.getMeanSavings());
                break;
           
           case 7:
                try {
                    System.out.print("Give the number of the top Depositor you want to print : \n");
                    int k=sc.nextInt();
                    sc.nextLine();
                    random.printΤopLargeDepositors(k);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            
            case 8:
                random.printByAFM();
                break;
        }
    }

    public static void menu(){
         //---------------------------MENU------------------------
        System.out.println("----------------------------------------------------\n\n1. Insert a Depositor\n2. Update a Depositor's savings\n3. Search a Depositor by AFM\n4. Search a Depositor by Last Name\n5. Remove a Depositor\n6. Get the mean savings of the Depositors\n7. Print the top Depositors\n8. Print the Depositors by AFM\n\n----------------------------------------------------");
    }
}