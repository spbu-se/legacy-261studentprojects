package database.generator;

import java.util.Random;
import utils.Util;
import dbentities.Sex;

/**
 * Предоставляет методы для генерации случайного отчества
 *
 * @author nastya
 * Date: 20.08.2009
 * Time: 20:49:51
 */
public class RandomMiddleNameGenerator {
    private static String[] maleMiddleNames = {"Иванович", "Степанович", "Львович", "Сидорович", "Константинович", "Федорович",
            "Викторович", "Максимович", "Сергеевич", "Игоревич", "Кириллович", "Светогорич", "Фердинандович", "Азатович", "Борисович",
            "Алексеевич", "Алекснадрович", "Евгеньевич", "Петрович", "Романович", "Аркадьевич", "Каримович", "Бедросович",
            "Филиппович", "Аристархович", "Адольфович", "Иосифич", "Пантелеймонович", "Лаврентьевич", "Еремеевич", "Вадимович",
            "Владимирович", "Святославович", "Олегович", "Артемович"};
    private static String[] femaleMiddleNames = {"Ивановна", "Степановна", "Львовна", "Сидоровна", "Константиновна", "Федоровна",
            "Викторовна", "Максимовна", "Сергеевна", "Игоревна", "Кирилловна", "Светогорна", "Фердинандовна", "Азатовна", "Борисовна",
            "Алексеевна", "Алекснадровна", "Евгеньевна", "Петровна", "Романовна", "Аркадьевна", "Каримовна", "Бедросовна",
            "Филипповна", "Аристарховна", "Адольфовна", "Иосифна", "Пантелеймоновна", "Лаврентьевна", "Еремеевна", "Вадимовна",
            "Владимировна", "Святославовна", "Олеговна", "Артемовна"};

    /**
     * Генерирует случайное отчество согласно выбранному полу
     * @param sex пол, для которого генерируется отчество
     * @return отчество
     */
    public static String generate(Sex sex) {
        String result = "";
        Random r = new Random();
        switch (sex) {
            case FEMALE:
                result = femaleMiddleNames[(int)(r.nextFloat() * (femaleMiddleNames.length - 1))];
                break;
            case MALE:
                result = maleMiddleNames[(int)(r.nextFloat() * (maleMiddleNames.length - 1))];
                break;
            case UNKNOWN:
                result = maleMiddleNames[(int)(r.nextFloat() * (maleMiddleNames.length - 1))];
                break;
        }

        return result;
    }

    /**
     * Максимальная длина отчестве среди всех имеющихся. Используется для генерации базы
     * @return максимальная длина поля отчества
     */
    public static int maxLength(){
        int maleLength = Util.maxElement(maleMiddleNames);
        int femaleLength = Util.maxElement(femaleMiddleNames);
        if(maleLength > femaleLength) return maleLength;
        return femaleLength;
    }


}
