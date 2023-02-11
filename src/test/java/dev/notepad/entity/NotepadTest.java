package dev.notepad.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

public class NotepadTest {
    private Notepad superNotepad;

    @BeforeEach
    public void init() {
        superNotepad = new Notepad("Супер блокнот");
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValuesTwoParameters() {
        Map<LocalDate, List<Notepad.Entry>> map = new HashMap<>();

        Assertions.assertTrue(superNotepad.getName().equals("Супер блокнот") &&
                superNotepad.getEntriesMap().equals(map));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObjectTwoParameters() {
        Notepad copySuperNotepad = new Notepad("Супер блокнот");

        Assertions.assertTrue(superNotepad.equals(copySuperNotepad));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsAuthorsTwoParameters() {
        Notepad otherNotepad = new Notepad("Обычный блокнот");

        Assertions.assertFalse(superNotepad.equals(otherNotepad));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObjectTwoParameters() {
        try {
            Notepad cloneSuperNotepad = superNotepad.clone();
            Assertions.assertTrue(superNotepad.equals(cloneSuperNotepad));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Notepad: " + ex.getMessage());
        }
    }
}
