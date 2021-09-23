import java.io.File;
import java.util.Scanner;

public class Covid_k {

    public static  City[] readFromFile(String filename){
        StringDoubleEndedQueueImpl strlist = new StringDoubleEndedQueueImpl();
        String [] arr;
        int ID,population,covidCases;
        String name;
        try{
            Scanner readFile = new Scanner(new File(filename));
            while(readFile.hasNextLine()){
                String line = readFile.nextLine();
                arr = line.split(" ");
                ID = Integer.parseInt(arr[0]);
                name = arr[1];
                population = Integer.parseInt(arr[2]);
                covidCases = Integer.parseInt(arr[3]);
                City city = new City(ID, name, population, covidCases);
                strlist.addLast(city);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int strlist_size = strlist.size();
        City[] city_list = new City[strlist_size];
        int i = 0;
        do {
            City node = strlist.removeFirst();
            city_list[i] = node;
            i++;
        }while(!strlist.isEmpty());
        return city_list;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the filename.");
        System.out.println(">");
        String fileName = in.nextLine();
        System.out.println("Please insert parameter k");
        System.out.println(">");
        int parameter_k = in.nextInt();
        City[] cities = readFromFile(fileName);
        if (parameter_k > cities.length) {
            System.out.println("invalid k parameter ");
            System.exit(0);
        }
        else {
            QuickSort quickSort = new QuickSort();
            quickSort.sort(cities, 0, cities.length - 1);
            System.out.println("The top " + parameter_k + " cities are:");
            for(int i = 0; i < parameter_k; i++) {
                System.out.println(cities[i].getName());
            }
        }
    }
}
