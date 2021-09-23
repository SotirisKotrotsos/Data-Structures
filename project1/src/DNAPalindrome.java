import java.util.Scanner;

public class DNAPalindrome {
    static boolean check(char exp, char[] character) {
        for (int count = 0; count < character.length; count++){
            if(character[count] == exp) return true;
        }
        return false;
    }

    public static void main(String[] args){
        StringDoubleEndedQueueImpl strList = new StringDoubleEndedQueueImpl();
        char[] words = {'A', 'T', 'G', 'C'};
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        System.out.println("Please insert DNA");
        System.out.println(">");
        String dna_input = in.nextLine();
        for (int i = 0; i < dna_input.length(); i++){
            if (!check(dna_input.charAt(i), words)) {
                System.out.println("Please insert the right form of DNA");
            }
        }
        if(dna_input.contains("A") || dna_input.contains("T")
                || dna_input.contains("G") || dna_input.contains("C")) {


            for (int i = 0; i < dna_input.length(); i++) {
                if (dna_input.charAt(i) == 'A') {
                    strList.addFirst("T");
                } else if (dna_input.charAt(i) == 'T') {
                    strList.addFirst("A");
                } else if (dna_input.charAt(i) == 'G') {
                    strList.addFirst("C");
                } else if (dna_input.charAt(i) == 'C') {
                    strList.addFirst("G");
                }
            }
            for (int x = 0; x < dna_input.length(); x++) {
                String a = String.valueOf(dna_input.charAt(x));
                if (strList.removeFirst().equals(a)) {
                    flag = true;
                    continue;
                } else {
                    System.out.println("DNA is not palindrome");
                    break;
                }
            }
            if (flag) {
                System.out.println("DNA is palindrome");
            }
        }
        else{
            System.out.println("Please insert the right form of DNA");
        }
    }
}
