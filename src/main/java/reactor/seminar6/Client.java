package reactor.seminar6;

import reactor.seminar6.service.FootballService;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        FootballService footballService = new FootballService();

        Thread.sleep(10000L);
    }
}
