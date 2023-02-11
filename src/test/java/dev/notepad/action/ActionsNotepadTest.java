package dev.notepad.action;

import dev.notepad.entity.Notepad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ActionsNotepadTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private ActionsNotepad actionsNotepad;

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outputStreamCaptor));

        Notepad simpleNotepad = new Notepad("Простой блокнот");
        actionsNotepad = new ActionsNotepad(simpleNotepad, "dd LLLL yyyy");
    }

    @Test
    @DisplayName("Добавление четырёх записей и проверка на то, что они добавлены")
    public void addFourEntries() {
        LocalDate date1 = LocalDate.of(2010, 10, 23);
        actionsNotepad.addEntry(date1, "Заголовок 1", "Текст 1");
        LocalDate date2 = LocalDate.of(2015, 5, 3);
        actionsNotepad.addEntry(date2, "Текст 2");
        actionsNotepad.addEntry("Заголовок 3", "Текст 3");
        actionsNotepad.addEntry("Текст 4");

        Map<LocalDate, List<Notepad.Entry>> map = actionsNotepad.getNotepad().getEntriesMap();
        int size = map.get(date1).size();
        Assertions.assertEquals(1, size);
        size = map.get(date2).size();
        Assertions.assertEquals(1, size);
        LocalDate date3 = LocalDate.now();
        size = map.get(date3).size();
        Assertions.assertEquals(2, size);
    }

    @Test
    @DisplayName("Удаление одной записи за определенную дату")
    public void tryDeleteEntry() {
        LocalDate date = LocalDate.of(2015, 5, 3);
        actionsNotepad.addEntry(date, "Заголовок 1", "Текст 1");
        actionsNotepad.addEntry(date, "Текст 2");
        actionsNotepad.addEntry(date, "Заголовок 3", "Текст 3");
        actionsNotepad.addEntry(date, "Текст 4");
        Map<LocalDate, List<Notepad.Entry>> map = actionsNotepad.getNotepad().getEntriesMap();
        int size = map.get(date).size();
        Assertions.assertEquals(4, size);
        actionsNotepad.deleteEntry(date, 2);
        map = actionsNotepad.getNotepad().getEntriesMap();
        size = map.get(date).size();
        Assertions.assertEquals(3, size);
    }
}
