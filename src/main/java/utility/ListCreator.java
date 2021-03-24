package utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCreator<E> {
    protected List<E> lst = new ArrayList<>();

    public ListCreator(List<E> args) {
        lst.addAll(args);
    }

    public ListCreator(E args) {
        lst.add(args);
    }

    public ListCreator(List<E> args,List<E> argss) {
        lst.addAll(args);
        lst.addAll(argss);
    }

    public ListCreator<E> add(E args) {
        lst.add(args);
        return this;
    }

    public ListCreator<E> add(List<E> args) {
            lst.addAll(args);
        return this;
    }

    public List<E> get() {
        return lst;
    }

    public List<E> sort(Comparator<? super E> c) {
        List<E> rtn = lst;
        rtn.sort(c);
        return rtn;
    }
}
