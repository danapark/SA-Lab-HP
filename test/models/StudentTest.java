package models;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class StudentTest extends ModelTest {
    @Test
    public void create() throws Exception {
        Student student = new Student();
        student.email = "kakismin@naver.com";
        student.family_name_en = "Ahn";
        student.family_name_ko = "안";
        student.name_en = "Juhee";
        
        Long id = Student.create(student);
        assertThat(Student.findById(id).name_en).isEqualTo("Juhee");
    }

    @Test
    public void findById() throws Exception {
        assertThat(Student.findById(1l).family_name_en).isEqualTo("Ahn");
    }
    
    @Test
    public void update() throws Exception {
        Student student = new Student();
        student.email = "hwi.ahn@gmail.com";
        Student.update(student, 1l);
        assertThat(Student.findById(1l).email).isEqualTo("hwi.ahn@gmail.com");
        assertThat(Student.findById(1l).name_en).isEqualTo("Hwi");
    }

    @Test
    public void findByName() throws Exception {
        assertThat(Student.findByName("Ahn", "Hwi").id).isEqualTo(1l);
    }

    @Test
    public void all() throws Exception {
        assertThat(Student.all().size()).isEqualTo(7);
    }

    @Test
    public void allMaster() {
        List<Student> students = Student.allMaster();
        assertThat(students.size()).isEqualTo(3);
        assertThat(students.get(2).name_en).isEqualTo("Philsu");
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
