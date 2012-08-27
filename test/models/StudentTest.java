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
    public void findByName() throws Exception {
        assertThat(Student.findByName("Ahn", "Hwi").id).isEqualTo(1l);
    }

    @Test
    public void all() throws Exception {
        assertThat(Student.all().size()).isEqualTo(7);
    }

    @Test
    public void allMaster() throws Exception {
        List<Student> students = Student.allMaster();
        assertThat(students.size()).isEqualTo(3);
        assertThat(students.get(2).name_en).isEqualTo("Philsu");
        assertThat(students.get(2).researchAreas.get(0).id).isEqualTo(1l);
    }

    @Test
    public void allPhD() throws Exception {
        List<Student> students = Student.allPhD();
        assertThat(students.size()).isEqualTo(2);
        assertThat(students.get(0).name_en).isEqualTo("Hwi");
        assertThat(students.get(0).researchAreas.get(0).id).isEqualTo(2l);
    }
    
    @Test
    public void allStudents() throws Exception {
        List<Student> students = Student.allStudents();
        assertThat(students.size()).isEqualTo(5);
        assertThat(students.get(0).family_name_en).isEqualTo("Ahn");
    }

    @Test
    public void allAlumni() throws Exception {
        List<Student> students = Student.allAlumni();
        assertThat(students.size()).isEqualTo(2);
        assertThat(students.get(0).name_en).isEqualTo("Seokhwan");
        assertThat(students.get(0).researchAreas.get(0).id).isEqualTo(1l);
    }
    
    @Test
    public void allAlumniByYear() throws Exception {
        assertThat(Student.allAlumniByYear(2011).size()).isEqualTo(1);
    }
    
    @Test
    public void deleteResearchArea() throws Exception {
        Student.deleteResearchArea(1l, 2l);
        List<ResearchArea> researchAreas = Student.findByIdWithResearch(1l).researchAreas; 
        assertThat(researchAreas.size()).isEqualTo(2);
    }
}
