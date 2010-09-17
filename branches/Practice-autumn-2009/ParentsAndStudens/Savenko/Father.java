/*
 * Class for common Father
 * Savenko Maria (c)2009
 */

package msavenko.parentsandstudens;

public class Father extends Human implements IFather{
    
    protected Student[] students;
    
    public Father(String Name, String Surname, String Patronymic, Sex Sex, int Age, Student[] Children) {
        name = Name;
        surname = Surname;
        patronymic = Patronymic;
        sex = Sex;
        age = Age;
        students = Children;
    }
    
    @Override
    public int getStudentCount() { return students.length; }
    
    @Override
    public Student getStudent(int index) { return students[index]; }
}