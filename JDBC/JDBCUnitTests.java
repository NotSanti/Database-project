package JDBC;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JDBCUnitTests {
    @Test
    void testAuditLog() {
        Date newDate = new Date(0);
        AuditLog auditlog = new AuditLog("A0001","newuser", newDate, "information");
        assertEquals("A0001", auditlog.getAuditId());
        assertEquals("newuser", auditlog.getUsername());
        assertEquals(newDate, auditlog.getDate());
        assertEquals("information", auditlog.getInfo());
    }
    @Test
    void testComponents() {
        List<Song> songs = new ArrayList<>();
        Components components = new Components("thisid", "A12345", 52, 23, "3242", 2, 5, songs);
        assertEquals("thisid", components.getComponentId());
        assertEquals("A12345", components.getSongId());
        assertEquals(52, components.getOffsetComponent());
        assertEquals(23, components.getDurationComponent());
        assertEquals("3242", components.getComponentNum());
        assertEquals(2, components.getOffsetSong());
        assertEquals(5, components.getDurationSong());
    }
    @Test
    void testContributors() {
        Contributors contributors = new Contributors("A5678", "Joe Schmoe");
        assertEquals("A5678", contributors.getContributorId());
        assertEquals("Joe Schmoe", contributors.getName());
    }
    @Test
    void testDistribution() {
        Date thisdate = new Date(0);
        List<Song> songs = new ArrayList<>();
        Distribution distribution = new Distribution("B12345", "Z2234","M123", thisdate, "Never Gonna Give You Up", songs);
        assertEquals("B12345", distribution.getSongId());
        assertEquals("Z2234", distribution.getRecordLabelId());
        assertEquals(thisdate, distribution.getReleaseDate());
        assertEquals("M123", distribution.getMarketId());
        assertEquals("Never Gonna Give You Up", distribution.getSongTitle());
    }
    @Test
    void testMarkets() {
        List<Distribution> distributions = new ArrayList<>();
        Markets markets = new Markets("M2345", "Michigan", distributions);
        assertEquals("M2345", markets.getMarketId());
        assertEquals("Michigan", markets.getArea());
    }
    @Test
    void testRecordLabel() {
        List<Distribution> distributions = new ArrayList<>();
        RecordLabel recordlabel = new RecordLabel("R123", "Sony Music", distributions);
        assertEquals("R123", recordlabel.getRecordLabelId());
        assertEquals("Sony Music", recordlabel.getName());
    }
    @Test
    void testRoles() {
        Roles roles = new Roles("RO123", "Audio Engineer");
        assertEquals("RO123", roles.getRoleId());
        assertEquals("Audio Engineer", roles.getRoleName());
    }
    @Test
    void testSong() {
        List<Contributors> contributors = new ArrayList<Contributors>();
        List<Roles> roles = new ArrayList<Roles>();
        List<Components> components = new ArrayList<Components>();
        Date thisdate = new Date(0);
        Song song = new Song("S123", thisdate, 100, contributors, roles, components);
        assertEquals("S123", song.getSongId());
        assertEquals(100, song.getDuration());
        assertEquals(contributors, song.getContributors());
        assertEquals(roles, song.getRoles());
        assertEquals(components, song.getComponents());
    }
}
