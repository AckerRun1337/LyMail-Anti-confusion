/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.Validate
 */
package Ly.mail.plugin.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.Validate;

public final class ArrayWrapper<E> {
    private E[] _array;

    public int hashCode() {
        ArrayWrapper IiiIiIiiII = null;
        return Arrays.hashCode(IiiIiIiiII._array);
    }

    public void setArray(E[] IiiIiIiiII) {
        Validate.notNull(IiiIiIiiII, (String)"The array must not be null.");
        IiiIiIiiII = IiiIiIiiII;
    }

    public ArrayWrapper(E ... IiiIiIiiII) {
        ArrayWrapper IiiIiIiiII2 = null;
        IiiIiIiiII2.setArray(IiiIiIiiII);
    }

    public static <T> T[] toArray(Iterable<? extends T> IiiIiIiiII, Class<T> IiiIiIiiII2) {
        Object[] objectArray;
        int n = -1;
        if (IiiIiIiiII instanceof Collection) {
            objectArray = ((Collection)IiiIiIiiII).toArray();
            n = objectArray.length;
        }
        if (n < 0) {
            n = 0;
            objectArray = new Iterator[]{IiiIiIiiII.iterator()};
            {
                ++n;
                Object e = Arrays.stream(((Iterator[]) objectArray)).toArray();
            }
        }
        objectArray = (Object[])Array.newInstance(IiiIiIiiII2, n);
        int n2 = 0;
        for (Object e : IiiIiIiiII) {
            objectArray[n2++] = e;
        }
        return (T[]) objectArray;
    }

    public E[] getArray() {
        ArrayWrapper IiiIiIiiII = null;
        return (E[]) IiiIiIiiII._array;
    }

    public boolean equals(Object IiiIiIiiII) {
        ArrayWrapper IiiIiIiiII2 = null;
        if (!(IiiIiIiiII instanceof ArrayWrapper)) {
            return false;
        }
        return Arrays.equals(IiiIiIiiII2._array, ((ArrayWrapper)IiiIiIiiII)._array);
    }
}

