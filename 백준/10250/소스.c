#include <stdio.h>
#pragma warning(disable:4996)
int main(void) {
    int t, h, w, n;
    int i;
    int a, b, x, y;
    scanf("%d", &t);
    for (i = 0; i < t; i++) {
        scanf("%d%d%d", &h, &w, &n);
        a = n / h;
        b = n % h;
        if (b == 0) {
            y = h;
            x = a;
        }
        else {
            y = b;
            x = a + 1;
        }
        if (x < 10)
            printf("%d0%d", y, x);
        else
            printf("%d%d", y, x);
    }
}