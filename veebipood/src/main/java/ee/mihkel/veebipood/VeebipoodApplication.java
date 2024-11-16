package ee.mihkel.veebipood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//15. T 03.12
//16. R 06.12
//17. T 10.12
//18. R 20.12 lõpuprojekt