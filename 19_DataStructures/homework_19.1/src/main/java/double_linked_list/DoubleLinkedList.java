package double_linked_list;

public class DoubleLinkedList {

    private ListItem head;
    private ListItem tail;

    public ListItem getHeadElement() {
        return head;
    }

    public ListItem getTailElement() {
        return tail;
    }

    public ListItem popHeadElement() {
        ListItem listItem = head;
        if (head != null) {
            head = head.getNext();
            head.setPrev(null);
            if (head == null) {
                tail = null;
            }
        }
        return listItem;
    }

    public ListItem popTailElement() {
        ListItem listItem = tail;
        if (tail != null) {
            tail = tail.getPrev();
            tail.setNext(null);
            if (tail == null) {
                head = null;
            }
        }
        return listItem;
    }

    public void removeHeadElement() {
        if (head != null) {
            head = head.getNext();
            head.setPrev(null);
            if (head == null) {
                tail = null;
            }
        }
    }

    public void removeTailElement() {
        if (tail != null) {
            tail = tail.getPrev();
            tail.setNext(null);
            if (tail == null) {
                head = null;
            }
        }
    }

    public void addToHead(ListItem item) {
        if (head != null) {
            head.setPrev(item);
            item.setNext(head);
        } else {
            tail = item;
        }

        head = item;
    }

    public void addToTail(ListItem item) {
        if (tail != null) {
            tail.setNext(item);
            item.setPrev(tail);
        } else {
            head = item;
        }

        tail = item;
    }
}