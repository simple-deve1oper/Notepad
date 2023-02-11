package dev.notepad.entity;

import dev.notepad.util.DeepClone;
import dev.notepad.util.IdRegulation;

import java.time.LocalDate;
import java.util.*;

/**
 * Класс для описания блокнота, который может хранить несколько записей за конкретную дату
 * @version 1.0
 */
public class Notepad implements Cloneable {
    /* Наименование блокнота */
    private String name;
    /* Объект карты Дата - Список записей для хранения несколько записей за конкретную дату */
    private Map<LocalDate, List<Entry>> entriesMap;

    /**
     * Конструктор для создания нового объекта типа Notepad
     * @param name - наименование блокнота
     */
    public Notepad(String name) {
        this.name = name;
        this.entriesMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<LocalDate, List<Entry>> getEntriesMap() {
        DeepClone deepClone = new DeepClone();
        Map<LocalDate, List<Entry>> copy = deepClone.cloneMap(entriesMap);
        return copy;
    }
    public void setEntriesMap(Map<LocalDate, List<Entry>> entriesMap) {
        this.entriesMap = entriesMap;
    }

    @Override
    public Notepad clone() throws CloneNotSupportedException {
        final DeepClone deepClone = new DeepClone();

        Notepad notepad = (Notepad) super.clone();
        notepad.entriesMap = deepClone.cloneMap(entriesMap);
        return notepad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Notepad notepad = (Notepad) obj;
        return (name == notepad.name || ((name != null) && name.equals(notepad.name))) &&
                (entriesMap == notepad.entriesMap || ((entriesMap != null) && entriesMap.equals(notepad.entriesMap)));
    }
    @Override
    public int hashCode() {
        return 31 * 1 + (name == null ? 0 : name.hashCode()) + (entriesMap == null ? 0 : entriesMap.hashCode());
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Notepad{", "}")
                .add("name=" + name).add("entriesMap=" + entriesMap).toString();
    }

    /**
     * Внутренний класс для описания записи для блокнота
     * @version 1.0
     */
    public class Entry implements Cloneable {
        /* Неизменяемый объект для вызова метода по установке значения для id */
        private final static IdRegulation regulation = new IdRegulation();

        /* Идентификатор */
        private long id;
        /* Заголовок записи */
        private String title;
        /* Контент записи */
        private String content;

        /**
         * Конструктор для создания нового объекта типа Entry
         * @param date - объект даты
         * @param title - заголовок записи
         * @param content - контент записи
         */
        public Entry(LocalDate date, String title, String content) {
            this.id = regulation.getIdForNewEntry(date, entriesMap);
            this.title = title;
            this.content = content;
        }

        /**
         * Перегруженный конструктор для создания нового объекта типа Entry
         * @param date - объект даты
         * @param content - контент записи
         * @see Entry#Entry(LocalDate, String, String)
         */
        public Entry(LocalDate date, String content) {
            this(date, "", content);
        }

        public long getId() {
            return id;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public Entry clone() throws CloneNotSupportedException {
            Entry entry = (Entry) super.clone();
            return entry;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            Entry entry = (Entry) obj;
            return (id == entry.id) && (title == entry.title || ((title != null) && title.equals(entry.title))) &&
                    (content == entry.content || ((content != null) && content.equals(entry.content)));
        }
        @Override
        public int hashCode() {
            return (int) (31 * 1 + id + (title == null ? 0 : title.hashCode()) + (content == null ? 0 : content.hashCode()));
        }

        @Override
        public String toString() {
            return new StringJoiner(",", "Entry{", "}")
                    .add("id=" + id).add("title=" + title)
                    .add("content=" + content).toString();
        }
    }
}
