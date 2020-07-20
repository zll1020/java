package list;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-20
 * Time: 09:56
 * @see     Collection
 * @see     List
 * @see     LinkedList
 * @see     Vector
 * @since   1.2
 */
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable{

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 将list的元素 转换成 size 长度的，类似以size长度截取数组
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * 扩容
     * @param minCapacity 期望的最小容量
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // 如果不是默认元素表，则为任意大小
                ? 0
                // 大于默认空表的默认值。它应该是默认大小。
                : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    /**
     * add 时扩容
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // 期望的最小容量>原数组容量，进行扩容
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    private void grow(int minCapacity) {

        int oldCapacity = elementData.length;
        // 扩容容量 右移一位相当于/2，所以 = 原容量1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 新容量 < 期望的最小容量 = 期望的最小容量
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        // 新容量 > 整数最大值 - 8，判断期望的最小容量 如果<0 内存溢出，大于MAX_ARRAY_SIZE 使用MAX_VALUE，否则MAX_ARRAY_SIZE
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity 通常接近大小
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * list 中 element的数量
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * 根据元素查找元素位置
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        // 判断查找元素为空的情况
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            // 查找元素不为空，遍历查找
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        // 不存在，返回-1
        return -1;
    }

    /**
     * 浅复制
     * @return
     */
    @Override
    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 范围检查
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 对于添加元素的范围检查
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    /**
     * 在某个位置替换值
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public boolean add(E e) {
        // 期望扩容量 size + 1
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
    /**
     * 随机在某个位置插入元素
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        // 复制 index-》size
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            // 先移动
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);

        // 批量添加-》复制
        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 移除某个位置上的元素
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        // 需要移动的数量
        int numMoved = size - index - 1;
        // 如果 > 0 移动
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        // 最后一个位置 为 null,并size -1
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    /**
     * 相比remove 减少了一次 O(n)的查找
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }


    /**
     * 移除某个元素，使用
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

}
