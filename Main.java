public class Main {
    public static int countInversions(int[] arr) {
        int[] temp = new int[arr.length];
        return mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static int mergeSort(int[] arr, int[] temp, int left, int right) {
        int invCount = 0;
        if (left < right) {
            int mid = (left + right) / 2;

            // Count inversions in left and right halves
            invCount += mergeSort(arr, temp, left, mid);
            invCount += mergeSort(arr, temp, mid + 1, right);

            // Count inversions during merging
            invCount += merge(arr, temp, left, mid, right);
        }
        return invCount;
    }

    private static int merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left, invCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i + 1); // Count inversions
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, left, arr, left, right - left + 1);
        return invCount;
    }

    public static void main(String[] args) {
        int[] arr = {8, 4, 2, 1};
        int inversionCount = countInversions(arr);
        System.out.println("Inversion Count: " + inversionCount);
    }
}
