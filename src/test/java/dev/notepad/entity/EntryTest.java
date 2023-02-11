package dev.notepad.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EntryTest {
    private Notepad notepad = new Notepad("Супер блокнот");
    private final Notepad.Entry entryThreeParameters = notepad.new Entry(LocalDate.now(), "Заголовок 1", "Текст 1");
    private final Notepad.Entry entryTwoParameters = notepad.new Entry(LocalDate.of(2025, 10, 10), "Текст 2");

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям (три параметра)")
    public void equalsValuesThreeParameters() {
        Assertions.assertTrue((entryThreeParameters.getId() == 1) &&
                entryThreeParameters.getTitle().equals("Заголовок 1") &&
                entryThreeParameters.getContent().equals("Текст 1"));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями (три параметра)")
    public void equalsCopyObjectThreeParameters() {
        Notepad.Entry copyEntry = notepad.new Entry(LocalDate.now(), "Заголовок 1", "Текст 1");
        // несмотря на то, что идентификатор увеличивается на один с помощью класса IdRegulation, этого не произойдет
        // пока одна из записей не будет добавлена в карту. Когда запись будет добавлена в карту, то у следующей
        // вновь создаваемой записи идентификатор увеличится на один
        Assertions.assertTrue(entryThreeParameters.equals(copyEntry));
        Assertions.assertEquals(1, entryThreeParameters.getId());
        Assertions.assertEquals(1, copyEntry.getId());
        Assertions.assertNotEquals(2, copyEntry.getId());
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями (три параметра)")
    public void notEqualsAuthorsThreeParameters() {
        LocalDate date = LocalDate.of(2134, 6, 7);
        Notepad.Entry otherEntry = notepad.new Entry(date, "Заголовок 99", "Текст 99");
        Assertions.assertFalse(entryThreeParameters.equals(otherEntry));
        // несмотря на то, что идентификатор увеличивается на один с помощью класса IdRegulation, этого не произойдет
        // пока не одна из записей не будет добавлена в карту. Когда запись будет добавлена в карту, то у следующей
        // вновь создаваемой записи идентификатор увеличится на один
        Assertions.assertEquals(1, entryThreeParameters.getId());
        Assertions.assertEquals(1, otherEntry.getId());
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты (три параметра)")
    public void equalsCloneObjectThreeParameters() {
        try {
            Notepad.Entry cloneEntry = entryThreeParameters.clone();
            Assertions.assertTrue(entryThreeParameters.equals(cloneEntry));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Notepad.Entry: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям (два параметра)")
    public void equalsValuesTwoParameters() {
        LocalDate date = LocalDate.of(2025, 10, 10);
        Assertions.assertTrue((entryTwoParameters.getId() == 1) && entryTwoParameters.getTitle().equals("") &&
                entryTwoParameters.getContent().equals("Текст 2"));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями (два параметра)")
    public void equalsCopyObjectTwoParameters() {
        Notepad.Entry copyEntry = notepad.new Entry(LocalDate.of(2025, 10, 10), "Текст 2");
        // несмотря на то, что идентификатор увеличивается на один с помощью класса IdRegulation, этого не произойдет
        // пока не одна из записей не будет добавлена в карту. Когда запись будет добавлена в карту, то у следующей
        // вновь создаваемой записи идентификатор увеличится на один
        Assertions.assertTrue(entryTwoParameters.equals(copyEntry));
        Assertions.assertEquals(1, entryTwoParameters.getId());
        Assertions.assertEquals(1, copyEntry.getId());
        Assertions.assertNotEquals(2, copyEntry.getId());
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями (два параметра)")
    public void notEqualsAuthorsTwoParameters() {
        LocalDate date = LocalDate.of(2134, 6, 7);
        Notepad.Entry otherEntry = notepad.new Entry(LocalDate.of(2030, 1, 20), "Текст 3");
        Assertions.assertFalse(entryTwoParameters.equals(otherEntry));
        // несмотря на то, что идентификатор увеличивается на один с помощью класса IdRegulation, этого не произойдет
        // пока не одна из записей не будет добавлена в карту. Когда запись будет добавлена в карту, то у следующей
        // вновь создаваемой записи идентификатор увеличится на один
        Assertions.assertEquals(1, entryTwoParameters.getId());
        Assertions.assertEquals(1, otherEntry.getId());
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты (два параметра)")
    public void equalsCloneObjectTwoParameters() {
        try {
            Notepad.Entry cloneEntry = entryTwoParameters.clone();
            Assertions.assertTrue(entryTwoParameters.equals(cloneEntry));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Notepad.Entry: " + ex.getMessage());
        }
    }
}
