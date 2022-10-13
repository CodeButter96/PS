#include <stdio.h>
#pragma warning(disable:4996)
#pragma warning(disable:6001)
#pragma warning(disable:6066)

int main(void) {
    int num, i, min, max;
    int* arr;
    scanf("%d", &num);
    arr = (int*)malloc(sizeof(int) * num);

    for (i = 0; i < num; i++) {
        scanf("%d", arr + i);
    }
    for (i = 0; i < num; i++) {
        printf("%d ", arr + i);
    }

    min = arr[0];
    for (i = 0; i < num; i++) {
        if (arr[i] < min)
            min = arr[i];
    }
    max = arr[0];
    for (i = 0; i < num; i++) {
        if (arr[i] < max)
            max = arr[i];
    }
    printf("%d %d", min, max);

    free(arr);
    return 0;
}