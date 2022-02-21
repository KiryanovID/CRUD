package actual;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="country", schema = "main")
public class Country implements Comparable<Country> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long square;
    private int population;
    private Continent continent;

    public Country() {
    }

    public Country(String name, long square, int population, Continent continent) {
        this.name = name;
        this.square = square;
        this.population = population;
        this.continent = continent;
    }
    public Country(long id, String name, long square, int population, Continent continent) {
        this.id = id;
        this.name = name;
        this.square = square;
        this.population = population;
        this.continent = continent;
    }

    @Override
    public String toString() {
        return
                "Страна: '" + name + '\'' +
                        ", площадь: " + square + "кв.км" +
                        ", население: " + population + "чел." +
                        ", континент: " + continent;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSquare() {
        return square;
    }

    public void setSquare(long square) {
        this.square = square;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return square == country.square && population == country.population && Objects.equals(name, country.name) && continent == country.continent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, square, population, continent);
    }

    @Override
    public int compareTo(Country o) {
        return this.getName().compareTo(o.getName());
    }


}
