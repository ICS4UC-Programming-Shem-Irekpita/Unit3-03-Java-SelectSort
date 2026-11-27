/*
 * This program reads lines of unsorted integers from input.txt,
 * sorts each line using the selection sort algorithm, and writes
 * the sorted lines to output.txt.
 *
 * @author  Shem Irekpita
 * @version 1.0
 * @since   2026-11-27
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is the SelectSort class.
 */
public final class SelectSort {

    /**
     * Private constructor to prevent instantiation.
     *
     * @exception IllegalStateException if the class is instantiated
     */
    private SelectSort() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Sorts an array of integers in place using selection sort.
     *
     * @param numbers the array to be sorted
     */
    public static void selectionSort(final int[] numbers) {
        // Move the boundary of the sorted section one step right per pass
        // (the last element is automatically in place)
        for (int i = 0; i < numbers.length - 1; i++) {
            // Assume the current item is the smallest so far
            int minIndex = i;

            // Scan the unsorted section for anything smaller
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    // Remember where the new minimum is
                    minIndex = j;
                }
            }

            // Swap the minimum into the first unsorted position
            final int temp = numbers[minIndex];
            numbers[minIndex] = numbers[i];
            numbers[i] = temp;
        }
    }

    /**
     * The starting method.
     *
     * @param args nothing passed in
     */
    public static void main(final String[] args) {
        // Open both files; try-with-resources closes them automatically
        try (BufferedReader reader =
                new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer =
                new BufferedWriter(new FileWriter("output.txt"))) {

            // Read the first line (null means end of file)
            String line = reader.readLine();

            while (line != null) {
                // Split the line on spaces into separate number strings
                final String[] parts = line.trim().split("\\s+");

                // Convert each string to an integer
                final int[] numbers = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    numbers[i] = Integer.parseInt(parts[i]);
                }

                // Sort the numbers for this line
                selectionSort(numbers);

                // Build the sorted line as space-separated text
                final StringBuilder result = new StringBuilder();
                for (int i = 0; i < numbers.length; i++) {
                    if (i > 0) {
                        result.append(" ");
                    }
                    result.append(numbers[i]);
                }

                // Write the sorted line and move to the next input line
                writer.write(result.toString());
                writer.newLine();
                line = reader.readLine();
            }
        } catch (IOException | NumberFormatException exception) {
            // Show a message if the file is missing or input is invalid
            System.err.println("Error: " + exception.getMessage());
        }
    }
}
