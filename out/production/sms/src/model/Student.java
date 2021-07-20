package model;

public class Student {
    private String sn;
    private String name;
    private String sex;
    private String birthday;
    private String grade;
    private String stuClass;
    private int math;
    private int english;
    private int sports;
    private int java;
    private int totalScores;

    public Student(String sn, String name, String sex, String birthday, String grade, String stuClass, int math, int english, int sports, int java, int totalScores) {
        this.sn = sn;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.grade = grade;
        this.stuClass = stuClass;
        this.math = math;
        this.english = english;
        this.sports = sports;
        this.java = java;
        this.totalScores = totalScores;
    }

    public Student() {

    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getSports() {
        return sports;
    }

    public void setSports(int sports) {
        this.sports = sports;
    }

    public int getJava() {
        return java;
    }

    public void setJava(int java) {
        this.java = java;
    }

    public int getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(int totalScores) {
        this.totalScores = totalScores;
    }

    public void addInformation() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", grade='" + grade + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", math=" + math +
                ", english=" + english +
                ", sports=" + sports +
                ", java=" + java +
                ", average=" + totalScores +
                '}';
    }
}
