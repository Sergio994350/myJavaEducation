package binary_search;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearch
{
    private ArrayList<String> list;

    public BinarySearch(ArrayList<String> list)
    {
        this.list = list;
        this.list.sort(Comparator.<String>naturalOrder());
    }

    public int search(String query)
    {
        return search(query, 0, list.size() - 1);
    }

    private int search(String query, int from, int to)
    {
        if (from > to) {return -1;}
        int middle = from + (to - from) / 2;
        int comparison = query.compareTo(list.get(middle));

        if (comparison == 0) {return 0;}
        else if (comparison > 0) {return search(query, middle + 1, to);}
        else {return search(query, from, middle - 1);}
    }
}
