import java.util.Scanner;

public class StringManipulation {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Ask user to enter a sentence
        System.out.print("Enter a sentence: ");
        String sentence = input.nextLine();

        // 1. Total number of characters
        int characters = sentence.length();

        // 2. Total number of words
        String[] words = sentence.trim().split("\\s+");
        int wordCount = words.length;

        // 3. Sentence in uppercase
        String upper = sentence.toUpperCase();

        // 4. Sentence in lowercase
        String lower = sentence.toLowerCase();

        // 5. Reverse the sentence
        String reverse = "";
        for (int i = sentence.length() - 1; i >= 0; i--) {
            reverse += sentence.charAt(i);
        }

        // 6. Count vowels
        int vowels = 0;

        // 7. Count consonants
        int consonants = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char ch = Character.toLowerCase(sentence.charAt(i));

            if (Character.isLetter(ch)) {

                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        // 8. Check palindrome
        String clean = sentence.replaceAll("\\s+", "").toLowerCase();

        String reversedClean = "";
        for (int i = clean.length() - 1; i >= 0; i--) {
            reversedClean += clean.charAt(i);
        }

        String palindrome;

        if (clean.equals(reversedClean)) {
            palindrome = "Yes";
        } else {
            palindrome = "No";
        }

        // Display results
        System.out.println("\nTotal Characters: " + characters);
        System.out.println("Total Words: " + wordCount);
        System.out.println("Uppercase: " + upper);
        System.out.println("Lowercase: " + lower);
        System.out.println("Reverse: " + reverse);
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Palindrome: " + palindrome);

        input.close();
    }
}