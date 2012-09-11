package models;

import java.util.List;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class HistoryTest extends ModelTest{
    @Test
    public void create() throws Exception {
        String organization = "FORTE 2001 International Conference";
        String position = "Co-chair";
        String kind = History.ACTIVITY;
        History newHistory = new History();
        newHistory.organization = organization;
        newHistory.position = position;
        newHistory.kind = kind;
        
        Long newId = History.create(newHistory);
        
        assertThat(History.findById(newId).organization).isEqualTo("FORTE 2001 International Conference");
    }
    
    @Test
    public void allProfessional() throws Exception {
        List<History> historyList = History.allProfessional();
        assertThat(historyList.size()).isEqualTo(4);
        assertThat(historyList.get(0).position).isEqualTo("Associate Professor");
    }
    
    @Test
    public void allActivities() throws Exception {
        List<History> historyList = History.allActivities();
        assertThat(historyList.size()).isEqualTo(2);
        assertThat(historyList.get(0).position).isEqualTo("Program Chair");
    }
    
    @Test
    public void representitive() throws Exception {
        List<History> historyList = History.representitive();
        assertThat(historyList.size()).isEqualTo(1);
        assertThat(historyList.get(0).id).isEqualTo(2l);
    }
}
