package single_linked_list;

import java.util.Objects;

public class SingleLinkedList {
    private ListItem top;

    public void push(ListItem item) {
        if (top != null) {
            item.setNext(top);
        }
        top = item;
    }

    public ListItem pop() {
        ListItem item = top;
        if (top != null) {
            top = top.getNext();
            item.setNext(null);
        }
        return item;
    }

    public void removeTop() {
        if (top != null) {
            top = top.getNext();
        }
    }

    public void removeLast() {
        ListItem item = top;
        ListItem itemLast = top;
        if(item != null)
        {
            do {
                itemLast = item;
                item = item.getNext();
//                System.out.println(item.getData());
            } while (item.getNext() != null);
            itemLast.setNext(null);

//      item.setNext(null);
        }
//        if (top != null) {
//            ListItem previous = top;
//            ListItem current = previous.getNext();
//            while (current != null) {
//                previous = previous.getNext();
//                current = current.getNext();
//                if (current.getNext() == null) {
//                    current = null;
//                    previous.setNext(null);
//                }
//            }
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleLinkedList that = (SingleLinkedList) o;
        return Objects.equals(top, that.top);
    }

    @Override
    public String toString() {
        return "SingleLinkedList{" +
                "top=" + top +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(top);
    }
}