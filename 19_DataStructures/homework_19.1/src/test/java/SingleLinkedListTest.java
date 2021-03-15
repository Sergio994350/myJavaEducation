import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import single_linked_list.ListItem;
import single_linked_list.SingleLinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Тестирование односвязного списка")
public class SingleLinkedListTest {
    private SingleLinkedList singleLinkedList;

    @BeforeEach
    void setUp() {
        singleLinkedList = new SingleLinkedList();
    }

    @Test
    @DisplayName("Добавление элемента")
    void testPushOneItem() {
        ListItem listItem = new ListItem("first");
        singleLinkedList.push(listItem);

        ListItem actualItem = singleLinkedList.pop();

        assertNotNull(actualItem);
        assertEquals(listItem, actualItem);
    }

    @Test
    @DisplayName("Получение первого элемента (top)")
    void testGetTopItem() {
        ListItem lastItem = new ListItem("last");
        ListItem topItem = new ListItem("top");

        singleLinkedList.push(lastItem);
        singleLinkedList.push(topItem);
        ListItem poppedItem = singleLinkedList.pop();

        assertEquals(topItem, poppedItem);

        SingleLinkedList actualSingleList = new SingleLinkedList();
        actualSingleList.push(new ListItem("last"));

        assertEquals(actualSingleList, singleLinkedList);
    }

    @Test
    @DisplayName("Удаление первого элемента (top)")
    void testRemoveTop() {
        ListItem lastItem = new ListItem("last");
        ListItem topItem = new ListItem("top");

        singleLinkedList.push(lastItem);
        singleLinkedList.push(topItem);
        singleLinkedList.removeTop();

        SingleLinkedList actualSingleList = new SingleLinkedList();
        actualSingleList.push(new ListItem("last"));

        assertEquals(actualSingleList, singleLinkedList);
    }

    @Test
    @DisplayName("Удаление последнего элемента (last)")
    void testRemoveLast() {
        ListItem lastItem = new ListItem("last");
        ListItem topItem = new ListItem("top");

        singleLinkedList.push(lastItem);
        singleLinkedList.push(topItem);
        singleLinkedList.removeLast();

        SingleLinkedList actualSingleList = new SingleLinkedList();
        actualSingleList.push(new ListItem("top"));

        assertEquals(actualSingleList, singleLinkedList);
    }
}
