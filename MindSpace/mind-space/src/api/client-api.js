import {CLIENT_DEFAULT_URL, DEFAULT_BACKEND_PATH, makeGetRequest, USERS_DEFAULT_URL} from "./defaults";

export async function fetchClientProfile(clientId, accessToken) {
    const url = DEFAULT_BACKEND_PATH + CLIENT_DEFAULT_URL + `/${clientId}`;

    return makeGetRequest(url, accessToken)
}

export async function fetchCurrentUser(accessToken) {
    const url = DEFAULT_BACKEND_PATH + USERS_DEFAULT_URL + "/profile";

    return makeGetRequest(url, accessToken)
}
