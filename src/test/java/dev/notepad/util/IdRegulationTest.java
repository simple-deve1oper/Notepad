package dev.notepad.util;

import dev.notepad.action.ActionsNotepad;
import dev.notepad.entity.Notepad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class IdRegulationTest {
    private final IdRegulation idRegulation = new IdRegulation();
    private ActionsNotepad actionsNotepad;
    private Notepad notepad;

    @BeforeEach
    public void init() {
        notepad = new Notepad("Блокнот");
        actionsNotepad = new ActionsNotepad(notepad, "dd LLLL yyyy");
        LocalDate date = LocalDate.of(2025, 8, 8);
        actionsNotepad.addEntry(date, "Заголовок 1", "Текст 1");
    }

    @Test
    @DisplayName("Проверка на то, что идентификаторы увеличиваются придобавлении новой записи за определенную дату")
    public void tryIncrementId() {
        LocalDate date = LocalDate.of(2025, 8, 8);
        Assertions.assertEquals(1, notepad.getEntriesMap().get(date).size());
        Assertions.assertEquals(1, notepad.getEntriesMap().get(date).get(0).getId());
        Assertions.assertEquals("Заголовок 1", notepad.getEntriesMap().get(date).get(0).getTitle());
        Assertions.assertEquals("Текст 1", notepad.getEntriesMap().get(date).get(0).getContent());

        actionsNotepad.addEntry(date, "Текст 2");
        Assertions.assertEquals(2, notepad.getEntriesMap().get(date).size());
        Assertions.assertEquals(2, notepad.getEntriesMap().get(date).get(1).getId());
        Assertions.assertEquals("", notepad.getEntriesMap().get(date).get(1).getTitle());
        Assertions.assertEquals("Текст 2", notepad.getEntriesMap().get(date).get(1).getContent());

        actionsNotepad.addEntry(date, "Заголовок 3", "Текст 3");
        Assertions.assertEquals(3, notepad.getEntriesMap().get(date).size());
        Assertions.assertEquals(3, notepad.getEntriesMap().get(date).get(2).getId());
        Assertions.assertEquals("Заголовок 3", notepad.getEntriesMap().get(date).get(2).getTitle());
        Assertions.assertEquals("Текст 3", notepad.getEntriesMap().get(date).get(2).getContent());

        actionsNotepad.deleteEntry(date, 2);
        Assertions.assertEquals(2, notepad.getEntriesMap().get(date).size());
        Assertions.assertEquals(3, notepad.getEntriesMap().get(date).get(1).getId());
        Assertions.assertEquals("Заголовок 3", notepad.getEntriesMap().get(date).get(1).getTitle());
        Assertions.assertEquals("Текст 3", notepad.getEntriesMap().get(date).get(1).getContent());

        actionsNotepad.addEntry(date, "Заголовок 4", "Текст 4");
        Assertions.assertEquals(3, notepad.getEntriesMap().get(date).size());
        Assertions.assertEquals(4, notepad.getEntriesMap().get(date).get(2).getId());
        Assertions.assertEquals("Заголовок 4", notepad.getEntriesMap().get(date).get(2).getTitle());
        Assertions.assertEquals("Текст 4", notepad.getEntriesMap().get(date).get(2).getContent());
    }
}
