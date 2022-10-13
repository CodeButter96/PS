import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private static Scanner sc;
    private static UserSolution usersolution = new UserSolution();

    private static int mSeed;
    private static int pseudo_rand()
    {
        mSeed = mSeed * 214013 + 2531011;
        return (mSeed >> 16) & 0x7FFF;
    }

    static final int CMD_INIT = 100;
    static final int CMD_DESTROY = 200;
    static final int CMD_UPDATE = 300;
    static final int CMD_PARTICIPATE = 400;
    static final int CMD_CANCEL = 500;
    static final int CMD_CALCPROFIT = 600;

    private static void String2Char(char[] buf, String str) {
        Arrays.fill(buf, (char)0);
        for (int i = 0; i < str.length(); ++i)
            buf[i] = str.charAt(i);
        buf[str.length()] = '\0';
    }

    private static int run() throws IOException {
        int isOK = 0;

        int mN, mK = 0, mM;
        int[] mResPrice = new int[10];
        int ResMod=0;
        int mBlockR, mBlockC, mCost;
        int [][] mResInfo= new int [100][100];
        int mR1, mC1, mR2, mC2;
        char[] mCompany = new char[11];
        int mTop;

        int N = sc.nextInt();
        int S = sc.nextInt();

        int cmd, result, check;

        int inputOrder = 0;

        for (int i = 0; i < N; ++i) {

            cmd =  sc.nextInt();
            switch (cmd) {
            case CMD_INIT:
                mSeed = S;
                mN = sc.nextInt();
                mK = sc.nextInt();
                mM = sc.nextInt();
                ResMod = 1 << mM;
                for (int m = 0; m < mM; m++) {
                    mResPrice[m] =  sc.nextInt();
                }
                usersolution.init(mN, mK, mM, mResPrice);
                inputOrder = 0;
                isOK = 1;
                break;

            case CMD_UPDATE:
                mBlockR = sc.nextInt();
                mBlockC = sc.nextInt();
                mCost = sc.nextInt();
                if (S != -1) {
                    if(inputOrder % 6 == 0){
                        for (int r = 0; r < mK; r++) {
                            for (int c = 0; c < mK; c++) {
                                mResInfo[r][c] =  sc.nextInt();
                            }
                        }
                    }else {
                        for (int r = 0; r < mK; r++) {
                            for (int c = 0; c < mK; c++) {
                                mResInfo[r][c] = pseudo_rand() % ResMod;
                            }
                        }
                    }
                } else {
                    mResInfo[0][0] = sc.nextInt();
                    mResInfo[0][1] = sc.nextInt();
                    mResInfo[1][0] = sc.nextInt();
                    mResInfo[1][1] = sc.nextInt();
                }
                usersolution.update(mBlockR, mBlockC, mCost, mResInfo);
                inputOrder++;
                break;

            case CMD_PARTICIPATE:
                String2Char(mCompany,  sc.next());
                mR1 = sc.nextInt();
                mC1 = sc.nextInt();
                mR2 = sc.nextInt();
                mC2 = sc.nextInt();
                result = usersolution.participate(mCompany, mR1, mC1, mR2, mC2);
                check = sc.nextInt();
                if (result != check)
                    isOK = 0;
                break;

            case CMD_CANCEL:
                String2Char(mCompany,  sc.next());
                usersolution.cancel(mCompany);
                break;

            case CMD_CALCPROFIT:
                mTop = sc.nextInt();
                result = usersolution.calcProfit(mTop);
                check = sc.nextInt();
                if (result != check)
                    isOK = 0;
                break;
            }
        }
        usersolution.destroy();
        return isOK;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;
        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        sc = new Scanner(System.in);

        T = sc.nextInt();
        MARK = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            if (run() == 1)
                System.out.println("#" + tc + " " + MARK);
            else
                System.out.println("#" + tc + " 0");
        }
        sc.close();
    }
}