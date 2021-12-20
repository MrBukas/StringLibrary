package mystringlib;

public class Main {
    public static void main(String[] args) {
        String s = "Hello";
        System.out.println(String.format("Hello %s %d %s", "Anna", 112323, "123"));
//        test(1, " asd", "gfdsgfd ", "asdaxcv");

//        System.out.println(MyString.format(
//                new MyString("Hello %s %s %s"),
//                new MyString("Anna"),
//                new MyString("Nikita"),
//                new MyString("121233")));

    }

    public static void test(Integer test, String... args){
        System.out.println(test);
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
