#pragma warning(disable:4996)
#include<iostream>
#include<string>
#include<cstring>

using namespace std;

long long dp[10000][16];

int main(int argc, char** argv)
{
	int test_case;
	int T;
	freopen("sample_input.txt", "r", stdin);
	cin >> T;

	for (test_case = 1; test_case <= T; ++test_case)
	{
		
		string input;
		cin >> input;

		cout << input << endl;

		long long cnt = 0;
		//memset(dp, 0, sizeof(dp));

		int n = input.size();

		/*for (int d = 0; d < n; d++) {
			int admin = 1<<(input[d] - 'A');
			for (int i = 1; i < 16; i++) {
				if (d == 0) {
					if ((admin & i) != 0 && (i & 1) != 0) {
						dp[d][i]++;
					}
				}
				else {
					for (int j = 1; j < 16; j++) {
						if ((i&j) != 0 && (i&admin) != 0) {
							dp[d][i] = (dp[d][i] + dp[d - 1][j]) % 1000000007;
						}
					}
				}

				
			}
		}*/
		//for (int i = 1; i < 16; i++) {
			//cnt = (cnt + dp[n - 1][i]) % 1000000007;
		//}

		cout << cnt << endl;


	}
	return 0;
}