import java.util.List;

public interface MafiaInterface {

    void insert(Suspect item);
    void load(String filename);
    void updateSavings(int AFM, double savings);
    Suspect searchByAFM(int AFM);
    List searchByLastName(String lastName);
    void remove(int AFM);
    double getMeanSavings();
    void printTopSuspects(int k);
    void printByAFM();
}
