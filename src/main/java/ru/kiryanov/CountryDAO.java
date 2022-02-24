package ru.kiryanov;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CountryDAO {

    private Session session = HibernateSession.getSessionFactory().openSession();
    private Transaction transaction;

    public void create(String name, long square, int population, Continent continent) {
        transaction = session.beginTransaction();
        session.save(new Country(name, square, population, continent));
        transaction.commit();

    }

    public Country read(long find) {
        Country country = session.get(Country.class, find);

        return country;
    }

    public void update(long find, String name, long square, int population, Continent continent) {
        transaction = session.beginTransaction();
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
