package dev.notepad.util;

import dev.notepad.entity.Notepad;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Класс для описания методов по регулированию идентификаторов для их автоматической установки записи
 * @version 1.0
 */
public class IdRegulation {
    /**
     * Метод для получения доступного id для новой записи на конкретную дату
     * @param date - дата
     * @param entries - список уже имеющихся (или нет) записей
     * @return возвращает доступный id для новой записи
     */
    public long getIdForNewEntry(LocalDate date, Map<LocalDate, List<Notepad.Entry>> entries) {
        long id;
        List<Notepad.Entry> entryList = entries.get(date);
        if (entryList != null) {
            int lastIndexElement = entryList.size() - 1;
            id = entryList.get(lastIndexElement).getId() + 1;
        } else {
            id = 1;
        }
        return id;
    }
}
