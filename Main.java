import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        String[] studnetIDs = "s13fi004 s13fi123 s14fi006 s14fi007 s14fi029 s14fi038 s14fi052 s14fi079 s14fi601 s15fi002 s15fi003 s15fi004 s15fi005 s15fi006 s15fi007 s15fi008 s15fi009 s15fi010 s15fi011 s15fi013 s15fi014 s15fi015 s15fi016 s15fi017 s15fi018 s15fi019 s15fi021 s15fi022 s15fi023 s15fi024 s15fi025 s15fi026 s15fi027 s15fi028 s15fi029 s15fi030 s15fi032 s15fi033 s15fi034 s15fi035 s15fi036 s15fi039 s15fi040 s15fi041 s15fi042 s15fi043 s15fi044 s15fi045 s15fi046 s15fi047 s15fi048 s15fi050 s15fi051 s15fi052 s15fi053 s15fi054 s15fi056 s15fi057 s15fi058 s15fi059 s15fi061 s15fi062 s15fi063 s15fi064 s15fi065 s15fi067 s15fi069 s15fi070 s15fi071 s15fi072 s15fi074 s15fi075 s15fi077 s15fi078 s15fi080 s15fi081 s15fi082 s15fi083 s15fi084 s15fi085 s15fi086 s15fi087 s15fi088 s15fi089 s15fi090 s15fi091 s15fi092 s15fi093 s15fi094 s15fi095 s15fi096 s15fi097 s15fi098 s15fi099 s15fi100 s15fi102 s15fi103 s15fi105 s15fi106 s15fi108 s15fi109 s15fi110 s15fi111 s15fi112 s15fi113 s15fi115 s15fi116 s15fi117 s15fi118 s15fi119 s15fi121 s15fi123 s15fi124 s15fi125 s15fi126 s15fi128 s15fi129 s15fi130 s15fi131 s15fi132 s15fi133 s15fi134 s15fi401".split(" ");
        for (String id : studnetIDs) {
            testEx(id);
        }
    }

    public static void testEx(String studentID) {
        System.out.println(studentID);
        System.out.println(" 1: " + checkItem1(studentID));
        System.out.println(" 2: " + checkItem2(studentID));
    }

    public static int checkItem1(String studentID) {
        // チェックに使う適当な変数
        String test_id = "99fi999";
        String test_name = "電大 太郎";
        String test_address = "日本";
        String test_tel = "999-999-99999";
        String test_str = "a Student(" + test_id + ", " + test_name + ", " + test_address + ", " + test_tel + ")";

        String test_name2 = "電大 花子";
        String test_address2 = "アメリカ";
        String test_tel2 = "000-000-00000";
        boolean check = true;
        try {
            Class c = Class.forName(studentID + ".Student");
            Constructor constructor = c.getConstructor(String.class, String.class, String.class, String.class);
            Object student = constructor.newInstance(test_id, test_name, test_address, test_tel);

            // 各メソッドのチェック
            check &= test_id.equals(student.getClass().getMethod("getStudentNumber").invoke(student));
            check &= test_name.equals(student.getClass().getMethod("getName").invoke(student));
            check &= test_address.equals(student.getClass().getMethod("getAddress").invoke(student));
            check &= test_tel.equals(student.getClass().getMethod("getTel").invoke(student));

            // toString, あいまい性
            check &= test_str.replace(" ", "").equals(((String) student.getClass().getMethod("toString").invoke(student)).replace(" ", ""));

            // setter のチェック
            student.getClass().getMethod("setName", new Class[]{String.class}).invoke(student, test_name2);
            student.getClass().getMethod("setAddress", new Class[]{String.class}).invoke(student, test_address2);
            student.getClass().getMethod("setTel", new Class[]{String.class}).invoke(student, test_tel2);

            check &= test_name2.equals(student.getClass().getMethod("getName").invoke(student));
            check &= test_address2.equals(student.getClass().getMethod("getAddress").invoke(student));
            check &= test_tel2.equals(student.getClass().getMethod("getTel").invoke(student));
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
//            e.printStackTrace();
            return 1;
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            // クラスが見つからない場合は未提出
            return 0;
        }
        return check ? 3 : 1;
    }

    public static int checkItem2(String studentID) {
        // チェックに使う適当な変数
        String test_id = "99fi999";
        String test_name = "電大 太郎";
        String test_address = "日本";
        String test_tel = "999-999-99999";
        String test_subject = "オブジェクト指向";
        String test_extension = "9999";
        String test_subject2 = "プログラミング基礎";
        String test_extension2 = "9998";
        String test_str = "a TA(99fi999, 電大 太郎, 日本, 999-999-99999, オブジェクト指向, 9999)";

        boolean check = true;
        try {
            Class c = Class.forName(studentID + ".TA");
            Constructor constructor = c.getConstructor(String.class, String.class, String.class, String.class, String.class, String.class);
            Object student = constructor.newInstance(test_id, test_name, test_address, test_tel, test_subject, test_extension);

            // 各メソッドのチェック
            check &= test_subject.equals(student.getClass().getMethod("getSubject").invoke(student));
            check &= test_extension.equals(student.getClass().getMethod("getExtension").invoke(student));

            // toString, あいまい性
            check &= test_str.replace(" ", "").equals(((String) student.getClass().getMethod("toString").invoke(student)).replace(" ", ""));

            // setter のチェック
            student.getClass().getMethod("setSubject", new Class[]{String.class}).invoke(student, test_subject2);
            student.getClass().getMethod("setExtension", new Class[]{String.class}).invoke(student, test_extension2);

            check &= test_subject2.equals(student.getClass().getMethod("getSubject").invoke(student));
            check &= test_extension2.equals(student.getClass().getMethod("getExtension").invoke(student));
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            return 1;
        } catch (ClassNotFoundException e) {
            return 0;
        }
        return check ? 3 : 1;
    }
}
