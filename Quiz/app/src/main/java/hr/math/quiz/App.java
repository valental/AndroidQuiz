package hr.math.quiz;

import android.app.Application;
import android.util.Log;

import java.util.List;

import hr.math.quiz.entities.MyObjectBox;
import hr.math.quiz.entities.Question;
import hr.math.quiz.entities.QuestionEN;
import hr.math.quiz.entities.QuestionHR;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public class App extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();
        populateDatabase();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    private void populateDatabase() {
        Box<Question> questionBox = boxStore.boxFor(Question.class);
        Box<QuestionHR> questionHRBox = boxStore.boxFor(QuestionHR.class);
        Box<QuestionEN> questionENBox = boxStore.boxFor(QuestionEN.class);

        QuestionHR qhr;
        QuestionEN qen;
        Question question;

        long hrId, enId;

        qhr = new QuestionHR(1, "Koji je tim osvojio NBA naslov 2011. godine?", "Dallas Mavericks", "Miami Heat", "Houston Rockets", "Boston Celtics");
        qen = new QuestionEN(1, "Which team won the NBA title in 2011?", "Dallas Mavericks", "Miami Heat", "Houston Rockets", "Boston Celtics");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(1, 1, 1, 1, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(2, "Opseg nogometne lopte je (u cm)?", "56-58", "60-62", "68-70", "74-76");
        qen = new QuestionEN(2, "The perimiter of the football ball is (in cm)?", "56-58", "60-62", "68-70", "74-76");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(2, 1, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(3, "_ bio je najpoznatiji hrvatski košarkaš u NBA-u.", "Dario Šarić", "Dražen Petrović", "Bojan Bogdanović", "Dino Rađa");
        qen = new QuestionEN(3, "_ was the most famous Croatian basketball player in the NBA.", "Dario Šarić", "Dražen Petrović", "Bojan Bogdanović", "Dino Rađa");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(3, 1, 2, 2, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(4, "Prva sezona utrke Formule 1 bila je koje godine?", "1948.", "1950.", "1952.", "1954.");
        qen = new QuestionEN(4, "The first season of the Formula 1 race was in?", "1948.", "1950.", "1952.", "1954.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(4, 1, 1, 2, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(5, "Koje su godine održane Mediteranske igre u Splitu?", "1977.", "1978.", "1979.", "1980.");
        qen = new QuestionEN(5, "What year did the Mediterranean Games take place in Split?", "1977.", "1978.", "1979.", "1980.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(5, 1, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(6, "_ nije sudjelovala na prvim OI.", "Danska", "Mađarska", "Švicarska", "Rusija");
        qen = new QuestionEN(6, "_ did not participate in the first OG?", "Denmark", "Hungary", "Swizerland", "Russia");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(6, 1, 2, 4, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(7, "Koje je godine odigrana prva košarkaška utakmica?", "1890", "1891", "1893", "1895");
        qen = new QuestionEN(7, "What year did the first basketball match take place?", "1890", "1891", "1893", "1895");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(7, 1, 1, 2, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(8, "Koliko je bacačkih disciplina u atletskom desetoboju?", "2", "3", "4", "5");
        qen = new QuestionEN(8, "How many throwing disciplines are there in the athletic decal?", "2", "3", "4", "5");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(8, 1, 1, 2, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(9, "Gdje je boksač Mate Parlov osvojio zlato na OI?", "Rim 1960.", "Tokio 1964.", "Mexico City 1968.", "München 1972.");
        qen = new QuestionEN(9, "Where did the boxer Mate Parlov win gold at the OG?", "Rome 1960.", "Tokyo 1964.", "Mexico City 1968.", "München 1972.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(9, 1, 1, 4, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(10, "Hrvatska reprezentacija osvojila je srebro 2018. na SP u kojoj državi?", "Njemačka", "Francuska", "Rusija", "Poljska");
        qen = new QuestionEN(10, "The Croatian national team won the silver medal at the 2018. World Cup in what country?", "Germany", "France", "Russia", "Poland");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(10, 1, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(11, "Talijanski nogometni klub A.S. Bari svoje utakmice igra na?", "Stadio Enzo Ricci", "Stadio San Paolo", "Stadio San Nicola", "Stadio Delle Alpi");
        qen = new QuestionEN(11, "Italian Football Club A.S. Bari plays their matches at?", "Stadio Enzo Ricci", "Stadio San Paolo", "Stadio San Nicola", "Stadio Delle Alpi");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(11, 2, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(12, "Najbolji strijelac engleske nogometne lige je?", "Thierry Henry", "Andrew Cole", "Alan Shearer", "Gary Lineker");
        qen = new QuestionEN(12, "The best scorer in the English Football League is?", "Thierry Henry", "Andrew Cole", "Alan Shearer", "Gary Lineker");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(12, 2, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(13, "Koliko je puta u nizu Bjorn Borg osvajao Wimbledon?", "3", "7", "9", "5");
        qen = new QuestionEN(13, "How many times in a row did Bjorn Borg won Wimbledon?", "3", "7", "9", "5");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(13, 2, 1, 4, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(14, "Hrvatska muška košarkaška reprezentacija osvojila je srebro na OI koje godine?", "1992.", "1996.", "2000.", "2004.");
        qen = new QuestionEN(14, "In what year did the Croatian men's basketball team win silver at the OG?", "1992.", "1996.", "2000.", "2004.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(14, 2, 1, 1, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(15, "Visina mreže u tenisu na rubovima je?", "92 cm", "107 cm", "124 cm", "147 cm");
        qen = new QuestionEN(15, "The height of the tennis net at the edges is?", "92 cm", "107 cm", "124 cm", "147 cm");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(15, 2, 1, 2, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(16, "Koji tim je osvojio NBA naslov 2008. godine?", "Orlando Magic", "Boston Celtics", "Dallas Mavericks", "San Antonio Spurs");
        qen = new QuestionEN(16, "Which team won the NBA title in 2008?", "Orlando Magic", "Boston Celtics", "Dallas Mavericks", "San Antonio Spurs");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(16, 2, 1, 2, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(17, "Koji je klub osvoji UEFA Champions League 2010?", "Inter", "Real Madrid", "Bayern", "Barcelona");
        qen = new QuestionEN(17, "Which club won UEFA Champions League 2010?", "Inter", "Real Madrid", "Bayern", "Barcelona");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(17, 2, 1, 1, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(18, "Najbolji golfer je?", "Kevin Durant", "Robert Fischer", "Mario Andretti", "Tiger Woods");
        qen = new QuestionEN(18, "The best golfer is?", "Kevin Durant", "Robert Fischer", "Mario Andretti", "Tiger Woods");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(18, 2, 1, 4, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(19, "Najbolji strijelac španjolske nogometne lige je?", "Zarra", "Hugo Sanchez", "Blanco Raul", "Cesar");
        qen = new QuestionEN(19, "The best scorer in the Spanish Football League is?", "Zarra", "Hugo Sanchez", "Blanco Raul", "Cesar");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(19, 2, 1, 1, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(20, "Širina gola u rukometu je?", "3 m", "3,2 m", "3,4 m", "3,6 m");
        qen = new QuestionEN(20, "Width of the goal in handball is?", "3 m", "3,2 m", "3,4 m", "3,6 m");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(20, 2, 1, 1, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(21, "Svjetski rekord u dizanju utega je?", "213 kg", "237 kg", "263 kg", "281 kg");
        qen = new QuestionEN(21, "World record in weightlifting is?", "213 kg", "237 kg", "263 kg", "281 kg");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(21, 3, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(22, "Najviše osvojenih zlatnih medalja na EP u vaterpolu ima?", "Mađarska", "Jugoslavija", "Grčka", "Hrvatska");
        qen = new QuestionEN(22, "Who won the most gold medals in Europe in water polo?", "Hungary", "Yugoslavia", "Greece", "Croatia");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(22, 3, 1, 1, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(23, "Koja je reprezentacija osvojila SP u nogometu 1959.g?", "Australija", "Čile", "Urugvaj", "Brazil");
        qen = new QuestionEN(23, "Which team won the World Cup in football in 1959?", "Australia", "Chile", "Uruguay", "Brazil");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(23, 3, 1, 4, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(24, "U kojoj je zemlji održano prvo SP u nogometu 1930.g?", "Brazil", "Paragvaj", "Engleska", "Urugvaj");
        qen = new QuestionEN(24, "In what country was the first football World Cup in 1930 held?", "Brazil", "Paraguay", "England", "Uruguay");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(24, 3, 1, 4, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(25, "Talijanski nogometni klub iz Bergama je?", "Atalanta", "Sampdoria", "Chievo", "Lazio");
        qen = new QuestionEN(25, "The Italian football club from Bergamo is?", "Atalanta", "Sampdoria", "Chievo", "Lazio");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(25, 3, 1, 1, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(26, "Koji klub je najviše puta osvojio portugalsko nogometno prvenstvo?", "Porto", "Sporting Lisabon", "Benfica Lisabon", "Boavista Porto");
        qen = new QuestionEN(26, "Which club won the Portuguese Football Championship the most times?", "Porto", "Sporting Lisabon", "Benfica Lisabon", "Boavista Porto");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(26, 3, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(27, "Koja je reprezentacija osvojila SP u nogometu 1938.g?", "Italija", "Meksiko", "SAD", "Kenija");
        qen = new QuestionEN(27, "Which team won the World Cup in football in 1938?", "Italy", "Mexico", "USA", "Kenia");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(27, 3, 1, 1, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(28, "Koje je godine rođen tenisač Andre Agassi?", "1964.", "1970.", "1968.", "1966.");
        qen = new QuestionEN(28, "Tennis player Andre Agassi was born in?", "1964.", "1970.", "1968.", "1966.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(28, 3, 1, 2, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(29, "Cristiano Ronaldo trenutno igra u?", "Barcelona", "Real Madrid", "Juventus", "Bayern");
        qen = new QuestionEN(29, "Cristiano Ronaldo is currently playing for?", "Barcelona", "Real Madrid", "Juventus", "Bayern");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(29, 3, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(30, "Koje godine je Engleska osvojila SP u nogometu?", "1934.", "1858.", "1966.", "1974.");
        qen = new QuestionEN(30, "What year did England won the World Cup in football?", "1934.", "1858.", "1966.", "1974.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(30, 3, 1, 3, Question.Category.SPORT, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(31, "Koja je normalna ljudska temperatura (u C)?", "35", "36", "37", "38");
        qen = new QuestionEN(31, "What is the normal temperature of the human body(in C)?", "35", "36", "37", "38");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(31, 1, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(32, "U kemiji srebro se označava s _", "S", "Ag", "Sr", "Se");
        qen = new QuestionEN(32, "In chemistry silver is denoted by _", "S", "Ag", "Sr", "Se");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(32, 1, 3, 2, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(33, "Magnetski medij za pohranu podataka nije?", "tvrdi disk", "floopy disk", "zip disk", "compact disk");
        qen = new QuestionEN(33, "Which is not magnetic storage media?", "hard disc", "floopy disc", "zip disc", "compact disc");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(33, 1, 1, 4, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(34, "Poremećaj prepoznavanja boja zovemo?", "astigmatizam", "miopija", "ambliopija", "daltonizam");
        qen = new QuestionEN(34, "Color recognition disorder is called?", "astigmatism", "myopia", "amblyopia", "daltonism");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(34, 1, 1, 4, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(35, "Ekstenzija \"xslx\" ukazuje na?", "zvuk", "tekst", "proračunsku tablicu", "prezentaciju");
        qen = new QuestionEN(35, "Extension \"xslx\" points to?", "sound", "text", "spreadsheet", "presentation");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(35, 1, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(36, "Mjerna jedinica jačine magnetskog polja je?", "Tesla", "Weber", "Ohm", "Watt");
        qen = new QuestionEN(36, "The magnetic field strength measuring unit is?", "Tesla", "Weber", "Ohm", "Watt");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(36, 1, 1, 1, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(37, "Koje boje je dragi kamen Smaragd?", "plava", "crvena", "žuta", "zelena");
        qen = new QuestionEN(37, "What's the color of Emerald?", "blue", "red", "yellow", "green");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(37, 1, 1, 4, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(38, "Kemijska oznaka sumporne kiseline je?", "H2SO4", "H2S", "SO2", "H2SO2");
        qen = new QuestionEN(38, "The chemical name of sulfuric acid is?", "H2SO4", "H2S", "SO2", "H2SO2");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(38, 1, 1, 1, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(39, "Znanost koja proučava biljke zove se?", "botanika", "ekologija", "šumarstvo", "agronomija");
        qen = new QuestionEN(39, "Science that studies plants is called?", "botany", "ecology", "forestry", "agronomy");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(39, 1, 1, 1, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(40, "Anosmija je odsutnost osjeta?", "vida", "sluha", "mirisa", "dodira");
        qen = new QuestionEN(40, "Anosmia is loss of the sense of?", "vision", "hearing", "scents", "contact");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(40, 1, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(41, "Atmosfera Marsa je primarno sastavljena od?", "CO2", "H", "O", "N");
        qen = new QuestionEN(41, "The atmosphere of Mars is primarily composed of?", "CO2", "H", "O", "N");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(41, 2, 1, 1, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(42, "Python je stvoren?", "1970.", "1980.", "1990.", "2000.");
        qen = new QuestionEN(42, "Python was created in?", "1970.", "1980.", "1990.", "2000.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(42, 2, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(43, "Heksadecimalni broj A2 je dekadski broj?", "112", "162", "254", "312");
        qen = new QuestionEN(43, "Hexadecimal number A2 is a decade number?", "112", "162", "254", "312");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(43, 2, 1, 2, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(44, "Koja je domena Sjeverne Koreje?", "nc", "sk", "kr", "nk");
        qen = new QuestionEN(44, "What is the domain of North Korea?", "nc", "sk", "kr", "nk");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(44, 2, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(45, "Što nije bolest oka?", "astigmatizam", "otitis", "glaukom", "katarakta");
        qen = new QuestionEN(45, "What is not an eye disease?", "astigmatism", "otitis", "glaucoma", "cataract");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(45, 2, 1, 2, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(46, "Koliko posto mase čine svi mišići ljudskog tijela?", "0.2", "0.3", "0.4", "0.5");
        qen = new QuestionEN(46, "What percentage of the human body do the muscules muscles make up?", "0.2", "0.3", "0.4", "0.5");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(46, 2, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(47, "Koji dio mozga je odgovoran za koordinaciju?", "talamus", "hipotalamus", "veliki mozak", "mali mozak");
        qen = new QuestionEN(47, "Which part of the brain is responsible for coordination?", "thalamus", "hypothalamus", "big brain", "cerebellum");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(47, 2, 1, 4, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(48, "Jedna stopa iznosi?", "0,3 m", "0,4 m", "0,5 m", "0,6 m");
        qen = new QuestionEN(48, "One foot has how many meters?", "0,3 m", "0,4 m", "0,5 m", "0,6 m");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(48, 2, 1, 1, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(49, "Izmjeničnu struju izmislio je?", "Watt", "Tesla", "Kelvin", "Born");
        qen = new QuestionEN(49, "Alternate current was invented by?", "Watt", "Tesla", "Kelvin", "Born");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(49, 2, 1, 2, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(50, "Kvadrilijun ima koliko nula?", "12", "24", "36", "48");
        qen = new QuestionEN(50, "Quadrillion has how many zeros?", "12", "24", "36", "48");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(50, 2, 1, 2, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(51, "Standard USB 2.0 omogućuje prijenos podataka brzinom od?", "240 Mbit/s", "480 Mbit/s", "960 Mbit/s", "1024 Mbit/s");
        qen = new QuestionEN(51, "Standard USB 2.0 enables data transfer with speed of?", "240 Mbit/s", "480 Mbit/s", "960 Mbit/s", "1024 Mbit/s");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(51, 3, 1, 2, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(52, "Triton i Nereid su dva velika satelita planeta?", "Neptun", "Saturn", "Jupiter", "Uran");
        qen = new QuestionEN(52, "Triton and Nereid are two great satellites of planet?", "Neptune", "Saturn", "Jupiter", "Uranus");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(52, 3, 1, 1, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(53, "Kovalentnu vezu tvore?", "metali", "nemetali", "kovine", "platinski metali");
        qen = new QuestionEN(53, "Covalent bonds are formed from?", "metals", "nonmetals", "ore", "platinum metals");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(53, 3, 1, 2, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(54, "Koja žlijezda luči adrenalin?", "nadbubrežna", "gušterača", "hipofiza", "epifiza");
        qen = new QuestionEN(54, "Which gland raises the adrenaline?", "adrenal gland", "pancreas", "hypophysis", "pineal");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(54, 3, 1, 1, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(55, "U kojem je mjesecu Zemlja najbliža Suncu?", "ožujak", "lipanj", "rujan", "siječanj");
        qen = new QuestionEN(55, "In what month is the Earth closest to the Sun?", "march", "june", "september", "january");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(55, 3, 1, 4, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(56, "Koliko mišića ima u ljudskom tijelu?", "oko 100", "oko 300", "oko 500", "oko 700");
        qen = new QuestionEN(56, "Human body has how many muscles?", "around 100", "around 300", "around 500", "around 700");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(56, 3, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(57, "Koji je od navedenih planeta najgušći?", "Jupiter", "Zemlja", "Pluton", "Merkur");
        qen = new QuestionEN(57, "Which of these planets is the most dense?", "Jupiter", "Earth", "Pluto", "Mercury");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(57, 3, 1, 2, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(58, "Koliko satelita ima Merkur?", "0", "1", "2", "4");
        qen = new QuestionEN(58, "Mercury has how many satellites?", "0", "1", "2", "4");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(58, 3, 1, 1, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(59, "Znanost o tlu zove se?", "melioracija", "limnologija", "pedologija", "geodezija");
        qen = new QuestionEN(59, "Science of soil is called?", "melioration", "limnology", "pedology", "geodesy");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(59, 3, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(60, "U periodnom sustavu elementi su poredani po broju?", "neutrona", "aniona", "protona", "bozona");
        qen = new QuestionEN(60, "In the periodic system the elements are sorted by number of?", "neutrons", "anions", "protons", "bosons");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(60, 3, 1, 3, Question.Category.SCIENCE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(61, "Hadrijanov zid nalazio se u?", "Britaniji", "Dalmaciji", "Palestini", "Daciji");
        qen = new QuestionEN(61, "Hadrian's wall was built in?", "Britain", "Dalmatia", "Palestine", "Dation");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(61, 1, 1, 1, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(62, "Na kojem otoku je umro Napoleon?", "Sardinija", "Sveta Helena", "Korzika", "Sicilija");
        qen = new QuestionEN(62, "On what island did Napoleon die?", "Sardinia", "Saint Helena", "Corsica", "Sicily");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(62, 1, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(63, "Najveći rimski pisac komedija je?", "Ciceron", "Plaut", "Petronije", "Lukrecije");
        qen = new QuestionEN(63, "The greatest roman comedy writer is?", "Cicero", "Plaute", "Petronius", "Lucrecius");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(63, 1, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(64, "Zastava koje države ne sadrži crvenu boju?", "Španjolske", "Japana", "Njemačke", "Irske");
        qen = new QuestionEN(64, "Which country flag does not contain the color red?", "Spain", "Japan", "Germany", "Irland");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(64, 1, 1, 4, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(65, "Tko je uništio Kartagu?", "Rimljani", "Feničani", "Arapi", "Beduini");
        qen = new QuestionEN(65, "Who destroyed the Carthage?", "Romans", "Phoenicians", "Arabs", "Bedouins");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(65, 1, 1, 1, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(66, "Roman \"Oliver Twist\" napisao je?", "Gustave Flaubert", "Charles Dickens", "George Eliot", "Walter Scott");
        qen = new QuestionEN(66, "Who wrote \"Oliver Twist\"?", "Gustave Flaubert", "Charles Dickens", "George Eliot", "Walter Scott");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(66, 1, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(67, "Tko je bio Aristotelov učitelj?", "Euklid", "Platon", "Pitagora", "Sokrat");
        qen = new QuestionEN(67, "Who was Aristotle's teacher?", "Euclid", "Plato", "Pythagoras", "Socrates");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(67, 1, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(68, "Koje godine je stvoren NATO savez?", "1955.", "1945.", "1958.", "1949.");
        qen = new QuestionEN(68, "What year was the NATO alliance created?", "1955.", "1945.", "1958.", "1949.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(68, 1, 1, 4, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(69, "Hercule Poirot je lik romana koje spisateljice?", "Charlotte Bronte", "Jane Austen", "Agatha Christie", "Virginia Woolf");
        qen = new QuestionEN(69, "Hercule Poirot is a character from?", "Charlotte Bronte", "Jane Austen", "Agatha Christie", "Virginia Woolf");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(69, 1, 1, 3, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(70, "U kojem gradu je donesen Ustav SAD-a?", "Bostonu", "Washingtonu", "Philadelphiji", "New Yorku");
        qen = new QuestionEN(70, "In what city was the Constitution of the United States written?", "Boston", "Washington", "Philadelphia", "New York");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(70, 1, 1, 3, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(71, "Koje godine su u Italiji fašisti uveli diktaturu?", "1918.", "1922.", "1926.", "1933.");
        qen = new QuestionEN(71, "What year did fascists introduce dictatorship in Italy?", "1918.", "1922.", "1926.", "1933.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(71, 2, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(72, "Radnja romana \"Starac i more\" odvija se na otoku?", "Jamajka", "Hispaniola", "Kuba", "Aruba");
        qen = new QuestionEN(72, "The story of \"Old Man and the Sea\" takes place on the island?", "Jamaica", "Hispaniola", "Cuba", "Aruba");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(72, 2, 1, 3, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(73, "\"Otac\" Sherlocka Holmesa je?", "A. Conan Doyle", "G. Eliot", "D. Defoe", "W. Blake");
        qen = new QuestionEN(73, "The character of Sherlock Holmes was created by?", "A. Conan Doyle", "G. Eliot", "D. Defoe", "W. Blake");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(73, 2, 1, 1, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(74, "Gdje se nalazi Galerija Uffizi?", "Firenca", "Novara", "Torino", "Piancenza");
        qen = new QuestionEN(74, "Where is the Uffizi Galerie located?", "Firenze", "Novare", "Torino", "Piancenza");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(74, 2, 1, 1, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(75, "Tragediju Antigona napisao je?", "Homer", "Euripid", "Teokrit", "Sofoklo");
        qen = new QuestionEN(75, "Who wrote the tragedy Antigona?", "Homer", "Euripidus", "Teokritius", "Sophocles");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(75, 2, 1, 4, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(76, "Koji je njemački vladar uspostavio Sveto Rimsko Carstvo?", "Henrik I.", "Oton I.", "Oton II.", "Oton III.");
        qen = new QuestionEN(76, "Who established the Holy Roman Empire?", "Henrik I.", "Oton I.", "Oton II.", "Oton III.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(76, 2, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(77, "Kratku priču \"Crni mačak\" napisao je?", "Mark Twain", "Zane Grey", "Edgar Allan Poe", "Truman Capote");
        qen = new QuestionEN(77, "Who wrote the story \"Black cat\"?", "Mark Twain", "Zane Grey", "Edgar Allan Poe", "Truman Capote");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(77, 2, 1, 3, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(78, "U kojem je gradu rođen Kristofor Kolumbo?", "Palermo", "Venecija", "Genova", "Rim");
        qen = new QuestionEN(78, "Christopher Colimbus was born in?", "Palermo", "Venice", "Genova", "Rome");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(78, 2, 1, 3, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(79, "Koje godine je tiskana prva hrvatska knjiga?", "1482.", "1481.", "1484.", "1483.");
        qen = new QuestionEN(79, "What year was the first Croatian book printed?", "1482.", "1481.", "1484.", "1483.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(79, 2, 1, 4, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(80, "\"Guernicu\" je naslikao?", "Rembrant", "Picasso", "Van Gogh", "Dali");
        qen = new QuestionEN(80, "Who painted \"Guernica\"?", "Rembrant", "Picasso", "Van Gogh", "Dali");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(80, 2, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(81, "Koliko je dana postojala Pariška komuna?", "61", "72", "95", "127");
        qen = new QuestionEN(81, "How many days did the Municipality of Paris exist?", "61", "72", "95", "127");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(81, 3, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(82, "Koji od navedenih nije dobio Nobelovu nagradu za književnost?", "Albert Camus", "Andre Gide", "Anatole France", "Marcel Proust");
        qen = new QuestionEN(82, "Who didn't get the Nobel price for literature?", "Albert Camus", "Andre Gide", "Anatole France", "Marcel Proust");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(82, 3, 1, 4, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(83, "Rusko-Japanski rat vodio se?", "1904-1905", "1875-1879", "1861-1863", "1855-1858");
        qen = new QuestionEN(83, "When was the Russian-Japanese war?", "1904-1905", "1875-1879", "1861-1863", "1855-1858");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(83, 3, 1, 1, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(84, "Umjetnički stil Pop art javio se u?", "Kanadi", "Francuskoj", "Engleskoj", "Italiji");
        qen = new QuestionEN(84, "Artistic style Pop art appeared in?", "Canada", "France", "England", "Italy");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(84, 3, 1, 3, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(85, "U kojoj državi je nastao glazbeni žanr Fado?", "Španjolska", "Portugal", "Italija", "Grčka");
        qen = new QuestionEN(85, "In which country was the music genre Fado formed?", "Spain", "Portugal", "Italy", "Greece");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(85, 3, 1, 2, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(86, "Koje je godine osnovana Hrvatska pučka seljačka stranka?", "1904.", "1896.", "1912.", "1920.");
        qen = new QuestionEN(86, "What year was the Croatian Peasant Party founded?", "1904.", "1896.", "1912.", "1920.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(86, 3, 1, 1, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(87, "Tko je bio japanski premijer u Drugom svjetskom ratu?", "Hideki Tojo", "Hideki Mojo", "Tashiba Cojo", "Mashuba Jojo");
        qen = new QuestionEN(87, "Who was the Japanese Prime Minister in the Second World War?", "Hideki Tojo", "Hideki Mojo", "Tashiba Cojo", "Mashuba Jojo");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(87, 3, 1, 1, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(88, "Koliko krugova ima pakao u Danteovoj \"Božanstvenoj komediji\"?", "5", "7", "9", "11");
        qen = new QuestionEN(88, "How many circles of hell are there in Dante's \"Divine Comedy\"?", "5", "7", "9", "11");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(88, 3, 1, 3, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(89, "Ustanak Mau Mau je pobuna naroda u?", "Angoli", "Sudanu", "Senegalu", "Keniji");
        qen = new QuestionEN(89, "The rise of Mau Mau is the rebellion of the people in?", "Angola", "Sudan", "Senegal", "Kenya");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(89, 3, 1, 4, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(90, "Trojanski rat započeo je zbog otmice kraljice?", "Penelopa", "Kasiopeja", "Helena", "Antiopa");
        qen = new QuestionEN(90, "Trojan war was begun because of the kidnaping of queen?", "Penelopa", "Kasiopeja", "Helena", "Antiopa");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(90, 3, 1, 3, Question.Category.HISTORY_ART, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(91, "Glavni grad Rusije je?", "Minsk", "Sankt Peterburg", "Moskva", "Soči");
        qen = new QuestionEN(91, "The capital of Russia is?", "Minsk", "Saint Petersburg", "Moscow", "Sochi");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(91, 1, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(92, "Kako se zove najduža rijeka u Africi?", "Nil", "Okavango", "Oranje", "Tay");
        qen = new QuestionEN(92, "What is the longest river in Africa?", "Nile", "Okavango", "Oranje", "Tay");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(92, 1, 1, 1, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(93, "Kroz Sarajevo, glavni grad BIH, teče?", "Neretva", "Željeznica", "Miljacka", "Drina");
        qen = new QuestionEN(93, "What river flows through Sarajevo, the capital city of Bosnia and Herzegovina?", "Neretva", "Željeznica", "Miljacka", "Drina");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(93, 1, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(94, "Kineski zid je dug?", "8851,8 km", "7256,3 km", "6514,2 km", "3568,9 km");
        qen = new QuestionEN(94, "How long is the Chinese Wall?", "8851,8 km", "7256,3 km", "6514,2 km", "3568,9 km");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(94, 1, 1, 1, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(95, "S kojom državom Belgija ne graniči?", "Francuska", "Austrija", "Nizozemska", "Luksemburg");
        qen = new QuestionEN(95, "Which country does not border with Belgium?", "France", "Austria", "Netherlands", "Luxembourg");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(95, 1, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(96, "U kojoj se državi nalaze Apenini?", "Austrija", "Italija", "Slovenija", "Portugal");
        qen = new QuestionEN(96, "Apennines are located in?", "Austria", "Italy", "Slovenia", "Portugal");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(96, 1, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(97, "Tko je otkrio Novi Zeland?", "Mirko Seljan", "George Vancouver", "Abel Tasman", "Hernan Cortes");
        qen = new QuestionEN(97, "Who discovered New Zealand?", "Mirko Seljan", "George Vancouver", "Abel Tasman", "Hernan Cortes");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(97, 1, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(98, "Prosječna dubina Jadranskog mora je?", "21 m", "77 m", "104 m", "173 m");
        qen = new QuestionEN(98, "The average depth of the Adriatic Sea is?", "22 m", "78 m", "105 m", "174 m");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(98, 1, 1, 4, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(99, "U kojoj državi se nalazi vrh Kilimandžaro?", "Kenija", "Uganda", "Tanzanija", "Ruanda");
        qen = new QuestionEN(99, "The top of Kilimanjaro is in?", "Kenya", "Uganda", "Tanzania", "Ruanda");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(99, 1, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(100, "Na kojem se hrvatskom otoku nalaze Košljun i Kolan?", "Hvar", "Brač", "Dugi otok", "Pag");
        qen = new QuestionEN(100, "On which Croatian island are Košljun and Kolan?", "Hvar", "Brač", "Long Island", "Pag");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(100, 1, 1, 4, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(101, "Kielski kanal povezuje Sjeverno more i?", "Grenlandsko more", "Baltičko more", "Barentsovo more", "Norveško more");
        qen = new QuestionEN(101, "The Kiel Channel connects the North Sea and?", "Grenland Sea", "Baltic Sea", "Barents Sea", "Norwegian Sea");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(101, 2, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(102, "Koja rijeka ne utječe u Crno more?", "Dunav", "Dnjepar", "Don", "Volta");
        qen = new QuestionEN(102, "Which river does not flow into the Black Sea?", "Dunav", "Dnjepar", "Don", "Volta");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(102, 2, 1, 4, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(103, "Koja država nema izlaz na more?", "Senegal", "Maroko", "Niger", "Nigerija");
        qen = new QuestionEN(103, "Which countries does not have a sea exit?", "Senegal", "Mrocco", "Niger", "Nigeria");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(103, 2, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(104, "Najveća rijeka u Južnoafričkoj Republici je?", "Tay", "Oranje", "Po", "Okavango");
        qen = new QuestionEN(104, "The largest river in Republic of South Africa is?", "Tay", "Oranje", "Po", "Okavango");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(104, 2, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(105, "Kroz koju državu ne protječe rijeka Eufrat?", "Sirija", "Irak", "Jordan", "Turska");
        qen = new QuestionEN(105, "River Euphrates does not flow through?", "Syria", "Iraq", "Jordan", "Turkey");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(105, 2, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(106, "Uz malteški koji je službeni jezik Malte?", "francuski", "talijanski", "engleski", "arapski");
        qen = new QuestionEN(106, "The official languages of Malta are maltese and?", "french", "italian", "english", "arabian");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(106, 2, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(107, "Duljina ekvatora je približno?", "40 000 km", "10 000 km", "20 000 km", "80 000 km");
        qen = new QuestionEN(107, "The length of the equator is approximately?", "40 000 km", "10 000 km", "20 000 km", "80 000 km");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(107, 2, 1, 1, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(108, "Površinski najmanja država SAD-a je?", "New Yersey", "Rhode Island", "Havaji", "Maryland");
        qen = new QuestionEN(108, "The smallest state in the United States is?", "New Yersey", "Rhode Island", "Hawaii", "Maryland");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(108, 2, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(109, "Koja država ne pripada Skandinaviji?", "Danska", "Finska", "Švedska", "Norveška");
        qen = new QuestionEN(109, "Which country does not belong to Scandinavia?", "Denmark", "Finland", "Sweden", "Norway");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(109, 2, 1, 1, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(110, "Koliko je širok Gibraltarski tjesnac?", "7 km", "21 km", "23 km", "14 km");
        qen = new QuestionEN(110, "How wide is the Gibraltar Strait?", "7 km", "21 km", "23 km", "14 km");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(110, 2, 1, 4, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(111, "Najveći vulkan na zemlji po obujmu je?", "Vezuv", "Mauna Loa", "Kilimandžaro", "Etna");
        qen = new QuestionEN(111, "The largest volcano on the Earth by volume is?", "Vesuv", "Mauna Loa", "Kilimanjaro", "Etna");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(111, 3, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(112, "Managua je glavni grad koje države?", "Mali", "Nikaragva", "Salvador", "Honduras");
        qen = new QuestionEN(112, "Managua is the capital of which country?", "Mali", "Nikaragua", "Salvador", "Honduras");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(112, 3, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(113, "Uz urdski,koji je još službeni jezik u Pakistanu?", "engleski", "španjolski", "nizozemski", "francuski");
        qen = new QuestionEN(113, "The official languages of Pakistan are Urdu and?", "English", "Spanish", "Dutch", "French");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(113, 3, 1, 1, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(114, "Uz malgaški koji je službeni jezik na Madagaskaru?", "engleski", "portugalski", "francuski", "španjolski");
        qen = new QuestionEN(114, "The official languages of Madagascar are Malagasy and?", "English", "Portuguese", "French", "Spanish");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(114, 3, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(115, "Koji je službeni jezik u državi Sijera Leone?", "francuski", "engleski", "španjolski", "portugalski");
        qen = new QuestionEN(115, "What is the official language of Sierra Leone?", "French", "English", "Spanish", "Portuguese");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(115, 3, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(116, "Koje godine su Bahami proglasili neovisnost?", "1954.", "1964.", "1973.", "1987.");
        qen = new QuestionEN(116, "What year have the Bahamas declared independence?", "1954.", "1964.", "1973.", "1987.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(116, 3, 1, 3, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(117, "Orly je zračna luka u blizini?", "Pariza", "Londona", "Madrida", "Praga");
        qen = new QuestionEN(117, "Orly is an airport nearby?", "Paris", "London", "Madrid", "Prague");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(117, 3, 1, 1, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(118, "Koji je glavni grad Bocvane?", "Yangon", "Gaboron", "Port Louis", "Praia");
        qen = new QuestionEN(118, "What is the capital of Bocva?", "Yangon", "Gaboron", "Port Louis", "Praia");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(118, 3, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(119, "Koji je glavni grad Vanuatua?", "Funafuti", "Port Vila", "Honiara", "Dushanbe");
        qen = new QuestionEN(119, "What is the capital of Vanuatu?", "Funafuti", "Port Vila", "Honiara", "Dushanbe");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(119, 3, 1, 2, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(120, "Najveći grad u Maroku je?", "Rabat", "Marrakech", "Agadir", "Casablanca");
        qen = new QuestionEN(120, "The biggest city in Morocco is?", "Rabat", "Marrakech", "Agadir", "Casablanca");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(120, 3, 1, 4, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(121, "Tko je bivši muž Jennifer Aniston?", "Matt Damon", "Ben Affleck", "Brad Pitt", "Johnny Depp");
        qen = new QuestionEN(121, "Who is the ex-husband of Jennifer Aniston?", "Matt Damon", "Ben Affleck", "Brad Pitt", "Johnny Depp");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(121, 1, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(122, "Kako se zove morsko čudovište iz filma \"Pirati s Kariba\"?", "Zana ", "Ogopogo", "Kraken", "Kongamato");
        qen = new QuestionEN(122, "What is the name of the Sea Monster from the movie \"Pirates of the Caribbean\"?", "Zana ", "Ogopogo", "Kraken", "Kongamato");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(122, 1, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(123, "Tko glumi Oskara Schindlera u filmu \"Schindlerova lista\"?", "Liam Neeson", "Russell Crowe", "Kevin Spacey", "Kevin Costner");
        qen = new QuestionEN(123, "Who plays Oskar Schindler in the movie \"Schindler's List\"?", "Liam Neeson", "Russell Crowe", "Kevin Spacey", "Kevin Costner");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(123, 1, 1, 1, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(124, "\"Houston, we have a problem\" je rečenica iz filma?", "Fantastična četvorka", "Odredište Mjesec", "Solaris", "Apollo 13");
        qen = new QuestionEN(124, "\"Houston, we have a problem\" is a line from the movie?", "Fantastic four", "Destination Moon", "Solaris", "Apollo 14");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(124, 1, 1, 4, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(125, "Redatelj filma \"Pasija\" je?", "John Ford", "Mel Gibson", "Oliver Stone", "Stanley Kubrick");
        qen = new QuestionEN(125, "The director of the movie \"The Passion of the Christ\" is?", "John Ford", "Mel Gibson", "Oliver Stone", "Stanley Kubrick");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(125, 1, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(126, "Darth Vader je fiktivni lik kojeg serijala?", "Zvjezdane staze", "Ratovi zvijezda", "Indiana Jones", "Gospodar prstenova");
        qen = new QuestionEN(126, "Darth Vader is a fictional character of the franchise?", "Star Trek", "Star Wars", "Indiana Jones", "Lord of the rings");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(126, 1, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(127, "Glavnu ulogu nu filmu \"Doručak kod Tiffany\" tumači?", "Uma Thurman", "Sandra Bullock", "Julia Roberts", "Audrey Hepburn");
        qen = new QuestionEN(127, "WHo plays the main role in the film \"Breakfast at Tiffany\"?", "Uma Thurman", "Sandra Bullock", "Julia Roberts", "Audrey Hepburn");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(127, 1, 1, 4, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(128, "Koja pjevačica glumi u filmu \"Zvijezda je rođena\"?", "Lady Gaga", "Beyonce", "Rihanna", "J-Lo");
        qen = new QuestionEN(128, "Which singer plays in the movie \"Star is born\"?", "Lady Gaga", "Beyonce", "Rihanna", "J-Lo");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(128, 1, 1, 1, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(129, "Neo u filmu \"Matrix\" je?", "Tom Hanks", "Dustin Hoffman", "Keanu Reeves", "Tom Cruise");
        qen = new QuestionEN(129, "Neo in the movie \"Matrix\" is played by?", "Tom Hanks", "Dustin Hoffman", "Keanu Reeves", "Tom Cruise");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(129, 1, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(130, "Koga nije glumio Arnold Schwarzenegger?", "Conan Barbarin", "Rambo", "Kalidor", "Terminator");
        qen = new QuestionEN(130, "Which character Arnold Schwarzenegger did not play?", "Conan Barbarin", "Rambo", "Kalidor", "Terminator");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(130, 1, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(131, "Koji film nije osvojio 11 Oskara?", "Ben Hur", "Priča sa zapadne strane", "Gospodar prstenova", "Titanik");
        qen = new QuestionEN(131, "Which movie did not win 11 Oscars?", "Ben Hur", "West side story", "Lord of the rings", "Titanic");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(131, 2, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(132, "Rečenica \"Show me the money!\" je iz filma?", "Mirni čovjek", "Kišni čovjek", "Jerry Maguire", "Amarcord");
        qen = new QuestionEN(132, "The line \"Show me the money!\" is from the movie?", "The quiet man", "Rain man", "Jerry Maguire", "Amarcord");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(132, 2, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(133, "Film \"Jurski park\" odvija se na otoku?", "Ko Tapu", "Crab", "Nublar", "Lotus");
        qen = new QuestionEN(133, "Movie \"Jurassic park\" takes place on the island?", "Ko Tapu", "Crab", "Nublar", "Lotus");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(133, 2, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(134, "Koji film nije režirao Steven Spielberg?", "Povratak u budućnost", "Umjetna inteligencija", "Jurski park", "Braća Blues");
        qen = new QuestionEN(134, "Which movie was not directed by Steven Spielberg?", "Back to the Future", "Artificial Intelligence", "Jurassic Park", "Blues Brothers");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(134, 2, 1, 4, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(135, "Glavnu žensku ulogu u filmu \"Titanik\" tumači?", "Uma Thurman", "Kate Winslet", "Milla Jovovich", "Jodie Foster");
        qen = new QuestionEN(135, "Who plays the main female role in the movie \"Titanic\"?", "Uma Thurman", "Kate Winslet", "Milla Jovovich", "Jodie Foster");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(135, 2, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(136, "Koje prezime nosi lik Eric iz serijala \"South Park\"?", "Cartman", "Marsh", "Broflovski", "McCormick");
        qen = new QuestionEN(136, "What's the surname of Eric in the \"South Park\" series?", "Cartman", "Marsh", "Broflovski", "McCormick");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(136, 2, 1, 1, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(137, "Tko glumi Izeta u seriji \"Lud, zbunjen, normalan\"?", "Predrag Vušović", "Mustafa Nadarević", "Boris Dvornik", "Ivo Gregurević");
        qen = new QuestionEN(137, "Who plays Izet in the series \"Crazy, confused, normal\"?", "Predrag Vušović", "Mustafa Nadarević", "Boris Dvornik", "Ivo Gregurević");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(137, 2, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(138, "Tko je odigrao glavnu ulogu u filmu \"Hrabro srce\"?", "Liam Neeson", "Clint Eastwood", "Mel Gibson", "Harrison Ford");
        qen = new QuestionEN(138, "Who played the lead role in the movie \"Braveheart\"?", "Liam Neeson", "Clint Eastwood", "Mel Gibson", "Harrison Ford");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(138, 2, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(139, "Glavni lik crtića \"Oblačno s čuftama\" zove se?", "Mate Sardela", "Šljapko", "Ivo Lubin", "Marin Prskalo");
        qen = new QuestionEN(139, "The main character of the cartoon \"Cloudy with Chance of Meatballs\" is called?", "Manny", "Shelbourne", "Sam Sparks", "Flint Lockwood");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(139, 2, 1, 4, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(140, "Kako se zove princeza iz crtića \"Shrek\"?", "Helena ", "Gothel", "Charlotte", "Fiona");
        qen = new QuestionEN(140, "What is the name of the princess in cartoon \"Shrek\"?", "Helena ", "Gothel", "Charlotte", "Fiona");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(140, 2, 1, 4, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(141, "Film \"Đuka Begović\" režirao je?", "Branko Ivanda", "Branko Schmidt", "Lordan Zafranović", "Krsto Papić");
        qen = new QuestionEN(141, "Who directed the movie \"Đuka Begović\"?", "Branko Ivanda", "Branko Schmidt", "Lordan Zafranović", "Krsto Papić");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(141, 3, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(142, "Koje je godine prvi put održan Filmski festival u Cannesu?", "1968.", "1957.", "1946.", "1938.");
        qen = new QuestionEN(142, "What year was the first Cannes Film Festival held?", "1968.", "1957.", "1946.", "1938.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(142, 3, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(143, "Halle Berry osvojila je Oskara za ulogu u filmu?", "X-Men", "Konačna odluka", "Monster's Ball", "Žena mačka");
        qen = new QuestionEN(143, "Halle Berry has won the Oscar for the role in the movie?", "X-Men", "", "Monster's Ball", "Catwoman");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(143, 3, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(144, "Glumica Charlize Theron rođena je u?", "Liberiji", "Keniji", "Senegalu", "Južnoafričkoj Republici");
        qen = new QuestionEN(144, "The actress Charlize Theron was born in?", "Liberia", "Kenya", "Senegal", "Republic of South Africa");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(144, 3, 1, 4, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(145, "Film \"Vlak u snijegu\" nastao je po romanu kojeg pisca?", "Mato Lovrak", "Zvonimir Balog", "Hrvoje Hitrec", "Ivan Kušan");
        qen = new QuestionEN(145, "The movie \"Train in the Snow\" was created from the novel by writer?", "Mato Lovrak", "Zvonimir Balog", "Hrvoje Hitrec", "Ivan Kušan");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(145, 3, 1, 1, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(146, "Glazbu za film \"Dobar, loš, zao\" napisao je?", "Stevie Wonder", "Ennio Morricone", "Henry Mancini", "Jerry Goldsmith");
        qen = new QuestionEN(146, "Music for the movie \"The good, the Bad and the Ugly\" wrote?", "Stevie Wonder", "Ennio Morricone", "Henry Mancini", "Jerry Goldsmith");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(146, 3, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(147, "Zebra, lav, žirafa i nilski konj glavni su likovi kojeg crtića?", "Shrek", "Rio", "Sezona lova", "Madagaskar");
        qen = new QuestionEN(147, "Zebra, lion, giraffe and hippo are the main characters of the cartoon?", "Shrek", "Rio", "Open season", "Madagaskar");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(147, 3, 1, 4, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(148, "Koje je godine prikazan prvi zvučni film?", "1914.", "1921.", "1927.", "1936.");
        qen = new QuestionEN(148, "When was the first sounded movie?", "1914.", "1921.", "1927.", "1936.");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(148, 3, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(149, "Film \"Anđeli i demoni\" nastao je po romanu kojeg pisca?", "Jon Land", "Dan Brown", "Alan Jacobson", "James Rollins");
        qen = new QuestionEN(149, "The movie \"Angels and Demons\" was created from the novel by the writer?", "Jon Land", "Dan Brown", "Alan Jacobson", "James Rollins");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(149, 3, 1, 2, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        qhr = new QuestionHR(150, "Glavnu ulogu u filmu \"Spartak\" odigrao je?", "Harrison Ford", "Anthony Perkins", "Kirk Douglas", "Jack Nicholson");
        qen = new QuestionEN(150, "Who played the main role in movie \"Spartacus\"?", "Harrison Ford", "Anthony Perkins", "Kirk Douglas", "Jack Nicholson");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);
        question = new Question(150, 3, 1, 3, Question.Category.MOVIE, hrId, enId);

        questionBox.put(question);

        List<Question> all = questionBox.getAll();
        List<QuestionEN> enall = questionENBox.getAll();
        List<QuestionHR> hrall = questionHRBox.getAll();
        QuestionHR hr = all.get(0).questionHR.getTarget();
        QuestionHR hr2 = questionHRBox.get(all.get(0).questionHRId);
        Log.d("a", "b");
    }
}
