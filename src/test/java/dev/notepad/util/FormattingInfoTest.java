package dev.notepad.util;

import dev.notepad.entity.Notepad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormattingInfoTest {
    private final FormattingInfo info = new FormattingInfo();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Notepad notepad = new Notepad("Блокнот");

    @Test
    @DisplayName("Сравнение текста с датой")
    public void tryEqualsTextDate() {
        LocalDate date = LocalDate.of(2027, 6, 8);
        String formattedDate = date.format(formatter);
        String textDate = info.getTextDate(formattedDate);

        Assertions.assertEquals("Дата: 08.06.2027\n-------------------\n", textDate);
    }

    @Test
    @DisplayName("Сравнения текста одной записи")
    public void tryEqualsTextEntry() {
        LocalDate date = LocalDate.now();
        Notepad.Entry entry = notepad.new Entry(date, "Заголовок 1", "Текст 1");
        String textEntry = info.getTextEntry(entry);

        final StringBuilder sb = new StringBuilder();
        sb.append("ID: 1");
        sb.append("\nTitle: Заголовок 1");
        sb.append("\nBody: Текст 1");
        sb.append("\n-------------------\n");
        String strEquals = sb.toString();

        Assertions.assertEquals(strEquals, textEntry);
    }
}
