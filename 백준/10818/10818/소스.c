#include <stdio.h>
int main(void) {
    int num, i, min, max = 0;
    scanf("%d", &num);
    int arr[num];
    for (i = 0; i++; i < num) {
        scanf("%d", &arr[i]);
    }
    min = arr[0];
    max = arr[0];
    for (i = 0; i++; i < num) {
        if (arr[i] > max)
            max = arr[i];
        if (arr[i] < min)
            min = arr[i];
    }
    printf("%d %d", min, max);
}