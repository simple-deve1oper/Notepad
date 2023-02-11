package dev.notepad.util;

import dev.notepad.entity.Notepad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeepCloneTest {
    private final Notepad superNotepad = new Notepad("Супер блокнот");
    private final DeepClone deepClone = new DeepClone();
    private Map<LocalDate, List<Notepad.Entry>> entriesMap;

    @BeforeEach
    public void init() {
        entriesMap = new HashMap<>();
        List<Notepad.Entry> list = new ArrayList<>();
        LocalDate date = LocalDate.of(2018, 8, 8);
        list.add(superNotepad.new Entry(date, "Текст 1"));
        list.add(superNotepad.new Entry(date, "Заголовок 2", "Текст 2"));
        list.add(superNotepad.new Entry(date, "Текст 3"));
        entriesMap.put(date, list);
        list = new ArrayList<>();
        date = LocalDate.now();
        list.add(superNotepad.new Entry(date, "Текст 6"));
        list.add(superNotepad.new Entry(date, "Заголовок 7", "Текст 7"));
        list.add(superNotepad.new Entry(date, "Текст 8"));
        entriesMap.put(date, list);
    }

    @Test
    @DisplayName("Проверка на то, что карта скопируется и измененная копия не изменит оригинальную карту")
    public void tryChangeMap() {
        Map<LocalDate, List<Notepad.Entry>> copy = deepClone.cloneMap(entriesMap);
        Assertions.assertTrue(entriesMap.equals(copy));
        LocalDate newDate = LocalDate.of(2031, 6, 6);
        copy.put(newDate, new ArrayList<Notepad.Entry>());
        Assertions.assertEquals(0, copy.get(newDate).size());
        Assertions.assertNull(entriesMap.get(newDate));
        Assertions.assertNotNull(copy.get(newDate));
    }
}
