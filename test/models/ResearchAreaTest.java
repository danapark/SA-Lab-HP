package models;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ResearchAreaTest extends ModelTest{
    @Test
    public void create() throws Exception {
        boolean create1 = ResearchArea.create("Sigma Process");
        boolean create2 = ResearchArea.create("Software Product Line Testing");
        
        assertThat(create1).isEqualTo(true);
        assertThat(ResearchArea.findByName("Sigma Process").id).isEqualTo(13l);
        assertThat(create2).isEqualTo(false);
    }
    
    @Test
    public void update() throws Exception {
        ResearchArea researchArea = new ResearchArea();
        researchArea.name = "Software Testing Process";
        ResearchArea.update(researchArea, ResearchArea.findByName("Software Testing").id);
        
        assertThat(ResearchArea.findById(7l).name).isEqualTo("Software Testing Process");
    }
    
    @Test
    public void alreadyAdded() throws Exception {
        String name = "Software Product Line";
        Long userId = 1l;
        assertThat(ResearchArea.alreadyAdded(userId, name)).isEqualTo(true);
    }
}
