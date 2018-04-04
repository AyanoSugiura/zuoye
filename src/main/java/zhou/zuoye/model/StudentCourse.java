package zhou.zuoye.model;

import javax.persistence.*;

@Entity
@Table(name="student_course")
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sid")
    private User student;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Course course;

    @Column(columnDefinition = "INTEGER DEFAULT 1")
    private Integer verify=1;    //审核状态 0--未通过 1--待审核 2--已通过

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }
}
