package ru.job4j.task;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class MinCount {
    public static void main(String[] args) {
        String path = args[0];
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(path))) {
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer[] nums = list.toArray(new Integer[0]);
        System.out.println(minStepsCount(nums));
    }

    public static int minStepsCount(Integer[] nums) {
        int m = median(nums);
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - m);
        }
        return sum;
    }

    public static int median(Integer[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
