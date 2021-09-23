public class QuickSort {


    public QuickSort() {
    }

    public void sort(City[] array, int begin, int end){

        if (end <= begin) return;
        int i = partition(array, begin, end);
        sort(array, begin, i-1);
        sort(array, i+1, end);
    }

    private void swap(City array[], int i, int j) {
        City temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int partition(City[] array, int left, int right) {
        City pivot = array[right];
        int partitionIndex = left;
        for (int i =left; i< right; i++){
            if (array[i].compareTo(pivot) > 0){ //descending order
                swap(array, i, partitionIndex);
                partitionIndex++;
            } else if (array[i].compareTo(pivot) == 0) {
                String str1 = array[i].getName();
                String str2 = pivot.getName();
                if (str1.compareTo(str2) < 0) {
                    swap(array, i, partitionIndex);
                    partitionIndex++;
                } else if (str1.equals(str2)) {
                    if (array[i].getID() < pivot.getID()) {
                        swap(array, i, partitionIndex);
                        partitionIndex++;
                    }
                }
            }
        }
        swap(array, partitionIndex, right);
        return partitionIndex;
    }


}
