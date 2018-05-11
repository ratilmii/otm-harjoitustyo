package opintovahti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Databases {

    private Connection connection;

    Databases() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        setupDb();
    }

    // Luodaan käytössä oleva tietokanta mikäli sitä ei vielä ole
    
    public void setupDb() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS CurrentPeriod (" +
            "    id integer," +
            "    user_id integer NOT NULL," +
            "    periodNumber integer NOT NULL," +
            "    PRIMARY KEY (id)," +
            "    FOREIGN KEY (user_id) REFERENCES User(id)" +
            ");"
        );
        stmt.executeUpdate();
        stmt.close();

        stmt = connection.prepareStatement(
            "CREATE UNIQUE INDEX IF NOT EXISTS idx_period_user ON CurrentPeriod (user_id);"
        );
        stmt.executeUpdate();
        stmt.close();

        stmt = connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS TimetableEntry (" +
            "    id integer, " +
            "    user_id integer NOT NULL, " +
            "    period_id integer NOT NULL, " +
            "    slot_id integer, " +
            "    description text, " +
            "    PRIMARY KEY (id), " +
            "    FOREIGN KEY (user_id) REFERENCES User(id)" +
            ");"
        );
        stmt.executeUpdate();
        stmt.close();
        stmt = connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS User (" +
            "    `id`    INTEGER," +
            "    `username`    TEXT," +
            "    `hash`    TEXT," +
            "    PRIMARY KEY(`id`)" +
            ");"
        );
        stmt.executeUpdate();
        stmt.close();
        stmt = connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS NotesEntry (" +
            "    id    integer," +
            "    user_id    integer NOT NULL," +
            "    period_id    integer NOT NULL," +
            "    description    text," +
            "    PRIMARY KEY(id)," +
            "    FOREIGN KEY (user_id) REFERENCES User(id)" +         
            ");"
        );
        stmt.executeUpdate();
        stmt.close();
        
    }

    // Uuden käyttäjän tallentaminen tietokantaan
    
    public void createUser(String username, String hash) throws Exception {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User (username, hash) VALUES ('" + username + "', '" + hash + "');");
        stmt.executeUpdate();
        stmt.close();
        Integer id = getUserId(username);
        PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO CurrentPeriod (periodNumber, user_id) VALUES (1, " + id + ");");
        stmt2.executeUpdate();
        stmt2.close();

    }

    // Varmistetaan onko käyttäjä jo olemassa
    
    public boolean checkIfUserExists(String username) throws Exception {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE username = '" + username + "';");
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            stmt.close();

            return true;
        } else {
            stmt.close();

            return false;
        }

    }

    
    public Integer getUserId(String username) throws Exception {
        PreparedStatement stmt = connection.prepareStatement("SELECT id FROM User WHERE username = '" + username + "';");
        ResultSet rs = stmt.executeQuery();

        Integer id = rs.getInt("id");

        stmt.close();

        return id;

    }

    public String getHash(String username) throws Exception {
        PreparedStatement stmt = connection.prepareStatement("SELECT hash FROM User WHERE username = '" + username + "';");
        ResultSet rs = stmt.executeQuery();

        String hash = rs.getString("hash");

        stmt.close();

        return hash;

    }

    // Tarkistetaan tietokannasta mikä periodi tällä hetkellä on auki
    
    public Integer checkCurrentPeriod(Integer userId) throws Exception {
        PreparedStatement stmt =
            connection.prepareStatement("SELECT periodNumber FROM CurrentPeriod, User WHERE " + userId + " = CurrentPeriod.user_id;");
        ResultSet rs = stmt.executeQuery();

        Integer period = rs.getInt("periodNumber");

        stmt.close();
        return period;
    }

    // Haetaan muistiinpanot tässä periodissa
    
    public String getNotesEntry(Integer userId, Integer periodId) throws Exception {
        PreparedStatement stmt =
            connection.prepareStatement("SELECT description FROM NotesEntry "
                                        + "WHERE user_id = ? "
                                        + "AND period_id = ? ");
        stmt.setInt(1, userId);
        stmt.setInt(2, periodId);
        
        ResultSet rs = stmt.executeQuery();
        boolean resultExists = rs.isBeforeFirst();
        String result;
        if (resultExists) {
            result = rs.getString("description");
        } else {
            result = null;
        }
        stmt.close();
        return result;
        
    }
    
    // Tallennetaan muistiinpanot tietokantaan
    
    public void setNotesEntry(Integer userId, Integer periodId, String description) throws Exception {
        boolean notesEntryExists = false;
        if (getNotesEntry(userId, periodId) != null) {
            notesEntryExists = true;
        }

        PreparedStatement stmt;
        if (notesEntryExists) {
            stmt = connection.prepareStatement("UPDATE NotesEntry "
                                               + "SET description=? "
                                               + "WHERE user_id=? AND period_id=?");
            stmt.setString(1, description);
            stmt.setInt(2, userId);
            stmt.setInt(3, periodId);
        } else {
            stmt = connection.prepareStatement("INSERT INTO NotesEntry "
                                               + "(user_id, period_id, description) "
                                               + "VALUES(?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, periodId);
            stmt.setString(3, description);
        }

        stmt.executeUpdate();
        stmt.close();
    }
    
    // Haetaan lukujärjestykseen tallennetut tiedot tässä periodissa
    
    public String getTimetableEntry(Integer userId, Integer slotId, Integer periodId) throws Exception {
        PreparedStatement stmt =
            connection.prepareStatement("SELECT description FROM TimetableEntry "
                                        + "WHERE user_id = ? "
                                        + "AND slot_id = ? "
                                        + "AND period_id = ? ");
        stmt.setInt(1, userId);
        stmt.setInt(2, slotId);
        stmt.setInt(3, periodId);

        ResultSet rs = stmt.executeQuery();
        boolean resultExists = rs.isBeforeFirst();
        String result;
        if (resultExists) {
            result = rs.getString("description");
        } else {
            result = null;
        }
        stmt.close();
        return result;
    }

    // Tallennetaan lukujärjestyksen tiedot tietokantaan
    
    public void setTimetableEntry(Integer userId, Integer slotId, Integer periodId, String description) throws Exception {
        boolean timetableEntryExists = false;
        if (getTimetableEntry(userId, slotId, periodId) != null) {
            timetableEntryExists = true;
        }

        PreparedStatement stmt;
        if (timetableEntryExists) {
            stmt = connection.prepareStatement("UPDATE TimetableEntry "
                                               + "SET description=? "
                                               + "WHERE user_id=? AND period_id=? AND slot_id=?");
            stmt.setString(1, description);
            stmt.setInt(2, userId);
            stmt.setInt(3, periodId);
            stmt.setInt(4, slotId);
        } else {
            stmt = connection.prepareStatement("INSERT INTO TimetableEntry "
                                               + "(user_id, period_id, slot_id, description) "
                                               + "VALUES(?, ?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, periodId);
            stmt.setInt(3, slotId);
            stmt.setString(4, description);
        }

        stmt.executeUpdate();
        stmt.close();
    }

    public String getUsername(Integer userId) throws Exception {
        PreparedStatement stmt = 
            connection.prepareStatement("SELECT username FROM User "
                                        + "WHERE id=?");
        stmt.setInt(1, userId);
        
        ResultSet rs = stmt.executeQuery();
        
        String result = rs.getString("username");
        
        stmt.close();
        return result;
        
    }
    
    // Asettaa nykyisen periodin numeron tietokantaan
    
    public void saveCurrentPeriod(Integer newPeriodNumber, Integer userId) throws Exception {
        PreparedStatement stmt =
            connection.prepareStatement("UPDATE CurrentPeriod SET periodNumber = " + newPeriodNumber + " WHERE user_id = " + userId + ";");
        stmt.executeUpdate();
        stmt.close();
    }
}
