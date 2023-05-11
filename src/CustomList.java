import java.util.*;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T> {
    private class Node {
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node first = null;
    private Node last = null;
    @Override
    public T get(int index) {
        int counter = 0;
        if(first == null || index > size())
            throw new NoSuchElementException();
        Node currentElement = first;
        while(counter != index) {
            currentElement = currentElement.next;
            counter++;
        }
        return currentElement.value;
    }

    @Override
    public int size() {
        int counter = 0;
        if(first == null) {
            return 0;
        }
        else if(first == last) {
            return 1;
        }
        else {
            Node currentElement = first;
            while(currentElement != last) {
                currentElement = currentElement.next;
                counter++;
            }
        }
        return counter;
    }

    public boolean add(T t) {
        addLast(t);
        return true;
    }

    public void addLast(T value) {
        Node node = new Node(value,null);

        //jeśli "lista" jest pusta
        if(first == null) {
            first = node;
            last = node;
        }
        //jeśli jest >= 1 element "listy"
        else {
            last.next = node;
            last = node;
        }
    }

    public T getLast() {
        return last.value;
    }

    public void addFirst(T value) {
        Node node = new Node(value,null);
        if(first == null) {
            first = node;
            last = node;
        }
        else {
//            Node temp = new Node(first.value,first.next);
//            first.next = temp;
//            first.value = node.value;
            node.next = first;
            first = node;
        }
    }

    public T getFirst() {
        return first.value;
    }

    public T removeFirst() {
        T previousFirstValue = first.value;
        if(first == last) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
        }
        return previousFirstValue;
    }

    public T removeLast() {
        T previousLastValue = last.value;
        if(last == first) {
            first = null;
            last = null;
        }
        else {
            Node currentElement = first;
            while(currentElement != last) {
                currentElement = currentElement.next;
            }
            currentElement.next = null;
            last = currentElement;
        }
        return previousLastValue;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node currentNode = first;
            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T value = currentNode.value;
                currentNode = currentNode.next;
                return value;
            }
        };
    }

    public Stream<T> stream() {
        Stream.Builder<T> streamBuilder = Stream.builder();
        for(T entry : this) {
            streamBuilder.accept(entry);
        }
        return streamBuilder.build();
    }

    static <T> List<T> findTheSameClass(List<T> list, Class<?> clasS) {
        List<T> matching = new ArrayList<>();
        for(T element : list) {
            if(clasS.isAssignableFrom(element.getClass())) {
                matching.add(element);
            }
        }
        return matching;
    }
}
