/*
 * Generator of collection of humans.
 * Korshakov Stepan - 261 Group - (c) 2009
 */
package hotheart.parentsandstudens;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
  * @author Korshakov Stepan
 */
public class Generator {

    private static final Random rnd = new Random();
    private static final String[] namesMale = new String[]{
        "Александр", "Степан", "Дситрий", "Илья", "Сергей", "Михаил"
    };
    private static final String[] namesFemale = new String[]{
        "Александра", "Алина", "Марина", "Анастасия", "Катерина", "Аня"
    };
    private static final String[] surnamesMale = new String[]{
        "Коршаков", "Табуреткин", "Семенихин", "Ланцелотов", "Турбофишкин", "Орехов", "Белочкин"
    };
    private static final String[] faculties = new String[]{
        "Математико-Механический", "Задолбайства и сельского хозяйства", "Юридический"
    };

    public static String generateName(Sex sex) {
        if (sex == Sex.FEMALE) {
            return namesFemale[rnd.nextInt(namesFemale.length)];
        } else {
            return namesMale[rnd.nextInt(namesMale.length)];
        }
    }

    public static String generateSurname(Sex sex) {
        if (sex == Sex.FEMALE) {
            return surnamesMale[rnd.nextInt(surnamesMale.length)] + "а";
        } else {
            return surnamesMale[rnd.nextInt(surnamesMale.length)];
        }
    }

    public static String generatePatronymic(String fatherName, Sex sex) {
        if (sex == Sex.FEMALE) {
            return fatherName + "овна";
        } else {
            return fatherName + "ович";
        }
    }

    public static String generateFaculty() {
        return faculties[rnd.nextInt(faculties.length)];
    }

    public static Student generateStudent(String fatherName, String surname) {
        Sex sex = Sex.FEMALE;
        if (rnd.nextBoolean()) {
            sex = Sex.MALE;
        }

        String name = generateName(sex);
        String patronymic = generatePatronymic(fatherName, sex);
        String faculty = generateFaculty();

        int age = 17 + rnd.nextInt(10);

        if (rnd.nextBoolean()) {
            return new Student(name, surname, patronymic, sex, age, faculty);
        } else {
            return new Botan(name, surname, patronymic, sex, age, faculty);
        }
    }

    public static Parent generateParent() {
        Sex sex = Sex.FEMALE;
        if (rnd.nextBoolean()) {
            sex = Sex.MALE;
        }

        String name = generateName(sex);
        String surname = generateSurname(sex);
        String patronymic = generatePatronymic(generateName(Sex.MALE), sex);
        int age = 30 + rnd.nextInt(10);

        int studentsCount = 10 + rnd.nextInt(10);
        Student[] students = new Student[studentsCount];
        for (int i = 0; i < studentsCount; i++) {
            students[i] = generateStudent(name, surname);
        }

        if (rnd.nextBoolean()) {
            return new Parent(name, surname, patronymic, sex, age, students);
        } else {
            return new CoolParent(name, surname, patronymic, sex, age, students);
        }
    }

    public static List<IHuman> generateCollection() {
        LinkedList<IHuman> res = new LinkedList<IHuman>();

        int parentCount = rnd.nextInt(40);
        for (int i = 0; i < parentCount; i++) {
            Parent p = generateParent();
            res.add(p);

            for (int j = 0; j < p.getStudentCount(); j++) {
                res.add(p.getStrudent(j));
            }
        }
        return res;
    }
}