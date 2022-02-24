package actual;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CountryDAO {

    Session session = HibernateSession.getSessionFactory().openSession();
    Transaction transaction;

    public void create(String name, long square, int population, Continent continent) {
        transaction = session.beginTransaction();
        session.save(new Country(name, square, population, continent));
        transaction.commit();

    }

    public Country read(long find) {
        Country country = session.get(Country.class, find);

        return country;
    }

    public void update(int find, String name, long square, int population, Continent continent) {
        transaction = session.getTransaction();
        Country countryTemp = session.get(Country.class, find);
        countryTemp.setName(name);
        countryTemp.setSquare(square);
        countryTemp.setPopulation(population);
        countryTemp.setContinent(continent);
        session.update(countryTemp);
        transaction.commit();

    }

    public void delete(long find) {
        transaction = session.beginTransaction();
        Country country = session.get(Country.class, find);
        session.delete(country);
        transaction.commit();

    }

    public List<Country> showAll() {
        transaction = session.beginTransaction();
        List country = session.createQuery("FROM Country").list();

        transaction.commit();

        return country;
    }

}
