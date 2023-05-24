import {BrowserRouter, Routes, Route} from "react-router-dom";
import React from "react";
import ReactDOM from "react-dom/client";
import {HeaderResponsive} from "./parts/Header";
import {Footer} from "./parts/Footer";
import {MainPage} from "./mainPage/MainPage";
import {AuthenticationForm} from "./client-components/autentication/Autentication";
import {StepQuestionnaire} from "./client-components/StepQuestionnaire";
import {ReservationTable} from "./therapist-components/ListOfReservations";
import {ClientsTable} from "./therapist-components/ListOfClients";
import {ClientNavbar} from "./client-components/ClientProfile";
import {ClientData} from "./client-components/ClientData";

function App() {
    return (
        <React.Fragment>
            <BrowserRouter>
                <HeaderResponsive links={[
                    { link: '', label: 'Home' },
                    { link: '', label: 'Our therapists' },
                    { link: '', label: 'For therapists' },
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
                    <Route path="/clientDataProfile" element={<ClientData {...ClientData.id = 3} />} />
                    <Route path="/clientProfile" element={<ClientNavbar/>} />
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

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
export default App;