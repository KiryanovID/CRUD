package actual;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CountryDAO {
    private final MyJDBCTemplate myJDBCTemplate = new MyJDBCTemplate();
    private final JdbcTemplate jdbcTemplate = myJDBCTemplate.getJdbcTemplate();

    public void create(String name, long square, int population, Continent continent) {
        String sql = "INSERT INTO Country(id, name,square,population,continent) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, name, square, population, continent);

    }

    public Country read(int find) {
        String sql = "SELECT name, square, population, continent FROM country WHERE id = ?;";
        Country country = jdbcTemplate.query(sql,
                new Object[]{find},
                new CountryMapper()).stream().findAny().orElse(null);

        return country;
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

    public List<Country> showAll() {
        String sql = "SELECT * FROM country";
        List<Country> country = jdbcTemplate.query(sql, new CountryMapper());
        return country;
    }

}