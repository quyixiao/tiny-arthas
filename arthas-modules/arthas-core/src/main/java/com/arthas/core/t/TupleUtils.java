package com.arthas.core.t;

import com.arthas.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TupleUtils {

    public static final List<String> contains = new ArrayList<String>();

    static {
        contains.add("String");
        contains.add("Double");
        contains.add("double");
        contains.add("Float");
        contains.add("float");
        contains.add("Long");
        contains.add("long");
        contains.add("Integer");
        contains.add("int");
        contains.add("Byte");
        contains.add("byte");
        contains.add("Character");
        contains.add("Short");
        contains.add("short");
        contains.add("boolean");
        contains.add("Boolean");
    }

    public static Map<String, Object> tupleToMap(TwoTuple tuple) {
        Map<String, Object> map = new HashMap<String,Object>();
        if (tuple instanceof TenTuple) {
            getTen(tuple, map);

        } else if (tuple instanceof NineTuple) {
            getNine(tuple, map);

        } else if (tuple instanceof EightTuple) {
            getEight(tuple, map);

        } else if (tuple instanceof SevenTuple) {
            getSeven(tuple, map);


        } else if (tuple instanceof SixTuple) {
            getSix(tuple, map);


        } else if (tuple instanceof FiveTuple) {
            getFive(tuple, map);

        } else if (tuple instanceof FourTuple) {
            getFourth(tuple, map);

        } else if (tuple instanceof ThreeTuple) {
            getThree(tuple, map);

        } else if (tuple instanceof TwoTuple) {
            getTwo(tuple, map);

        }

        return map;
    }

    private static void getTen(TwoTuple tuple, Map<String, Object> map) {
        //10
        String tenName = getSimpleName(((TenTuple) tuple).getTen().getClass().getSimpleName());
        buildParams(tenName, "ten", ((TenTuple) tuple).getTen(), map);

        //9
        getNine(tuple, map);
    }

    private static void getNine(TwoTuple tuple, Map<String, Object> map) {
        //9
        String nineName = getSimpleName(((NineTuple) tuple).getNine().getClass().getSimpleName());
        buildParams(nineName, "nine", ((NineTuple) tuple).getNine(), map);

        //8
        getEight(tuple, map);
    }

    private static void getEight(TwoTuple tuple, Map<String, Object> map) {
        //8
        String eight = getSimpleName(((EightTuple) tuple).getEight().getClass().getSimpleName());
        buildParams(eight, "eight", ((EightTuple) tuple).getEight(), map);


        //7
        getSeven(tuple, map);
    }

    private static void getSeven(TwoTuple tuple, Map<String, Object> map) {
        //7
        String seven = getSimpleName(((SevenTuple) tuple).getSeven().getClass().getSimpleName());
        buildParams(seven, "seven", ((SevenTuple) tuple).getSeven(), map);

        //6
        getSix(tuple, map);
    }

    private static void getSix(TwoTuple tuple, Map<String, Object> map) {
        //6
        String sixth = getSimpleName(((SixTuple) tuple).getSixth().getClass().getSimpleName());
        buildParams(sixth, "sixth", ((SixTuple) tuple).getSixth(), map);

        //5
        getFive(tuple, map);
    }

    private static void getFive(TwoTuple tuple, Map<String, Object> map) {
        //5
        String fifth = getSimpleName(((FiveTuple) tuple).getFifth().getClass().getSimpleName());
        buildParams(fifth, "fifth", ((FiveTuple) tuple).getFifth(), map);


        //4
        getFourth(tuple, map);
    }

    private static void getFourth(TwoTuple tuple, Map<String, Object> map) {
        //4
        String fourth = getSimpleName(((FourTuple) tuple).getFourth().getClass().getSimpleName());
        buildParams(fourth, "fourth", ((FourTuple) tuple).getFourth(), map);


        //3
        getThree(tuple, map);
    }

    private static void getThree(TwoTuple tuple, Map<String, Object> map) {
        //3
        String third = getSimpleName(((ThreeTuple) tuple).getThird().getClass().getSimpleName());
        buildParams(third, "third", ((ThreeTuple) tuple).getThird(), map);

        //2
        getTwo(tuple, map);
    }

    private static void getTwo(TwoTuple tuple, Map<String, Object> map) {
        //2
        String secend = getSimpleName(tuple.getSecond().getClass().getSimpleName());
        buildParams(secend, "secend", tuple.getSecond(), map);

        //1
        String firstName = getSimpleName(tuple.getFirst().getClass().getSimpleName());
        buildParams(firstName, "first", tuple.getFirst(), map);
    }


    public static void buildParams(String simpleName, String defaultValue, Object value, Map<String, Object> data) {
        if (StringUtils.isEmpty(simpleName)) {
            simpleName = defaultValue;
            return ;
        }
        data.put(simpleName, value);
    }

    public static String getSimpleName(String simpleName) {
        if (contains.contains(simpleName)) {
            return null;
        }
        return (simpleName.substring(0, 1)).toLowerCase()
                + simpleName.substring(1, simpleName.length());
    }


}
