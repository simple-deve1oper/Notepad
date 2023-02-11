package dev.notepad.action;

import dev.notepad.entity.Notepad;
import dev.notepad.util.FormattingInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс для описания действий с текстом в блокноте
 * @version 1.0
 */
public class ActionsNotepad {
    /* Объект блокнота */
    private Notepad notepad;
    /* Объект для форматирования даты по заданному паттерну */
    private DateTimeFormatter formatter;

    /**
     * Конструктор для создания нового объекта типа Notepad
     * @param notepad - объект блокнота
     * @param patternDate - паттерн для форматирования даты
     */
    public ActionsNotepad(Notepad notepad, String patternDate) {
        this.notepad = notepad;
        this.formatter = DateTimeFormatter.ofPattern(patternDate);
    }

    /**
     * Метод по добавлению записи в блокнот за определенную дату
     * @param date - объект даты
     * @param title - заголовок
     * @param content - контент
     */
    public void addEntry(LocalDate date, String title, String content) {
        Map<LocalDate, List<Notepad.Entry>> map = notepad.getEntriesMap();
        List<Notepad.Entry> list = notepad.getEntriesMap().get(date);
        Notepad.Entry entry = notepad.new Entry(date, title, content);
        if (list == null) {
            List<Notepad.Entry> entries = Arrays.asList(entry);
            map.put(date, entries);
        } else {
            map.get(date).add(entry);
        }
        notepad.setEntriesMap(map);

        String formattedDate = date.format(formatter);

        System.out.println("Запись добавлена к дате " + formattedDate + " с идентификатором " + entry.getId());
    }

    /**
     * Перегруженный метод по добавлению записи в блокнот за определенную дату
     * @param date - объект даты
     * @param content - контент
     * @see ActionsNotepad#addEntry(LocalDate, String, String)
     */
    public void addEntry(LocalDate date, String content) {
        Map<LocalDate, List<Notepad.Entry>> map = notepad.getEntriesMap();
        List<Notepad.Entry> list = notepad.getEntriesMap().get(date);
        Notepad.Entry entry = notepad.new Entry(date, content);
        if (list == null) {
            List<Notepad.Entry> entries = Arrays.asList(entry);
            map.put(date, entries);
        } else {
            map.get(date).add(entry);
        }
        notepad.setEntriesMap(map);

        String formattedDate = date.format(formatter);

        System.out.println("Запись добавлена к дате " + formattedDate + " с идентификатором " + entry.getId());
    }

    /**
     * Перегруженный метод по добавлению записи в блокнот за определенную дату
     * @param title - заголовок записи
     * @param content - контент
     * @see ActionsNotepad#addEntry(LocalDate, String, String)
     */
    public void addEntry(String title, String content) {
        LocalDate date = LocalDate.now();
        addEntry(date, title, content);
    }

    /**
     * Перегруженный метод по добавлению записи в блокнот за определенную дату
     * @param content - контент
     * @see ActionsNotepad#addEntry(LocalDate, String, String)
     */
    public void addEntry(String content) {
        LocalDate date = LocalDate.now();
        addEntry(date, content);
    }

    /**
     * Метод для удаления записи по его id и за конкретную дату
     * @param date - объект даты
     * @param id - идентификатор
     * @return возвращает логическое значение о том, удалён ли объект с записью или нет
     */
    public boolean deleteEntry(LocalDate date, long id) {
        String formattedDate = date.format(formatter);

        Map<LocalDate, List<Notepad.Entry>> map = notepad.getEntriesMap();
        List<Notepad.Entry> list = map.get(date);
        if (list == null) {
            System.out.println("За данную дату записи не найдены");
            return false;
        } else {
            Optional<Notepad.Entry> optionalEntry = getEntryById(id, list);
            if (optionalEntry.isPresent()) {
                Notepad.Entry entry = optionalEntry.get();
                map.get(date).remove(entry);
                notepad.setEntriesMap(map);
                System.out.println("Запись с id " + id + " удалена за дату " + formattedDate);
                return true;
            } else {
                System.out.println("Запись с id " + id + " не найдена");
                return false;
            }
        }
    }

    /**
     * Печать информации о записи
     * @param date - объект даты
     * @param id - идентификатор
     */
    public void printEntryByDateAndId(LocalDate date, long id) {
        FormattingInfo info = new FormattingInfo();

        String formattedDate = date.format(formatter);

        Map<LocalDate, List<Notepad.Entry>> map = notepad.getEntriesMap();
        List<Notepad.Entry> list = map.get(date);
        Optional<Notepad.Entry> optionalEntry = getEntryById(id, list);
        if (optionalEntry.isPresent()) {
            Notepad.Entry entry = optionalEntry.get();

            final StringBuilder sb = new StringBuilder();
            sb.append(info.getTextDate(formattedDate));
            sb.append(info.getTextEntry(entry));
            System.out.println(sb.toString());
        } else {
            System.out.println("Записи по дате " + date + " и id " + id + " в блокноте не найдено");
        }
    }

    /**
     * Печать информации о списке записей
     * @param date - объект даты
     */
    public void printListEntriesByDate(LocalDate date) {
        FormattingInfo info = new FormattingInfo();

        String formattedDate = date.format(formatter);

        Map<LocalDate, List<Notepad.Entry>> map = notepad.getEntriesMap();
        Optional<List<Notepad.Entry>> optionalEntries = getListEntries(date);
        if (optionalEntries.isPresent()) {
            List<Notepad.Entry> entries = optionalEntries.get();

            final StringBuilder sb = new StringBuilder();
            sb.append(info.getTextDate(formattedDate));
            for (Notepad.Entry entry : entries) {
                sb.append(info.getTextEntry(entry));
            }
            System.out.println(sb.toString());
        } else {
            System.out.println("Записей по дате " + date + " в блокноте не найдено");
        }
    }

    /**
     * Метод для возвращения записи по его id
     * @param id - идентификатор
     * @param entries - список записей
     * @return возвращает класс оболочку с записью
     */
    private Optional<Notepad.Entry> getEntryById(long id, List<Notepad.Entry> entries) {
        for (Notepad.Entry entry : entries) {
            if (entry.getId() == id) {
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }

    /**
     * Метод для возвращения списка записей по дате
     * @param date - объект даты
     * @return возвращает класс оболочку со списком записей
     */
    private Optional<List<Notepad.Entry>> getListEntries(LocalDate date) {
        Map<LocalDate, List<Notepad.Entry>> map = notepad.getEntriesMap();
        List<Notepad.Entry> list = map.get(date);
        if (list == null) {
            return Optional.empty();
        } else {
            return Optional.of(list);
        }

    }

    public Notepad getNotepad() {
        return notepad;
    }
    public void setNotepad(Notepad notepad) {
        this.notepad = notepad;
    }
}
