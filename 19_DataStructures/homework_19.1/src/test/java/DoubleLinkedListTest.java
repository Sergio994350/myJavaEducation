import double_linked_list.DoubleLinkedList;
import double_linked_list.ListItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoubleLinkedListTest {

    String[] testArray = new String[]{"A", "B", "C", "D", "E"};

    DoubleLinkedList doubleLinkedListTest = new DoubleLinkedList();
    private ListItem listItem;

    @BeforeEach
    void setUp() {

        ListItem temp = null;

        for (String member : testArray) {
            temp = new ListItem(member);
            doubleLinkedListTest.addToHead(temp);
        }
        listItem = temp;

        System.out.println("\n=================================================================\n");

        System.out.print("doubleLinkedListTest: ");

        ListItem item = doubleLinkedListTest.getHeadElement();
        if (item != null) {
            int countItem = 1;
            System.out.print(item.getData() + " ");
            do {
                item = item.getNext();
                countItem++;
                System.out.print(item.getData() + " ");
            } while (item.getNext() != null);

            System.out.println("\nК-во членов doubleLinkedListTest: " + countItem);

        }

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void popHeadElement() {
        System.out.println("head - " + doubleLinkedListTest.getHeadElement().getData());
        System.out.println("tail - " + doubleLinkedListTest.getTailElement().getData());

        ListItem itemHead = doubleLinkedListTest.popHeadElement();

        System.out.println("\nAfter popHeadElement:\n");
        System.out.print("popHeadElement: " + itemHead.getData());
        System.out.print("\ndoubleLinkedListTest: ");

        ListItem item = doubleLinkedListTest.getHeadElement();

        if (item != null) {
            int countItem = 1;
            System.out.print(item.getData() + " ");
            do {
                item = item.getNext();
                countItem++;
                System.out.print(item.getData() + " ");
            } while (item.getNext() != null);

            System.out.println("\nК-во членов doubleLinkedListTest: " + countItem);
            System.out.println("head - " + doubleLinkedListTest.getHeadElement().getData());
            System.out.println("tail - " + doubleLinkedListTest.getTailElement().getData());
        }

        ListItem itemExpected = new ListItem(testArray[testArray.length - 1]);

        Assertions.assertEquals(itemExpected.getData(), itemHead.getData());
    }

    @Test
    void popTailElement() {
        System.out.println("head - " + doubleLinkedListTest.getHeadElement().getData());
        System.out.println("tail - " + doubleLinkedListTest.getTailElement().getData());

        ListItem itemTail = doubleLinkedListTest.popTailElement();

        System.out.println("\nAfter popTailElement:\n");
        System.out.print("popTailElement: " + itemTail.getData());
        System.out.print("\ndoubleLinkedListTest: ");

        ListItem item = doubleLinkedListTest.getHeadElement();

        if (item != null) {
            int countItem = 1;
            System.out.print(item.getData() + " ");
            do {
                item = item.getNext();
                countItem++;
                System.out.print(item.getData() + " ");
            } while (item.getNext() != null);

            System.out.println("\nК-во членов doubleLinkedListTest: " + countItem);
            System.out.println("head - " + doubleLinkedListTest.getHeadElement().getData());
            System.out.println("tail - " + doubleLinkedListTest.getTailElement().getData());

        }

        ListItem itemExpected = new ListItem(testArray[0]);

        Assertions.assertEquals(itemExpected.getData(), itemTail.getData());
    }

    @Test
    void removeHeadElement() {
        System.out.println("head - " + doubleLinkedListTest.getHeadElement().getData());
        System.out.println("tail - " + doubleLinkedListTest.getTailElement().getData());
        doubleLinkedListTest.removeHeadElement();

        System.out.println("\nAfter removeHeadElement:\n");
        System.out.print("doubleLinkedListTest: ");

        ListItem item = doubleLinkedListTest.getHeadElement();
        int countItem = 1;

        if (item != null) {
            System.out.print(item.getData() + " ");
            do {
                item = item.getNext();
                countItem++;
                System.out.print(item.getData() + " ");
            } while (item.getNext() != null);

            System.out.println("\nК-во членов doubleLinkedListTest: " + countItem);
            System.out.println("head - " + doubleLinkedListTest.getHeadElement().getData());
            System.out.println("tail - " + doubleLinkedListTest.getTailElement().getData());

        }
        Assertions.assertEquals(testArray.length - 1, countItem);
    }

    @Test
    void removeTailElement() {
        System.out.println("head - " + doubleLinkedListTest.getHeadElement().getData());
        System.out.println("tail - " + doubleLinkedListTest.getTailElement().getData());
        doubleLinkedListTest.removeTailElement();

        System.out.println("\nAfter removeTailElement:\n");
        System.out.print("doubleLinkedListTest: ");

        ListItem item = doubleLinkedListTest.getHeadElement();
        int countItem = 1;

        if (item != null) {
            System.out.print(item.getData() + " ");
            do {
                item = item.getNext();
                countItem++;
                System.out.print(item.getData() + " ");
            } while (item.getNext() != null);

            System.out.println("\nК-во членов doubleLinkedListTest: " + countItem);
            System.out.println("head - " + doubleLinkedListTest.getHeadElement().getData());
            System.out.println("tail - " + doubleLinkedListTest.getTailElement().getData());
        }

        Assertions.assertEquals(testArray.length - 1, countItem);

    }

    @Test
    void addToHead() {

        ListItem tempAdd = new ListItem("F");
        doubleLinkedListTest.addToHead(tempAdd);

        System.out.println("\nAfter addToHead:\n");
        System.out.print("doubleLinkedListTest: ");

        ListItem item = doubleLinkedListTest.getHeadElement();
        int countItem = 1;

        if (item != null) {
            System.out.print(item.getData() + " ");
            do {
                item = item.getNext();
                countItem++;
                System.out.print(item.getData() + " ");
            } while (item.getNext() != null);

            System.out.println("\nК-во членов doubleLinkedListTest: " + countItem);

        }

        Assertions.assertEquals(testArray.length + 1, countItem);


    }

    @Test
    void addToTail() {

        ListItem tempAdd = new ListItem("F");
        doubleLinkedListTest.addToTail(tempAdd);

        System.out.println("\nAfter addToTail:\n");
        System.out.print("doubleLinkedListTest: ");

        ListItem item = doubleLinkedListTest.getHeadElement();
        int countItem = 1;

        if (item != null) {
            System.out.print(item.getData() + " ");
            do {
                item = item.getNext();
                countItem++;
                System.out.print(item.getData() + " ");
            } while (item.getNext() != null);

            System.out.println("\nК-во членов doubleLinkedListTest: " + countItem);

        }

        Assertions.assertEquals(testArray.length + 1, countItem);

    }
}