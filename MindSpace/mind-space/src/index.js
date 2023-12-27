import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.scss';
import reportWebVitals from './reportWebVitals';
import {HeaderResponsive} from "./parts/Header";
import {MainPage} from "./common-pages/main-page/MainPage";
import {FeaturesCards} from "./common-pages/main-page/SecondSection"
import {Footer} from "./parts/Footer";
import {AuthenticationForm} from "./client-profile/autentication/Autentication";
import {StepQuestionnaire} from "./client-profile/StepQuestionnaire";
import App  from "./App"

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      < App />

      {/*<MainPage />*/}
      {/*<FeaturesCards />*/}
      {/*<AuthenticationForm />*/}
      {/*<StepQuestionnaire />*/}

  </React.StrictMode>
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
