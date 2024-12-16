package ee.mihkel.veebipood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // võimaldab CRON kasutada, lisaks peab application.properties seadistama
@SpringBootApplication
public class VeebipoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeebipoodApplication.class, args);
	}

}


// 7. R 01.11
// 8. T 05.11 - rollid, autentimine front-endis
// 9. N 14.11 - exceptionid
//		autentimisel --> cart osas token külge++
// 		blokeerida --> kui pole õigusi lehte näha++
//		veateade --> kui pole õigusi lehte näha++

//      expection --> general exception, kõik ülejäänud kukuksid üldisesse
//		Angularis subscribe() joon peal
//		kontrollida, et seal e-maili külge panemata ei tuleks "Kategooriat ei leitud"

//10. R 15.11 -
//11. T 19.11 - API päringud rakendusest välja (pakiautomaadid, makse (tagasi kaupmehe juurde))
//12. R 22.11 - Unit Testid
//13. T 26.11 -
//14. N 28.11
// tõstatage kodustes töödes probleeme. siinses rakenduses probleeme/edasiarendusi
// huvipakkuvaid teemasid
//15. T 03.12 Angular
//16. R 06.12 cron   2ak/h
//17. K 11.12 Docker  9.00-12.15   Docker
//18. K 18.12 lõpuprojekt  4ak/h  9.00-12.15 SecurityConfigus asjade peale panema

// 06.12  2ak/h
// 11.12  4ak/h
// 12.12  4ak/h