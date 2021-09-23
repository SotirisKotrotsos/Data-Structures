import java.util.Comparator;

public class PQ implements PQInterface {
    private City[] heap;
    private int size;
    private int [] IDs;


    public PQ(int length){
        this.heap = new City[length + 1];
        this.size = 0;
        this.IDs = new int[length + 1];
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public void resize(){
        City[] newheap =new City[2*heap.length];
        for (int i = 0;i<=size; i++){
            newheap[i] = heap[i];
        }
        heap = newheap;
    }
    @Override
    public void insert(City x) {
        if(size()>=3/4){
            resize();
        }
        heap[++size] = x;
        swim(size);
    }

    private void swim(int i) {
        // if i is root (i==1) return
        if (i == 1)
            return;

        // find parent
        int parent = i / 2;

        // compare parent with child i
        while (i != 1 && heap[i].compareTo(heap[parent]) == 1) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }

        // recursive function
        // if (heap[i] > heap[parent]) {
        //     swap(i, parent);
        //     swim(parent);
        // }
    }

    @Override
    public City max() {
        if (isEmpty()){
            return null;
        }
        else{
            return heap[size];
        }
    }

    @Override
    public City getMax() {
        if(isEmpty()){
            return null;
        }
        City root = heap[1];
        heap[1] = heap[size];
        size--;

        sink(1);
        return root;

    }

    private void sink(int i) {
        // determine left, right child
        int left = 2 * i;
        int right = left + 1;

        // if 2*i > size, node i is a leaf return
        if (left > size)
            return;

        // while haven't reached the leafs
        while (left <= size) {
            // Determine the largest child of node i
            int max = left;
            if (right <= size) {
                if(heap[left].compareTo(heap[right]) == -1){
                    max = right;
                }
                /*if (compare(heap[left], heap[right]) < 0)
                    max = right;*/
            }

            // If the heap condition holds, stop. Else swap and go on.
            // child smaller than parent
            /*if (compare(heap[i], heap[max]) >= 0)
                return;*/
            if(heap[i].compareTo(heap[max]) == 0 || heap[i].compareTo(heap[max]) == 1){
                return;
            }
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

    private void swap(int i, int j) {
        City tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    @Override
    public City remove(int id) {
        int position = IDs[id];
        if (size == 0) {
            return null;
        }
        City temp = heap[position];
        heap[position] = heap[size];
        IDs[heap[position].getID()] = position;
        size--;
        sink(position);
        return temp;

    }


}
