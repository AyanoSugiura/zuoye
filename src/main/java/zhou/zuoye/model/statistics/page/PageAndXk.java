package zhou.zuoye.model.statistics.page;

import zhou.zuoye.model.StudentCourse;

import java.util.List;

public class PageAndXk {
    List<StudentCourse> studentCourses;
    Integer page ;

    public PageAndXk() {
    }

    public PageAndXk(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public PageAndXk(Integer page) {
        this.page = page;
    }

    public PageAndXk(List<StudentCourse> studentCourses, Integer page) {
        this.studentCourses = studentCourses;
        this.page = page;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
