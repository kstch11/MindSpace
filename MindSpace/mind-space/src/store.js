import {configureStore} from "@reduxjs/toolkit";
import currentUserSlice from "./slices/UserSlice";

export const store = configureStore({
    reducer: {
        currentUser: currentUserSlice
    }
})
