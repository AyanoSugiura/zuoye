package zhou.zuoye.model;

import javax.persistence.*;

/*@Entity
@Table(name="work_file")*/
public class StudentWorkFile {

 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "stuw_id")
    private StudentWork studentWork;

    @ManyToOne
    @JoinColumn(name = "fid")
    private ConFile file;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer isPg=0;    //可用状态 0--学生附件 1--批改附件

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StudentWork getStudentWork() {
        return studentWork;
    }

    public void setStudentWork(StudentWork studentWork) {
        this.studentWork = studentWork;
    }

    public ConFile getFile() {
        return file;
    }

    public void setFile(ConFile file) {
        this.file = file;
    }

    public Integer getIsPg() {
        return isPg;
    }

    public void setIsPg(Integer isPg) {
        this.isPg = isPg;
    }*/


}
