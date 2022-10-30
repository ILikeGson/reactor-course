package reactor.seminar2.home;

import reactor.seminar2.classroom.gateway.OfficialJokeClient;
import reactor.seminar2.home.service.MessageService;

public class Main {
    public static void main(String[] args) {

    }

    private static void task_1() {
        MessageService messageService = new MessageService();
        OfficialJokeClient jokeClient = new OfficialJokeClient();

        // 1. Сгенерировать коллекцию/стрим из емейлов (В классе Generator есть метод generateEmail)
        // 2. Создать Flux из коллекции/стрима и для каждого емайла - сходить на https://official-joke-api.appspot.com/random_joke
        //       (с помощью OfficialJokeClient) и запросить шутку (метод getJoke()) (Например, используя оператор map)
        // 3. Каждому клиенту отправить шутку используя messageService (оператор doOnNext)
        // 4. Выполнять эту логику только, если есть подписчик
    }

    private static void task_2() {
        // Мы находимся на станции подзарядки электромобилей. В очереди одни теслы. К сожалению из-за сбоя в системе заряжаться
        //      могут только автомобили модели Cybertrack.
        // Необходимо нагенерировать очередь из машин и отфильтровать, оставляя в рядах только модели Cybertrack
        // Вся логика должна выполняться только при наличии подписчика.
    }
}