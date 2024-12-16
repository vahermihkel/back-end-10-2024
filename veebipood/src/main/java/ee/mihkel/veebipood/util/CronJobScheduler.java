package ee.mihkel.veebipood.util;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

// e-maili / SMSi väljasaatmised
// maksed, mis on maksmata seisundis
// andmebaasipuhastus - order

@Component
public class CronJobScheduler {

//    @Autowired
//    OrderRepository orderRepository;
//
//    // küsimärk lõpus -> vastavalt kellaajale
//    //@Scheduled(cron = "0 30 9 * * ?")
//    @Scheduled(cron = "0 * * * * *")
//    public void runEveryMinute() {
//        Date date = new Date();
//        System.out.println(date.getMinutes() + ":" + date.getSeconds());
//    }
//
//    @Scheduled(cron = "0 49 9 * * MON-FRI")
//    public void sendBookingsReminders() {
////        List<Order> orders = orderRepository.findAllByCreationBetween();
////        for (Order o: orders) {
////            o.getPerson().getEmail();
////            // saada kõikidele tellimuse omanikele meeldetuletuse e-mail
////        }
//        System.out.println("Tuli CRON 9:49");
//    }
//
//    @Scheduled(cron = "0 50 9 * * MON-FRI")
//    public void sendBookingsReminders2() {
////        List<Order> orders = orderRepository.findAllByCreationBetween();
////        for (Order o: orders) {
////            o.getPerson().getEmail();
////            // saada kõikidele tellimuse omanikele meeldetuletuse e-mail
////        }
//        System.out.println("Tuli CRON 9:50");
//    }
//
//    @Scheduled(cron = "0/15 * * * * MON-FRI")
//    public void every15Seconds() {
////        List<Order> orders = orderRepository.findAllByCreationBetween();
////        for (Order o: orders) {
////            o.getPerson().getEmail();
////            // saada kõikidele tellimuse omanikele meeldetuletuse e-mail
////        }
//        System.out.println("15 sek tagant");
//    }
//
//    @Scheduled(cron = "0/10 * * 6 * SAT")
//    public void every10Seconds() {
////        List<Order> orders = orderRepository.findAllByCreationBetween();
////        for (Order o: orders) {
////            o.getPerson().getEmail();
////            // saada kõikidele tellimuse omanikele meeldetuletuse e-mail
////        }
//        System.out.println("10 sek tagant");
//    }
}
