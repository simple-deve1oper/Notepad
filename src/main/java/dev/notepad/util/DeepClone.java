package dev.notepad.util;

import dev.notepad.entity.Notepad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для описания методов по глубокому копированию структур данных
 * @version 1.0
 */
public class DeepClone {
    /**
     * Метод для глубокого клонирования карты (отображения)
     * @param entriesTempMap - исходная карта (отображение) с датами и записями
     * @return возвращает копию карты (отображения) с датами и записями
     */
    public final Map<LocalDate, List<Notepad.Entry>> cloneMap(Map<LocalDate, List<Notepad.Entry>> entriesTempMap) {
        Map<LocalDate, List<Notepad.Entry>> copy = new HashMap<>();
        for (Map.Entry<LocalDate, List<Notepad.Entry>> entry : entriesTempMap.entrySet()) {
            copy.put(entry.getKey(), cloneList(entry.getValue()));
        }
        return copy;
    }

    /**
     * Метод для глубокого клонирования списка
     * @param entriesListFromMap - исходный список с записями
     * @return возвращает копию списка с записями
     */
    public final List<Notepad.Entry> cloneList(List<Notepad.Entry> entriesListFromMap) {
        List<Notepad.Entry> clone = new ArrayList<>(entriesListFromMap.size());
        for (Notepad.Entry entry : entriesListFromMap) {
            try {
                clone.add(entry.clone());
            } catch (CloneNotSupportedException ex) {
                System.err.println("Ошибка клонирования объекта типа Notepad.Entry: " + ex.getMessage());
            }
        }
        return clone;
    }
}
