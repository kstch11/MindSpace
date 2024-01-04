import {SPECIFICATION_DEFAULT_URL, LOCAL_BACKEND_PATH, makeGetRequest} from "./defaults";

export async function fetchAllSpecifications(accessToken) {
    const url = LOCAL_BACKEND_PATH + SPECIFICATION_DEFAULT_URL + "/allThemes";

    return makeGetRequest(url, accessToken);
}

