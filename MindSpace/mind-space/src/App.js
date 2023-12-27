import {BrowserRouter, Routes, Route} from "react-router-dom";
import React from "react";
import {HeaderResponsive} from "./parts/Header";
import {Footer} from "./parts/Footer";
import {MainPage} from "./common-pages/main-page/MainPage";
import {AuthenticationForm} from "./client-profile/autentication/Autentication";
import {StepQuestionnaire} from "./client-profile/StepQuestionnaire";
import {ReservationTable} from "./therapist-profile/ListOfReservations";
import {ClientsTable} from "./therapist-profile/ListOfClients";
import {ClientNavbar} from "./client-profile/ClientProfile";
import {TherapistNavbar} from "./therapist-profile/TherapistProfile";
import {TherapistMainPage} from "./common-pages/therapist-application/TherapistMainPage";
import {TherapistsList} from "./common-pages/therapists-list/TherapistsList";
import {ApplicationStepper} from "./common-pages/therapist-application/ApplicationStepper";

function App() {
    return (
        <React.Fragment>
            <BrowserRouter>
                <HeaderResponsive links={[
                    { link: '', label: 'Home' },
                    { link: '', label: 'Our therapists' },
                    { link: '/forTherapists', label: 'For therapists' },
                    { link: '/login', label: 'Log in' },
                    { link: '', label: 'Find a therapist' },
                ]}></HeaderResponsive>
                <Routes>
                    <Route exact path="" element={<MainPage />}/>
                    <Route path="/login" element={<AuthenticationForm />} />
                    <Route path="/questionnaire" element={<StepQuestionnaire />} />
                    <Route path="/reservations" element={<ReservationTable data={[
                        {dateTime: "9th May", client: "tetya frosya", email: "fffff@tt.sd", reservationId: "1"}
                    ]}/>} />
                    <Route path="/clients" element={<ClientsTable data={[
                        {name: "Selina", surname: "Kadyrova", phoneNumber: "111111111", email: "ffffff@fff.ff", id: "1"},
                        {name: "Selina", surname: "Kadyrova", phoneNumber: "111111111", email: "ffffff@fff.ff", id: "2"},
                        {name: "Selina", surname: "Kadyrova", phoneNumber: "111111111", email: "ffffff@fff.ff", id: "3"}
                    ]}/>} />
                    <Route path="/clientProfile" element={<ClientNavbar/>} />
                    <Route path="/therapistProfile" element={<TherapistNavbar />} />
                    <Route path="/forTherapists" element={<TherapistMainPage />} />
                    <Route path="/therapistsList" element={<TherapistsList />} />
                    <Route path="/therapistApplication" element={<ApplicationStepper/>} />
                </Routes>
                <Footer links={[
                    { link: "https://example.com/home", label: "Home" },
                    { link: "https://example.com/about", label: "About" },
                    { link: "https://example.com/services", label: "Services" },
                    { link: "https://example.com/contact", label: "Contact" },
                ]} />
            </BrowserRouter>
        </React.Fragment>
    )
}

export default App;
