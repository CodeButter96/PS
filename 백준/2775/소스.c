#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>
void reverse(char arr[]) {
    int len = strlen(arr);
    for (int i = 0; i < len / 2; i++) {
        char temp = arr[i];
        arr[i] = arr[len - i - 1];
        arr[len - i - 1] = temp;
    }
}
int main(void) {
    int carry = 0, i;
    char a[10001];
    char b[10001];
    char res[10001];
    scanf("%s%s", a, b);
    reverse(a);
    reverse(b);
    int len = strlen(a) > strlen(b) ? strlen(a) : strlen(b);
    for (i = 0; i < len; i++) {
        int sum = a[i] - '0' + b[i] - '0' + carry;
        while (sum < 0)
            sum += '0';
        if (sum > 9)
            carry = 1;
        else
            carry = 0;
        res[i] = sum % 10 + '0';
    }
    if (carry == 1)
        res[len] = '1';
    reverse(res);
    printf("%s", res);
    return 0;
}