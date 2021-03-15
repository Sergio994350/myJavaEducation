package single_linked_list;

import java.util.Objects;

public class ListItem {
    private final String data;
    private ListItem next;

    public ListItem(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "data='" + data + '\'' +
                ", next=" + next +
                '}';
    }

    public ListItem getNext() {
        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListItem listItem = (ListItem) o;
        return Objects.equals(data, listItem.data) && Objects.equals(next, listItem.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, next);
    }

    public void setNext(ListItem item) {
        next = item;
    }
}