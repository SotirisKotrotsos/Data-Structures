import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class RandomizedBST implements MafiaInterface{

    public RandomizedBST(Comparator comparator) {
        this.comparator = comparator;
    }

    private class TreeNode{
        private Suspect item;
        private TreeNode left;
        private TreeNode right;
        int N;

        public TreeNode(){
        }

        public TreeNode(Suspect item){
            this.item = item;
        }

        public Suspect getData() {
            return item;
        }

        public void setData(Suspect item) {
            this.item = item;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    private TreeNode root;
    private Comparator comparator;

    @Override
    public void insert(Suspect item) {
        if(root == null){
            root = new TreeNode(item);
        }
        TreeNode current = root;

        while(true){
            if (current.getData() == item)
                return;

            if (comparator.compare(current.getData(), item) < 0) {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode(item));
                    return;
                } else {
                    current = current.getRight();
                }
            } else {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode(item));
                    return;
                } else {
                    current = current.getLeft();
                }
            }
        }
    }

    @Override
    public void load(String filename) {
        int AFM;
        String firstName;
        String lastName;
        double savings;
        double taxedIncome;
        try{
            Scanner readfile = new Scanner(new File(filename));
            while(readfile.hasNextLine()){
                String line = readfile.nextLine();
                String[] split_text = line.split(" ");
                AFM= Integer.parseInt(split_text[0]);
                firstName = split_text[1];
                lastName = split_text[2];
                savings = Double.parseDouble(split_text[3]);
                taxedIncome = Double.parseDouble(split_text[4]);
                Suspect suspect = new Suspect(AFM,firstName, lastName, savings, taxedIncome);
                insert(suspect);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateSavings(int AFM, double savings) {
        Suspect searchingsuspect = searchByAFM(AFM);
        if (searchingsuspect == null){
            System.out.println("Suspect didnt found");
            return;
        }
        searchingsuspect.setSavings(savings);
    }

    @Override
    public Suspect searchByAFM(int AFM) {
        TreeNode current  = root;
        while(true){
            if(current == null){
                System.out.println("The item does not exist");
            }
            if(current.getData().getAFM() == AFM){
                current.toString();
            }
            if(comparator.compare(current.getData().getAFM(), AFM) < 0){
                current = current.getRight();
            }
            else {
                current = current.getLeft();
            }

        }
    }

    @Override
    public List searchByLastName(String lastName) {
        TreeNode current = root;
        while(true){
            if(current == null){
                System.out.println("The item does not exist");
            }
            if(current.getData().getLastName() == lastName){
                current.toString();
            }
            if(comparator.compare(current.getData().getLastName(), lastName) < 0){
                current = current.getRight();
            }
            else{
                current = current.getLeft();
            }
        }
    }

    @Override
    public void remove(int AFM) {
        TreeNode current = root;
        TreeNode parent = null;
        TreeNode replace = null;

        while(true){
            if(current == null){
                return;
            }
            if(current.getData().equals(AFM)){
                break;
            }
            parent = current;

            if(comparator.compare(current.getData().getAFM(), AFM) < 0){
                current  = current.getRight();
            }
            else{
                current = current.getLeft();
            }
        }

        if(current.getLeft() == null){
            replace = current.getRight();
        }
        else if(current.getRight() == null){
            replace = current.getLeft();
        }
        else{
            TreeNode findCurrent = current.getRight();

            while (true){
                if(findCurrent.getLeft() != null){
                    findCurrent = findCurrent.getLeft();
                }
                else {
                    break;
                }
            }
            remove(findCurrent.getData().getAFM());

            findCurrent.setLeft(current.getLeft());
            findCurrent.setRight(current.getRight());
        }

        if(parent == null){
            root = replace;
        }else{
            if(parent.getLeft() == current){
                parent.setLeft(replace);
            }
            if(parent.getRight() == current){
                parent.setRight(replace);
            }
        }

    }

    private int countAllSuspects(TreeNode t){
        if (t == null){
            return 0;
        }
        return 1 + countAllSuspects(t.left) + countAllSuspects(t.right);
    }

    private double countSAvingsFromAllSuspects(TreeNode t){
        if (t == null){
            return 0;
        }
        return t.getData().getSavings() + countSAvingsFromAllSuspects(t.left) + countSAvingsFromAllSuspects(t.right);
    }
    @Override
    public double getMeanSavings() {
        return countAllSuspects(root) / countSAvingsFromAllSuspects(root);
    }

    @Override
    public void printTopSuspects(int k) {

    }

    @Override
    public void printByAFM() {
        printAfm(root);
    }

    private void printAfm(TreeNode root) {
        if(root == null){
            return;
        }
        printAfm(root.getLeft());
        System.out.println(root.getData().toString());
        printAfm(root.getRight());
    }

    static void printMenu(){
        System.out.println("1--> Insert suspect");
        System.out.println("2--> Load suspects from a file");
        System.out.println("3--> Update savings from a suspect");
        System.out.println("4--> Search a suspect by AFM");
        System.out.println("5--> Search a suspect by last name");
        System.out.println("6--> Remove a suspect");
        System.out.println("7--> Get mean savings");
        System.out.println("8--> Print top suspects");
        System.out.println("9--> Print suspects by AFM");
        System.out.println("0-->1 Exit");
    }
    public static void main(String[] args) {
        RandomizedBST myTree = new RandomizedBST(new SuspectComparator());
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int input = scanner.nextInt();
        if (input == 1){
            System.out.println("AFM suspect");
            int afm = scanner.nextInt();
            System.out.println("Name of suspect");
            String name = scanner.next();
            System.out.println("Last name suspect");
            String lastName = scanner.next();
            System.out.println("Savings suspect");
            double savings = scanner.nextDouble();
            System.out.println("Taxed incomed suspect");
            double taxedincome = scanner.nextDouble();
            myTree.insert(new Suspect(afm, name, lastName, savings, taxedincome));
        }
        else if( input == 2){
            System.out.println("Insert file path");
            myTree.load(scanner.next());
        }
        else if( input == 3){
            System.out.println("Type AFM and savings to update");
            int afm = scanner.nextInt();
            double savings = scanner.nextDouble();
            myTree.updateSavings(afm, savings);
        }
        else if( input == 4){
            System.out.println("Type suspect's AFM");
            myTree.searchByAFM(scanner.nextInt());
        }
        else if( input == 5){
            System.out.println("Type suspect's last name");
            myTree.searchByLastName(scanner.nextLine());
        }
        else if( input == 6){
            System.out.println("Type the AFM of the suspect you want to remove");
            myTree.remove(scanner.nextInt());
        }
        else if( input == 7){
            System.out.println(myTree.getMeanSavings());
        }
        else if( input == 8){
            System.out.println("How many of top suspect's you want to print:");
            myTree.printTopSuspects(scanner.nextInt());
        }
        else if( input == 9){
            myTree.printByAFM();
        }
        else{
            System.exit(0);
        }
    }
}
