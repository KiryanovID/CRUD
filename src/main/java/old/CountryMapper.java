package old;

import ru.kiryanov.Continent;
import ru.kiryanov.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();

        country.setName(rs.getString("name"));
        country.setSquare(rs.getLong("square"));
        country.setPopulation(rs.getInt("population"));
        country.setContinent(Continent.valueOf(rs.getString("continent")));

        return country;
    }
}
