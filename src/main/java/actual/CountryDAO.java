package actual;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CountryDAO {
    private final MyJDBCTemplate myJDBCTemplate = new MyJDBCTemplate();
    private final JdbcTemplate jdbcTemplate = myJDBCTemplate.getJdbcTemplate();

    public void create(String name, long square, int population, Continent continent) {

        jdbcTemplate.update("INSERT INTO country(name,square,population,continent) VALUES(?,?,?,?)", name, square, population, continent);

    }

    public Country read(int find) {
        Country country = jdbcTemplate.query("SELECT name, square, population, continent FROM country WHERE id = ?;",
                new Object[]{find},
                new CountryMapper()).stream().findAny().orElse(null);

        return country;
    }

    public void update(int find, Country country) {

        jdbcTemplate.update("UPDATE country SET name = ?, square = ?, population = ?, continent = ? WHERE id = ?;",
                country.getName(), country.getSquare(), country.getPopulation(), country.getContinent(), find);

    }

    public void delete(int find) {
        String sql = "delete from country where id = ?;";
        jdbcTemplate.update(sql, find);
    }

    public List<Country> showAll() {
        List<Country> country = jdbcTemplate.query("select * from country", new CountryMapper());
        return country;
    }

}
