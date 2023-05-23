package com.example.mindspace.config;

import com.example.mindspace.dao.ClientRepository;
import com.example.mindspace.dao.ScheduleRepository;
import com.example.mindspace.dao.ThemeRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.dao.TimeCellRepository;
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

    public InitDataLoader(
            ClientRepository clientRepository,
            ThemeRepository themeRepository,
            TimeCellRepository timeCellRepository,
            ScheduleRepository scheduleRepository,
            TherapistRepository therapistRepository
    ) {
        this.clientRepository = clientRepository;
        this.themeRepository = themeRepository;
        this.timeCellRepository = timeCellRepository;
        this.scheduleRepository = scheduleRepository;
        this.therapistRepository = therapistRepository;
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
    }

    private void deleteData() {
        themeRepository.deleteAll();
        timeCellRepository.deleteAll();
        scheduleRepository.deleteAll();
        therapistRepository.deleteAll();
        clientRepository.deleteAll();
    }

    private List<Theme> loadThemes() {
        var theme1 = new Theme();
        theme1.setName("Anxiety");
        var theme2 = new Theme();
        theme2.setName("Family Therapy");
        var theme3 = new Theme();
        theme3.setName("Trauma");
        return themeRepository.saveAll(Arrays.asList(theme1, theme2, theme3));
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
                List.of(), List.of(), List.of(themes.get(0), themes.get(1)), null, true);
        var therapist2 = new Therapist(List.of(), List.of(), List.of(themes.get(2)), null, true);

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
}
