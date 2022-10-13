#include <iostream>
#include <algorithm>
#include <cstdlib>
#pragma warning(disable:4996)

using namespace std;

#define MAXN 3000
#define MAXM 100

int N, M;
int A[MAXN], B[MAXM];
int dp[MAXN + 1][MAXM + 1][MAXM + 1][2];

int Find(int idx, int l, int r, int take);
int compare(const void* a, const void* b);

int main(int argc, char** argv)
{
    int test_case;
    int T;
    freopen("input.txt", "r", stdin);
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case)
    {
        cin >> N;
        for (int n = 0; n < N; n++) cin >> A[n];
        cin >> M;
        for (int m = 0; m < M; m++) cin >> B[m];
        for (int n = 0; n <= N; n++) for (int m = 0; m <= M; m++)
            for (int m2 = 0; m2 <= M; m2++) {
                dp[n][m][m2][0] = -1;
                dp[n][m][m2][1] = -1;
            }
        qsort(B, M,sizeof(int),compare);
        int answer = 0;
        for (int l = 0; l <= M; l++) {
            int r = M - l;
            answer = max(answer, Find(N, l, r, 0));
            answer = max(answer, Find(N, l, r, 1));
        }
        cout << '#' << test_case << ' ' << answer << '\n';


    }
    return 0;
}

int Find(int idx, int l, int r, int take) {
    if (dp[idx][l][r][take] != -1) return dp[idx][l][r][take];
    if (idx == 0 && l == 0) return dp[idx][l][r][take] = 0;
    if (l + r > M) return dp[idx][l][r][take] = 0;

    int val;
    if (take == 1) {
        int f1 = 0, f2 = 0;
        if (idx > 0) f1 = Find(idx - 1, l, r, 0) + A[idx - 1];
        if (l > 0) f2 = Find(idx, l - 1, r, 0) + B[M - l];
        val = max(f1, f2);
    }
    else {
        int f1 = 0, f2 = 0, f3 = 0, f4 = 0;
        if (idx > 0) {
            f1 = Find(idx - 1, l, r, 0);
            f2 = Find(idx - 1, l, r, 1);
        }
        if (r > 0) {
            f3 = Find(idx, l, r - 1, 0);
            f4 = Find(idx, l, r - 1, 1);
        }
        val = max(max(f1, f2), max(f3, f4));
    }
    return dp[idx][l][r][take] = val;
}

int compare(const void* a, const void* b)
{
    const int* x = (int*)a;
    const int* y = (int*)b;

    if (*x > *y)
        return 1;
    else if (*x < *y)
        return -1;

    return 0;
}