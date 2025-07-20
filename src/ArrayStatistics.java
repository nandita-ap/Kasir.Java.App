public class ArrayStatistics {
    public static void main(String[] args) {
      int[] arr = {12, 7, 19, 26, 33, 2, 11, 17, 10, 9};
  
      // Display the array elements and their order
      System.out.println("Urutan data array:");
      for (int i = 0; i < arr.length; i++) {
        System.out.println((i + 1) + ". " + arr[i]);
      }
  
      // Calculate average
      double sum = 0;
      for (int num : arr) {
        sum += num;
      }
      double average = sum / arr.length;
      System.out.println("\nNilai rata-rata array: " + average);
  
      // Find odd numbers
      System.out.print("\nBilangan ganjil pada array: ");
      boolean hasOdd = false;
      for (int num : arr) {
        if (num % 2 != 0) {
          System.out.print(num + " ");
          hasOdd = true;
        }
      }
      if (!hasOdd) {
        System.out.print("Tidak ada bilangan ganjil");
      }
      System.out.println();
  
      // Find prime numbers
      System.out.print("\nBilangan prima pada array: ");
      boolean hasPrime = false;
      for (int num : arr) {
        if (isPrime(num)) {
          System.out.print(num + " ");
          hasPrime = true;
        }
      }
      if (!hasPrime) {
        System.out.print("Tidak ada bilangan prima");
      }
      System.out.println();
  
      // Find max and min
      int max = arr[0];
      int min = arr[0];
      for (int num : arr) {
        if (num > max) { max = num; }
        if (num < min) { min = num; }
      }
      System.out.println("\nNilai maksimum pada array: " + max);
      System.out.println("Nilai minimum pada array: " + min);
    }
  
    private static boolean isPrime(int n) {
      if (n <= 1) return false;
      if (n == 2) return true;
      if (n % 2 == 0) return false;
      for (int i = 3; i <= Math.sqrt(n); i += 2) {
        if (n % i == 0) return false;
      }
      return true;
    }
  }
  