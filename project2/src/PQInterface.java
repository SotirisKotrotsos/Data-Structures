public interface PQInterface {

    boolean isEmpty();//check if queue is empty

    int size(); // return the size of queue

    void insert(City x); //insert in queue

    City max(); // return the maximum

    City getMax(); //return and delete the max

    City remove(int id); //delete and remove the city with id

    void resize();
}
