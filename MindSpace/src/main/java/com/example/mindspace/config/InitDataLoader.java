package com.example.mindspace.config;

import com.example.mindspace.model.*;
import com.example.mindspace.repository.*;
import com.example.mindspace.service.interfaces.TimeCellService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Component
@EnableTransactionManagement
public class InitDataLoader {
    private final Logger LOGGER =Logger.getLogger(InitDataLoader.class.getName());
    private final ClientRepository clientRepository;
    private final ThemeRepository themeRepository;
    private final TimeCellRepository timeCellRepository;
    private final ScheduleRepository scheduleRepository;
    private final TherapistRepository therapistRepository;
    private final AdminRepository adminRepository;

    private final TimeCellService timeCellService;

    private final SpokenLanguageRepository languageRepository;

    public InitDataLoader(
            ClientRepository clientRepository,
            ThemeRepository themeRepository,
            TimeCellRepository timeCellRepository,
            ScheduleRepository scheduleRepository,
            TherapistRepository therapistRepository,
            AdminRepository adminRepository,
            TimeCellService timeCellService, SpokenLanguageRepository languageRepository) {
        this.clientRepository = clientRepository;
        this.themeRepository = themeRepository;
        this.timeCellRepository = timeCellRepository;
        this.scheduleRepository = scheduleRepository;
        this.therapistRepository = therapistRepository;
        this.adminRepository = adminRepository;
        this.timeCellService = timeCellService;
        this.languageRepository = languageRepository;
    }

    @PostConstruct
    @Transactional
    public void loadData() {
        deleteData();

        var themes = loadThemes();
        var languages = loadLanguages();
        var therapists = loadTherapists(themes, languages);
        var schedules = loadSchedules(therapists);

        var clients = loadClients(therapists);
        LOGGER.info("Client id is " + clients.get(0).getId());
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

    private List<SpokenLanguage> loadLanguages() {
        List<String> languagesNames = Arrays.asList(
                "English", "Czech", "Ukrainian", "German", "Russian"
        );

        List<SpokenLanguage> languages = new ArrayList<>();
        for (String name : languagesNames) {
            SpokenLanguage language = new SpokenLanguage();
            language.setName(name);
            languages.add(language);
        };

        return languageRepository.saveAll(languages);
    }


    private List<Schedule> loadSchedules(List<Therapist> therapists) {
        var schedule1 = new Schedule(therapists.get(0), List.of());
        var schedule2 = new Schedule(therapists.get(1), List.of());
        var schedule3 = new Schedule(therapists.get(2), List.of());
        var schedule4 = new Schedule(therapists.get(3), List.of());
        var schedule5 = new Schedule(therapists.get(4), List.of());
        var schedule6 = new Schedule(therapists.get(5), List.of());
        var schedule7 = new Schedule(therapists.get(6), List.of());
        var schedule8 = new Schedule(therapists.get(7), List.of());
        var schedule9 = new Schedule(therapists.get(8), List.of());
        var schedule10 = new Schedule(therapists.get(9), List.of());

        List<Schedule> schedules = scheduleRepository.saveAll(Arrays.asList(
                schedule1, schedule2, schedule3, schedule4, schedule5,
                schedule6, schedule7, schedule8, schedule9, schedule10
        ));

        timeCellService.generateTimeCells(schedule1);
        timeCellService.generateTimeCells(schedule2);

        return schedules;
    }

    private List<Therapist> loadTherapists(List<Theme> themes, List<SpokenLanguage> languages) {
        var therapist1 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1)),
                null,
                null,
                "",
                "",
                List.of(languages.get(0), languages.get(1)),
                "No",
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
                List.of(languages.get(1), languages.get(2)),
                "Yes",
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

        var therapist3 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1), themes.get(3)),
                null,
                null,
                "",
                "PhD in Clinical Psychology from Stanford University",
                List.of(languages.get(0), languages.get(2)),
                "Yes",
                "https://meragor.com/files/styles//ava_800_800_wm/avatar-212826-000227.png",
                "Bay Area Therapists Network",
                List.of(),
                true
        );
        therapist3.setName("Emily");
        therapist3.setSurname("Green");
        therapist3.setGender(User.Gender.FEMALE);
        therapist3.setDescription("Proficient in cognitive behavioral therapy, I have a strong emphasis on managing and mitigating stress. My approach integrates various stress management techniques into the therapeutic process. I focus on helping clients develop effective coping strategies to handle life's pressures. This involves personalized care that tailors cognitive behavioral methods to each individual's unique needs and stressors.");

        var therapist4 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1), themes.get(3)),
                null,
                null,
                "",
                "PsyD in Clinical Psychology, University of Chicago",
                List.of(languages.get(4)),
                "Yes",
                "https://meragor.com/files/styles//ava_800_800_wm/avatar-211746-001266.png",
                "Chicago Therapists Alliance",
                List.of(),
                true
        );
        therapist4.setName("David");
        therapist4.setSurname("Wilson");
        therapist4.setGender(User.Gender.MALE);
        therapist4.setDescription("As an expert in adolescent psychology, my specialization encompasses a comprehensive understanding of the unique psychological challenges and behavioral issues faced by teenagers. My practice is dedicated to addressing these complexities through a blend of therapeutic techniques tailored specifically for this age group. This includes navigating developmental changes, emotional regulation, and social dynamics. I employ a patient and empathetic approach to help adolescents build resilience and healthy coping mechanisms for their individual struggles.");

        var therapist5 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1), themes.get(3)),
                null,
                null,
                "",
                "Masters in Psychology, Harvard University",
                List.of(languages.get(0)),
                "No",
                "https://meragor.com/files/styles//ava_800_800_wm/avatar-448391-055741.png",
                "New York Psychotherapy Group",
                List.of(),
                true
        );
        therapist5.setName("Sarah");
        therapist5.setSurname("Brown");
        therapist5.setGender(User.Gender.FEMALE);
        therapist5.setDescription("With a specialization in family therapy, my practice is dedicated to addressing and resolving complex dynamics within family units. My expertise extends to relationship counseling, where I assist couples in improving communication, understanding, and emotional connection. I employ a range of therapeutic techniques tailored to the unique needs of each family or couple, aiming to foster healthier and more fulfilling relationships. This includes facilitating open dialogues, building empathy, and resolving conflicts in a safe and supportive environment.");

        var therapist6 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1), themes.get(3)),
                null,
                null,
                "",
                "Master of Social Work, NYU",
                List.of(languages.get(0)),
                "Yes",
                "https://meragor.com/files/styles//220_220_bottom_wm/avatar-212470-004033.png",
                "National Association of Addiction Treatment Providers",
                List.of(),
                true
        );
        therapist6.setName("Michael Lee");
        therapist6.setSurname("Patel");
        therapist6.setGender(User.Gender.MALE);
        therapist6.setDescription("I am specialized in addiction therapy, offering comprehensive support for those on the path to recovery. My expertise encompasses a range of treatments and modalities designed to address the complexities of addiction. I provide tailored support, focusing on both the psychological and physical aspects of addiction recovery. This approach ensures a holistic path to healing, fostering long-term resilience and sobriety in individuals grappling with addiction challenges.");

        var therapist7 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1), themes.get(3)),
                null,
                null,
                "",
                "MPhD in Psychology, UCLA",
                List.of(languages.get(0), languages.get(3)),
                "Yes",
                "https://meragor.com/files/styles//220_220_bottom_wm/avatar-213375-002075.png",
                "American Trauma Society",
                List.of(),
                true
        );
        therapist7.setName("Angela");
        therapist7.setSurname("Davis");
        therapist7.setGender(User.Gender.FEMALE);
        therapist7.setDescription("I specialize in trauma therapy, particularly in the treatment of Post-Traumatic Stress Disorder (PTSD). My practice is dedicated to helping individuals who have experienced traumatic events, providing them with the support and techniques needed to heal and regain control of their lives. Utilizing a combination of evidence-based approaches, I aim to facilitate recovery and resilience. This involves creating a safe and nurturing environment where clients can explore and process their experiences, and learn coping strategies to manage and alleviate their symptoms.");

        var therapist8 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1), themes.get(3)),
                null,
                null,
                "",
                "Masters in Gerontology, University of Florida",
                List.of(languages.get(0), languages.get(3)),
                "No",
                "https://meragor.com/files/styles//220_220_bottom_wm/avatar-219504-033656.png",
                "Gerontological Society of America",
                List.of(),
                true
        );
        therapist8.setName("Jason");
        therapist8.setSurname("Martinez");
        therapist8.setGender(User.Gender.MALE);
        therapist8.setDescription("I possess extensive experience in the field of geriatric psychology, with a specialized focus on cognitive decline in older adults. My expertise encompasses a deep understanding of the psychological changes and challenges associated with aging, particularly in terms of memory and cognitive function. Through a compassionate and evidence-based approach, I work to support seniors in managing and adapting to these cognitive shifts. This involves both individualized therapeutic strategies and guidance for families navigating the complexities of cognitive decline in their loved ones.");

        var therapist9 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1), themes.get(3)),
                null,
                null,
                "",
                "PhD in Clinical Psychology, Columbia University",
                List.of(languages.get(0), languages.get(3)),
                "No",
                "https://meragor.com/files/styles//220_220_bottom_wm/avatar-416450-040272png",
                "Anxiety and Depression Association of America",
                List.of(),
                true
        );
        therapist9.setName("Nicole");
        therapist9.setSurname("Rodriguez");
        therapist9.setGender(User.Gender.FEMALE);
        therapist9.setDescription("My specialty lies in treating anxiety disorders through advanced cognitive therapy techniques. I have a deep understanding of how anxiety can impact one's life, and I use evidence-based cognitive approaches to address these challenges. My practice is centered around identifying and altering thought patterns that contribute to anxiety, providing clients with tools to manage and reduce their symptoms. This comprehensive approach ensures a supportive and effective treatment path for those dealing with various forms of anxiety.");

        var therapist10 = new Therapist(
                List.of(),
                List.of(),
                List.of(themes.get(0), themes.get(1), themes.get(3)),
                null,
                null,
                "",
                "Masters in Occupational Therapy, Boston University",
                List.of(languages.get(0), languages.get(3)),
                "No",
                "https://meragor.com/files/styles//220_220_bottom_wm/avatar-211741-001254.png",
                "American Occupational Therapy Association",
                List.of(),
                true
        );
        therapist10.setName("Christopher");
        therapist10.setSurname("Gonzalez");
        therapist10.setGender(User.Gender.MALE);
        therapist10.setDescription("I am an expert in occupational therapy, specializing in addressing and alleviating workplace stress. My expertise includes developing personalized therapeutic strategies that focus on improving job satisfaction and reducing stress-related issues in the work environment. Through a combination of therapeutic techniques and practical workplace solutions, I help clients navigate the challenges of occupational stress. My goal is to foster healthier work habits and environments, enabling individuals to achieve a better work-life balance.");

        return therapistRepository.saveAll(Arrays.asList(
                therapist1, therapist2, therapist3, therapist4, therapist5,
                therapist6, therapist7, therapist8, therapist9, therapist10));
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
