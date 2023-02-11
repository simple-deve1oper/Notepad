package dev.notepad;

import dev.notepad.action.ActionsNotepad;
import dev.notepad.entity.Notepad;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Главный класс для запуска приложения
 * @version 1.0
 */
public class NotepadRun {
    public static void main(String[] args) {
        Notepad simpleNotepad = new Notepad("Обычный блокнот");
        System.out.println("Наименование блокнота: " + simpleNotepad.getName());
        System.out.println();
        ActionsNotepad actionsNotepad = new ActionsNotepad(simpleNotepad, "dd.MM.yyyy");
        System.out.println("Добавление записей в " + simpleNotepad.getName() + ":");
        LocalDate date1 = LocalDate.of(2022, 6, 6);
        actionsNotepad.addEntry(date1, "Заголовок 1", "Текст 1");
        actionsNotepad.addEntry(date1, "Заголовок 2", "Текст 2");
        actionsNotepad.addEntry(date1, "Текст 3");

        LocalDate date2 = LocalDate.of(2025, Month.JANUARY, 10);
        actionsNotepad.addEntry(date2, "Текст 1");
        actionsNotepad.addEntry(date2, "Текст 2");

        actionsNotepad.addEntry("Заголовок 1", "Текст 1");
        actionsNotepad.addEntry("Текст 2");
        System.out.println();

        System.out.println("Вывод нескольких записей за определенную дату:");
        actionsNotepad.printListEntriesByDate(date1);
        actionsNotepad.printListEntriesByDate(date2);
        LocalDate dateNow = LocalDate.now();
        actionsNotepad.printListEntriesByDate(dateNow);

        System.out.println("Удаление записи за дату " + date2 + " с идентификатором 2:");
        actionsNotepad.deleteEntry(date1, 2);
        actionsNotepad.printListEntriesByDate(date1);

        String formattedDateNow = dateNow.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println("Вывод записи с идентификатором 1 за дату " + formattedDateNow + ":");
        actionsNotepad.printEntryByDateAndId(dateNow, 1);
    }
}
