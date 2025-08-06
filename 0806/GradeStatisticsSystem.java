public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        int sum = 0, max = scores[0], min = scores[0];
        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double avg = sum * 1.0 / scores.length;
        int[] levels = new int[5]; // A~F
        int aboveAvg = 0;

        for (int score : scores) {
            if (score >= 90) levels[0]++;
            else if (score >= 80) levels[1]++;
            else if (score >= 70) levels[2]++;
            else if (score >= 60) levels[3]++;
            else levels[4]++;
            if (score > avg) aboveAvg++;
        }

        System.out.printf("平均：%.2f，最高：%d，最低：%d\n", avg, max, min);
        System.out.printf("A:%d B:%d C:%d D:%d F:%d\n", levels[0], levels[1], levels[2], levels[3], levels[4]);
        System.out.println("高於平均的人數：" + aboveAvg);
        System.out.println("完整成績：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
    }
}
