package ru.job4j.tracker;

import junit.framework.TestCase;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HbmTrackerTest extends TestCase {
    private HbmTracker tracker;

    @Override
    public void setUp() {
        tracker = new HbmTracker();
    }

    public void testAdd() {
        Item item = new Item("name1", "desr1");
        tracker.add(item);
        List<Item> all = tracker.findAll();
        assertThat(all.size(), is(1));
        Item found = all.get(0);
        assertThat(found.getName(), is(item.getName()));
        assertThat(found.getDescription(), is(item.getDescription()));
        assertThat(found.getId(), is(1));
    }

    public void testReplace() {
        Item item = new Item("name1", "desr1");
        tracker.add(item);
        item.setName("newName");
        item.setDescription("newDescr");
        tracker.replace(String.valueOf(item.getId()), item);
        List<Item> all = tracker.findAll();
        assertThat(all.size(), is(1));
        Item found = all.get(0);
        assertThat(found.getName(), is(item.getName()));
        assertThat(found.getDescription(), is(item.getDescription()));
        assertThat(found.getId(), is(1));
    }

    public void testDelete() {
        Item item = new Item("name1", "desr1");
        tracker.add(item);
        List<Item> all = tracker.findAll();
        assertThat(all.size(), is(1));
        tracker.delete(String.valueOf(item.getId()));
        all = tracker.findAll();
        assertThat(all.size(), is(0));
    }

    public void testFindAll() {
        Item item = new Item("name1", "desr1");
        Item item2 = new Item("name2", "desr2");
        tracker.add(item);
        tracker.add(item2);
        List<Item> all = tracker.findAll();
        assertThat(all.size(), is(2));
        assertThat(all, is(List.of(item, item2)));
    }

    public void testFindByName() {
        Item item = new Item("name1", "desr1");
        Item item2 = new Item("name1", "desr2");
        Item item3 = new Item("name2", "desr3");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> all = tracker.findByName("name1");
        assertThat(all.size(), is(2));
        assertThat(all, is(List.of(item, item2)));
    }

    public void testFindById() {
        Item item = new Item("name1", "desr1");
        tracker.add(item);
        Item found = tracker.findById("1");
        assertThat(found.getName(), is(item.getName()));
        assertThat(found.getDescription(), is(item.getDescription()));
        assertThat(found.getId(), is(1));
    }
}