import {
    createStyles,
    Stepper,
    Group,
    Button,
    Center,
    TextInput,
    Textarea,
    MultiSelect,
    FileInput,
    Select,
    Title,
    Text, SimpleGrid
} from "@mantine/core";
import { useState, useEffect } from 'react';
import {useForm} from "@mantine/form";
import {DatePickerInput, MonthPickerInput} from "@mantine/dates";
import {useDisclosure} from "@mantine/hooks";
import {useSelector} from "react-redux";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchAllSpecifications} from "../../api/specification-api";
import {postTherapistQuestionnaire} from "../../api/therapist-api";
import {Navigate} from "react-router-dom";

const useStyles = createStyles((theme) =>({
    inner: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        paddingTop: theme.spacing.md,
        paddingBottom: `calc(${theme.spacing.xl} * 5)`,
        flexDirection: 'column',
    },

    content: {
        width: 900,
        marginRight: `0 auto`,

        [theme.fn.smallerThan('sm')]: {
            maxWidth: '100%',
            margin: '0 3%',
        },
    },


}))

export function ApplicationStepper() {
    const {classes} = useStyles();
    const [active, setActive] = useState(0);
    const [visible, { toggle }] = useDisclosure(false);
    const [formData, setFormData] = useState({specializations: []})
    const accessToken = useSelector(state => state.currentUser.accessToken);

    const form = useForm({
        initialValues: {
            firstname:'',
            surname:'',
            dateOfBirth: null,
            gender: '',
            description: '',
            education: '',
            specialization: [],
            certificates: [],
            therapeuticCommunity: '',
            languages: [],
            personalTherapy: '',
            phoneNumber: '',
            experience: null,
        },

        validate: (values) => {
            if (active === 0) {
                return {
                    firstname: values.firstname.trim().length < 2 ? 'First name must include at least 2 characters' : null,
                    surname: values.surname.trim().length < 2 ? 'Second name must include at least 2 characters' : null,
                    description: values.description.trim().length < 2 ? 'Description name must include at least 2 characters' : null,
                    dateOfBirth: ((val) => {
                        if (!val) return 'Date of birth is required';

                        const today = new Date();
                        const birthDate = new Date(val);
                        let age = today.getFullYear() - birthDate.getFullYear();
                        const m = today.getMonth() - birthDate.getMonth();

                        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                            age--;
                        }

                        return age >= 18 ? null : 'You must be at least 18 years old';
                    })(values.dateOfBirth),
                }
            }

            if (active === 1) {
                return {
                    specialization: ((val) => (val.length < 3 ? 'You must choose at least 3 topics' : null))(values.specialization),
                    therapeuticCommunity: values.therapeuticCommunity.trim().length < 2 ? 'You should answer at least something' : null,
                    languages: ((val) => (val.length === 0 ? 'You must choose at least 1 language' : null))(values.languages),
                }
            }

            if (active === 2) {
                return {
                    education: values.education.trim().length < 5 ? 'Education must include at least 5 characters' : null,
                    certificates: ((val) => (val.length === 0 ? 'You must upload your diploma' : null))(values.certificates),
                }
            }

            return {}
        }
    })


    const nextStep = async () => {
        const validation = await form.validate();

        if (!validation.hasErrors) {
            setActive((current) => (current < 3 ? current + 1 : current));

            if (active === 2) {
                // handleTherapistRegistration()
            }
        }
    };
    const prevStep = () => setActive((current) => (current > 0 ? current - 1 : current));

    const {
        isPending,
        isError,
        data,
        isFetched,
        error
    } = useQuery({
        queryKey: ['getAllSpecializations'], queryFn: () => fetchAllSpecifications(accessToken)
    });

    useEffect(() => {
        if (isFetched) {
            const specializationArray = data.map(s => s.name);
            setFormData({
                specializations: specializationArray
            });
        }
    }, [data, isFetched])

    const calculateExperience = (startDate) => {
        const currentDate = new Date();
        const startYear = startDate.getFullYear();
        const startMonth = startDate.getMonth();
        const currentYear = currentDate.getFullYear();
        const currentMonth = currentDate.getMonth();

        let years = currentYear - startYear;
        if (currentMonth < startMonth || (currentMonth === startMonth && currentDate.getDate() < startDate.getDate())) {
            years--;
        }

        return years;
    };

    const {
        mutate,
        isPending: questionnairePending,
        isSuccess,
        isError: questionnaireError
    } = useMutation({
        mutationFn: (questionnaireBody) => {
            return postTherapistQuestionnaire(accessToken, questionnaireBody);
        }
    })

    const handleTherapistRegistration = () => {
        mutate({
            name: form.values.firstname,
            surname: form.values.surname,
            birthDate: form.values.dateOfBirth,
            gender: form.values.gender,
            description: form.values.description,
            topics: form.values.specialization,
            education: form.values.education,
            therapeuticCommunity: form.values.therapeuticCommunity,
            languages: form.values.languages,
            personalPsychotherapy: form.values.personalTherapy,
            experience: calculateExperience(form.values.experience),
            phoneNumber: form.values.phoneNumber
        })
    }

    const handleFinish = () => {
        console.log("Therapist registered")
        handleTherapistRegistration()
    }

    useEffect(() => {
        if (isSuccess) {
            console.log("successful registration")
        }
    }, [isSuccess])

    if (isSuccess) {
        return <Navigate to={"/therapistDoneRegistration"} />
    }

    return(
        <div className={classes.inner}>
            <div className={classes.content}>
                <Stepper active={active} onStepClick={setActive} breakpoint="sm">
                    <Stepper.Step label="First step" description="Personal information">
                        <TextInput
                            label="First name"
                            placeholder="First name"
                            {...form.getInputProps('firstname')}
                            required
                        />
                        <TextInput
                            label="Last name"
                            placeholder="Last name"
                            {...form.getInputProps('surname')}
                            required
                        />
                        <DatePickerInput
                            label="Date of Birth"
                            placeholder="Date of birth"
                            {...form.getInputProps('dateOfBirth')}
                            required
                        />
                        <Select
                            required
                            label="Your gender:"
                            placeholder="Pick one"
                            data={[
                                { label: 'Male', value: 'Male' },
                                { label: 'Female', value: 'Female' },
                                { label: 'Prefer not to say', value: 'noSelection'}
                            ]}
                            {...form.getInputProps('gender')}
                        />
                        <TextInput
                            required
                            label="Phone number address for contacting"
                            placeholder="Phone number"
                            {...form.getInputProps('phoneNumber')}
                        />
                        <Textarea
                            label="Self-description"
                            placeholder="Self-description"
                            {...form.getInputProps('description')}
                            minRows={3}
                            required
                        />
                    </Stepper.Step>
                    <Stepper.Step label="Second step" description="Professional information">
                        <MultiSelect
                            required
                            data={formData.specializations}
                            label="Choose the topics you work with:"
                            placeholder="Select specializations"
                            {...form.getInputProps('specialization')}
                        />
                        <MultiSelect
                            required
                            data={[
                                'English', 'Czech', 'Ukrainian', 'German', 'Russian'
                            ]}
                            label="In which languages you can lead sessions?"
                            placeholder="Languages"
                            {...form.getInputProps('languages')}
                        />
                        <Textarea
                            required
                            label="Do you belong to any psychotherapeutic community. If yes, which one?"
                            placeholder="Enter text here"
                            {...form.getInputProps('therapeuticCommunity')}
                        />
                        <MonthPickerInput
                            valueFormat="YYYY MMM"
                            label="When did you start counseling? For money, not as part of a training program. Please choose year and month"
                            placeholder="Date input"
                            {...form.getInputProps('experience')}
                        />
                        <Select
                            required
                            label="Are you in personal psychotherapy?"
                            placeholder="Pick one"
                            data={[
                                { label: 'Yes', value: 'yes' },
                                { label: 'No', value: 'no' },
                            ]}
                            {...form.getInputProps('personalTherapy')}
                        />
                    </Stepper.Step>
                    <Stepper.Step label="Final step" description="Education">
                        <TextInput
                            required
                            label="What is your higher education? Write about basic psychological (related) training or retraining: 1. Year of graduation 2. Name of institution of higher education 3. Name of department and specialization 4. Indicate academic degree (bachelor's, master's) or academic degree (if any)."
                            placeholder="Your educational background"
                            {...form.getInputProps('education')}
                        />
                        <FileInput
                            label="Please attach photos of detailed diplomas and certificates confirming the training. Required documents: (1) diploma of basic psychological (related) training / retraining (2) documents of training in the method. If the training is not completed, please attach a certificate from the training institution."
                            placeholder="Upload your diplomas and certificates"
                            {...form.getInputProps('certificates')}
                            multiple
                            required
                        />
                    </Stepper.Step>
                    <Stepper.Completed>
                        <Center>
                            <SimpleGrid cols={1}>
                                <Title order={2}>You successfully sent an application!</Title>
                                <Text c="dimmed">
                                    <p>Thank you for your application to join our team. We appreciate your interest in becoming a therapist with us. We want to let you know that your application is important to us, and our team will carefully review it.</p>
                                    <p>Once your application has been processed, you will be notified via email regarding the outcome. We kindly ask for your patience during this process, and we look forward to being in touch soon.</p>
                                    <p>If you have any urgent questions or concerns, please don't hesitate to contact us. Thank you for your interest in joining our team as a therapist.</p>
                                </Text>
                            </SimpleGrid>
                        </Center>
                    </Stepper.Completed>
                </Stepper>

                <Group position="right" mt="xl">
                    {active !== 0 && (
                        <Button variant="default" onClick={prevStep}>
                            Back
                        </Button>
                    )}
                    {active !== 3 && <Button onClick={nextStep}>Next step</Button>}
                    {active === 3 && <Button onClick={handleFinish} type="button">Finish</Button>}
                </Group>
            </div>
        </div>
    );
}