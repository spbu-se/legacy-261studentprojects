package database.generator;

import java.util.Random;
import utils.Util;
import dbentities.Sex;

/**
 * Предоставляет методы для генерации случайного имени
 *
 * @author nastya
 * Date: 20.08.2009
 * Time: 20:23:29
 *
 */
public class RandomNameGenerator {
    private static String[] maleNames = {"Александр", "Евгений", "Никита", "Константин", "Аркадий", "Виктор", "Сергей",
            "Вадим", "Евсей", "Пантелеймон", "Авдотий", "Акакий", "Лаврентий", "Андрей", "Елисей", "Иоан",
            "Азат", "Владимир", "Дмитрий", "Федор", "Валерий", "Аристарх", "Максим", "Олесь", "Владислав",
            "Прокопий", "Протопоп", "Терентий", "Николай", "Макар", "Герасим", "Лев", "Юрий", "Михаил",
            "Геннадий", "Артем", "Павел", "Даниил", "Данила", "Фердинанд", "Эдварт", "Хуасе", "Эльадр", "Хуанитто",
            "Роман", "Патрик", "Сквидвард", "Спанч Боб", "Остап", "Бендер", "Барт", "Маркус", "Кайл", "Кенни", "Марчелло", "Мавродий", "Евлампий"};

    private static String[] femaleNames = {"Анастасия", "Светлана", "Ольга", "Регина", "Барбара", "Варвара", "Белла",
            "Ружена", "Мария", "Милена", "Милана", "Евгения", "Татьяна", "Екатерина", "Вера", "Татьяна", "Клавдия", "Фаина",
            "Евдокия", "Клавдия", "Глафира", "Параша", "Надежда", "Кристина", "Анна", "Алена", "Елена", "Алла",
            "Кира", "Ярослова", "Василиса", "Ника", "Варвара", "Алина", "Алеся", "Олеся", "Маргарита",
            "Ксения", "Джамиля", "Джорджия", "Снежана", "Мардж", "Евлампия", "Анфиса"};

    /**
     * Генерирует случайное имя согласно выбранному полу
     * @param sex пол, для которого генерируется имя
     * @return имя
     */
    public static String generate(Sex sex) {
        String result = "";
        Random r = new Random();
        switch (sex) {
            case FEMALE:
                result = femaleNames[(int)(r.nextFloat() * (femaleNames.length - 1))];
                break;
            case MALE:
                result = maleNames[(int)(r.nextFloat() * (maleNames.length - 1))];
                break;
            case UNKNOWN:
                result = maleNames[(int)(r.nextFloat() * (maleNames.length - 1))];
                break;
        }
        return result;
    }

    /**
     * Максимальная длина имени среди всех имеющихся. Используется для генерации базы
     * @return максимальная длина поля имени
     */
    public static int maxLength(){
        int maleLength = Util.maxElement(maleNames);
        int femaleLength = Util.maxElement(femaleNames);
        if(maleLength > femaleLength) return maleLength;
        return femaleLength;
    }

}