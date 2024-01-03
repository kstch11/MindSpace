export const LOCAL_BACKEND_PATH = "http://localhost:8090"

export const AUTH_URL = "/oauth2/authorize/google"

export const LOCAL_REDIRECT_LINK = "http://localhost:3000/oauth2/redirect"

export const AUTH_LOCAL_LINK = LOCAL_BACKEND_PATH + AUTH_URL + "?redirect_uri=" + LOCAL_REDIRECT_LINK

export const USERS_DEFAULT_URL = "/users"

export const CLIENT_DEFAULT_URL = "/clients"


export async function makeGetRequest(url, accessToken) {
    const response = await fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${accessToken}`
        }
    })

    if (!response.ok) {
        throw new Error(`Error while making GET request to ${url}`)
    }

    return response.json();
}

export async function makePostRequest(url, accessToken, body) {
    const response = await fetch(url, {
        method: "POST",
        body: body ? JSON.stringify(body) : null,
        headers: {
            "Authorization": `Bearer ${accessToken}`
        }
    })

    if (!response.ok) {
        throw new Error(`Error while making POST request to ${url}. Body: ${body}`)
    }

    return response.json();
}

export async function makePutRequest(url, accessToken, body) {
    const response = await fetch(url, {
        method: "PUT",
        body: JSON.stringify(body),
        headers: {
            "Authorization": `Bearer ${accessToken}`,
            "Content-Type": "application/json"
        }
    })

    if (!response.ok) {
        throw new Error(`Error while making POST request to ${url}. Body: ${body}`)
    }

    if (await response.text() === "") {
        return Promise.resolve()
    }
    return await response.json()
}
