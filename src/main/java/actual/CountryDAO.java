package actual;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.util.List;

public class CountryDAO {
    private final MyJDBCTemplate myJDBCTemplate = new MyJDBCTemplate();
    private final JdbcTemplate jdbcTemplate = myJDBCTemplate.getJdbcTemplate();
    Session session = HibernateSession.getSessionFactory().openSession();


//    public void create(String name, long square, int population, Continent continent) {
//        String sql = "INSERT INTO Country(id, name,square,population,continent) VALUES(?,?,?,?,?)";
//        jdbcTemplate.update(sql, name, square, population, continent);
//
//    }
    public void create(String name, long square, int population, Continent continent){
        Transaction transaction = session.beginTransaction();
        session.save(new  Country(name,square,population,continent));
        transaction.commit();
    }

//    public Country read(int find) {
//        String sql = "SELECT name, square, population, continent FROM country WHERE id = ?;";
//        Country country = jdbcTemplate.query(sql,
//                new Object[]{find},
//                new CountryMapper()).stream().findAny().orElse(null);
//
//        return country;
//    }
    public Country read(long find){
        return HibernateSession.getSessionFactory().openSession().get(Country.class, find);
    }

    public void update(int find, Country country) {
        String sql = "UPDATE country SET name = ?, square = ?, population = ?, continent = ? WHERE id = ?;";
        jdbcTemplate.update(sql,
                country.getName(), country.getSquare(), country.getPopulation(), country.getContinent(), find);

    }

    public void delete(int find) {
        String sql = "delete from country where id = ?;";
        jdbcTemplate.update(sql, find);
    }

//    public List<Country> showAll() {
//        String sql = "SELECT * FROM country";
//        List<Country> country = jdbcTemplate.query(sql, new CountryMapper());
//        return country;
//    }
    public List<Country> showAll() {
        EntityManager entityManager = HibernateSession.getSessionFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> list = (List<Country>) entityManager.createQuery("FROM Country ").getResultList();
        return list;
    }

}
