import {BrowserRouter, Routes, Route} from "react-router-dom";
import React from "react";
import ReactDOM from "react-dom/client";
import {HeaderResponsive} from "./parts/Header";
import {Footer} from "./parts/Footer";
import {MainPage} from "./mainPage/MainPage";
import {AuthenticationForm} from "./autentication/Autentication";
import {StepQuestionnaire} from "./client/StepQuestionnaire";

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