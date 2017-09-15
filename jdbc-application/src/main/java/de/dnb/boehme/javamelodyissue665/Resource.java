package de.dnb.boehme.javamelodyissue665;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@Path("/")
public class Resource {

    private static final Logger logger = Logger.getLogger(Resource.class.getName());

    @GET
    public String get() {
        try {
            final Context ctx = new InitialContext();
            final DataSource datasource = (DataSource) ctx.lookup("java:jboss/datasources/ExampleDS");
            logger.info("Opening connection");
            final Connection connection = datasource.getConnection();
            connection.close();
            logger.info("Closed connection");
        } catch (NamingException e) {
            logger.severe("Failed to retrieve datasource: " + e);
        } catch (SQLException e) {
            logger.severe("Failed to retrieve connection: " + e);
        }
        return "Opened and closed connection to exampleDS";
    }

}
