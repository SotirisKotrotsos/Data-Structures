import java.util.Scanner;
public class PostfixToinfix {
    static boolean isOperand(char x){
        return (x >= '0' && x <= '9');
    }
    static boolean check(char exp, String[] character) {
        for (int count = 0; count < character.length; count++){
            if(character[count].equals( String.valueOf(exp)/*exp*/)) return true;
        }
        return false;
    }
    public static void main(String[] args){
        //char[] words = {​​'1','2','3','4','5','6','7','8','9','0','+','-','*','/'}​​;
        StringDoubleEndedQueueImpl strList = new StringDoubleEndedQueueImpl();
        String[] words =new String[] {"1","2","3","4","5","6","7","8","9","0","+","-","*","/"};
        Scanner in = new Scanner(System.in);
        System.out.println("Please insert the postfix");
        System.out.println(">");
        String postfix = in.nextLine();
        for (int i = 0; i < postfix.length(); i++){
            if (!check(postfix.charAt(i), words)) {
                System.out.println("Incorect type of postfix !!");
            }
        }
        if (postfix.endsWith("+") || postfix.endsWith("-")
                || postfix.endsWith("/") || postfix.endsWith("*")) {
            for (int i = 0; i<postfix.length(); i++){
                if(isOperand(postfix.charAt(i))){
                    strList.addLast(postfix.charAt(i) + "");
                }
                else{
                    String operand1 = strList.removeLast();
                    String operand2 = strList.removeLast();
                    strList.addLast("("+operand2 + postfix.charAt(i) + operand1 + ")");
                }
            }
            System.out.println(strList.removeLast());
        }else{
            System.out.println("Incorect type of postfix !!");
        }
    }
}
