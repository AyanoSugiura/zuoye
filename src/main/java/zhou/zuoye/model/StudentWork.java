package zhou.zuoye.model;

import javax.persistence.*;

@Entity
@Table(name="student_work")
public class StudentWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "sid")
    private User student;

    @ManyToOne
    @JoinColumn(name = "tsk_id")
    private Tassk tassk ;

    @Column(columnDefinition = "INTEGER DEFAULT 2")
    private Integer verify=2;    //审核状态 0--未通过 1--待审核 2--已通过
    @Column(columnDefinition = "INTEGER DEFAULT 1")
    private Integer usable=1;    //可用状态 1--可用 0--不可用

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer isPg=0;    //可用状态 0--未批改 1--已批改

    private String comment;

    private String score;

    private String files_links;

    private String pg_files_links;

    private String files_names;

    @Transient
    private String subStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Tassk getTassk() {
        return tassk;
    }

    public void setTassk(Tassk tassk) {
        this.tassk = tassk;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Integer getUsable() {
        return usable;
    }

    public Integer getIsPg() {
        return isPg;
    }

    public void setIsPg(Integer isPg) {
        this.isPg = isPg;
    }

    public void setUsable(Integer usable) {
        this.usable = usable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScore() {
        return score;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFiles_links() {
        return files_links;
    }

    public String getPg_files_links() {
        return pg_files_links;
    }

    public void setPg_files_links(String pg_files_links) {
        this.pg_files_links = pg_files_links;
    }

    public void setFiles_links(String files_links) {
        this.files_links = files_links;
    }

    public String getFiles_names() {
        return files_names;
    }

    public void setFiles_names(String files_names) {
        this.files_names = files_names;
    }

    public StudentWork(Integer id) {
        this.id = id;
    }

    public StudentWork(String content, User student, Tassk tassk, String files_links) {
        this.content = content;
        this.student = student;
        this.tassk = tassk;
        this.files_links = files_links;
    }

    public StudentWork() { }
}
