package com.example.mindspace.config;

import com.example.mindspace.model.User;
import com.example.mindspace.repository.AdminRepository;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ScheduleRepository;
import com.example.mindspace.repository.ThemeRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.repository.TimeCellRepository;
import com.example.mindspace.model.Admin;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.Theme;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.TimeCell;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@EnableTransactionManagement
public class InitDataLoader {

    private final ClientRepository clientRepository;
    private final ThemeRepository themeRepository;
    private final TimeCellRepository timeCellRepository;
    private final ScheduleRepository scheduleRepository;
    private final TherapistRepository therapistRepository;
    private final AdminRepository adminRepository;

    public InitDataLoader(
            ClientRepository clientRepository,
            ThemeRepository themeRepository,
            TimeCellRepository timeCellRepository,
            ScheduleRepository scheduleRepository,
            TherapistRepository therapistRepository,
            AdminRepository adminRepository
    ) {
        this.clientRepository = clientRepository;
        this.themeRepository = themeRepository;
        this.timeCellRepository = timeCellRepository;
        this.scheduleRepository = scheduleRepository;
        this.therapistRepository = therapistRepository;
        this.adminRepository = adminRepository;
    }

    @PostConstruct
    @Transactional
    public void loadData() {
        deleteData();

        var themes = loadThemes();
        var timeCells = loadTimeCells();
        var therapists = loadTherapists(themes);
        var schedules = loadSchedules(therapists, timeCells);

        var clients = loadClients(therapists);
        loadAdmin();
    }

    private void deleteData() {
        themeRepository.deleteAll();
        timeCellRepository.deleteAll();
        scheduleRepository.deleteAll();
        therapistRepository.deleteAll();
        clientRepository.deleteAll();
    }

    private List<Theme> loadThemes() {
        List<String> themeNames = Arrays.asList(
                "Depression", "Anxiety", "Stress", "Interpersonal relationships", "Emotion management",
                "Self-esteem", "Self-acceptance", "Trauma or loss", "Addictions and habits",
                "Personal development", "Professional issues", "Sexual issues", "Family issues",
                "Relationship issues", "Loneliness", "Eating disorder", "Concentration issues",
                "Panic attacks", "Insomnia", "Emotional dependence"
        );

        List<Theme> themes = new ArrayList<>();
        for (String name : themeNames) {
            Theme theme = new Theme();
            theme.setName(name);
            themes.add(theme);
        }

        return themeRepository.saveAll(themes);
    }

    private List<TimeCell> loadTimeCells() {
        var timecell1 = new TimeCell();
        var timecell2 = new TimeCell();
        var timecell3 = new TimeCell();
        var timecell4 = new TimeCell();

        timecell1.setStartTime(LocalDateTime.of(2023, 5, 29, 9, 0));
        timecell2.setStartTime(LocalDateTime.of(2023, 5, 29, 12, 0));
        timecell3.setStartTime(LocalDateTime.of(2023, 5, 31, 8, 0));
        timecell4.setStartTime(LocalDateTime.of(2023, 5, 31, 11, 0));

        return timeCellRepository.saveAll(Arrays.asList(timecell1, timecell2, timecell3, timecell4));
    }

    private List<Schedule> loadSchedules(List<Therapist> therapists, List<TimeCell> timeCells) {
        var schedule1 = new Schedule(therapists.get(0), List.of(timeCells.get(0), timeCells.get(2)));
        var schedule2 = new Schedule(therapists.get(1), List.of(timeCells.get(1), timeCells.get(3)));

        List<Schedule> schedules = scheduleRepository.saveAll(Arrays.asList(schedule1, schedule2));

        // Set the owning side of the relationship
        for (TimeCell timeCell : schedule1.getAvailableTimeCells()) {
            timeCell.setSchedule(schedule1);
            timeCellRepository.save(timeCell);
        }
        for (TimeCell timeCell : schedule2.getAvailableTimeCells()) {
            timeCell.setSchedule(schedule2);
            timeCellRepository.save(timeCell);
        }

        return schedules;
    }

    private List<Therapist> loadTherapists(List<Theme> themes) {
        var therapist1 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1)),
                null,
                null,
                "",
                "",
                List.of(),
                "",
                "https://thumbs.dreamstime.com/b/handsome-old-man-looking-camera-outdoor-fall-male-portrait-attractive-confident-middle-aged-man-city-park-handsome-man-174761151.jpg",
                "",
                List.of(),
                true
        );
        therapist1.setName("Alex");
        therapist1.setSurname("Smith");
        therapist1.setGender(User.Gender.MALE);
        therapist1.setDescription("\"Welcome! I'm Dr. Alex, a licensed therapist with over 10 years of experience. My approach is empathetic and non-judgmental, offering a safe space for you to explore your thoughts and feelings. I specialize in CBT, MBSR, and SFBT, tailoring my methods to your unique needs. I offer online sessions for convenience and accessibility, helping you tackle challenges like anxiety, depression, or relationship issues. Let's work together towards a more balanced and fulfilling life.\"");
        var therapist2 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1)),
                null,
                null,
                "",
                "",
                List.of(),
                "",
                "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cmFuZG9tJTIwcGVyc29ufGVufDB8fDB8fHww",
                "",
                List.of(),
                true
        );
        therapist2.setName("Jane");
        therapist2.setSurname("Wolf");
        therapist2.setGender(User.Gender.FEMALE);
        therapist2.setDescription(
                "Hello, I'm Jane Wolf, a compassionate and experienced online therapist dedicated to supporting you through life's challenges. With a background in clinical psychology and over a decade of helping clients, I blend empathy with expertise to create a welcoming and understanding environment. My practice focuses on evidence-based approaches like CBT and mindfulness, customized to your individual journey. Whether you're facing anxiety, stress, or personal hurdles, I'm here to guide and empower you. Embrace the convenience of online therapy with me and take a step towards positive change and well-being.");

        return therapistRepository.saveAll(Arrays.asList(therapist1, therapist2));
    }

    private List<Client> loadClients(List<Therapist> therapists) {
        var client1 = new Client(List.of(), therapists.get(0));
        client1.setName("James");
        client1.setSurname("Bond");
        client1.setEmail("abc@abc.com");
        client1.setPhoneNumber("+420123123123");

        var client2 = new Client(List.of(), therapists.get(1));
        client2.setName("Adam");
        client2.setSurname("Smith");
        client2.setEmail("foo@bar.com");
        client2.setPhoneNumber("+420777777777");

        return clientRepository.saveAll(Arrays.asList(client1, client2));
    }

    private Admin loadAdmin() {
        var admin = new Admin();

        return adminRepository.save(admin);
    }
}
