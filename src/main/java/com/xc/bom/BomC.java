package com.xc.bom;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lucyf
 * Date: 2018-02-05
 * Time: 17:58
 */
public class BomC {

    private String name;
    private Integer age;
    private Integer score;
    private String grade;

    public BomC() {
    }

    public BomC(String name, Integer age, Integer score, String grade) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
