package models;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

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
        assertThat(Student.all().size()).isEqualTo(6);
    }
    
    @Test
    public void allMaster() {
        List<Student> students = Student.allMaster();
        assertThat(students.size()).isEqualTo(2);
        assertThat(students.get(0).name_en).isEqualTo("Jungmin");
    }
    
    @Test
    public void allPhD() {
        List<Student> students = Student.allPhD();
        assertThat(students.size()).isEqualTo(2);
        assertThat(students.get(0).name_en).isEqualTo("Hwi");
    }
    
    @Test
    public void allAlumni() {
        List<Student> students = Student.allAlumni();
        assertThat(students.size()).isEqualTo(2);
        assertThat(students.get(0).name_en).isEqualTo("Seokhwan");
    }
}
