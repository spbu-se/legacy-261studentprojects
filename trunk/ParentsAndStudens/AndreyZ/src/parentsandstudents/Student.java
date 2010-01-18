/*
 * Class Student;
 * Zubrilin Andrey,261 gr (c) 2009
 */

package parentsandstudents;

public class Student implements IHuman {

    private String surname;
    private String name;
    private String patron;
    private boolean sex;
    private int age;
    private String fac;
    public int examsNum = 5;

    public Student(String surname,String name,String patron,boolean sex,int age,String fac){
        this.fac = fac;
        this.sex = sex;
        this.age = age;
        this.patron = patron;
        this.name = name;
        this.surname = surname;
    }

    //Оценки студента;
    public int examMarks(int id) {
        if ((id < 0) || (id >= examsNum))
            throw new IllegalArgumentException();
        return 3;
    }

    public String Name() {
        return name;
    }

    public String Surname() {
        return surname;
    }

    public String Patron() {
        return patron;
    }

    public boolean Sex() {
        return sex;
    }

    public int Age() {
        return age;
    }

    public int whatTheMark(){
        return 3;
    }

    public String whatTheFac(){
        return fac;
    }
    
    public HumanType hType(){
        return HumanType.Student;
    }
}
