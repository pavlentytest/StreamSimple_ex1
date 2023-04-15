import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Stream stream = list.stream();

        Stream<String> stringStream = Stream.of("a1","a2","a3");
        System.out.println(stringStream.collect(Collectors.toList()));

        String[] array = {"a1","a2","a3"};
        Stream<String> stream1 = Arrays.stream(array);
        // печать в виде - аналогично

        File file = new File("1.tmp");
        PrintWriter out = new PrintWriter(file);
        out.write("a1 a2 a3");
        out.close();

        Stream<String> stream2 = Files.lines(Paths.get(file.getAbsolutePath()));
        System.out.println(stream2.collect(Collectors.toList()));

        // создание безконечных стримов
        Stream<Integer> stream3 = Stream.iterate(1, n -> n + 2);
        System.out.println(stream3.limit(3).collect(Collectors.toList()));

        Stream<String> stream4 = Stream.generate(()->"Hello");
        System.out.println(stream4.limit(5).collect(Collectors.toList()));

        Path path = Files.write(Paths.get("2.tmp"),"line1\nline2\n".getBytes());
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        Stream<String> stream5 = reader.lines();
        System.out.println(stream5.collect(Collectors.toList()));

        List<Integer> numbers = Arrays.asList(1,2,3,4);
        // получить сумму нечетных чисел
        int sum = numbers.stream().mapToInt((p) -> p % 2 == 1 ? p : 0).sum();
        System.out.println(sum);

        // вычесть из каждого элемента 1 и получить среднее задачи
        double avg = numbers.stream().collect(Collectors.averagingInt((p)->p-1));
        System.out.println("avg="+avg);

        // разделить на четные и нечетные
        Map<Boolean,List<Integer>> parts = numbers
                .stream()
                .collect(Collectors.partitioningBy((p)->p%2 == 0));
        System.out.println(parts);


        List<String> list1 = Arrays.asList("a1","a2","a3","a1");
        List<String> distinctList = list1.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctList);

        long count = list1.stream().filter("a1"::equals).count();
        System.out.println(count);


        List<Student> students = Arrays.asList(
                new Student("Ivan", 16, 50),
                new Student("Olga", 18, 37),
                new Student("Petr", 21, 45),
                new Student("Oleg", 19, 16),
                new Student("Alex", 23, 89)
        );

        // Выбрать студента с минимальным возрастом
        Student student = students
                .stream()
                .min((p1,p2) -> p1.getAge()-p2.getAge())
                .get();
        System.out.println("student="+student.getName());



        List<Student> marks = students
                .stream()
                .filter((p) -> p.getMark() >= 40 && p.getMark() <= 80)
                .toList();
        System.out.println(marks);

        long count2 = students
                .stream()
                .filter((p)->p.getAge() > 20)
                .count();
        System.out.println(count2);

        // Вывести среднюю оценку всех у кого возраст > 20

        double avg2 = students
                .stream()
                .filter((p)->p.getAge() > 20)
                .mapToInt(Student::getMark)
                .average()
                .getAsDouble();
        System.out.println(avg2);

        List<String> list3 = Arrays.asList("a1","a2","a3","a1");

        // вернуть два элемента начиная со второго
        List<String> result4 = list3
                .stream()
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());



    }
}
class Student {
    private String name;
    private int age;
    private int mark;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mark=" + mark +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Student(String name, int age, int mark) {
        this.name = name;
        this.age = age;
        this.mark = mark;
    }
}