package models;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class StudentTest extends ModelTest {
    @Test
    public void findById() throws Exception {
        assertThat(Student.findById(1l).family_name_en).isEqualTo("Ahn");
    }
    
    @Test
    public void findByName() throws Exception {
        assertThat(Student.findByName("Ahn", "Hwi").id).isEqualTo(1l);
    }
    
    @Test
    public void all() throws Exception {
        assertThat(Student.all().size()).isEqualTo(3);
    }
}
