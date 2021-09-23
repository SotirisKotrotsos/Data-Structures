public class Node {
    public City data;
    protected Node next;
    protected Node prev;

    public Node(City data){
        this.data = data;
    }

    public City getData(){
        return this.data;
    }

    public Node getNext(){
        return this.next;
    }

    public Node getPrev(){
        return this.prev;
    }

    public void  setNext(Node next){
        this.next = next;
    }

    public void setPrev(Node prev){
        this.prev = prev;
    }
}
