import {THERAPIST_DEFAULT_URL, LOCAL_BACKEND_PATH, makeGetRequest, makePostRequest, USERS_DEFAULT_URL} from "./defaults";

export async function fetchTherapistProfile(therapistId, accessToken) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + `/${therapistId}`;

    return makeGetRequest(url, accessToken);
}