import java.util.Comparator;

public class SuspectComparator implements Comparator<Suspect> {
    @Override
    public int compare(Suspect o1, Suspect o2) {
        double suspicion1 = o1.getSavings() - o1.getTaxedIncomes();
        double suspicion2 = o2.getSavings() - o2.getTaxedIncomes();
        double compareSus = suspicion1 - suspicion2;

        if (o1.getTaxedIncomes() < 9000) {
            if (suspicion1 < suspicion2){
                return -1;
            }
            else if (suspicion1 > suspicion2){
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            if (compareSus < 0){
                return -1;
            }
            else if (compareSus > 0){
                return 1;
            }
            else {
                return 0;
            }
        }
    }
}
