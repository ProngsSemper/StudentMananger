package student.entity;

public class Student {
    private int sno;
    private String sname;
    private int sage;
    private String saddress;
    private String spassword;
    private String simg;
    private int snum;
    private String sgender;

    public Student(String simg) {
        this.simg = simg;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public Student(int sno, String sname, int sage, String saddress, String spassword, String simg, int snum, String sgender) {
        this.sno = sno;
        this.sname = sname;
        this.sage = sage;
        this.saddress = saddress;
        this.spassword = spassword;
        this.simg = simg;
        this.snum = snum;
        this.sgender = sgender;
    }

    public Student(int sno, String sname, int sage, String saddress, String spassword, int snum, String sgender) {
        this.sno = sno;
        this.sname = sname;
        this.sage = sage;
        this.saddress = saddress;
        this.spassword = spassword;
        this.snum = snum;
        this.sgender = sgender;
    }

    public Student(String sname, int sage, String saddress, String spassword, int snum, String sgender) {
        this.sname = sname;
        this.sage = sage;
        this.saddress = saddress;
        this.spassword = spassword;
        this.snum = snum;
        this.sgender = sgender;
    }

    public Student() {
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    public String getSgender() {
        return sgender;
    }

    public void setSgender(String sgender) {
        this.sgender = sgender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", saddress='" + saddress + '\'' +
                ", spassword='" + spassword + '\'' +
                ", simg='" + simg + '\'' +
                ", snum=" + snum +
                ", sgender='" + sgender + '\'' +
                '}';
    }
}
