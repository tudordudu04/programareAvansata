import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {


        try {
            CountryDAO countryDAO = new CountryDAO();
            ContinentDAO continentDAO = new ContinentDAO();
            Database.getConnection().commit();

            String name = continentDAO.findById(3);
            System.out.println(name);

            Database.getConnection().close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Database.rollback();
        }

    }
}
