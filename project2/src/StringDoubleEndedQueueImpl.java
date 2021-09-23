import java.io.PrintStream;
import java.util.NoSuchElementException;


public class StringDoubleEndedQueueImpl implements StringDoubleEndedQueue {
    private Node head;
    private Node tail;
    protected static int count = 0;

    @Override
    public boolean isEmpty(){

        return this.head == null;
    }

    public void addFirst(City item){
        Node n = new Node(item);
        if(isEmpty()){
            this.head = this.tail = n;
        }
        else{
            n.setNext(head);
            n.setPrev(null);
            head.setPrev(n);
            head = n;
        }
        count++;
    }

    public City removeFirst() throws NoSuchElementException{
        City data = head.getData();
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(head == tail){
            head = null;
            tail = null;
        }
        else{
            head = head.getNext();
        }
        count--;
        return data;
    }


    public void addLast(City item){
        Node n = new Node(item);
        if (isEmpty()){
            head = tail = n;
        }
        else{
            n.setPrev(tail);
            n.setNext(null);
            tail.setNext(n);
            tail = n;
        }
        count++;
    }

    public City removeLast() throws NoSuchElementException{

        if(this.isEmpty()) {
            throw new NoSuchElementException();
        }
        City data = tail.getData();
        if(head == tail){
            head = null;
            tail = null;
        }
        else{
            tail = tail.getPrev();
            tail.setNext(null);
        }
        count --;
        return data;
    }

    public City getFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return head.getData();
    }

    public City getLast() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return tail.getData();
    }

    public void printQueue(PrintStream stream) {


            if (isEmpty()) {
                stream.println("List is empty!");
            } else if (head.getData() == tail.getData()) {
                stream.println(head.getData());
            } else {
                Node node = head;
                do {
                    stream.println(node.getData());

                    node = node.getNext();

                } while (node.getNext() != null);
                stream.println(node.getData());
            }
    }




    public int size() {
        return count;
    }
}


