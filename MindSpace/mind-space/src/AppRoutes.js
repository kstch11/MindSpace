import {BrowserRouter, Route, Routes} from "react-router-dom";
import {HeaderResponsive} from "./parts/Header";
import {MainPage} from "./common-pages/main-page/MainPage";
import {AuthenticationForm} from "./common-pages/autentication/Autentication";
import Oauth2Redirect from "./common-pages/autentication/Oauth2Redirect";
import {StepQuestionnaire} from "./common-pages/client-registration/StepQuestionnaire";
import {ReservationTable} from "./therapist-profile/ListOfReservations";
import {ClientsTable} from "./therapist-profile/ListOfClients";
import {ClientNavbar} from "./client-profile/ClientProfile";
import {TherapistNavbar} from "./therapist-profile/TherapistProfile";
import {TherapistMainPage} from "./common-pages/therapist-application/TherapistMainPage";
import {TherapistsList} from "./common-pages/therapists-list/TherapistsList";
import {ApplicationStepper} from "./common-pages/therapist-application/ApplicationStepper";
import {Footer} from "./parts/Footer";
import React, {useEffect} from "react";
import PrivateRoute from "./PrivateRoute";
import {useSelector} from "react-redux";
import LogOut from "./common-pages/autentication/LogOut";
import AuthWrapper from "./common-pages/autentication/AuthWrapper";
import {Schedule} from "./therapist-profile/Schedule";
import {TherapistData} from "./common-pages/therapists-list/TherapistData";
import ClientSuccessRegistration from "./client-profile/ClientSuccessRegistration";
import AdminProfile from "./admin-profile/AdminProfile";

export default function AppRoutes() {
    const isLoggedIn = useSelector(state => state.currentUser.accessToken != null)
    return (
        <React.Fragment>
            <BrowserRouter>
                <AuthWrapper>
                    <HeaderResponsive links={!isLoggedIn ? [
                        {link: '', label: 'Home'},
                        {link: '/therapistsList', label: 'Our therapists'},
                        {link: '/forTherapists', label: 'For therapists'},
                        {link: '/login', label: 'Log in'},
                    ] : [
                        {link: '/logout', label: 'Log out'},
                        {link: '/clientProfile', label: 'Profile'}
                    ]}/>
                    <Routes>
                        <Route exact path="" element={<MainPage/>}/>
                        <Route path="/login" element={<AuthenticationForm/>}/>
                        <Route path="/oauth2/redirect" element={<Oauth2Redirect/>}/>
                        <Route path="/questionnaire" element={<StepQuestionnaire/>}/>
                        <Route path="/reservations" element={<ReservationTable data={[
                            {dateTime: "9th May", client: "tetya frosya", email: "fffff@tt.sd", reservationId: "1"}
                        ]}/>}/>
                        <Route path="/clients" element={<ClientsTable data={[
                            {
                                name: "Selina",
                                surname: "Kadyrova",
                                phoneNumber: "111111111",
                                email: "ffffff@fff.ff",
                                id: "1"
                            },
                            {
                                name: "Selina",
                                surname: "Kadyrova",
                                phoneNumber: "111111111",
                                email: "ffffff@fff.ff",
                                id: "2"
                            },
                            {
                                name: "Selina",
                                surname: "Kadyrova",
                                phoneNumber: "111111111",
                                email: "ffffff@fff.ff",
                                id: "3"
                            }
                        ]}/>}/>
                        <Route path="/clientProfile" element={<PrivateRoute/>}>
                            <Route path="/clientProfile" element={<ClientNavbar/>}/>
                        </Route>
                        <Route path="/therapistProfile" element={<PrivateRoute/>}>
                            <Route path="/therapistProfile" element={<TherapistNavbar/>}/>
                        </Route>
                        <Route path="/clientDoneRegistration" element={<PrivateRoute/>}>
                            <Route path="/clientDoneRegistration" element={<ClientSuccessRegistration/>}/>
                        </Route>

                        <Route path="/adminProfile" element={<PrivateRoute/>}>
                            <Route path="/adminProfile" element={<AdminProfile/>}/>
                        </Route>

                        <Route path="/therapistProfile" element={<TherapistNavbar/>}/>
                        <Route path="/forTherapists" element={<TherapistMainPage/>}/>
                        <Route path="/therapistsList" element={<TherapistsList toggleValue='all' />}/>
                        <Route path="/schedule" element={<Schedule/>}/>
                        <Route path="/therapist" element={<TherapistData toggleValue='clientRegistration'/>}/>
                        <Route path="/therapistApplication" element={<ApplicationStepper/>}/>
                        <Route path="/logout" element={<LogOut/>}/>

                    </Routes>
                    <Footer links={[
                        {link: "https://example.com/home", label: "Home"},
                        {link: "https://example.com/about", label: "About"},
                        {link: "https://example.com/services", label: "Services"},
                        {link: "https://example.com/contact", label: "Contact"},
                    ]}/>
                </AuthWrapper>
            </BrowserRouter>
        </React.Fragment>
    )
}
