package mystringlib;

import java.util.Map;
import java.util.TreeMap;

public class MyString {
    private static final char[] lowerLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static Map<Character, Character> ignoredCasePairsMap = null;
    private char[] data;

    private static void initializeMaps(){
        if (ignoredCasePairsMap != null){
            return;
        }
        ignoredCasePairsMap = new TreeMap<>();
        for (int i = 0; i < lowerLetters.length; i++) {
            ignoredCasePairsMap.put(
                    lowerLetters[i],
                    upperLetters[i]);
            ignoredCasePairsMap.put(
                    upperLetters[i],
                    lowerLetters[i]);
        }
    }
    public MyString(char[] word) {
        initializeMaps();
        this.data = word;
    }

    public MyString(String word) {
        initializeMaps();
        this.data = word.toCharArray();
    }

    public int indexOf(int ch) {
        return data[ch];
    }

    public MyString[] split(int cutIndex) {
        char[] first = new char[cutIndex];
        for (int i = 0; i < cutIndex; i++) {
            first[i] = data[i];
        }
        char[] second = new char[data.length - cutIndex];
        for (int i = cutIndex; i < data.length; i++) {
            second[i - cutIndex] = data[i];
        }
        MyString[] result = new MyString[2];
        result[0] = new MyString(first);
        result[1] = new MyString(second);
        return result;
    }

    public MyString join(MyString other) {
        char[] newData = new char[data.length + other.data.length];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        for (int i = 0; i < other.data.length; i++) {
            newData[i + data.length] = other.data[i];
        }
        data = newData;
        return this;
    }


    private int findCutIndex(MyString pattern) {
        char[] charPattern = pattern.data;
        for (int i = 0; i < data.length - charPattern.length + 1; i++) {
            if (checkIfMatches(i, charPattern)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkIfMatches(int start, char[] pattern) {
        try {
            for (int i = start; i < start + pattern.length; i++) {
                if (data[i] != pattern[i - start]) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public MyString replace(MyString pattern, MyString replacement) {
        if (pattern.data.length > data.length) return this;

        int cutIndex = findCutIndex(pattern);
        MyString head = this.split(cutIndex)[0];
        MyString tail = this.split(cutIndex + pattern.data.length)[1];

        data = head.join(replacement).join(tail).data;
        return this;

    }

    public boolean equals(MyString other) {
        if (data.length != other.data.length) return false;

        for (int i = 0; i < data.length; i++) {
            if (data[i] != other.data[i]) return false;
        }
        return true;
    }

    public boolean equalsIgnoreCase(MyString other) {
        if (data.length != other.data.length) return false;

        for (int i = 0; i < data.length; i++) {
            if (data[i] != other.data[i] &&
                    data[i] != ignoredCasePairsMap.get(other.data[i])
            ) return false;
        }
        return true;
    }

    public static MyString format(MyString format, MyString... strings){
        for (int i = 0; i < strings.length; i++) {
            format.replace(new MyString("%s"), strings[i]);
        }
        return format;
    }

    @Override
    public String toString() {
        String res = "";
        for (Character c : data) {
            res += c;
        }
        return res;
//        return String.valueOf(data);
    }
}
