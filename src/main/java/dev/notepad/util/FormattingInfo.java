package dev.notepad.util;

import dev.notepad.entity.Notepad;

/**
 * Класс для описания методов по выводу информации о записях с датой
 * @version 1.0
 */
public class FormattingInfo {
    /**
     * Метод для форматирования строки с датой для вывода
     * @param date - объект даты
     * @return возвращает отформатированную строку с датой
     */
    public final String getTextDate(String date) {
        String str = "Дата: " + date + "\n-------------------\n";
        return str;
    }

    /**
     * Метод для форматирования строки с записью для вывода
     * @param entry - объект записи
     * @return возвращает отформатированную строку с записью
     */
    public final String getTextEntry(Notepad.Entry entry) {
        final StringBuilder sb = new StringBuilder();
        sb.append("ID: " + entry.getId());
        sb.append("\nTitle: ");
        if (entry.getTitle().isBlank()) {
            sb.append("-");
        } else {
            sb.append(entry.getTitle());
        }
        sb.append("\nBody: " + entry.getContent());
        sb.append("\n-------------------\n");
        return sb.toString();
    }
}
