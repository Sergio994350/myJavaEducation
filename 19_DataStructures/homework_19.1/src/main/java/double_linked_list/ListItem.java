package double_linked_list;

public class ListItem
{
    private String data;
    private ListItem prev;
    private ListItem next;

    public ListItem(String data)
    {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ListItem getPrev() {
        return prev;
    }

    public void setPrev(ListItem prev) {
        this.prev = prev;
    }

    public ListItem getNext() {
        return next;
    }

    public void setNext(ListItem next) {
        this.next = next;
    }
}