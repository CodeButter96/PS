#include <stdio.h>
int main(void) {
    int a, b, v, c;
    scanf("%d%d%d", &a, &b, &v);
    c = v / (a - b);
    while (c * (a - b) + b >= v) {
        c -= 1;
    }
    while (c * (a - b) + b < v) {
        c += 1;
    }
    printf("%d", c)
        return 0;
}