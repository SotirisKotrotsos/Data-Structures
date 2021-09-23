import java.text.DecimalFormat;

public  class City implements CityInterface, Comparable<City> {
    int ID;
    String name;
    int population;
    int CovidCases;


    public City(int ID, String name, int population, int CovidCases){
        this.ID = ID;
        this.name = name;
        this.population = population;
        this.CovidCases = CovidCases;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPopulation() {
        return population;
    }

    @Override
    public int getCovidCases() {
        return CovidCases;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public void setCovidCases(int CovidCases) {
        this.CovidCases = CovidCases;
    }

    @Override
    public int compareTo(City city) {
        double temp = this.calculateDensity() - city.calculateDensity();
        if(temp>0){
            return 1;
        }
        else if(temp == 0 ){
            return 0;
        }
        else {
            return -1;
        }
    }

    public double calculateDensity( ){
        double density = (this.getCovidCases() * 50_000)/ this.getPopulation();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return Double.parseDouble(df.format(density));
    }
}
